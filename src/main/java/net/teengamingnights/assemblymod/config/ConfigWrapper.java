package net.teengamingnights.assemblymod.config;

import net.teengamingnights.assemblymod.config.impl.Config;
import org.bukkit.plugin.Plugin;

public class ConfigWrapper extends TOMLWrapper<Config> {

    public ConfigWrapper(Plugin pl) {
        super(pl, Config.class);
    }
}
