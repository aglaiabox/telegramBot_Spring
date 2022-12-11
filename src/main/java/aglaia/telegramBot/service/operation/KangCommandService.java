package aglaia.telegramBot.service.operation;

import aglaia.telegramBot.model.entity.tasks.AbstractTask;
import aglaia.telegramBot.model.entity.UserBot;
import aglaia.telegramBot.model.entity.tasks.KangTask;
import aglaia.telegramBot.service.KangTaskService;
import aglaia.telegramBot.service.UserBotService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class KangCommandService extends AbstractCommandService {
    KangTaskService kangTaskService;

    public KangCommandService(UserBotService userBotService, KangTaskService kangTaskService) {
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
        } else if (userBot.isActualKangTaskDone()) {
            kangTask = kangTask.getNext();
            userBot.setActualTask(kangTask);
            userBot.setActualKangTaskDone(false);
        }
        userBotService.save(userBot);
        return kangTask;
    }

}
