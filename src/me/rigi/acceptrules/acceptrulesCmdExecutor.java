package me.rigi.acceptrules;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class acceptrulesCmdExecutor implements CommandExecutor {

private AcceptRulesMain plugin;
	
	public acceptrulesCmdExecutor(AcceptRulesMain plugin) {
		// TODO Auto-generated constructor stub
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
			String[] args) {
		Player player = (Player)sender;
		if (commandLabel.equalsIgnoreCase("acceptrules")) {
			if (sender instanceof Player){
				if(args.length == 0){
				if (AcceptRulesMain.players.contains(player.getName())){
					sender.sendMessage(AcceptRulesMain.AcceptedAllreadyMsg.replaceAll("&([a-f0-9])", ChatColor.COLOR_CHAR + "$1"));
				}else{						
					if(AcceptRulesMain.readed.contains(sender)){
						AcceptRulesPreferences.UserWriter(player.getName());
						sender.sendMessage(AcceptRulesMain.AcceptedMsg.replaceAll("&([a-f0-9])", ChatColor.COLOR_CHAR + "$1"));
						AcceptRulesPreferences.UserReader();
						if(AcceptRulesMain.TpAfterAccept == true){
							Location loc = new Location(Bukkit.getWorld("world"), 0, 0, 0, 0, 0);
							if(!AcceptRulesMain.TpPosition.equals(loc)){
							player.teleport(AcceptRulesMain.TpPosition);
							}
						}
						 System.out.println("[AcceptRules] Player: "+player.getName()+" have accepted rules!");
						 Player[] playersonline = Bukkit.getServer().getOnlinePlayers();
						 if(AcceptRulesMain.Notify==true){
							 for(Player p:playersonline){
								 if (p.isOp()||p.hasPermission("acceptrules.notifyonaccept")){
								 p.sendMessage(ChatColor.GOLD+"[AcceptRules] "+ChatColor.GREEN+"Player: "+player.getName()+" has accepted rules!");
							 }
						 }
					}
					
					}else{
						sender.sendMessage(AcceptRulesMain.MustReadRules.replaceAll("&([a-f0-9])", ChatColor.COLOR_CHAR + "$1"));
					}	
				}
				}else{
				if(args[0].equalsIgnoreCase("settp")){
					
					if (!(player.hasPermission("acceptrules.settp") || player.isOp())){
						sender.sendMessage("You dont have permission to do that!");
						}else{
						String World = player.getWorld().getName();
						Location location = player.getLocation();
						double x = location.getX();
						double y = location.getY();
						double z = location.getZ();
						double pitch = location.getPitch();
						double yaw = location.getYaw();
						
						plugin.savePosToConfig("tp",World, x, y, z, pitch, yaw);
						sender.sendMessage("Teleport position succesfuly changed!");
					}
				}else if(args[0].equalsIgnoreCase("setspawn")){
					
					if (!(player.hasPermission("acceptrules.settp") || player.isOp())){
						sender.sendMessage("You dont have permission to do that!");
						}else{
						String World = player.getWorld().getName();
						Location location = player.getLocation();
						double x = location.getX();
						double y = location.getY();
						double z = location.getZ();
						double pitch = location.getPitch();
						double yaw = location.getYaw();
						
						plugin.savePosToConfig("spawn",World, x, y, z, pitch, yaw);
						sender.sendMessage("Spawn position succesfuly changed!");
					}
				}else if(args[0].equalsIgnoreCase("reload")){
					
					if (!(player.hasPermission("acceptrules.settp") || player.isOp())){
						sender.sendMessage("You dont have permission to do that!");
						}else{
						AcceptRulesMain.players.clear();
						AcceptRulesMain.rules.clear();
						AcceptRulesPreferences.UserReader();
						AcceptRulesPreferences.RulesReader();
						plugin.reloadConfig();
						sender.sendMessage(ChatColor.GOLD+"[AcceptRules] "+ChatColor.GREEN+"AcceptRules reloaded files!");
					}
				}
			}
		}
	}
		
		return true;
}

}
