package com.example.dorm_bot;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@SpringBootApplication
public class DormBotApplication {

	@Value("${spring.datasource.url}")
	private static String url;

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();

		Properties props = new Properties();
		props.setProperty("TELEGRAM_BOT_TOKEN", dotenv.get("TELEGRAM_BOT_TOKEN"));
		props.setProperty("TELEGRAM_BOT_USERNAME", dotenv.get("TELEGRAM_BOT_USERNAME"));
		props.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		props.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

		SpringApplication app = new SpringApplication(DormBotApplication.class);

		System.out.println(url);
		app.setDefaultProperties(props);
		app.run(args);
	}
}

// TODO:
// add bot phrases in database and birthday
// to uppercase everything
// multiple products in one message
// bug if there is interception between methods
// tests
// disload main class

// проверить не надо ли application.yaml перенести в корень
