package dev.millzyg.ItemFlagger.Listeners;

import dev.millzyg.ItemFlagger.Flagger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;

import static dev.millzyg.ItemFlagger.GlobalFields.config;

public class OnBlockPlace implements org.bukkit.event.Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        for (String blockName : config.getBlocks()) {
            if (blockName.equals(event.getBlock().getType().name())) {
                new Flagger(event.getPlayer(), event.getPlayer().getLocation(), event.getBlockPlaced().getType())
                        .setAlertOperators(config.getAlertOperators())
                        .setLogToFile(config.getLogToFile())
                        .setPrintToConsole(config.getPrintToConsole())
                        .sendFlagMessage();
            }
        }
    }
}
