package com.kreckin.herobrine.actions;

import com.kreckin.herobrine.api.Action;
import com.kreckin.herobrine.util.Util;
import org.bukkit.entity.Player;

public class BurnPlayer extends Action {
    
    public BurnPlayer() {
        super(true);
    }

    @Override
    public String callAction(Player player, Object[] metadata) {
        int time = Util.getRandom().nextInt(5) * 20;
        if (time == 0) {
            time = 20;
        }
        player.setFireTicks(time);
        return ("Set on fire for " + (time / 20) + " seconds!");
    }
}
