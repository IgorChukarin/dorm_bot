package com.example.dorm_bot.comands;

import com.example.dorm_bot.Bot;
import com.example.dorm_bot.repositories.CleaningRepository;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class CleanThisWeekCommand extends Command{

    CleaningRepository cleaningRepository;

    public CleanThisWeekCommand(CleaningRepository cleaningRepository) {
        this.cleaningRepository = cleaningRepository;
    }

    @Override
    public boolean isApplicable(Update update) {
        Message message = update.getMessage();
        return message.hasText() && message.getText().startsWith("/cleanthisweek");
    }

    @Override
    public String process(Update update, Bot bot) {
        return cleaningRepository.getCurrentWeekCleaners();
    }
}
