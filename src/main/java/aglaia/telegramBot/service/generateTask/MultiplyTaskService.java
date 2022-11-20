package aglaia.telegramBot.service.generateTask;

import aglaia.telegramBot.model.AbstractTask;
import aglaia.telegramBot.model.UserBot;
import aglaia.telegramBot.model.AbstractTask;
import aglaia.telegramBot.model.GeneratedTask;
import aglaia.telegramBot.model.TypeOfGeneratedTask;
import aglaia.telegramBot.model.UserBot;

public class MultiplyTaskService extends AbstractTaskService {

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
