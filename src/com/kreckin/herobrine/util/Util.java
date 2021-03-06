package com.kreckin.herobrine.util;

import com.kreckin.herobrine.Herobrine;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Util {
    
    private final static SafeRandom random = new SafeRandom();
    private final static ArrayList<Material> solids = new ArrayList<Material>() {{
        add(Material.STONE);
        add(Material.GRASS);
        add(Material.DIRT);
        add(Material.COBBLESTONE);
        add(Material.WOOD);
        add(Material.SAND);
        add(Material.SANDSTONE);
    }};
    private final static ArrayList<Material> airs = new ArrayList<Material>() {{
        add(Material.AIR);
        add(Material.LONG_GRASS);
        add(Material.SAPLING);
        add(Material.BROWN_MUSHROOM);
        add(Material.RED_MUSHROOM);
        add(Material.RED_ROSE);
        add(Material.YELLOW_FLOWER);
    }};

    public static ItemStack getColoredArmour(Material mat, Color color) {
        Validate.isSafe(mat, color);
        ItemStack itemStack = new ItemStack(mat, 1);
        LeatherArmorMeta meta = (LeatherArmorMeta) itemStack.getItemMeta();
        meta.setColor(color);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
    
    public static boolean shouldAct() {
        return shouldAct(null);
    }

    public static boolean shouldAct(Player player) {
        int actionChance = Herobrine.getConfigFile().getInt("Herobrine.actionChance");
        if (player != null) {
            if (player.getWorld().getTime() >= 13000 && player.getWorld().getTime() <= 14200) {
                actionChance /= 4;
            }
            actionChance /= Herobrine.getHotspotManager().getImportance(player.getLocation());
            if (Herobrine.getConfigFile().getBoolean("Herobrine.heavierEquation") && player.getNearbyEntities(5, 5, 5).size() <= 3) {
                actionChance /= 4;
            }
        }
        return (random.nextInt(actionChance) == 0);
    }
    
    public static String getMessage(String path) {
        Validate.isSafe(path);
        List<String> strings = Herobrine.getConfigFile().getStringList(path);
        if (strings.isEmpty()) {
            return null;
        }
        if (strings.size() == 1) {
            return strings.get(0);
        }
        return strings.get(random.nextInt(strings.size()));
    }

    public static Location getNearbyLocation(Player player, int distance) {
        Validate.isSafe(player, distance);
        int addX = (random.nextBoolean() ? -random.nextInt(distance) : random.nextInt(distance));
        int addZ = (random.nextBoolean() ? -random.nextInt(distance) : random.nextInt(distance));
        return (player.getLocation().add(addX, 0, addZ));
    }
    
    public static boolean isValid(Block block) {
        Validate.isSafe(block);
        return (airs.contains(block.getType())) && isSolid(block.getWorld().getBlockAt(block.getLocation().subtract(0, 1, 0)));
    }
    
    public static boolean isSolid(Block block) {
        Validate.isSafe(block);
        return solids.contains(block.getType());
    }
    
    public static String formatString(String message) {
        Validate.isSafe(message);
        return ("[" + ChatColor.RED + Herobrine.getConfigFile().getString("Herobrine.entityName") + ChatColor.WHITE + "] " + message);
    }
    
    public static SafeRandom getRandom() {
        return random;
    }
}
