package aglaia.telegramBot.commands.operation;

import aglaia.telegramBot.service.operation.CommandServiceMultiply;
import org.springframework.stereotype.Component;

@Component
public class TaskCommandMultiply extends AbstractTaskCommand {

    public TaskCommandMultiply(CommandServiceMultiply commandServiceMultiply) {
        super("multiply", "Задания на умножение", commandServiceMultiply);
    }

}
