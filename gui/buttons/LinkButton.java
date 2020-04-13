package ru.armagidon.api.gui.buttons;

import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class LinkButton extends GUIButton<Player>
{

    private final InventoryHolder inventory;

    public LinkButton(ItemStack stack, InventoryHolder inventory) {
        super(stack);
        this.inventory = inventory;
    }

    @Override
    public void action(Player player) {
        player.openInventory(inventory.getInventory());
    }
}
