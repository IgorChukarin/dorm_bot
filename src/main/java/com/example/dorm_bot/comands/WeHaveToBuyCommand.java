package com.example.dorm_bot.comands;

import com.example.dorm_bot.Bot;
import com.example.dorm_bot.repositories.ProductRepository;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.HashSet;
import java.util.Set;

@Component
public class WeHaveToBuyCommand extends Command{

    private final ProductRepository productRepository;
    private final Set<Long> awaitingInput = new HashSet<>();

    public WeHaveToBuyCommand(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean isApplicable(Update update) {
        Message message = update.getMessage();
        return message.hasText() && message.getText().startsWith("/wehavetobuy") || !message.getText().startsWith("/") && awaitingInput.size() > 0;
    }

    @Override
    public String process(Update update, Bot bot) {
        Message message = update.getMessage();
        Long chatId = message.getChatId();
        String command = message.getText();
        if (command.equals("/wehavetobuy") || command.equals("/wehavetobuy@DormAmicoBot")) {
            awaitingInput.add(chatId);
            return "E che cazzo!\nWhat do you need to buy?";
        }

        if (awaitingInput.contains(chatId)) {
            awaitingInput.remove(chatId);
            productRepository.addProduct(command.toUpperCase());
            return command.concat(" added to the list!");
        }

        else {
            String product = command.substring(command.indexOf(" "));
            productRepository.addProduct(product);
            return product.concat(" added").concat(" to the list!");
        }
    }
}
