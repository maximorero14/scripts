package com.maximorero.telegram_bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class TelegramBotApplication {

	public static void main(String[] args) throws TelegramApiException {


		// Instantiate Telegram Bots API
		TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);

		// Register our bot
		try {
			botsApi.registerBot(new MyAmazingBot());
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

}
