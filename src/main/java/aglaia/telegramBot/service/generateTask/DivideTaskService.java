package aglaia.telegramBot.service.generateTask;

import aglaia.telegramBot.model.AbstractTask;
import aglaia.telegramBot.model.AbstractTask;
import aglaia.telegramBot.model.GeneratedTask;
import aglaia.telegramBot.model.TypeOfGeneratedTask;
import aglaia.telegramBot.model.UserBot;

public class DivideTaskService extends AbstractTaskService {

    public DivideTaskService() {
        super();
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