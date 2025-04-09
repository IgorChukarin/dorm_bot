package com.example.dorm_bot.comands;

import com.example.dorm_bot.Bot;
import com.example.dorm_bot.BotMessages;
import com.example.dorm_bot.repositories.ProductRepository;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashSet;
import java.util.Set;

@Component
public class BoughtCommand extends Command{

    private final ProductRepository productRepository;
    private final Set<Long> awaitingInput = new HashSet<>();

    public BoughtCommand(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean isApplicable(Update update) {
        Message message = update.getMessage();
        String messageText = message.getText();

        boolean messageHasText = !messageText.isBlank();
        boolean isBoughtCommand = messageText.startsWith("/bought");
        boolean isWeDontNeedCommand = messageText.startsWith("/wedontneed");
        boolean messageIsInput = !messageText.startsWith("/") && awaitingInput.size() > 0;

        return messageHasText && (isBoughtCommand || isWeDontNeedCommand || messageIsInput);
    }

    @Override
    public String process(Update update, Bot bot) {
        Message message = update.getMessage();
        String messageText = message.getText();
        Long chatId = message.getChatId();

        if (messageText.startsWith("/bought") || messageText.startsWith("/wedontneed")) {
            awaitingInput.add(chatId);
            return BotMessages.REMOVE_PROMPT;
        }

        if (awaitingInput.contains(chatId)) {
            awaitingInput.remove(chatId);
            boolean isRemoved = productRepository.removeProductByName(messageText.toUpperCase());
            return getRemovalResponse(isRemoved, messageText.toUpperCase());
        }

        return null;
    }

    private String getRemovalResponse(boolean isRemoved, String item) {
        return isRemoved
                ? String.format(BotMessages.REMOVE_SUCCESS, item)
                : String.format(BotMessages.REMOVE_FAILURE, item);
    }
}
