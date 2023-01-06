package aglaia.telegramBot.controllers;

import aglaia.telegramBot.dto.KangTaskDto;
import aglaia.telegramBot.mapping.MappingKangTask;
import aglaia.telegramBot.mapping.MappingKangTaskDto;
import aglaia.telegramBot.model.entity.tasks.KangTask;
import aglaia.telegramBot.service.KangTaskService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@RestController
public class KangTaskController {
    KangTaskService kangTaskService;

    public KangTaskController(KangTaskService kangTaskService) {
        this.kangTaskService = kangTaskService;
    }

    @GetMapping("/main1")
    public String printMain() {
        log.info("we are in main method of KangTaskController, hello world");
        return "hello world";
    }

    // todo rest controller realisate 2 methods: get and post

    @GetMapping("/getAll")
    public List<KangTaskDto> getAll() {
        return kangTaskService.getAllDto();
    }

    @GetMapping("/get/{id}")
    public KangTaskDto getB(@PathVariable(value = "id") Long id) {
        return kangTaskService.getById(id);
    }

    public KangTask getKangTaskWithId() {

        KangTask kangTask = null;

        return kangTask;
    }

    @PostMapping("/addNew")
    public String post(@RequestBody KangTaskDto kangTaskDto) {
        if (kangTaskService.save(kangTaskDto)) return "Kang task saved";
        return "Error, this kang task already exist";
    }

    @PostMapping("/delete")
    public String post(@RequestBody Long id) {
        if (kangTaskService.delete(id)) return "Kang task deleted";
        return "Error, something go wrong";
    }

//todo save, delete by id, search by ???
}
