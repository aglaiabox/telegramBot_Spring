package aglaia.telegramBot.commands.menu;

import aglaia.telegramBot.service.RegistrationAndSettingService;
import aglaia.telegramBot.service.menu.AbstractMenuService;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class AbstractMenuCommand implements IBotCommand {

    private final String identifier;
    private final String description;
    protected AbstractMenuService abstractMenuService;
    protected RegistrationAndSettingService registrationAndSettingService;
    boolean keepActiveTask;

    AbstractMenuCommand(String identifier, String description, AbstractMenuService abstractMenuService, RegistrationAndSettingService registrationAndSettingService, boolean keepActiveTask) {
        super();
        this.description = description;
        this.identifier = identifier;
        this.abstractMenuService = abstractMenuService;
        this.keepActiveTask = keepActiveTask;
        this.registrationAndSettingService = registrationAndSettingService;
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
        String textToSend;
        textToSend = registrationAndSettingService.setDataOfUserToDatabaseAddGetAnswer(message.getChatId(), message.getText());
        Long chatId = message.getChatId();
        SendMessage answer;

        if (textToSend == null) {
            answer = startCommandLogic(chatId);
        } else {
            answer = new SendMessage();
            answer.setText(textToSend);
            answer.setChatId(message.getChatId());
        }

        try {
            absSender.execute(answer);
        } catch (TelegramApiException e) {
            System.out.println("Exception: " + e.getMessage());
            //логируем сбой Telegram Bot API, используя commandName и userName
        }
    }

    abstract SendMessage startCommandLogic(Long chatId);
}
