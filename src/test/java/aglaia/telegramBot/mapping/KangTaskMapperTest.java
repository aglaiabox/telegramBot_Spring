package aglaia.telegramBot.mapping;

import aglaia.telegramBot.dto.KangTaskDto;
import aglaia.telegramBot.model.entity.tasks.KangTask;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;

class KangTaskMapperTest {

    private KangTaskMapper kangTaskMapper;

    @BeforeEach
    private void init(){
        kangTaskMapper = new KangTaskMapper();
    }

    @Test
    public void convert() {
        KangTask source = KangTask.builder().id(1L).problem("blablabla").correctAnswer("a").scoreFor4(44).scoreFor5(55).difficultyLevel(3).build();
        KangTaskDto dto = kangTaskMapper.convert(source);
        assertNotNull(dto);
        assertEquals(source.getId(), dto.getId());
        assertEquals(source.getProblem(), dto.getProblem());
        assertEquals(source.getCorrectAnswer(), dto.getCorrectAnswer());
        assertEquals(source.getScoreFor4(), dto.getScoreFor4());
        assertEquals(source.getScoreFor5(), dto.getScoreFor5());
        assertEquals(source.getDifficultyLevel(), dto.getDifficultyLevel());
    }

}