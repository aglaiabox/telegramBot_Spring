package aglaia.telegramBot.commands.operation;

import aglaia.telegramBot.service.operation.CommandDivideService;
import org.springframework.stereotype.Component;

@Component
public class TaskCommandDivide extends AbstractTaskCommand {
    public TaskCommandDivide(CommandDivideService commandDivideService) {
        super("division", "Задания на деление", commandDivideService);
    }

}