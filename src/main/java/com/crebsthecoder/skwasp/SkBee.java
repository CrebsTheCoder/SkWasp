package com.crebsthecoder.skwasp;

import ch.njol.skript.Skript;
import com.crebsthecoder.skwasp.api.bound.Bound;
import com.crebsthecoder.skwasp.api.command.SkBeeInfo;
import com.crebsthecoder.skwasp.api.structure.StructureManager;
import com.crebsthecoder.skwasp.api.util.UpdateChecker;
import com.crebsthecoder.skwasp.api.util.Util;
import com.crebsthecoder.skwasp.api.wrapper.LazyLocation;
import com.crebsthecoder.skwasp.config.BoundConfig;
import com.crebsthecoder.skwasp.config.Config;
import com.crebsthecoder.skwasp.elements.other.sections.SecRunTaskLater;
import com.crebsthecoder.skwasp.elements.worldcreator.objects.BeeWorldConfig;
import com.shanebeestudios.vf.api.VirtualFurnaceAPI;
import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class for SkBee
 */
public class SkBee extends JavaPlugin {

    // Earliest MC Version that SkBee will support
    static final int[] EARLIEST_VERSION = new int[]{1, 18, 2};
    private static SkBee instance;

    static {
        ConfigurationSerialization.registerClass(Bound.class, "Bound");
        ConfigurationSerialization.registerClass(LazyLocation.class, "LazyLocation");
    }

    BoundConfig boundConfig = null;
    VirtualFurnaceAPI virtualFurnaceAPI;
    BeeWorldConfig beeWorldConfig;
    StructureManager structureManager = null;
    private Config config;
    private AddonLoader addonLoader = null;

    /**
     * @hidden must be public for Bukkit but let's hide from docs
     */
    public SkBee() {
    }

    /**
     * Get an instance of this plugin
     *
     * @return Instance of this plugin
     */
    public static SkBee getPlugin() {
        return instance;
    }

    /**
     * @hidden
     */
    @SuppressWarnings("deprecation")
    @Override
    public void onEnable() {
        // Let's get this party started...
        long start = System.currentTimeMillis();
        instance = this;
        this.config = new Config(this);
        PluginManager pm = Bukkit.getPluginManager();

        this.addonLoader = new AddonLoader(this);
        // Check if SkriptAddon can actually load
        if (!addonLoader.canLoadPlugin()) {
            pm.disablePlugin(this);
            return;
        }
        loadCommands();

        // Beta check + notice
        String version = getDescription().getVersion();
        if (version.contains("-")) {
            Util.log("&eThis is a BETA build, things may not work as expected, please report any bugs on GitHub");
            Util.log("&ehttps://github.com/crebsthecoder/skwasp/issues");
        }

        new UpdateChecker(this);
        Util.log("&aSuccessfully enabled v%s&7 in &b%.2f seconds", version, (float) (System.currentTimeMillis() - start) / 1000);

        // Load custom worlds if enabled in config
        if (this.beeWorldConfig != null) {
            this.beeWorldConfig.loadCustomWorlds();
        }
        // Looks like we made it after all
    }

    private void loadCommands() {
        //noinspection ConstantConditions
        getCommand("skwasp").setExecutor(new SkBeeInfo(this));
        //pm.registerEvents(new ScriptListener(), this); // Temp removed
    }


    /**
     * @hidden
     */
    @Override
    public void onDisable() {
        // Cancel tasks on stop to prevent async issues
        SecRunTaskLater.cancelTasks();
        if (this.virtualFurnaceAPI != null) {
            this.virtualFurnaceAPI.disableAPI();
        }
        if (this.boundConfig != null) {
            this.boundConfig.saveAllBounds();
        }
    }

    /**
     * Get an instance of this plugin's {@link Config}
     *
     * @return Instance of this plugin's config
     */
    public Config getPluginConfig() {
        return this.config;
    }

    /**
     * Get an instance of the {@link BoundConfig}
     *
     * @return Instance of bound config
     */
    public BoundConfig getBoundConfig() {
        return this.boundConfig;
    }

    /**
     * Get an instance of the {@link BeeWorldConfig}
     *
     * @return Instance of BeeWorld config
     * @hidden
     */
    public BeeWorldConfig getBeeWorldConfig() {
        return beeWorldConfig;
    }

    /**
     * Get an instance of the {@link VirtualFurnaceAPI}
     *
     * @return Instance of the Virtual Furnace API
     */
    public VirtualFurnaceAPI getVirtualFurnaceAPI() {
        return virtualFurnaceAPI;
    }

    /**
     * Get an instance of the {@link StructureManager}
     *
     * @return Instance of the Structure Bee Manager
     */
    public StructureManager getStructureManager() {
        return structureManager;
    }

    /**
     * @hidden
     */
    public AddonLoader getAddonLoader() {
        return addonLoader;
    }

}
