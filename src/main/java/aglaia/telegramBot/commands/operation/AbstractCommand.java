package aglaia.telegramBot.commands.operation;

import aglaia.telegramBot.model.tasks.AbstractTask;
import aglaia.telegramBot.service.operation.AbstractCommandService;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class AbstractCommand implements IBotCommand {

    public static final String NO_MORE_TASKS = "Unfortunately, all tasks was finished. Please try to come later";
    private String identifier;
    private String description;
    protected AbstractCommandService abstractCommandService;

    AbstractCommand(String identifier, String description, AbstractCommandService abstractCommandService) {
        super();
        this.description = description;
        this.identifier = identifier;
        this.abstractCommandService = abstractCommandService;
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
        AbstractTask task = abstractCommandService.giveMeATask(message.getChatId());

        answer.setText(task.getProblem());
        answer.setChatId(message.getChatId());

        try {
            absSender.execute(answer);
        } catch (TelegramApiException e) {
            System.out.println("Exception: " + e.getMessage());
            //логируем сбой Telegram Bot API, используя commandName и userName
        }

    }
}
