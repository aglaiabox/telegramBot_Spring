package aglaia.telegramBot.service.operation;

import aglaia.telegramBot.model.entity.tasks.AbstractTask;
import aglaia.telegramBot.model.entity.UserBot;
import aglaia.telegramBot.model.entity.tasks.KangTask;
import aglaia.telegramBot.model.entity.tasks.TypesOfTasks;
import aglaia.telegramBot.service.KangTaskService;
import aglaia.telegramBot.service.UserBotService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CommandServiceKang extends AbstractCommandService {
    KangTaskService kangTaskService;

    public CommandServiceKang(UserBotService userBotService, KangTaskService kangTaskService) {
        super(userBotService);
        this.kangTaskService = kangTaskService;
    }

    @Override
    public AbstractTask giveMeATask(Long chatId) {

        Optional<UserBot> optionalUserBot = userBotService.findByChatId(chatId);
        if (optionalUserBot.isEmpty()) throw new IllegalArgumentException();
        UserBot userBot = optionalUserBot.get();
        KangTask kangTask = userBot.getActualKangTask();
        if (kangTask == null) {
            kangTask = kangTaskService.getFirst();
            System.out.println(kangTask.getProblem());
            userBot.setActualTask(kangTask);
        } else if (userBot.isActualKangTaskDone() && kangTask.getNext() != null) {
            kangTask = kangTask.getNext();
            userBot.setActualTask(kangTask);
            userBot.setActualKangTaskDone(false);
        } else {
            kangTask = null;
        }
        userBot.setTypeOfActualTask(TypesOfTasks.KANG);
        userBotService.save(userBot);
        return kangTask;
    }

}
