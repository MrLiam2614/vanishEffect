package me.mrliam2614;

import me.mrliam2614.events.changeVanish;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class VanishEffect extends JavaPlugin {
    private FacilitisAPI facilitisAPI;
    public void onEnable(){
        facilitisAPI = FacilitisAPI.getInstance();
        facilitisAPI.messages.EnableMessage(this);
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new changeVanish(this), this);
    }

    public void onDisable(){
        facilitisAPI.messages.DisableMessage(this);
    }

    public FacilitisAPI getFacilitisAPI(){
        return facilitisAPI;
    }
}
