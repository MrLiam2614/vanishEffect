package me.mrliam2614.events;

import de.myzelyam.api.vanish.PlayerVanishStateChangeEvent;
import me.mrliam2614.VanishEffect;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class changeVanish implements Listener {
    private final VanishEffect plugin;

    public changeVanish(VanishEffect plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onStateChange(PlayerVanishStateChangeEvent event) {
        UUID uuid = event.getUUID();
        Player player = plugin.getServer().getPlayer(uuid);
        Location playerLoc = player.getLocation();
        World world = playerLoc.getWorld();

        int numLight = plugin.getConfig().getInt("LightningNumer");
        int i = 0;

        while (i < numLight) {
            world.strikeLightningEffect(playerLoc);
            i++;
        }
        int numEntity = plugin.getConfig().getInt("EntityNum");
        String entityName = plugin.getConfig().getString("Entity").toUpperCase();

        Long despawnTime = plugin.getConfig().getLong("DespawnTime");

        EntityType entityType = EntityType.valueOf(entityName);
        i = 0;

        while (i < numEntity) {
            Entity entity = world.spawnEntity(playerLoc, entityType);
            new BukkitRunnable() {
                @Override
                public void run() {
                    entity.remove();
                }
            }.runTaskLater(plugin, despawnTime);
            i++;
        }
    }
}
