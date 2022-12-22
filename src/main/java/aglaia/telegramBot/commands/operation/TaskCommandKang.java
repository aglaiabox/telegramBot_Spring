package aglaia.telegramBot.commands.operation;


import aglaia.telegramBot.model.entity.tasks.AbstractTask;
import aglaia.telegramBot.model.keyboards.KeyBoardABCDE;
import aglaia.telegramBot.service.ActiveTaskService;
import aglaia.telegramBot.service.operation.CommandServiceKang;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TaskCommandKang extends AbstractTaskCommand {
    ActiveTaskService activeTaskService;

    public TaskCommandKang(CommandServiceKang commandServiceKang, ActiveTaskService activeTaskService) {
        super("kangaroo", "Задания Кенгуру", commandServiceKang);
        this.activeTaskService = activeTaskService;
    }

    @Override
    public void processMessage(AbsSender absSender, Message message, String[] strings) {

        SendMessage answer = new SendMessage();
        AbstractTask task = abstractCommandService.giveMeATask(message.getChatId());
        if (task != null){
            answer.setText(task.getProblem());
            answer.setReplyMarkup(KeyBoardABCDE.getABCDEAnswers());
        } else {
            String text = activeTaskService.getTextByNameOfString(message.getChatId(), "NO_MORE_TASKS");
            answer.setText(text);
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
