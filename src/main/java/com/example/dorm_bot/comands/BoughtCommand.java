package com.example.dorm_bot.comands;

import com.example.dorm_bot.Bot;
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
        return message.hasText() && message.getText().startsWith("/bought") || !message.getText().startsWith("/") && awaitingInput.size() > 0;
    }

    @Override
    public String process(Update update, Bot bot) {
        Message message = update.getMessage();
        Long chatId = message.getChatId();
        String command = message.getText();

        if (command.equals("/bought") || command.equals("/bought@DormAmicoBot")) {
            awaitingInput.add(chatId);
            return "Allora, stronzo!\nWhat did you buy?";
        }

        if (awaitingInput.contains(chatId)) {
            awaitingInput.remove(chatId);
            if (productRepository.removeProductByName(command)) {
                return command.concat(" removed from list!");
            }
            else {
                return command.concat(" is not on the list!\nPorco dio!");
            }
        }

        else {
            int id = Integer.parseInt(command.split(" ")[1]);
            productRepository.deleteProductById(id);
            return "product removed from list";
        }
    }
}
