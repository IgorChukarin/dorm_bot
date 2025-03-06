package com.example.dorm_bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String botUsername;

    private final CleaningRepository cleaningRepository;

    public Bot(@Value("${bot.token}") String token, CleaningRepository cleaningRepository) {
        super(token);
        this.cleaningRepository = cleaningRepository;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String userMessage = update.getMessage().getText();
            if (userMessage.equals("/cleanorder")) {
                String message = cleaningRepository.getAllCleaners();
                sendMessage(message, update.getMessage().getChatId());
            } else if (userMessage.equals("/cleanthisweek")) {
                String message = cleaningRepository.getCurrentWeekCleaners();
                sendMessage(message, update.getMessage().getChatId());
            } else {
                String message = "E che cazzo!";
                sendMessage(message, update.getMessage().getChatId());
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    public void sendMessage(String message, Long chatId) {
        SendMessage newMessage = new SendMessage();
        newMessage.setChatId(chatId);
        newMessage.setText(message);
        try {
            execute(newMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
