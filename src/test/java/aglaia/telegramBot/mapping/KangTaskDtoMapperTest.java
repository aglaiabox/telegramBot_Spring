package aglaia.telegramBot.mapping;

import aglaia.telegramBot.dto.KangTaskDto;
import aglaia.telegramBot.model.entity.tasks.KangTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class KangTaskDtoMapperTest {
    KangTaskDtoMapper kangTaskDtoMapper;

    @BeforeEach
    private void init(){
        kangTaskDtoMapper = new KangTaskDtoMapper();
    }

    @Test
    public void convert() {
        KangTaskDto source = KangTaskDto.builder().id(0).problem("blablabla").correctAnswer("a").scoreFor4(44).scoreFor5(55).difficultyLevel(3).build();
        KangTask kangTask = kangTaskDtoMapper.convert(source);
        assertNotNull(kangTask);
        assertNotEquals(source.getId(), kangTask.getId());
        assertEquals(source.getProblem(), kangTask.getProblem());
        assertEquals(source.getCorrectAnswer(), kangTask.getCorrectAnswer());
        assertEquals(source.getScoreFor4(), kangTask.getScoreFor4());
        assertEquals(source.getScoreFor5(), kangTask.getScoreFor5());
        assertEquals(source.getDifficultyLevel(), kangTask.getDifficultyLevel());
    }

}