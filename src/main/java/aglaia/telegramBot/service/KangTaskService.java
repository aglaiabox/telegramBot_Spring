package aglaia.telegramBot.service;

import aglaia.telegramBot.dto.KangTaskDto;
import aglaia.telegramBot.mapping.MappingKangTask;
import aglaia.telegramBot.mapping.MappingKangTaskDto;
import aglaia.telegramBot.model.entity.tasks.KangTask;
import aglaia.telegramBot.repository.KangTaskRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class KangTaskService {
    private final KangTaskRepository kangTaskRepository;
    MappingKangTask mappingKangTask;
    MappingKangTaskDto mappingKangTaskDto;

    public KangTaskService(KangTaskRepository kangTaskRepository, MappingKangTask mappingKangTask, MappingKangTaskDto mappingKangTaskDto) {
        this.kangTaskRepository = kangTaskRepository;
        this.mappingKangTask = mappingKangTask;
        this.mappingKangTaskDto = mappingKangTaskDto;
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

    public KangTaskDto getById(Long id) {
        Optional<KangTask> optionalKangTask = kangTaskRepository.findById(id);
        if (optionalKangTask.isPresent()) {
            log.info("Get Kang task id number {}, problem {}", id, optionalKangTask.get().getProblem());
            return mappingKangTask.convert(optionalKangTask.get());
        } else {
            log.error("Get Kang task id number {} not exist", id);
            return null;
        }
    }

    public boolean save(KangTask kangTask) {
        log.info("Start saving new Kang task, problem: {}", kangTask.getProblem());
        if (!isThisTaskExist(kangTask)) {
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
            return false;
        }
        return true;
    }

    public boolean delete(Long id) {
        log.info("Try to delete Kang task N{}", id);
        Optional<KangTask> optionalKangTask = kangTaskRepository.findById(id);
        if (optionalKangTask.isEmpty()) {
            log.error("Kang task N{} does not exist", id);
            return false;
        }
        KangTask kangTask = optionalKangTask.get();
        log.info("Kang task N{} exist", id);
        Optional<KangTask> optionalPrevious = kangTaskRepository.findPrevious(id);
        if (optionalPrevious.isPresent()) {
            KangTask kangTaskPrevious = optionalPrevious.get();
            log.info("Kang task N{} change feald next Kang Task", kangTaskPrevious.getId());
            kangTaskPrevious.setNext(kangTask.getNext());
            kangTaskRepository.save(kangTaskPrevious);
        } else {
            log.info("Kang task N{} is the first so it does not have previus", id);
        }
        kangTaskRepository.delete(kangTask);
        log.info("Kang task N{} was deleted", id);
        return true;
    }

    private boolean isThisTaskExist(KangTask kangTask) {
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

    public List<KangTask> getAll() {
        return kangTaskRepository.findAll();
    }

    public List<KangTaskDto> getAllDto() {
        List<KangTask> list = getAll();
        List<KangTaskDto> listDto = list.stream().map(item -> mappingKangTask.convert(item)).toList();
        log.info("we are in getAll method of KangTaskController, list of Kang Task:");
        log.info(listDto);
        return listDto;
    }

    public boolean save(KangTaskDto kangTaskDto) {
        log.info("get new kang task DTO and save it to the base: {}", kangTaskDto.getProblem());
        KangTask kangTask = mappingKangTaskDto.convert(kangTaskDto);
        return save(kangTask);
    }
}
