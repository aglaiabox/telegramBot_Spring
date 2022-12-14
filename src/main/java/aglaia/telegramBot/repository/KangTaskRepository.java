package aglaia.telegramBot.repository;

import aglaia.telegramBot.model.entity.UserBot;
import aglaia.telegramBot.model.entity.tasks.KangTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface KangTaskRepository extends JpaRepository<KangTask, Long> {

    @Query("select k FROM KangTask k where k.next IS NULL")
    Optional<KangTask> findByEmptyNext();

    @Query(value = """
            select id
            from kang_task kt2
            except
            select distinct kt .next_kang_task_id
            from kang_task kt
            where kt .next_kang_task_id is not null
            """,
            nativeQuery = true)
    long findIdOfFirst();

    @Query("select k FROM KangTask k where k.next.id =:id")
    Optional<KangTask> findPrevious(@Param("id") Long id);

    Optional<KangTask> findByProblem(String problem);

    List<KangTask> findAll();

}
