package aglaia.telegramBot.service;

import aglaia.telegramBot.model.entity.UserBot;
import aglaia.telegramBot.model.entity.tasks.GeneratedTask;
import aglaia.telegramBot.model.entity.tasks.KangTask;
import aglaia.telegramBot.repository.GeneratedTaskRepository;
import aglaia.telegramBot.repository.KangTaskRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GeneratedTaskService {
    private final GeneratedTaskRepository generatedTaskRepository;

    public GeneratedTaskService(GeneratedTaskRepository generatedTaskRepository) {
        this.generatedTaskRepository = generatedTaskRepository;
    }


    public GeneratedTask save(GeneratedTask generatedTask) {
        UserBot userBot = generatedTask.getUserBot();
        Optional<GeneratedTask> generatedTaskLast = generatedTaskRepository.findByUserBotId(userBot.getId());
        generatedTaskLast.ifPresent(generatedTaskRepository::delete);
        return generatedTaskRepository.save(generatedTask);
    }

}
