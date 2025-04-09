package com.example.dorm_bot;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DormBotApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("TELEGRAM_BOT_TOKEN", dotenv.get("TELEGRAM_BOT_TOKEN"));
		System.setProperty("TELEGRAM_BOT_USERNAME", dotenv.get("TELEGRAM_BOT_USERNAME"));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		System.out.println("TELEGRAM_BOT_TOKEN: " + dotenv.get("TELEGRAM_BOT_TOKEN"));
		System.out.println("DB_USERNAME: " + dotenv.get("DB_USERNAME"));
		SpringApplication.run(DormBotApplication.class, args);
	}
}

// TODO:
// add bot phrases in database and birthday
// to uppercase everything
// multiple products in one message
// bug if there is interception between methods
// tests
// disload main class
