package aglaia.telegramBot.model.entity.tasks;

import aglaia.telegramBot.model.entity.UserBot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class GeneratedTask extends AbstractTask {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_bot_id")
    private UserBot userBot;

    TypeOfGeneratedTask typeOfGeneratedTask;
    final static TypesOfTasks type = TypesOfTasks.GENERATED;

    public GeneratedTask(String problem, String correctAnswer, TypeOfGeneratedTask typeOfGeneratedTask, UserBot userBot) {
        super(problem, correctAnswer);
        this.typeOfGeneratedTask = typeOfGeneratedTask;
        this.userBot = userBot;
    }

}
