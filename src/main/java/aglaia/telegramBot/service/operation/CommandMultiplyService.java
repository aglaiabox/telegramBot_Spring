package aglaia.telegramBot.service.operation;

import aglaia.telegramBot.database.Database;
import aglaia.telegramBot.entity.AbstractTask;
import aglaia.telegramBot.entity.UserBot;
import aglaia.telegramBot.entity.GeneratedTask;
import aglaia.telegramBot.entity.TypeOfGeneratedTask;
import org.springframework.stereotype.Component;

@Component
public class CommandMultiplyService extends AbstractCommandService {

    public CommandMultiplyService(Database database) {
        super(database);
    }

    @Override
    public AbstractTask giveMeATask(Long chatId) {
        UserBot userBot = database.getUserFromDatabase(chatId);
        AbstractTask actualTask = userBot.getActualTask();

        if (actualTask != null && actualTask.getClass().equals(GeneratedTask.class) && ((GeneratedTask) actualTask).getTypeOfGeneratedTask().equals(TypeOfGeneratedTask.MULTIPLY)) {
            return database.getUserFromDatabase(chatId).getActualTask();
        } else {
            return createNewTask(chatId);
        }
    }


    GeneratedTask createNewTask(Long chatId) {
        int intFirst = (int) (Math.random() * (10 - 3)) + 3;
        int intSecond = (int) (Math.random() * (10 - 3)) + 3;
        int res = intFirst * intSecond;

        String problem = intFirst + " * " + intSecond;
        String correctAnswer = Integer.toString(res);
        GeneratedTask generatedTask = new GeneratedTask(problem, correctAnswer, TypeOfGeneratedTask.MULTIPLY);
        database.addGeneratedTaskToUser(generatedTask, chatId);
        return generatedTask;
    }
}
