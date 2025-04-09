package com.example.dorm_bot.comands;

import com.example.dorm_bot.Bot;
import com.example.dorm_bot.BotMessages;
import com.example.dorm_bot.repositories.ProductRepository;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class ShowShoppingListCommand extends Command{

    private final ProductRepository productRepository;

    public ShowShoppingListCommand(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean isApplicable(Update update) {
        Message message = update.getMessage();
        String messageText = message.getText();

        boolean messageHasText = !messageText.isBlank();
        boolean isShowShoppingListCommand = messageText.startsWith("/showshoppinglist");

        return messageHasText && isShowShoppingListCommand;
    }

    @Override
    public String process(Update update, Bot bot) {
        List<String> products = productRepository.getAllProducts();
        if (products.isEmpty()) {
            return BotMessages.NOTHING_TO_BUY;
        }
        else {
            String productsColumn = String.join("\n", products);
            return String.format(BotMessages.HAVE_TO_BUY, productsColumn);
        }
    }
}
