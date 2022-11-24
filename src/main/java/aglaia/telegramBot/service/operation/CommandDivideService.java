package aglaia.telegramBot.service.operation;

import aglaia.telegramBot.database.Database;
import aglaia.telegramBot.entity.AbstractTask;
import aglaia.telegramBot.entity.GeneratedTask;
import aglaia.telegramBot.entity.TypeOfGeneratedTask;
import aglaia.telegramBot.entity.UserBot;
import org.springframework.stereotype.Component;

@Component
public class CommandDivideService extends AbstractCommandService {

    public CommandDivideService(Database database) {
        super(database);
    }

    @Override
    public AbstractTask giveMeATask(Long chatId) {
        UserBot userBot = database.getUserFromDatabase(chatId);
        AbstractTask actualTask = userBot.getActualTask();


        if (actualTask != null && actualTask.getClass().equals(GeneratedTask.class) && ((GeneratedTask) actualTask).getTypeOfGeneratedTask().equals(TypeOfGeneratedTask.DIVIDE)) {
            return database.getUserFromDatabase(chatId).getActualTask();
        } else {
            return generateNewTask(chatId);
        }
    }

    public GeneratedTask generateNewTask(Long chatId) {
        int intFirst = (int) (Math.random() * (9 - 3)) + 3;
        int intSecond = (int) (Math.random() * (9 - 3)) + 3;
        int res = intFirst * intSecond;
        String problem = res + " / " + intSecond;
        String correctAnswer = Integer.toString(intFirst);
        GeneratedTask generatedTask = new GeneratedTask(problem, correctAnswer, TypeOfGeneratedTask.DIVIDE);
        database.addGeneratedTaskToUser(generatedTask, chatId);
        return generatedTask;
    }
}