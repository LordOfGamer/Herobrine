package com.kreckin.herobrined.actions;

import com.kreckin.herobrined.api.IAction;
import com.kreckin.herobrined.api.IActionResult;
import org.bukkit.entity.Player;

public class ActionPlaceTorch implements IAction {

    @Override
    public IActionResult callAction(Player player) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean callCommand(String[] command, Player player) {
        return false;
    }
}
