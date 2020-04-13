package ru.armagidon.api.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import ru.armagidon.api.gui.buttons.ActionButton;
import ru.armagidon.api.gui.buttons.GUIButton;
import ru.armagidon.api.gui.buttons.ItemButton;
import ru.armagidon.api.gui.buttons.LinkButton;

import java.util.*;
import java.util.stream.Collectors;

public class GUIHolder implements InventoryHolder {


    private final Inventory container;
    private final Map<Integer, GUIButton> buttons = new HashMap<>();


    public GUIHolder(String title, int size) {
        if (size > 6) size = 6;
        container = Bukkit.createInventory(this, size * 9, title);
    }

    public void addButton(int slot, GUIButton button){
        buttons.put(slot,button);
        container.setItem(slot, button.getStack());
    }

    public void open(Player player){
        player.openInventory(container);
    }

    public void handleClick(InventoryClickEvent event){
        event.setCancelled(true);
        if(event.getSlotType().equals(InventoryType.SlotType.OUTSIDE)) return;
        if(event.getRawSlot()>container.getSize()) return;
        GUIButton button = buttons.get(event.getRawSlot());
        if(button!=null){
            if(button instanceof ActionButton){
                ActionButton b = (ActionButton) button;
                b.action(event);
            } else if(button instanceof LinkButton){
                LinkButton b = (LinkButton) button;
                b.action((Player) event.getWhoClicked());
            } else if(button instanceof ItemButton){}
        }
    }

    @Override
    public Inventory getInventory() {
        return container;
    }

    public void reload(){
        getInventory().clear();
        buttons.forEach((slot,item)-> getInventory().setItem(slot,item.getStack()));
        List<Player> pl = new ArrayList<>(getInventory().getViewers().stream().map(v->(Player)v).collect(Collectors.toList()));
        pl.forEach(HumanEntity::closeInventory);
    }

}
