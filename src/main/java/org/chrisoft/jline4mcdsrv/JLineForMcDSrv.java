package org.chrisoft.jline4mcdsrv;

import ca.stellardrift.confabricate.Confabricate;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.configurate.CommentedConfigurationNode;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.reference.ValueReference;

public final class JLineForMcDSrv implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("jline4mcdsrv");
    private static JLineForMcDSrv instance;
    private Config config;
    private ModContainer modContainer;

    public static JLineForMcDSrv get() {
        if (instance == null) {
            throw new IllegalStateException("jline4mcdsrv has not yet been initialized!");
        }
        return instance;
    }

    @Override
    public void onInitialize() {
        instance = this;
        this.modContainer = FabricLoader.getInstance().getModContainer("jline4mcdsrv")
                .orElseThrow(() -> new IllegalStateException("Could not find mod container for jline4mcdsrv"));
        this.loadModConfig();
    }

    private void loadModConfig() {
        try {
            final ValueReference<Config, CommentedConfigurationNode> reference = Confabricate.configurationFor(this.modContainer, false).referenceTo(Config.class);
            this.config = reference.get();
            reference.setAndSave(this.config);
        } catch (final ConfigurateException ex) {
            throw new RuntimeException("Failed to load config", ex);
        }
    }

    public Config config() {
        return this.config;
    }
}