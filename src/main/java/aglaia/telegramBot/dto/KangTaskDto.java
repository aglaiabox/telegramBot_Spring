package aglaia.telegramBot.dto;

import aglaia.telegramBot.model.entity.tasks.KangTask;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KangTaskDto {
    long id;
    String problem;
    String correctAnswer;
    int scoreFor4;
    int scoreFor5;
    int difficultyLevel;

}
