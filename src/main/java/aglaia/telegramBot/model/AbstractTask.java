package aglaia.telegramBot.model;

// TODO изменить. Инфа о задании должна храниться в UserBot
public abstract class AbstractTask {

    //todo убрать отсюда датабазу и сделать сохранение в сервисе или где-то еще


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
