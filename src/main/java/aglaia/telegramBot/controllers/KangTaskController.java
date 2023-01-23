package aglaia.telegramBot.controllers;

import aglaia.telegramBot.dto.KangTaskDto;
import aglaia.telegramBot.model.entity.tasks.KangTask;
import aglaia.telegramBot.service.KangTaskService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
public class KangTaskController {
    public static final String KANG_TASK_SAVED = "Kang task saved";
    public static final String KANG_TASK_ALREADY_EXIST = "Error, this kang task already exist";
    public static final String KANG_TASK_DELETED = "Kang task deleted";
    public static final String ERROR_DELETE_NO_SUCH_TASK_IN_DATABASE = "Error, This Task can't be deleted as there is no such Task in Database";
    private final KangTaskService kangTaskService;

    public KangTaskController(KangTaskService kangTaskService) {
        this.kangTaskService = kangTaskService;
    }

    @GetMapping("/main1")
    public String sandboxPrintMain() {
        log.info("we are in main method of KangTaskController, hello world");
        return "hello world";
    }

    // todo rest controller realisate 2 methods: get and post

    @GetMapping("/getAll")
    public List<KangTaskDto> getAll() {
        return kangTaskService.getAllDto();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<KangTaskDto> getById (@PathVariable(value = "id") Long id) {
        return ResponseEntity.of(kangTaskService.getDtoById(id));
    }

    public KangTask getKangTaskWithId() {
        KangTask kangTask = null;
        return kangTask;
    }

    @PostMapping("/addNew")
    public String post(@RequestBody KangTaskDto kangTaskDto) {
        if (kangTaskService.save(kangTaskDto)) return KANG_TASK_SAVED;
        return KANG_TASK_ALREADY_EXIST;
    }

    @PostMapping("/delete")
    public String post(@RequestBody Long id) {
        if (kangTaskService.delete(id)) return KANG_TASK_DELETED;
        return ERROR_DELETE_NO_SUCH_TASK_IN_DATABASE;
    }

}
