package aglaia.telegramBot.commands.operation;

import aglaia.telegramBot.service.operation.CommandMultiplyService;
import org.springframework.stereotype.Component;

@Component
public class TaskCommandMultiply extends AbstractTaskCommand {

    public TaskCommandMultiply(CommandMultiplyService commandMultiplyService) {
        super("multiply", "Задания на умножение", commandMultiplyService);
    }

}
