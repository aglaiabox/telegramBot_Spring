package aglaia.telegramBot.mapping;

import aglaia.telegramBot.dto.KangTaskDto;
import aglaia.telegramBot.model.entity.tasks.KangTask;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class KangTaskDtoMapper implements Converter<KangTaskDto, KangTask> {

    @Override
    public KangTask convert(KangTaskDto source) {
        KangTask kangTask = new KangTask(source.getProblem(), source.getCorrectAnswer(), source.getScoreFor5(), source.getScoreFor4(), source.getDifficultyLevel());
        return kangTask;
    }
}
