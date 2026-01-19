package dev.doingitsideways.events;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.hypixel.hytale.component.CommandBuffer;
import com.hypixel.hytale.component.Ref;
import com.hypixel.hytale.component.Store;
import com.hypixel.hytale.component.query.Query;
import com.hypixel.hytale.logger.HytaleLogger;
import com.hypixel.hytale.math.vector.Vector3d;
import com.hypixel.hytale.server.core.Message;
import com.hypixel.hytale.server.core.entity.entities.Player;
import com.hypixel.hytale.server.core.modules.entity.damage.Damage;
import com.hypixel.hytale.server.core.modules.entity.damage.DeathComponent;
import com.hypixel.hytale.server.core.modules.entity.damage.DeathSystems;
import com.hypixel.hytale.server.core.universe.PlayerRef;
import com.hypixel.hytale.server.core.universe.Universe;
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore;
import com.hypixel.hytale.server.npc.entities.NPCEntity;

import dev.doingitsideways.EntityDeathPlugin;
import dev.doingitsideways.config.EntityDeathConfig;
import fi.sulku.hytale.TinyMsg;

public class EntityDeathSystem extends DeathSystems.OnDeathSystem {

    @Override
    @Nullable
    public Query<EntityStore> getQuery() {
        return Query.or(Player.getComponentType(), NPCEntity.getComponentType());
    }
    
    @Override
    public void onComponentAdded(@Nonnull Ref<EntityStore> entityRef,
                                 @Nonnull DeathComponent component,
                                 @Nonnull Store<EntityStore> store,
                                 @Nonnull CommandBuffer<EntityStore> cmdBuf)
    {
        Player playerComponent = (Player) store.getComponent(entityRef, Player.getComponentType());

        assert playerComponent != null;

        Universe.get().getPlayers().forEach(playerRef -> {
            try {
                playerRef.sendMessage(Message.raw("Death player: " + playerComponent.getDisplayName()));
            } catch (Exception e) {
                // TODO: handle exception
            } 
        });
        Damage deathInfo = component.getDeathInfo();
        if (deathInfo != null) {
            Universe.get().sendMessage(Message.raw("Death info amount: " + deathInfo.getAmount()));
        }
        
        // {
        //     Player player = store.getComponent(entityRef, Player.getComponentType());

        //     if (player != null) {
        //         handlePlayerDeath(player, entityRef, component, store, cmdBuf);
        //         return;
        //     }
        // }

        // {
        //     var entityCompType = NPCEntity.getComponentType();
        //     if (entityCompType == null) {
        //         HytaleLogger.forEnclosingClass().atWarning().log("EntityDeathSystem Plugin: Invalid entity component type.");
        //         return;
        //     }

        //     NPCEntity npc = store.getComponent(entityRef, entityCompType);

        //     if (npc != null) {
        //         handleNPCDeath(entityRef, component, store, cmdBuf);
        //         return;
        //     }
        // }
    }

    private void handlePlayerDeath(@Nonnull Player player,
                                   @Nonnull Ref<EntityStore> entityRef,
                                   @Nonnull DeathComponent deathComponent,
                                   @Nonnull Store<EntityStore> store,
                                   @Nonnull CommandBuffer<EntityStore> cmdBuf)
    {
        var cfg = EntityDeathPlugin.instance.config;
        PlayerRef playerRef = store.getComponent(entityRef, PlayerRef.getComponentType());
        Message deathMsg = deathComponent.getDeathMessage();
        Universe.get().sendMessage(Message.raw("IM HERE 11111!"));
        if (cfg == null || playerRef == null || deathMsg == null)
            return;

        String playerName = player.getDisplayName();
        String deathStr = deathMsg.getAnsiMessage().replace("You were", " was");
        
        if (cfg.get().Player_showLocation) {
            Vector3d pos = playerRef.getTransform().getPosition();
            deathStr += String.format(" (%5d, %3d, %5d)", (int)pos.x, (int)pos.y, (int)pos.z);
        }

        deathStr = String.format("<c:red><b>%s</b> %s</c>", playerName, deathStr);
        var formattedMsg = TinyMsg.parse(deathStr);
        Universe.get().sendMessage(Message.raw("IM HERE 2!"));
        if (formattedMsg == null) // shouldn't happen
            return;
        Universe.get().sendMessage(Message.raw("IM HERE 3!"));
        
        if (cfg.get().Player_broadcastToOtherPlayers) {
            try {
                Universe.get().sendMessage(formattedMsg);   
            } catch (Exception e) {
                HytaleLogger.getLogger().atSevere().log("EntityDeathSystem Plugin: Couldn't send message to universe!");
            }
        }
        else {
            try {
                playerRef.sendMessage(formattedMsg);   
            } catch (Exception e) {
                HytaleLogger.getLogger().atSevere().log("EntityDeathSystem Plugin: Couldn't send message to player!");
            }
        }
    }

    private void handleNPCDeath(@Nonnull Ref<EntityStore> entityRef,
                                @Nonnull DeathComponent component,
                                @Nonnull Store<EntityStore> store,
                                @Nonnull CommandBuffer<EntityStore> cmdBuf)
    {
        // TODO
        // var msg = TinyMsg.parse("This is where the NPC <c:red>death message</c> will go! TODO!");
        // Universe.get().sendMessage(msg);
    }
}
