package com.example.dorm_bot.comands;

import com.example.dorm_bot.Bot;
import org.telegram.telegrambots.meta.api.objects.Update;

public abstract class Command {
    public abstract boolean isApplicable(Update update);
    public abstract String process(Update update, Bot bot);
}
