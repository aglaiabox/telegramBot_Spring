package aglaia.telegramBot.model.tasks;

public abstract class AbstractTask {



    String problem;
    String correctAnswer;

    public AbstractTask(String problem, String correctAnswer) {
        this.problem = problem;
        this.correctAnswer = correctAnswer;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }


}
