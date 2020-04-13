package ru.armagidon.api.gui;

import org.bukkit.entity.Player;
import ru.armagidon.api.gui.buttons.GUIButton;

import java.util.*;

public class PagedGUI
{
    private int page = 0;
    private int pages;
    private int rows;
    private final List<GUIHolder> holders;

    public PagedGUI(int pages, String title_format,int rows) {
        this.pages = pages;
        this.rows = rows;
        this.holders = new ArrayList<>(pages);
        for (int i = 0; i < this.pages; i++) {
            title_format = title_format.replace("(%PN%)",""+(i+1));
            GUIHolder holder = new GUIHolder(title_format,6);
            holders.add(holder);
        }
    }

    public void fill(List<GUIButton> buttons){
        Queue<GUIButton> queue = new LinkedList<>(buttons);
        int max_for_page = 9*this.rows;
        int pages = (int) Math.ceil((double) buttons.size()/max_for_page);
        for (int p = 0; p < pages&&!queue.isEmpty(); p++) {
            for (int slot = 0; slot < max_for_page&&!queue.isEmpty(); slot++) {
                holders.get(p).addButton(slot,queue.poll());
                System.out.println();
            }
        }
    }

    public void addButton(int free_slot, GUIButton button){
        holders.forEach(p-> p.addButton(free_slot,button));
    }

    public GUIHolder nextPage(){
        page++;
        if(page>pages) page=0;
        GUIHolder holder = holders.get(page);
        if(holder == null){
            holder = new GUIHolder("NULL OBJECT",6);
        }
        return holder;
    }

    public GUIHolder previousPage(){
        page--;
        if(page<0) page=pages-1;
        GUIHolder holder = holders.get(page);
        if(holder == null){
            holder = new GUIHolder("NULL OBJECT",6);
        }
        return holder;
    }

    public int getPage() {
        return page;
    }

    public void open(Player player){
        holders.get(0).open(player);
    }
}
