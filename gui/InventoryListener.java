package ru.armagidon.api.gui;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;

public class InventoryListener implements Listener
{

    public InventoryListener(Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        if(event.getInventory().getHolder() instanceof GUIHolder){
            GUIHolder holder = (GUIHolder) event.getInventory().getHolder();
            holder.handleClick(event);
        }
    }
}
