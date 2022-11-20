package aglaia.telegramBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class TelegramBotApplication {

	public static void main(String[] args) throws TelegramApiException {

//		SpringApplication.run(TelegramBotApplication.class, args);

		TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);

		String botName = "asdfg001";
		String botUsername = "asdfg000bot";
		String botToken = "5754138882:AAEpWoxDSDuLOooOodwj3y2J_QOgd2oRT-U";
		botsApi.registerBot(new Bot(botName, botUsername, botToken));

		System.out.println("Bot have been created, my general ^^");

	}

}
