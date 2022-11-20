package aglaia.telegramBot.service.commands.operation;

import aglaia.telegramBot.model.AbstractTask;
import aglaia.telegramBot.service.generateTask.AbstractTaskService;
import aglaia.telegramBot.model.AbstractTask;
import aglaia.telegramBot.service.generateTask.AbstractTaskService;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.IBotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class AbstractOperationCommand implements IBotCommand {

    public static final String NO_MORE_TASKS = "Unfortunately, all tasks was finished. Please try to come later";
    private String identifier;
    private String description;
    protected AbstractTaskService abstractTaskService;

    AbstractOperationCommand(String identifier, String description) {
        super();
        this.description = description;
        this.identifier = identifier;
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
        AbstractTask task = abstractTaskService.giveMeATask(message.getChatId());
        if (task != null){
            answer.setText(task.getProblem());
        } else {
            answer.setText(NO_MORE_TASKS);
        }


        answer.setChatId(message.getChatId());

        try {
            absSender.execute(answer);
        } catch (TelegramApiException e) {
            System.out.println("Exception: " + e.getMessage());
            //логируем сбой Telegram Bot API, используя commandName и userName
        }

    }
}
