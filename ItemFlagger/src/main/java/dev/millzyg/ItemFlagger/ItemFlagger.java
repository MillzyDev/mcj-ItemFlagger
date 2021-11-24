package dev.millzyg.ItemFlagger;

import dev.millzyg.ItemFlagger.Config.ConfigReader;
import dev.millzyg.ItemFlagger.Listeners.OnBlockPlace;
import dev.millzyg.ItemFlagger.Listeners.OnPlayerInteract;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class ItemFlagger extends JavaPlugin {
    public static JavaPlugin instance;
    PluginManager manager;

    @Override
    public void onEnable() {
        instance = this;

        this.saveResource("ItemFlagger.json", false);

        GlobalFields.config = ConfigReader.readItemsFromResource(new File(this.getDataFolder(), "ItemFlagger.json"));

        manager = getServer().getPluginManager();

        manager.registerEvents(new OnBlockPlace(), this);
        manager.registerEvents(new OnPlayerInteract(), this);
    }

    @Override
    public void onDisable(){

    }
}
