package aglaia.telegramBot.service;

import aglaia.telegramBot.Bot;
import aglaia.telegramBot.model.entity.tasks.KangTask;
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
public class KangTaskService {
    private final KangTaskRepository kangTaskRepository;

    public KangTaskService(KangTaskRepository kangTaskRepository) {
        this.kangTaskRepository = kangTaskRepository;
        addKangTaskToKangTaskMapa();

    }

    public KangTask getFirst() {
        long id = kangTaskRepository.findIdOfFirst();
        Optional<KangTask> optionalKangTask = kangTaskRepository.findById(id);
        if (optionalKangTask.isEmpty()) {
            log.error("Error! There is no Kang task id number {}", id);
            throw new IllegalArgumentException();
        }
        log.info("Get Kang task id number {}, problem {}", id, optionalKangTask.get().getProblem());
        return optionalKangTask.get();
    }

    public KangTask save(KangTask kangTask) {
        log.info("Start saving new Kang task, problem: {}", kangTask.getProblem());
        if (!isThisTaskExist(kangTask)){
            // тут он достает предыдущий канг таск и записывает в него ссылку на этот
            Optional<KangTask> optionalKangTaskLast = kangTaskRepository.findByEmptyNext();

            kangTaskRepository.save(kangTask);
            if (optionalKangTaskLast.isPresent()) {
                KangTask kangTaskLast = optionalKangTaskLast.get();
                kangTaskLast.setNext(kangTask);
                log.info("Last Exist Kang task in the list is N {}, problem: {}", kangTaskLast.getId(), kangTaskLast.getProblem());
                kangTaskRepository.save(kangTaskLast);
            }
            log.info("There is no Kang tasks in the base, this will be first");
        } else {
            log.error("this task exist in the base");
        }
        return kangTask;
    }

    private boolean isThisTaskExist (KangTask kangTask) {
        Optional<KangTask> optionalKangTask = kangTaskRepository.findByProblem(kangTask.getProblem());
        log.info("check is this task original");
        return optionalKangTask.isPresent();
    }

    private void addKangTaskToKangTaskMapa() {

        save(new KangTask("У Маши 3 брата и 2 сестры. Сколько братьев и сестер у ее брата Миши?",
                "3 брата и 2 сестры", "2 брата и 3 сестры",
                "2 сестры и 2 брата", "3 брата и 3 сестры",
                "невозможно определить", "b", 74, 77, 3));

        save(new KangTask("Сумма восьми чисел равна 1997. Число 997 — одно из них. Если его " +
                "заменить на 799, то новая сумма будет равна", "2195", "1799", "1899", "1979", "1998",
                "b", 83, 81, 3));
        // B 83 81

        save(new KangTask("У игрального кубика на всех парах противоположных граней сумма" +
                " очков одна и та же. Найдите эту сумму", "6", "7", "8", "9", "10",
                "b", 44, 59, 3));
        // B 44 59

    }
}
