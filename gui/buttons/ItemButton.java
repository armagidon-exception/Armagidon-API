package ru.armagidon.api.gui.buttons;

import org.bukkit.inventory.ItemStack;

public class ItemButton extends GUIButton
{

    public ItemButton(ItemStack stack) {
        super(stack);
    }

    @Override
    public void action(Object o) {}

    @Override
    public ItemStack getStack() {
        return super.getStack();
    }
}
