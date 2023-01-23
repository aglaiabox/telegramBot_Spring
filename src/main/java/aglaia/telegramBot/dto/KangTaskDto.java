package aglaia.telegramBot.dto;

import aglaia.telegramBot.model.entity.tasks.KangTask;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KangTaskDto {
    long id;
    String problem;
    String correctAnswer;
    int scoreFor4;
    int scoreFor5;
    int difficultyLevel;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KangTaskDto that = (KangTaskDto) o;

        if (getId() != that.getId()) return false;
        if (getScoreFor4() != that.getScoreFor4()) return false;
        if (getScoreFor5() != that.getScoreFor5()) return false;
        if (getDifficultyLevel() != that.getDifficultyLevel()) return false;
        if (getProblem() != null ? !getProblem().equals(that.getProblem()) : that.getProblem() != null) return false;
        return getCorrectAnswer() != null ? getCorrectAnswer().equals(that.getCorrectAnswer()) : that.getCorrectAnswer() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getProblem() != null ? getProblem().hashCode() : 0);
        result = 31 * result + (getCorrectAnswer() != null ? getCorrectAnswer().hashCode() : 0);
        result = 31 * result + getScoreFor4();
        result = 31 * result + getScoreFor5();
        result = 31 * result + getDifficultyLevel();
        return result;
    }
}
