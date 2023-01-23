package aglaia.telegramBot.model.entity.tasks;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
//@Entity
public abstract class AbstractTask {

    String problem;
    String correctAnswer;

    public AbstractTask(String problem, String correctAnswer) {
        this.problem = problem;
        this.correctAnswer = correctAnswer;
    }


}
