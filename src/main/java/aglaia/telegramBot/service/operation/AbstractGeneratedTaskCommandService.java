package aglaia.telegramBot.service.operation;

import aglaia.telegramBot.model.entity.UserBot;
import aglaia.telegramBot.model.entity.tasks.AbstractTask;
import aglaia.telegramBot.model.entity.tasks.GeneratedTask;
import aglaia.telegramBot.model.entity.tasks.TypeOfGeneratedTask;
import aglaia.telegramBot.model.entity.tasks.TypesOfTasks;
import aglaia.telegramBot.service.GeneratedTaskService;
import aglaia.telegramBot.service.UserBotService;

public abstract class AbstractGeneratedTaskCommandService extends AbstractCommandService {

    GeneratedTaskService generatedTaskService;

    public AbstractGeneratedTaskCommandService(UserBotService userBotService, GeneratedTaskService generatedTaskService) {
        super(userBotService);
        this.generatedTaskService = generatedTaskService;
    }

    @Override
    public AbstractTask giveMeATask(Long chatId) {
        if (userBotService.findByChatId(chatId).isEmpty()) throw new IllegalArgumentException();
        UserBot userBot = userBotService.findByChatId(chatId).get();
        GeneratedTask actualGeneratedTask = userBot.getActualGeneratedTask();
        if (actualGeneratedTask == null) {
            actualGeneratedTask = generateNewTask(userBot);
            generatedTaskService.save(actualGeneratedTask);
            userBot.setActualGeneratedTask(actualGeneratedTask);
            userBot.setTypeOfActualTask(TypesOfTasks.GENERATED);
            userBotService.save(userBot);
        }
        return actualGeneratedTask;
    }
    public abstract GeneratedTask generateNewTask(UserBot userBot);

}