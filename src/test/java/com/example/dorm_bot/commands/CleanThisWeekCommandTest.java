package com.example.dorm_bot.commands;

import com.example.dorm_bot.comands.CleanThisWeekCommand;
import com.example.dorm_bot.repositories.CleaningRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CleanThisWeekCommandTest {

    @Mock
    private CleaningRepository cleaningRepository;

    @InjectMocks
    private CleanThisWeekCommand cleanThisWeekCommand;

    @Test
    void isApplicable_shouldReturnTrue_whenTextStartsWithCommand() {
        Update update = mock(Update.class);
        Message message = mock(Message.class);

        when(update.getMessage()).thenReturn(message);
        when(message.hasText()).thenReturn(true);
        when(message.getText()).thenReturn("/cleanthisweek");

        assertTrue(cleanThisWeekCommand.isApplicable(update));

    }
}
