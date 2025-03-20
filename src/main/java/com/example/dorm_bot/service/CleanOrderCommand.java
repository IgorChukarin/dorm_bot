package com.example.dorm_bot.service;

import com.example.dorm_bot.Bot;
import com.example.dorm_bot.CleaningRepository;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class CleanOrderCommand extends Command{

    CleaningRepository cleaningRepository;

    public CleanOrderCommand(CleaningRepository cleaningRepository) {
        this.cleaningRepository = cleaningRepository;
    }

    @Override
    public boolean isApplicable(Update update) {
        Message message = update.getMessage();
        return message.hasText() && message.getText().startsWith("/cleanorder");
    }

    @Override
    public String process(Update update, Bot bot) {
        return cleaningRepository.getAllCleaners();
    }
}
