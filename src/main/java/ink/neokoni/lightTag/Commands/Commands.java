package ink.neokoni.lightTag.Commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import ink.neokoni.lightTag.LightTag;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;

public class Commands {
    private final LightTag plugin = LightTag.getInstance();
    public Commands() {
        plugin.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            LiteralArgumentBuilder<CommandSourceStack> lighttag = new AliasCommands().getBuilt("lighttag");
            LiteralArgumentBuilder<CommandSourceStack> ltag = new AliasCommands().getBuilt("ltag");
            commands.registrar().register(lighttag.build());
            commands.registrar().register(ltag.build());
        });
    }
}
