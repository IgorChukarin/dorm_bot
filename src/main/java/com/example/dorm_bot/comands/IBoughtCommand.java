package com.example.dorm_bot.comands;

import com.example.dorm_bot.Bot;
import com.example.dorm_bot.repositories.ProductRepository;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class IBoughtCommand extends Command{

    private final ProductRepository productRepository;

    public IBoughtCommand(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean isApplicable(Update update) {
        Message message = update.getMessage();
        return message.hasText() && message.getText().startsWith("/ibought");
    }

    @Override
    public String process(Update update, Bot bot) {
        Message message = update.getMessage();
        String command = message.getText();
        if (command.equals("/ibought") || command.equals("/ibought@DormAmicoBot")) {
            return "To execute this command please: " +
                    "\n1. Go to the command menu" +
                    "\n2. Press arrow in the right part of the command line" +
                    "\n3. Send command and the product id separated by space" +
                    "\nOr copy-paste this: /ibought 2077";
        } else {
            int id = Integer.parseInt(command.split(" ")[1]);
            productRepository.deleteProduct(id);
            return "product removed from list";
        }
    }
}
