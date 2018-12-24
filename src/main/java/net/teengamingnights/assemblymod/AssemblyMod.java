package net.teengamingnights.assemblymod;

import net.teengamingnights.assemblymod.config.ConfigWrapper;
import net.teengamingnights.assemblymod.config.TOMLWrapper;
import net.teengamingnights.assemblymod.config.impl.Config;
import net.teengamingnights.assemblymod.factory.FactoryManager;

import net.teengamingnights.assemblymod.listeners.InteractListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class AssemblyMod extends JavaPlugin {

    private FactoryManager factoryManager;

    private Config config;

    @Override
    public void onEnable() {
        try {
            ConfigWrapper configWrapper = new ConfigWrapper(this);
            configWrapper.load();
            config = configWrapper.getInstance();
        } catch(IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        factoryManager = new FactoryManager();

        Bukkit.getPluginManager().registerEvents(new InteractListener(factoryManager), this);
    }
}
