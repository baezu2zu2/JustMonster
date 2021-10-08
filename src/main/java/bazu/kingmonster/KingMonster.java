package bazu.kingmonster;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;

public final class KingMonster extends JavaPlugin {

    public static Objective deathCount;

    private static KingMonster inst;

    public static KingMonster getInst(){
        return inst;
    }

    @Override
    public void onEnable() {
        inst = this;
        Bukkit.getPluginManager().registerEvents(new Event(), this);

        deathCount = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("death");

        if (deathCount == null){
            deathCount = Bukkit.getScoreboardManager().getMainScoreboard().registerNewObjective("death", "deathCount", ChatColor.RED+"쥬금");
        }

        getServer().getPluginCommand("helpJustMonster").setExecutor(new Commands());
    }
}
