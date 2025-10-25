package ink.neokoni.lightTag.Commands;

import cn.jason31416.planetlib.gui.GUI;
import cn.jason31416.planetlib.gui.GUIBuilder;
import cn.jason31416.planetlib.gui.GUISession;
import cn.jason31416.planetlib.gui.GUITemplate;
import cn.jason31416.planetlib.gui.itemgroup.InventoryList;
import cn.jason31416.planetlib.message.Message;
import cn.jason31416.planetlib.wrapper.SimpleItemStack;
import cn.jason31416.planetlib.wrapper.SimplePlayer;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import ink.neokoni.lightTag.Commands.Functions.*;
import ink.neokoni.lightTag.DataStorage.Caches;
import ink.neokoni.lightTag.DataStorage.PlayerDatas;
import ink.neokoni.lightTag.DataStorage.Tags;
import ink.neokoni.lightTag.GUIs.SetTagGUI;
import ink.neokoni.lightTag.Utils.TagUtils;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

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
                            if (!(ctx.getSource().getSender() instanceof Player pl)) {
                                return Command.SINGLE_SUCCESS;
                            }

                            new GUISession(SimplePlayer.of(pl)).display(SetTagGUI.build(pl));

                            return Command.SINGLE_SUCCESS;
                        })

                        .then(Commands.argument("id", IntegerArgumentType.integer(1))
                            .executes(ctx -> {
                                new SetTag(ctx.getSource().getSender(), ctx.getArgument("id", Integer.class));
                                return Command.SINGLE_SUCCESS;
                        })))
                .then(Commands.literal("list")
                        .executes(ctx -> {
                            new SendPlayerTagList(ctx.getSource().getSender());
                            return Command.SINGLE_SUCCESS;
                        }))
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
                .then(Commands.literal("clear")
                    .executes(ctx -> {
                        new ClearTag(ctx.getSource().getSender());
                        return Command.SINGLE_SUCCESS;
                    }));
    }
}
