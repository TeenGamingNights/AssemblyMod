package net.teengamingnights.assemblymod;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AssemblyMod extends JavaPlugin {
    private static AssemblyMod instance;
    public static AssemblyMod getInstance() { return instance; }

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new EventHandle(), this);
    }
}
