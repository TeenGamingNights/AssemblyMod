package net.teengamingnights.assemblymod;

import net.teengamingnights.assemblymod.config.ConfigWrapper;
import net.teengamingnights.assemblymod.config.TOMLWrapper;
import net.teengamingnights.assemblymod.config.impl.Config;
import net.teengamingnights.assemblymod.factory.FactoryManager;

import net.teengamingnights.assemblymod.listeners.InteractListener;
import net.teengamingnights.assemblymod.sql.SQLManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class AssemblyMod extends JavaPlugin {

    private FactoryManager factoryManager;

    private Config config;
    private SQLManager sqlManager;

    @Override
    public void onEnable() {
        try {
            // Load config
            ConfigWrapper configWrapper = new ConfigWrapper(this);
            configWrapper.load();
            config = configWrapper.getInstance();

            // Connect to DB
            sqlManager = new SQLManager(config);
            sqlManager.run();
        } catch(IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        factoryManager = new FactoryManager();

        Bukkit.getPluginManager().registerEvents(new InteractListener(factoryManager), this);
    }
}
