package com.example.dorm_bot.comands;

import com.example.dorm_bot.Bot;
import com.example.dorm_bot.repositories.ProductRepository;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class DailyMessageScheduler {
    private final Bot bot;
    private final ProductRepository productRepository;
    private final String chatId = "-4624440055";
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final List<String> startPhrases = Arrays.asList(
            "E che cazzo, ragazzi!",
            "Porco dio, ragazzi!",
            "Dio lupo, ragazzi!",
            "Buongiorno, stronzi!"
    );

    public DailyMessageScheduler(Bot bot, ProductRepository productRepository) {
        this.bot = bot;
        this.productRepository = productRepository;
        scheduleDailyMessage();
    }

    private void scheduleDailyMessage() {
        Runnable task = () -> {
            List<String> products = productRepository.getAllProducts();
            if (products.isEmpty()) {
                System.out.println("nothing to print!");
                return;
            }
            try {
                System.out.println("something to print!");
                SendMessage response = new SendMessage();
                String messageText = generateMessageText();
                response.setChatId(chatId);
                response.setText(messageText);
                bot.execute(response);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        };
        long initialDelay = computeInitialDelay();
        scheduler.scheduleAtFixedRate(task, initialDelay, TimeUnit.DAYS.toMinutes(1), TimeUnit.MINUTES);
    }


    private long computeInitialDelay() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextRun = now.withHour(14).withMinute(45).withSecond(0);
        if (now.isAfter(nextRun)) {
            nextRun = nextRun.plusDays(1);
        }
        System.out.println(Duration.between(now, nextRun).toMinutes());
        return Duration.between(now, nextRun).toMinutes();
    }

    private int getRandomNumberInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    private String generateMessageText() {
        String startPhrase = startPhrases.get(getRandomNumberInRange(0,startPhrases.size() - 1));
        List<String> products = productRepository.getAllProducts();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < products.size(); i++) {
            sb.append(i + 1).append(". ").append(products.get(i)).append("\n");
        }
        String productsColumn = sb.toString().trim();
        return startPhrase +
                "\n\n" +
                "You are completely out of:" +
                "\n" +
                productsColumn +
                "\n\n" +
                "You have to buy it!";
    }
}

// add db
// we have to buy command change