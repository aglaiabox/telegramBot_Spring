package aglaia.telegramBot.commands.operation;

import aglaia.telegramBot.service.operation.CommandServiceDivide;
import org.springframework.stereotype.Component;

@Component
public class TaskCommandDivide extends AbstractTaskCommand {
    public TaskCommandDivide(CommandServiceDivide divideCommandService) {
        super("division", "Задания на деление", divideCommandService);
    }

}