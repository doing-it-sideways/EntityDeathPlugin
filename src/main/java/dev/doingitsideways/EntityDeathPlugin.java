package dev.doingitsideways;

import javax.annotation.Nonnull;

import com.hypixel.hytale.server.core.plugin.JavaPlugin;
import com.hypixel.hytale.server.core.plugin.JavaPluginInit;
import com.hypixel.hytale.server.core.util.Config;

import dev.doingitsideways.config.EntityDeathConfig;
import dev.doingitsideways.events.EntityDeathSystem;

public class EntityDeathPlugin extends JavaPlugin {

    public final Config<EntityDeathConfig> config;
    public static EntityDeathPlugin instance = null;

    public EntityDeathPlugin(@Nonnull JavaPluginInit init) {
        super(init);

        if (instance == null)
            instance = this;
        
        config = this.withConfig("EntityDeathConfig", EntityDeathConfig.CODEC);
    }
    
    @Override
    protected void setup() {
        super.setup();
        
        this.getEntityStoreRegistry().registerSystem(new EntityDeathSystem());
        config.save();
    }

    @Override
    protected void shutdown() {
        config.save();

        super.shutdown();
    }
}
