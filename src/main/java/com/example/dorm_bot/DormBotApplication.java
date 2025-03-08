package com.example.dorm_bot;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class DormBotApplication {

	public static void main(String[] args) {

//		local usage

//		Dotenv dotenv = Dotenv.load();
//		System.setProperty("TELEGRAM_BOT_TOKEN", dotenv.get("TELEGRAM_BOT_TOKEN"));
//		System.setProperty("TELEGRAM_BOT_USERNAME", dotenv.get("TELEGRAM_BOT_USERNAME"));

		String botToken = System.getenv("TELEGRAM_BOT_TOKEN");
		String botUsername = System.getenv("TELEGRAM_BOT_USERNAME");
		System.setProperty("TELEGRAM_BOT_TOKEN", botToken);
		System.setProperty("TELEGRAM_BOT_USERNAME", botUsername);

		SpringApplication.run(DormBotApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(Bot bot) {
		return args -> {
			TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
			telegramBotsApi.registerBot(bot);
		};
	}
}
