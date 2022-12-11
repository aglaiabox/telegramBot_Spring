package aglaia.telegramBot.repository;

import aglaia.telegramBot.model.entity.UserBot;
import aglaia.telegramBot.model.entity.tasks.GeneratedTask;
import aglaia.telegramBot.model.entity.tasks.KangTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GeneratedTaskRepository extends JpaRepository<GeneratedTask, Long> {

    Optional<GeneratedTask> findByUserBotId(Long userBotId);

}
