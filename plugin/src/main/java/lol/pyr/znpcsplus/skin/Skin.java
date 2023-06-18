package lol.pyr.znpcsplus.skin;

import com.github.retrooper.packetevents.protocol.player.TextureProperty;
import com.github.retrooper.packetevents.protocol.player.UserProfile;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.authlib.properties.PropertyMap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Skin {
    private final long timestamp = System.currentTimeMillis();
    private final List<TextureProperty> properties;

    public Skin(String texture, String signature) {
        properties = new ArrayList<>(1);
        properties.add(new TextureProperty("textures", texture, signature));
    }

    public Skin(TextureProperty... properties) {
        this.properties = Arrays.asList(properties);
    }

    public Skin(Collection<TextureProperty> properties) {
        this.properties = new ArrayList<>(properties);
    }

    public Skin(PropertyMap properties) {
        this.properties = properties.values().stream()
                .map(property -> new TextureProperty(property.getName(), property.getValue(), property.getSignature()))
                .collect(Collectors.toList());
    }

    public Skin(JsonObject obj) {
        properties = new ArrayList<>();
        for (JsonElement e : obj.get("properties").getAsJsonArray()) {
            JsonObject o = e.getAsJsonObject();
            properties.add(new TextureProperty(o.get("name").getAsString(), o.get("value").getAsString(), o.has("signature") ? o.get("signature").getAsString() : null));
        }
    }

    public UserProfile apply(UserProfile profile) {
        profile.setTextureProperties(properties);
        return profile;
    }

    public List<TextureProperty> getProperties() {
        return properties;
    }

    public boolean isExpired() {
        return System.currentTimeMillis() - timestamp > 60000L;
    }
}
