package aglaia.telegramBot.service.operation;

import aglaia.telegramBot.model.entity.UserBot;
import aglaia.telegramBot.model.entity.tasks.GeneratedTask;
import aglaia.telegramBot.model.entity.tasks.TypeOfGeneratedTask;
import aglaia.telegramBot.service.GeneratedTaskService;
import aglaia.telegramBot.service.UserBotService;
import org.springframework.stereotype.Component;

@Component
public class MultiplyCommandService extends AbstractGeneratedTaskCommandService {

    public MultiplyCommandService(UserBotService userBotService, GeneratedTaskService generatedTaskService) {
        super(userBotService, generatedTaskService);
    }


    @Override
    public GeneratedTask generateNewTask(UserBot userBot) {
        int intFirst = (int) (Math.random() * (10 - 3)) + 3;
        int intSecond = (int) (Math.random() * (10 - 3)) + 3;
        int res = intFirst * intSecond;

        String problem = intFirst + " * " + intSecond;
        String correctAnswer = Integer.toString(res);
        return new GeneratedTask(problem, correctAnswer, TypeOfGeneratedTask.MULTIPLY, userBot);
    }
}
