package aglaia.telegramBot.mapping;

import aglaia.telegramBot.dto.KangTaskDto;
import aglaia.telegramBot.model.entity.tasks.KangTask;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class KangTaskMapper implements Converter<KangTask, KangTaskDto> {

    @Override
    public KangTaskDto convert(KangTask source) {
        return new KangTaskDto(source.getId(), source.getProblem(), source.getCorrectAnswer(),
                source.getScoreFor4(), source.getScoreFor5(), source.getDifficultyLevel());
    }
}
