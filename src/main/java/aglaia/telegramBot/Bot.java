package aglaia.telegramBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class Bot extends TelegramLongPollingCommandBot {

    final String BOT_NAME;
    final String BOT_USERNAME;
    final String BOT_TOKEN;

    public Bot(String botName, String botUsername, String botToken) {
        super();
        BOT_NAME = botName;
        BOT_USERNAME = botUsername;
        BOT_TOKEN = botToken;

        register(new IBotCommand() {
            private final String identifier = "hello";
            private final String description = "say hello";
            @Override
            public String getCommandIdentifier() {
                return identifier;
            }

            @Override
            public String getDescription() {
                return description;
            }

            @Override
            public void processMessage(AbsSender absSender, Message message, String[] strings) {

                String textToSend = "Hello dear guest!";
                SendMessage answer = new SendMessage();
                answer.setText(textToSend);
                answer.setChatId(message.getChatId());

                try {
                    absSender.execute(answer);
                } catch (TelegramApiException e) {
                    System.out.println("Exception: " + e.getMessage());
                    //логируем сбой Telegram Bot API, используя commandName и userName
                }
            }
        });
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void processNonCommandUpdate(Update update) {

        Message msg = update.getMessage();
        Long msgChatId = msg.getChatId();
        String userName = getBotUsername();
        SendMessage answer = new SendMessage();

        answer.setText("I can't understand you, sorry");
        answer.setChatId(msgChatId.toString());
        try {
            execute(answer);

        } catch (TelegramApiException e) {
            //логируем сбой Telegram Bot API, используя userName
            System.out.println("TelegramApiException e: " + e.getMessage());
        }
    }
}
