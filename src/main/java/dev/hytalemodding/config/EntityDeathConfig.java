package dev.hytalemodding.config;

import javax.annotation.Nonnull;

import com.hypixel.hytale.codec.Codec;
import com.hypixel.hytale.codec.KeyedCodec;
import com.hypixel.hytale.codec.builder.BuilderCodec;

public class EntityDeathConfig {
    public boolean Player_broadcastToOtherPlayers = true;
    public boolean Player_showLocation = true;

    public boolean NPC_broadcastMessage = false;
    public boolean NPC_showLocation = true;

    @Nonnull
    public static final BuilderCodec<EntityDeathConfig> CODEC =
        BuilderCodec.builder(EntityDeathConfig.class, EntityDeathConfig::new)
        .append(new KeyedCodec<>("PlayerBroadcastToOtherPlayers", Codec.BOOLEAN),
            (cfg, val, _) -> cfg.Player_broadcastToOtherPlayers = val ? val : cfg.Player_broadcastToOtherPlayers,
            (cfg, _) -> cfg.Player_broadcastToOtherPlayers
        ).add()
        .append(new KeyedCodec<>("PlayerShowDeathCoordinates", Codec.BOOLEAN),
            (cfg, val, _) -> cfg.Player_showLocation = val ? val : cfg.Player_showLocation,
            (cfg, _) -> cfg.Player_showLocation
        ).add()
        .append(new KeyedCodec<>("NPCBroadcastDeathMessage", Codec.BOOLEAN),
            (cfg, val, _) -> cfg.NPC_broadcastMessage = val ? val : cfg.NPC_broadcastMessage,
            (cfg, _) -> cfg.NPC_broadcastMessage
        ).add()
        .append(new KeyedCodec<>("NPCShowDeathCoordinates", Codec.BOOLEAN),
            (cfg, val, _) -> cfg.NPC_showLocation = val ? val : cfg.NPC_showLocation,
            (cfg, _) -> cfg.NPC_showLocation
        ).add()
        .build();
}
