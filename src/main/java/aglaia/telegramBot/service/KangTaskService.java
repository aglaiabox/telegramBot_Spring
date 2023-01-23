package aglaia.telegramBot.service;

import aglaia.telegramBot.dto.KangTaskDto;
import aglaia.telegramBot.model.entity.tasks.KangTask;

import java.util.List;
import java.util.Optional;


public interface KangTaskService {

    // get first task from database  // test done
    public KangTask getFirst();

    // get task by id from database and return dta object  // test done
    public Optional<KangTaskDto> getDtoById(Long id);

    // get dto, cast it to new task and save to database
    public boolean save(KangTaskDto kangTaskDto);

    //delete task by id, couple previous task and next task // test done
    public boolean delete(Long id);

    // get list of all cast and cast to dto // test done
    public List<KangTaskDto> getAllDto();


}
