package aglaia.telegramBot.commands.operation;


import aglaia.telegramBot.entity.AbstractTask;
import aglaia.telegramBot.model.keyboards.KeyBoardABCDE;
import aglaia.telegramBot.service.operation.CommandKangService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TaskCommandKang extends AbstractTaskCommand {

    public TaskCommandKang(CommandKangService commandKangService) {
        super("kangaroo", "Задания Кенгуру", commandKangService);
    }

    @Override
    public void processMessage(AbsSender absSender, Message message, String[] strings) {

        SendMessage answer = new SendMessage();
        AbstractTask task = abstractCommandService.giveMeATask(message.getChatId());
        if (task != null){
            answer.setText(task.getProblem());
            answer.setReplyMarkup(KeyBoardABCDE.getABCDEAnswers());
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