package lol.pyr.znpcsplus.conversion.citizens.model.traits;

import lol.pyr.znpcsplus.conversion.citizens.model.SectionCitizensTrait;
import lol.pyr.znpcsplus.npc.NpcImpl;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HologramTrait extends SectionCitizensTrait {
    private final LegacyComponentSerializer textSerializer;

    public HologramTrait(LegacyComponentSerializer textSerializer) {
        super("hologramtrait");
        this.textSerializer = textSerializer;
    }

    @Override
    public @NotNull NpcImpl apply(NpcImpl npc, ConfigurationSection section) {
        ConfigurationSection linesSection = section.getConfigurationSection("lines");
        if (linesSection != null) {
            List<String> keys = new ArrayList<>(linesSection.getKeys(false));
            for (int i = keys.size() - 1; i >= 0; i--) {
                String line = linesSection.getConfigurationSection(keys.get(i)).getString("text");
                if (line != null) {
                    Component component = textSerializer.deserialize(line);
                    npc.getHologram().addTextLineComponent(component);
                }
            }
        }
        return npc;
    }
}
