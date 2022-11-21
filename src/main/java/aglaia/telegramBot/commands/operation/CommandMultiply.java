package aglaia.telegramBot.commands.operation;

import aglaia.telegramBot.service.operation.AbstractCommandService;
import aglaia.telegramBot.service.operation.CommandMultiplyService;
import org.springframework.stereotype.Component;

@Component
public class CommandMultiply extends AbstractCommand {

    public CommandMultiply(CommandMultiplyService commandMultiplyService) {
        super("multiply", "Задания на умножение", commandMultiplyService);
    }

}
