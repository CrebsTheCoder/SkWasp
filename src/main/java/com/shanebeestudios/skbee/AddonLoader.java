            addon.loadClasses("com.shanebeestudios.skbee.elements.nbt");
            new NBTListener(this.plugin);
            Util.logLoading("&5NBT Elements &asuccessfully loaded");
        } catch (IOException ex) {
            ex.printStackTrace();
            pluginManager.disablePlugin(this.plugin);
        }
    }

    private void loadRecipeElements() {
        if (!this.config.ELEMENTS_RECIPE) {
            Util.logLoading("&5Recipe Elements &cdisabled via config");
            return;
        }
        try {
            addon.loadClasses("com.shanebeestudios.skbee.elements.recipe");
            Util.logLoading("&5Recipe Elements &asuccessfully loaded");
        } catch (IOException ex) {
            ex.printStackTrace();
            pluginManager.disablePlugin(this.plugin);
        }
    }

    private void loadScoreboardElements() {
        if (!this.config.ELEMENTS_BOARD) {
            Util.logLoading("&5Scoreboard Elements &cdisabled via config");
            return;
        }
        try {
            addon.loadClasses("com.shanebeestudios.skbee.elements.scoreboard");
            pluginManager.registerEvents(new BoardManager(), this.plugin);
            Util.logLoading("&5Scoreboard Elements &asuccessfully loaded");
        } catch (IOException ex) {
            ex.printStackTrace();
            pluginManager.disablePlugin(this.plugin);
        }
    }

    private void loadScoreboardObjectiveElements() {
        if (!this.config.ELEMENTS_OBJECTIVE) {
            Util.logLoading("&5Scoreboard Objective Elements &cdisabled via config");
            return;
        }
        if (Classes.getClassInfoNoError("objective") != null || Classes.getExactClassInfo(Objective.class) != null) {
            Util.logLoading("&5Scoreboard Objective Elements &cdisabled");
            Util.logLoading("&7It appears another Skript addon may have registered Scoreboard Objective syntax.");
            Util.logLoading("&7To use SkBee Scoreboard Objectives, please remove the addon which has registered Scoreboard Objective already.");
            return;
        }
        try {
            addon.loadClasses("com.shanebeestudios.skbee.elements.objective");
            Util.logLoading("&5Scoreboard Objective Elements &asuccessfully loaded");
        } catch (IOException ex) {
            ex.printStackTrace();
            pluginManager.disablePlugin(this.plugin);
        }
    }

    private void loadTeamElements() {
        if (!this.config.ELEMENTS_TEAM) {
            Util.logLoading("&5Team Elements &cdisabled via config");
            return;
        }
        if (Classes.getClassInfoNoError("team") != null || Classes.getExactClassInfo(Team.class) != null) {
            Util.logLoading("&5Team Elements &cdisabled");
            Util.logLoading("&7It appears another Skript addon may have registered Team syntax.");
            Util.logLoading("&7To use SkBee Teams, please remove the addon which has registered Teams already.");
            return;
        }
        try {
            addon.loadClasses("com.shanebeestudios.skbee.elements.team");
            Util.logLoading("&5Team Elements &asuccessfully loaded");
        } catch (IOException ex) {
            ex.printStackTrace();
            pluginManager.disablePlugin(this.plugin);
        }
    }

    }

    private void loadBossBarElements() {
        if (!this.config.ELEMENTS_BOSS_BAR) {
            Util.logLoading("&5BossBar Elements &cdisabled via config");
            return;
        }
        if (Classes.getClassInfoNoError("bossbar") != null || Classes.getExactClassInfo(BossBar.class) != null) {
            Util.logLoading("&5BossBar Elements &cdisabled");
            Util.logLoading("&7It appears another Skript addon may have registered BossBar syntax.");
            Util.logLoading("&7To use SkBee BossBars, please remove the addon which has registered BossBars already.");
            return;
        }
        try {
            addon.loadClasses("com.shanebeestudios.skbee.elements.bossbar");
            Util.logLoading("&5BossBar Elements &asuccessfully loaded");
        } catch (IOException ex) {
            ex.printStackTrace();
            pluginManager.disablePlugin(this.plugin);
        }

    }

    private void loadStatisticElements() {
        if (!this.config.ELEMENTS_STATISTIC) {
            Util.logLoading("&5Statistic Elements &cdisabled via config");
            return;
        }
        if (Classes.getClassInfoNoError("statistic") != null || Classes.getExactClassInfo(Statistic.class) != null) {
            Util.logLoading("&5Statistic Elements &cdisabled");
            Util.logLoading("&7It appears another Skript addon may have registered Statistic syntax.");
            Util.logLoading("&7To use SkBee Statistics, please remove the addon which has registered Statistic already.");
            return;
        }
        try {
            addon.loadClasses("com.shanebeestudios.skbee.elements.statistic");
            Util.logLoading("&5Statistic Elements &asuccessfully loaded");
        } catch (IOException ex) {
            ex.printStackTrace();
            pluginManager.disablePlugin(this.plugin);
        }
    }

    private void loadVillagerElements() {
        if (!this.config.ELEMENTS_VILLAGER) {
            Util.logLoading("&5Villager Elements &cdisabled via config");
            return;
        }
        try {
            addon.loadClasses("com.shanebeestudios.skbee.elements.villager");
            Util.logLoading("&5Villager Elements &asuccessfully loaded");
        } catch (IOException ex) {
            ex.printStackTrace();
            pluginManager.disablePlugin(this.plugin);
        }
    }

    private void loadAdvancementElements() {
        if (!this.config.ELEMENTS_ADVANCEMENT) {
            Util.logLoading("&5Advancement Elements &cdisabled via config");
            return;
        }
        try {
            addon.loadClasses("com.shanebeestudios.skbee.elements.advancement");
            Util.logLoading("&5Advancement Elements &asuccessfully loaded");
        } catch (IOException ex) {
            ex.printStackTrace();
            pluginManager.disablePlugin(this.plugin);
        }
    }

    private void loadWorldBorderElements() {
        if (!this.config.ELEMENTS_WORLD_BORDER) {
            Util.logLoading("&5World Border Elements &cdisabled via config");
            return;
        }
        try {
            addon.loadClasses("com.shanebeestudios.skbee.elements.worldborder");
            Util.logLoading("&5World Border Elements &asuccessfully loaded");
        } catch (IOException ex) {
            ex.printStackTrace();
            pluginManager.disablePlugin(this.plugin);
        }
    }

    private void loadParticleElements() {
        if (!this.config.ELEMENTS_PARTICLE) {
            Util.logLoading("&5Particle Elements &cdisabled via config");
            return;
        }
        try {
            addon.loadClasses("com.shanebeestudios.skbee.elements.particle");
            Util.logLoading("&5Particle Elements &asuccessfully loaded");
        } catch (IOException ex) {
            ex.printStackTrace();
            pluginManager.disablePlugin(this.plugin);
        }
    }

    private void loadTagElements() {
        if (!this.config.ELEMENTS_MINECRAFT_TAG) {
            Util.logLoading("&5Minecraft Tag elements &cdisabled via config");
            return;
        }
        try {
            addon.loadClasses("com.shanebeestudios.skbee.elements.tag");
            Util.logLoading("&5Minecraft Tag elements &asuccessfully loaded");
        } catch (IOException ex) {
            ex.printStackTrace();
            pluginManager.disablePlugin(this.plugin);
        }
    }

    private void loadRayTraceElements() {
        if (!this.config.ELEMENTS_RAYTRACE) {
            Util.logLoading("&5RayTrace elements &cdisabled via config");
            return;
        }
        try {
            addon.loadClasses("com.shanebeestudios.skbee.elements.raytrace");
            Util.logLoading("&5RayTrace elements &asuccessfully loaded");
        } catch (IOException ex) {
            ex.printStackTrace();
            pluginManager.disablePlugin(this.plugin);
        }
    }

    private void loadFishingElements() {
        if (!this.config.ELEMENTS_FISHING) {
            Util.logLoading("&5Fishing elements &cdisabled via config");
            return;
        }
        try {
            addon.loadClasses("com.shanebeestudios.skbee.elements.fishing");
            Util.logLoading("&5Fishing elements &asuccessfully loaded");
        } catch (IOException ex) {
            ex.printStackTrace();
            pluginManager.disablePlugin(this.plugin);
        }
    }

    private void loadDisplayEntityElements() {
        if (!this.config.ELEMENTS_DISPLAY) {
            Util.logLoading("&5Display Entity elements &cdisabled via config");
            return;
        }
        if (!Skript.isRunningMinecraft(1, 19, 4)) {
            Util.logLoading("&5Display Entity elements &cdisabled &7(&eRequires Minecraft 1.19.4+&7)");
            return;
        }
        if (!Skript.classExists("org.bukkit.entity.TextDisplay$TextAlignment")) {
            Util.logLoading("&5Display Entity elements &cdisabled due to a Bukkit API change!");
            Util.logLoading("&7- &eYou need to update your server to fix this issue!");
            return;
        }
        try {
            addon.loadClasses("com.shanebeestudios.skbee.elements.display");
            Util.logLoading("&5Display Entity elements &asuccessfully loaded");
        } catch (IOException ex) {
            ex.printStackTrace();
            pluginManager.disablePlugin(this.plugin);
        }
    }

    private void loadDamageSourceElements() {
        if (!this.config.ELEMENTS_DAMAGE_SOURCE) {
            Util.logLoading("&5Damage Source elements &cdisabled via config");
            return;
        }
        if (!Skript.classExists("org.bukkit.damage.DamageSource")) {
            Util.logLoading("&5Damage Source elements &cdisabled &7(&eRequires Minecraft 1.20.4+&7)");
            return;
        }
        try {
            addon.loadClasses("com.shanebeestudios.skbee.elements.damagesource");
            Util.logLoading("&5Damage Source elements &asuccessfully loaded");
        } catch (IOException ex) {
            ex.printStackTrace();
            pluginManager.disablePlugin(this.plugin);
        }
    }

    private void loadItemComponentElements() {
        if (!this.config.ELEMENTS_ITEM_COMPONENT) {
            Util.logLoading("&5Item Component elements &cdisabled via config");
            return;
        }
        try {
            addon.loadClasses("com.shanebeestudios.skbee.elements.itemcomponent");
            Util.logLoading("&5Item Component Elements &asuccessfully loaded");
        } catch (IOException ex) {
            ex.printStackTrace();
            pluginManager.disablePlugin(this.plugin);
        }
    }

    public boolean isTextComponentEnabled() {
        return this.textComponentEnabled;
    }

}
