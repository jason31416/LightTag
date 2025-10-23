package ink.neokoni.lightTag.Commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import ink.neokoni.lightTag.Commands.Functions.ReloadCommand;
import ink.neokoni.lightTag.Commands.Functions.SetTagCommand;
import ink.neokoni.lightTag.GUIs.SetTagGUI;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.entity.Player;

public class AliasCommands {
    public LiteralArgumentBuilder<CommandSourceStack> getBuilt(String root) {
        return Commands.literal(root)
                .then(Commands.literal("reload")
                        .requires(ctx -> ctx.getSender().hasPermission("lighttag.reload"))
                        .executes(ctx->{
                            new ReloadCommand(ctx.getSource().getSender());
                            return Command.SINGLE_SUCCESS;
                        }))
                .then(Commands.literal("set")
                        .executes(ctx -> {
                            if (!(ctx.getSource().getSender() instanceof Player)) {
                                return Command.SINGLE_SUCCESS;
                            }

                            new SetTagGUI((Player) ctx.getSource().getSender()).open();

                            return Command.SINGLE_SUCCESS;
                        })

                        .then(Commands.argument("id", IntegerArgumentType.integer(1))
                        .executes(ctx -> {
                            new SetTagCommand(ctx.getSource().getSender(), ctx.getArgument("id", Integer.class));
                            return Command.SINGLE_SUCCESS;
                        })))
                .then(Commands.literal("unset"))
                .then(Commands.literal("list"))
                .then(Commands.literal("add"))
                .then(Commands.literal("remove"));
    }
}
