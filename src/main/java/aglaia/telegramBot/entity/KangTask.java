package aglaia.telegramBot.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//@Entity
@Getter
@Setter
@NoArgsConstructor

public class KangTask extends AbstractTask {
//    @Id
//    @Column(name = "id", nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public static final String A = "/a";
    public static final String B = "/b";
    public static final String C = "/c";
    public static final String D = "/d";
    public static final String E = "/e";
//    @Column
    private int index;
    String a;
    String b;
    String c;
    String d;
    String e;

    private static int counter = 0;

//    @Column
    int scoreFor4;
    int scoreFor5;

//    @Column
    int difficultyLevel;


    public KangTask(String problem, String a, String b, String c, String d, String e, String correctAnswer) {
        super(problem, correctAnswer);
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        counter++;
        index = counter;

        super.problem = problem + System.lineSeparator() + " " + A + " - " + a + "; " + B + " - " + b + "; " + C + " - " + c + "; " + D + " - " + d + "; " + E + " - " + e;
    }

    public KangTask(String problemAndOptionsABCDE, String correctAnswer) {
        super(problemAndOptionsABCDE, correctAnswer);
        counter++;
        index = counter;
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
