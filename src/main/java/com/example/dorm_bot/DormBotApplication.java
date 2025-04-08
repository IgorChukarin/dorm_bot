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
		SpringApplication app = new SpringApplication(DormBotApplication.class);
		Dotenv dotenv = Dotenv.load();
		System.setProperty("TELEGRAM_BOT_TOKEN", dotenv.get("TELEGRAM_BOT_TOKEN"));
		System.setProperty("TELEGRAM_BOT_USERNAME", dotenv.get("TELEGRAM_BOT_USERNAME"));
		Map<String, Object> properties = new HashMap<>();
		properties.put("bot.token", System.getenv("TELEGRAM_BOT_TOKEN"));
		properties.put("bot.username", System.getenv("TELEGRAM_BOT_USERNAME"));
		app.setDefaultProperties(properties);
		app.run(args);

//		SpringApplication app = new SpringApplication(DormBotApplication.class);
//		Map<String, Object> properties = new HashMap<>();
//		properties.put("bot.token", System.getenv("TELEGRAM_BOT_TOKEN"));
//		properties.put("bot.username", System.getenv("TELEGRAM_BOT_USERNAME"));
//		app.setDefaultProperties(properties);
//		app.run(args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(Bot bot) {
		return args -> {
			TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
			telegramBotsApi.registerBot(bot);
		};
	}
}

// TODO:
// connect redis for consumables
// add bot phrases in database and birthday
// sometimes it display that list is empty
// birthday list function

// to uppercase everything
// multiple products in one message
// bug if person didn't send anything
// tests