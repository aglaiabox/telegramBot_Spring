package aglaia.telegramBot.model.tasks;

public class KangTask extends AbstractTask {
    public static final String A = "/a";
    public static final String B = "/b";
    public static final String C = "/c";
    public static final String D = "/d";
    public static final String E = "/e";
    String a;
    String b;
    String c;
    String d;
    String e;

    private static int counter = 0;
    private int index;

    public KangTask(String problem, String a, String b, String c, String d, String e, String correctAnswer) {
        super(problem, correctAnswer);
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        counter ++;
        index = counter;


        super.problem = problem + System.lineSeparator()+ " " + A + " - " + a + "; " + B + " - " + b + "; " + C + " - " + c + "; " + D + " - " + d + "; " + E + " - " + e;
    }

    public Integer getIndex() {
        return index;
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
