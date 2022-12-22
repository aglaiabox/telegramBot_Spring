package aglaia.telegramBot.commands.language;

import aglaia.telegramBot.service.language.AbstractLanguageService;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class AbstractLanguageCommand implements IBotCommand {

    private final String identifier;
    private final String description;
    protected AbstractLanguageService abstractLanguageService;

    AbstractLanguageCommand(String identifier, String description, AbstractLanguageService abstractLanguageService) {
        super();
        this.description = description;
        this.identifier = identifier;
        this.abstractLanguageService = abstractLanguageService;
    }

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

        SendMessage answer = new SendMessage();
        String text = abstractLanguageService.setLanguageAndGetAnswer(message.getChatId());

        answer.setText(text);
        answer.setChatId(message.getChatId());

        try {
            absSender.execute(answer);
        } catch (TelegramApiException e) {
            System.out.println("Exception: " + e.getMessage());
            //логируем сбой Telegram Bot API, используя commandName и userName
        }

    }
}
