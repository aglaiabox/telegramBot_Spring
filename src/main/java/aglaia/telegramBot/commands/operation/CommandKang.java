package aglaia.telegramBot.commands.operation;


import aglaia.telegramBot.service.operation.AbstractCommandService;
import aglaia.telegramBot.service.operation.CommandKangService;
import org.springframework.stereotype.Component;

@Component
public class CommandKang extends AbstractCommand {

    public CommandKang(CommandKangService commandKangService) {
        super("kangaroo", "Задания Кенгуру", commandKangService);
    }
}
