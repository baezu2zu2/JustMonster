package bazu.kingmonster;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class Event implements Listener {

    public static double lvlNum = 1;

    @EventHandler
    public static void onPlayerDamaged(EntityDamageByEntityEvent event){
        setLevelNum();

        if (event.getDamager() instanceof Monster || (event.getDamager() instanceof Projectile && ((Projectile)event.getDamager()).getShooter() instanceof Monster)){
            if (event.getEntity() instanceof Player)
                event.setDamage(event.getDamage() * 2 * lvlNum);
        }
    }

    @EventHandler
    public static void onMobSpawning(EntitySpawnEvent event){
        setLevelNum();

        if (event.getEntity() instanceof Monster && !(event.getEntity() instanceof Boss)){
            Monster monster = (Monster)event.getEntity();

            monster.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(
                    monster.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue() * 3 * lvlNum
            );
            monster.setHealth(monster.getHealth() * 3 * lvlNum);
            monster.setSilent(true);
            monster.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(
                    monster.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue()*3 * lvlNum
            );
            monster.setCanPickupItems(true);
        }
    }

    @EventHandler
    public static void onBedEnter(PlayerBedEnterEvent event){
        event.getPlayer().sendMessage(ChatColor.RED +"침대에 잘 수 없습니다");
        event.setCancelled(true);
    }

    private static void setLevelNum(){
        for (Player player:Bukkit.getOnlinePlayers()) {
            lvlNum = Math.pow(KingMonster.deathCount.getScore(player.getName()).getScore(), 2);
            if (lvlNum < 1){
                lvlNum = 1;
            }
        }
    }
}
