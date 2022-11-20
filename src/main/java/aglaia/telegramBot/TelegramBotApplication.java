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

		String botName = System.getenv("BOT_NAME");
		String botUsername = System.getenv("BOT_USERNAME");
		String botToken = System.getenv("BOT_TOKEN");
		botsApi.registerBot(new Bot(botName, botUsername, botToken));


	}

}
