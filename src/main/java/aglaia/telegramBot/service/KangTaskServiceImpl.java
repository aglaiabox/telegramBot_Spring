package aglaia.telegramBot.service;

import aglaia.telegramBot.dto.KangTaskDto;
import aglaia.telegramBot.mapping.KangTaskDtoMapper;
import aglaia.telegramBot.mapping.KangTaskMapper;
import aglaia.telegramBot.model.entity.tasks.KangTask;
import aglaia.telegramBot.repository.KangTaskRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class KangTaskServiceImpl implements KangTaskService {
    private final KangTaskRepository kangTaskRepository;
    KangTaskMapper kangTaskMapper;
    KangTaskDtoMapper kangTaskDtoMapper;

    public KangTaskServiceImpl (KangTaskRepository kangTaskRepository, KangTaskMapper kangTaskMapper, KangTaskDtoMapper kangTaskDtoMapper) {
        this.kangTaskRepository = kangTaskRepository;
        this.kangTaskMapper = kangTaskMapper;
        this.kangTaskDtoMapper = kangTaskDtoMapper;
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

    public Optional<KangTaskDto> getDtoById(Long id) {
        Optional<KangTask> optionalKangTask = kangTaskRepository.findById(id);
        if (optionalKangTask.isPresent()) {
            log.info("Get Kang task id number {}, problem {}", id, optionalKangTask.get().getProblem());
            return Optional.of(kangTaskMapper.convert(optionalKangTask.get()));
        } else {
            log.error("Get Kang task id number {} not exist. Method return Optional.empty", id);
            return Optional.empty();
        }
    }

    public boolean save(KangTaskDto kangTaskDto) {
        log.info("get new kang task DTO and save it to the base: {}", kangTaskDto.getProblem());
        KangTask kangTask = kangTaskDtoMapper.convert(kangTaskDto);
        log.info("Start saving new Kang task, problem: {}", kangTask.getProblem());
        if (!isThisTaskExist(kangTask)) {
            // тут он достает предыдущий канг таск и записывает в него ссылку на этот
            Optional<KangTask> optionalKangTaskLast = kangTaskRepository.findByEmptyNext();
            if (optionalKangTaskLast.isPresent()) {
                KangTask kangTaskLast = optionalKangTaskLast.get();
                kangTaskLast.setNext(kangTask);
                log.info("Last Exist Kang task in the list is N {}, problem: {}", kangTaskLast.getId(), kangTaskLast.getProblem());
                kangTaskRepository.save(kangTaskLast);
            }
            log.info("There is no Kang tasks in the base, this will be first");
            kangTaskRepository.save(kangTask);
            log.info("Task saved");
        } else {
            log.error("this task exist in the base");
            return false;
        }
        return true;
    }

    public boolean delete(Long id) {
        log.info("Try to delete Kang task N{}", id);
        Optional<KangTask> optionalKangTask = kangTaskRepository.findById(id);

        // if there is no task by such id return false
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
        log.info(kangTask.toString());
        return true;
    }

    private boolean isThisTaskExist(KangTask kangTask) {
        Optional<KangTask> optionalKangTask = kangTaskRepository.findByProblem(kangTask.getProblem());
        log.info("check is this task original");
        return optionalKangTask.isPresent();
    }

    public List<KangTaskDto> getAllDto() {
        List<KangTask> list = kangTaskRepository.findAll();
        List<KangTaskDto> listDto = list.stream().map(item -> kangTaskMapper.convert(item)).toList();
        log.info("we are in getAll method of KangTaskController, list of Kang Task:");
        log.info(listDto);
        return listDto;
    }

}
