package aglaia.telegramBot.model.entity.tasks;

import aglaia.telegramBot.model.entity.UserBot;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class KangTask extends AbstractTask {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    public static final String A = "/a";
    public static final String B = "/b";
    public static final String C = "/c";
    public static final String D = "/d";
    public static final String E = "/e";


    int scoreFor4;
    int scoreFor5;
    int difficultyLevel;
    @OneToMany(mappedBy = "actualKangTask", fetch = FetchType.LAZY)
    private List<UserBot> userBots;
    final static TypesOfTasks type = TypesOfTasks.KANG;

    @OneToOne
    @JoinColumn(name = "next_kang_task_id")
    KangTask next;

    public KangTask(String problem, String a, String b, String c, String d, String e, String correctAnswer) {
        super(problem, correctAnswer);
        super.problem = problem + System.lineSeparator() + " " + A + " - " + a + "; " + B + " - " + b + "; " + C + " - " + c + "; " + D + " - " + d + "; " + E + " - " + e;
    }

    public KangTask(String problem, String a, String b, String c, String d, String e, String correctAnswer, int scoreFor4, int scoreFor5, int difficultyLevel) {
        super(problem, correctAnswer);
        this.scoreFor4 = scoreFor4;
        this.scoreFor5 = scoreFor5;
        this.difficultyLevel = difficultyLevel;

        super.problem = problem + System.lineSeparator() + " " + A + " - " + a + "; " + B + " - " + b + "; " + C + " - " + c + "; " + D + " - " + d + "; " + E + " - " + e;
    }

    public KangTask(String problemAndOptionsABCDE, String correctAnswer) {
        super(problemAndOptionsABCDE, correctAnswer);
    }

    @Override
    public String toString() {
        return super.problem;
    }

    @Override
    public String getCorrectAnswer() {
        return super.getCorrectAnswer();
    }
}
