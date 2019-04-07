package cn.nukkit.event.player;

import cn.nukkit.Player;
import cn.nukkit.item.Item;

public class PlayerArmorChangeEvent extends PlayerEvent{

    private Item from;
    private Item to;


    public PlayerArmorChangeEvent(Player player, Item from, Item to){
        this.player = player;
        this.from = from;
        this.to = to;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    public Item getFrom() {
        return from;
    }

    public Item getTo() {
        return to;
    }
}
