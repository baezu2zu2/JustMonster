package bazu.kingmonster;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Commands implements CommandExecutor {

    private final String[] TUTORIALS = new String[]{
            "JustMonster",
            "(야생 기준)난이도 : 중",
            "저스트 몬스터는 Just시리즈의 두번째 작품입니다.",
            "단순히 몬스터의 스펙이 강해지고, 밤을 넘길 수 없게 됩니다.",
            "게다가 죽은 횟수의 제곱만큼 몬스터가 더욱 강해집니다.",
            "강해지는 스펙은 이렇습니다.",
            " -공격력*2",
            " -체력*3",
            " -속도*3",
            " -몬스터가 조용해집니다.",
            " -몬스터가 아이템을 줍습니다.",
            "\n",
            "재밌게 즐겨주세요!",
            "by "+ ChatColor.GRAY+"bazu1229"+ChatColor.WHITE};

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;

        Player pSender = (Player)sender;

        if (label.equalsIgnoreCase("helpJustMonster")){
            tutorial(sender);
        }

        return true;
    }

    private void tutorial(CommandSender sender){
        new BukkitRunnable(){

            @Override
            public void run() {
                Player player = null;

                if (sender instanceof Player){
                    player = (Player)sender;
                }
                for (String tutorial:TUTORIALS) {
                    sender.sendMessage(tutorial);
                    if (player != null){
                        player.playSound(player.getLocation(), Sound.BLOCK_ANCIENT_DEBRIS_PLACE, 10, 1);
                    }

                    try {
                        Thread.sleep(700);
                    } catch (InterruptedException ignored) { }
                }
            }
        }.runTask(KingMonster.getInst());
    }
}
