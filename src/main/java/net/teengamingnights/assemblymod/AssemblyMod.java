package net.teengamingnights.assemblymod;

import net.teengamingnights.assemblymod.factory.FactoryManager;
import net.teengamingnights.assemblymod.factory.TestFactory;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AssemblyMod extends JavaPlugin {
    private static AssemblyMod instance;
    private static FactoryManager factoryManager;

    public static AssemblyMod getInstance() { return instance; }
    public static FactoryManager getFactoryManager() { return factoryManager; }

    @Override
    public void onEnable() {
        instance = this;
        factoryManager = new FactoryManager();

        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new EventHandle(), this);
        factoryManager.registerFactory(new TestFactory());
    }
}
