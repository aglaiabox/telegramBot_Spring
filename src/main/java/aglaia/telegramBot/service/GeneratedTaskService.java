package aglaia.telegramBot.service;

import aglaia.telegramBot.Bot;
import aglaia.telegramBot.model.entity.UserBot;
import aglaia.telegramBot.model.entity.tasks.GeneratedTask;
import aglaia.telegramBot.model.entity.tasks.KangTask;
import aglaia.telegramBot.repository.GeneratedTaskRepository;
import aglaia.telegramBot.repository.KangTaskRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Service
@Log4j2
public class GeneratedTaskService {
    private final GeneratedTaskRepository generatedTaskRepository;

    public GeneratedTaskService(GeneratedTaskRepository generatedTaskRepository) {
        this.generatedTaskRepository = generatedTaskRepository;


    }


    public GeneratedTask save(GeneratedTask generatedTask) {
        UserBot userBot = generatedTask.getUserBot();
        Optional<GeneratedTask> generatedTaskLast = generatedTaskRepository.findByUserBotId(userBot.getId());
        generatedTaskLast.ifPresent(generatedTaskRepository::delete);
        log.info("For user {}, name {} generated task: {}", userBot.getId(), userBot.getName(), generatedTask.getProblem());
        return generatedTaskRepository.save(generatedTask);
    }

}
