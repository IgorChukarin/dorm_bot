package com.example.dorm_bot.comands;

import com.example.dorm_bot.Bot;
import com.example.dorm_bot.BotMessages;
import com.example.dorm_bot.repositories.CleaningRepository;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class CleanOrderCommand extends Command{

    CleaningRepository cleaningRepository;

    public CleanOrderCommand(CleaningRepository cleaningRepository) {
        this.cleaningRepository = cleaningRepository;
    }

    @Override
    public boolean isApplicable(Update update) {
        Message message = update.getMessage();
        String messageText = message.getText();

        boolean messageHasText = !messageText.isBlank();
        boolean isCleanOrderCommand = messageText.startsWith("/cleanorder");

        return messageHasText && isCleanOrderCommand;
    }

    @Override
    public String process(Update update, Bot bot) {
        List<String> dormmates = cleaningRepository.getAllCleaners();
        String cleaningColumn = String.join("\n", dormmates);
        return String.format(BotMessages.CLEANING_ORDER, cleaningColumn);
    }
}
