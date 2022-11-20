package aglaia.telegramBot.model;

public class GeneratedTask extends AbstractTask {
    TypeOfGeneratedTask typeOfGeneratedTask;

    public GeneratedTask(String problem, String correctAnswer, TypeOfGeneratedTask typeOfGeneratedTask) {
        super(problem, correctAnswer);
        this.typeOfGeneratedTask = typeOfGeneratedTask;
    }

    public TypeOfGeneratedTask getTypeOfGeneratedTask() {
        return typeOfGeneratedTask;
    }

    public void setTypeOfGeneratedTask(TypeOfGeneratedTask typeOfGeneratedTask) {
        this.typeOfGeneratedTask = typeOfGeneratedTask;
    }
}
