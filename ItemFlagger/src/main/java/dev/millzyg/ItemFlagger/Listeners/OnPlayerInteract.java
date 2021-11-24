package dev.millzyg.ItemFlagger.Listeners;

import dev.millzyg.ItemFlagger.Flagger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import static dev.millzyg.ItemFlagger.GlobalFields.config;

public class OnPlayerInteract implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        for (String itemName : config.getItems()) {
            if (itemName.equals(event.getItem().getType().name())) {
                new Flagger(event.getPlayer(), event.getPlayer().getLocation(), event.getMaterial())
                        .setAlertOperators(config.getAlertOperators())
                        .setLogToFile(config.getLogToFile())
                        .setPrintToConsole(config.getPrintToConsole())
                        .sendFlagMessage();
            }
        }
    }
}
