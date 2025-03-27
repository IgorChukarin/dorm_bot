package com.example.dorm_bot.comands;

import com.example.dorm_bot.Bot;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class DailyMessageScheduler {
    private final Bot bot;
    private final String chatId = "-4624440055";
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public DailyMessageScheduler(Bot bot) {
        this.bot = bot;
        scheduleDailyMessage();
    }

    private void scheduleDailyMessage() {
        SendMessage response = new SendMessage();
        response.setChatId(chatId);
        response.setText("Hello!");
        Runnable task = () -> {
            try {
                bot.execute(response);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        };
        long initialDelay = computeInitialDelay();
        scheduler.schedule(task, initialDelay, TimeUnit.HOURS);
    }


    private long computeInitialDelay() {
        LocalDateTime now = LocalDateTime.now();
        int hours = getRandomNumberInRange(8, 16);
        int minutes = getRandomNumberInRange(0, 59);
        LocalDateTime nextRun = now.withHour(hours).withMinute(minutes);
        if (now.isAfter(nextRun)) {
            nextRun = nextRun.plusDays(1);
        }
        return Duration.between(now, nextRun).toMinutes();
    }

    private int getRandomNumberInRange(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}

// tag someone random
// some random phrase
// counter