package aglaia.telegramBot.commands.menu;

import aglaia.telegramBot.service.menu.AbstractMenuService;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class AbstractMenuCommand implements IBotCommand {

    private final String identifier;
    private final String description;
    protected AbstractMenuService abstractMenuService;
    protected String textToSend;
    boolean keepActiveTask;

    AbstractMenuCommand(String identifier, String description, AbstractMenuService abstractMenuService, boolean keepActiveTask) {
        super();
        this.description = description;
        this.identifier = identifier;
        this.abstractMenuService = abstractMenuService;
        this.keepActiveTask = keepActiveTask;
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
        answer.setText(textToSend);
        answer.setChatId(message.getChatId());


        try {
            absSender.execute(answer);
        } catch (TelegramApiException e) {
            System.out.println("Exception: " + e.getMessage());
            //логируем сбой Telegram Bot API, используя commandName и userName
        }


    }
}
