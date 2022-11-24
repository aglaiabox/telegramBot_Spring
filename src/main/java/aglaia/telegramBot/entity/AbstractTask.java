package aglaia.telegramBot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractTask {
//    @Id
//    @Column(name = "id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column
    String problem;
//    @Column
    String correctAnswer;

    public AbstractTask(String problem, String correctAnswer) {
        this.problem = problem;
        this.correctAnswer = correctAnswer;
    }


}
