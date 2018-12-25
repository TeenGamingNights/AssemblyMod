package net.teengamingnights.assemblymod.config;

import net.teengamingnights.assemblymod.config.impl.Config;
import org.bukkit.plugin.Plugin;

@FileName(fileName = "config.toml")
public class ConfigWrapper extends TOMLWrapper<Config> {

    public ConfigWrapper(Plugin pl) {
        super(pl, Config.class);
    }
}
