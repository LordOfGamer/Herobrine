package com.kreckin.herobrine.api;

import com.kreckin.herobrine.Herobrine;
import com.kreckin.herobrine.support.FactionsSupport;
import com.kreckin.herobrine.support.GriefPreventionSupport;
import com.kreckin.herobrine.support.HerobrineAISupport;
import com.kreckin.herobrine.support.MonsterApocalypseSupport;
import com.kreckin.herobrine.support.PreciousStonesSupport;
import com.kreckin.herobrine.support.ResidenceSupport;
import com.kreckin.herobrine.support.TownySupport;
import com.kreckin.herobrine.support.WorldGuardSupport;
import com.kreckin.herobrine.util.Validate;
import java.util.ArrayList;
import org.bukkit.Location;

public class SupportManager {
    
    private final ArrayList<Support> supports;
    
    public SupportManager() {
        supports = new ArrayList<Support>();
        addSupport(new ResidenceSupport());
        addSupport(new GriefPreventionSupport());
        addSupport(new PreciousStonesSupport());
        addSupport(new WorldGuardSupport());
        addSupport(new TownySupport());
        addSupport(new HerobrineAISupport());
        addSupport(new FactionsSupport());
        addSupport(new MonsterApocalypseSupport());
    }
    
    public final void addSupport(Support support) {
        Validate.isSafe(support);
        supports.add(support);
    }
    
    public void checkPlugins() {
        for (Support support : supports) {
            if (support.isEnabled() && support.getPlugin().isEnabled()) {
                Herobrine.getLog().info("Hooked: " + support.getName() + " v" + support.getPlugin().getDescription().getVersion());
            }
        }
    }
    
    public boolean checkPermissions(Location loc) {
        Validate.isSafe(loc);
        for (Support support : supports) {
            if (!support.isEnabled()) {
                continue;
            }
            if (!support.checkPermissions(loc, support.getPlugin())) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Support> getSupports() {
        return supports;
    }
}
