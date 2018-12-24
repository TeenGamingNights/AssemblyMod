package net.teengamingnights.assemblymod;

import net.teengamingnights.assemblymod.factory.FactoryManager;
import net.teengamingnights.assemblymod.factory.TestFactory;

import net.teengamingnights.assemblymod.listeners.InteractListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AssemblyMod extends JavaPlugin {

    private AssemblyMod instance;
    private FactoryManager factoryManager;

    @Override
    public void onEnable() {
        instance = this;
        factoryManager = new FactoryManager();

        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new InteractListener(), this);
        factoryManager.registerFactory(new TestFactory());
    }
}
