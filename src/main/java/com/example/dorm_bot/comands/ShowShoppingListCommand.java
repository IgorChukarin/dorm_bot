package com.example.dorm_bot.comands;

import com.example.dorm_bot.Bot;
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
        return message.hasText() && message.getText().startsWith("/showshoppinglist");
    }

    @Override
    public String process(Update update, Bot bot) {
        List<String> products = productRepository.getAllProducts();
        if (products.isEmpty()) {
            return "A-ma-zing! There is nothing to buy!";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < products.size(); i++) {
            sb.append(i + 1).append(". ").append(products.get(i)).append("\n");
        }
        String productsColumn = sb.toString().trim();
        return "This is what you have to buy:\n" + productsColumn;
    }
}
