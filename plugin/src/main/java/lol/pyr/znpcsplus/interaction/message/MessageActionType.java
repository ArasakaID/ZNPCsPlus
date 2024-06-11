package lol.pyr.znpcsplus.interaction.message;

import lol.pyr.director.adventure.command.CommandContext;
import lol.pyr.director.common.command.CommandExecutionException;
import lol.pyr.znpcsplus.api.interaction.InteractionType;
import lol.pyr.znpcsplus.interaction.InteractionActionImpl;
import lol.pyr.znpcsplus.api.interaction.InteractionActionType;
import lol.pyr.znpcsplus.interaction.InteractionCommandHandler;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

public class MessageActionType implements InteractionActionType<MessageAction>, InteractionCommandHandler {
    private final BukkitAudiences adventure;
    private final LegacyComponentSerializer textSerializer;

    public MessageActionType(BukkitAudiences adventure, LegacyComponentSerializer textSerializer) {
        this.adventure = adventure;
        this.textSerializer = textSerializer;
    }

    @Override
    public String serialize(MessageAction obj) {
        return Base64.getEncoder().encodeToString(obj.getMessage().getBytes(StandardCharsets.UTF_8)) + ";" + obj.getCooldown() + ";" + obj.getInteractionType().name() + ";" + obj.getDelay();
    }

    @Override
    public MessageAction deserialize(String str) {
        String[] split = str.split(";");
        InteractionType type = split.length > 2 ? InteractionType.valueOf(split[2]) : InteractionType.ANY_CLICK;
        return new MessageAction(adventure, textSerializer, new String(Base64.getDecoder().decode(split[0]), StandardCharsets.UTF_8), type, Long.parseLong(split[1]), Long.parseLong(split.length > 3 ? split[3] : "0"));
    }

    @Override
    public Class<MessageAction> getActionClass() {
        return MessageAction.class;
    }

    @Override
    public String getSubcommandName() {
        return "message";
    }

    @Override
    public void appendUsage(CommandContext context) {
        context.setUsage(context.getUsage() + " " + getSubcommandName() + " <id> <click type> <cooldown seconds> <delay ticks> <message>");
    }

    @Override
    public InteractionActionImpl parse(CommandContext context) throws CommandExecutionException {
        InteractionType type = context.parse(InteractionType.class);
        long cooldown = (long) (context.parse(Double.class) * 1000D);
        long delay = (long) (context.parse(Integer.class) * 1D);
        String message = context.dumpAllArgs();
        return new MessageAction(adventure, textSerializer, message, type, cooldown, delay);
    }

    @Override
    public List<String> suggest(CommandContext context) throws CommandExecutionException {
        if (context.argSize() == 1) return context.suggestEnum(InteractionType.values());
        if (context.argSize() == 2) return context.suggestLiteral("1");
        if (context.argSize() == 3) return context.suggestLiteral("0");
        return Collections.emptyList();
    }
}
