package ru.armagidon.api.gui.buttons;

import org.bukkit.inventory.ItemStack;

public abstract class GUIButton<T>
{
    private final ItemStack stack;

    public GUIButton(ItemStack stack) {
        this.stack = stack;
    }

    public ItemStack getStack() {
        return stack;
    }

    public abstract void action(T t);
}
