package aglaia.telegramBot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//@Entity
@Getter
@Setter
@NoArgsConstructor
public class GeneratedTask extends AbstractTask {
//    @Id
//    @Column(name = "id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Column
    TypeOfGeneratedTask typeOfGeneratedTask;

    public GeneratedTask(String problem, String correctAnswer, TypeOfGeneratedTask typeOfGeneratedTask) {
        super(problem, correctAnswer);
        this.typeOfGeneratedTask = typeOfGeneratedTask;
    }

}
