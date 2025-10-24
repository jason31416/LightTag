package ink.neokoni.lightTag.Commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import ink.neokoni.lightTag.Commands.Functions.AddTag;
import ink.neokoni.lightTag.Commands.Functions.ClearTag;
import ink.neokoni.lightTag.Commands.Functions.Reload;
import ink.neokoni.lightTag.Commands.Functions.SetTag;
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
                            new Reload(ctx.getSource().getSender());
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
                                new SetTag(ctx.getSource().getSender(), ctx.getArgument("id", Integer.class));
                                return Command.SINGLE_SUCCESS;
                        })))
                .then(Commands.literal("list"))
                .then(Commands.literal("add")
                        .then(Commands.literal("STATIC")
                                .then(Commands.argument("Content", StringArgumentType.string())
                                        .executes(ctx -> {
                                            new AddTag(ctx.getArgument("Content", String.class),
                                                    ctx.getSource().getSender());
                                            return Command.SINGLE_SUCCESS;
                                        })))
                        .then(Commands.literal("ANIMATION")
                                .then(Commands.argument("Content", StringArgumentType.string())
                                        .then(Commands.argument("Banner", StringArgumentType.string())
                                                .then(Commands.argument("Delay", IntegerArgumentType.integer(0))
                                                        .executes(ctx -> {
                                                            new AddTag(ctx.getArgument("Content", String.class),
                                                                    ctx.getArgument("Banner", String.class),
                                                                    ctx.getArgument("Delay", Integer.class),
                                                                    ctx.getSource().getSender());
                                                            return Command.SINGLE_SUCCESS;
                                                        }))))))
                .then(Commands.literal("remove"))
                .then(Commands.literal("clear")
                    .executes(ctx -> {
                        new ClearTag(ctx.getSource().getSender());
                        return Command.SINGLE_SUCCESS;
                    }));
    }
}
