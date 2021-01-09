package rip.koru.roads.generation;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.material.Stairs;
import rip.koru.roads.manager.RoadGenerator;
import rip.koru.roads.utils.Adapter;
import rip.koru.roads.utils.RoadDirection;
import rip.koru.roads.utils.Utils;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * Developed by FxMxGRAGFX
 * Project: KoruRoads
 **/

public class WestG {

    public static void generate(Player player, int max, String[] blocks) {
        int y = 100;
        long m1 = System.currentTimeMillis();
        for (int x = -0; x > -max; x--) {
            for (int z = 0; z < 5; z++) {
                Location blockTop = RoadGenerator.topBlock(player, x, z);
                HashMap<Integer, Material> mats = new HashMap<Integer, Material>();
                for(String s : blocks) {
                    Material type = Material.getMaterial(Integer.parseInt(s));
                    mats.put(mats.size() + 1, type);
                }
                Material selected = mats.get(Utils.getRandomNumber(1, mats.size() + 1));
                blockTop.getBlock().setType(selected);
                Adapter.adapt(blockTop,RoadDirection.NORHT);
            }
            for (int z = -0; z > -5; z--) {
                Location blockTop = RoadGenerator.topBlock(player, x, z);
                HashMap<Integer, Material> mats = new HashMap<Integer, Material>();
                for(String s : blocks) {
                    Material type = Material.getMaterial(Integer.parseInt(s));
                    mats.put(mats.size() + 1, type);
                }
                Material selected = mats.get(Utils.getRandomNumber(1, mats.size() + 1));
                blockTop.getBlock().setType(selected);
                Adapter.adapt(blockTop,RoadDirection.NORHT);
            }
            double porcentaje = Math.abs(((double)x * 100.0 / (double)max)) ;
            Bukkit.broadcastMessage("Generating the road WEST: " + new DecimalFormat("##.##").format((porcentaje)) + "%");
        }
        Bukkit.broadcastMessage("Road WEST generated successfully in " + (double) TimeUnit.MILLISECONDS.toSeconds((System.currentTimeMillis() - m1)) + " seconds!");
    }

    private static void buildPilar(Location l) {
        l.setY(l.getY() + 1);
        l.getBlock().setType(Material.COBBLESTONE);
        l.setY(l.getY() + 1);
        l.getBlock().setType(Material.COBBLESTONE);
        l.setY(l.getY() + 1);
        l.getBlock().setType(Material.COBBLESTONE);
        l.setY(l.getY() + 1);
        l.getBlock().setType(Material.COBBLESTONE);
        l.setY(l.getY() + 1);
        l.getBlock().setType(Material.COBBLESTONE);
        l.setX(l.getX() - 1);
        l.getBlock().setType(Material.COBBLESTONE_STAIRS);
        Block b1 = l.getBlock();
        BlockState state1 = b1.getState();
        Stairs stairs1 = (Stairs) state1.getData();
        stairs1.setFacingDirection(BlockFace.EAST);
        stairs1.setInverted(true);
        state1.update(false, false);

        l.setX(l.getX() + 2);
        l.getBlock().setType(Material.COBBLESTONE_STAIRS);
        Block b2 = l.getBlock();
        BlockState state2 = b2.getState();
        Stairs stairs2 = (Stairs) state2.getData();
        stairs2.setFacingDirection(BlockFace.WEST);
        stairs2.setInverted(true);
        state2.update(false, false);
    }
}
