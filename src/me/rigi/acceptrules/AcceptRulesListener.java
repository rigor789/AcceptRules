package me.rigi.acceptrules;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class AcceptRulesListener implements Listener {

	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event){
			Player player = event.getPlayer();
			String cmd = event.getMessage();
			String[] args = event.getMessage().split(" ");
			String[] command = AcceptRulesMain.RulesCmd.split(" ");
			
		if(cmd.equalsIgnoreCase(AcceptRulesMain.RulesCmd)){
			
			if(!(AcceptRulesMain.readed.contains(player))){
				AcceptRulesMain.readed.add(player);	
			}
			if(AcceptRulesMain.RulesMngr){
				 for(String r:AcceptRulesMain.rules){
					 player.sendMessage(ChatColor.GREEN+r);
				 }
			 }
			
		}else{
			if(!args[0].equalsIgnoreCase(command[0]) && AcceptRulesMain.BlockCmds && !args[0].equalsIgnoreCase("/acceptrules") && !AcceptRulesMain.players.contains(event.getPlayer().getName())){
				player.sendMessage(AcceptRulesMain.InformMsg.replaceAll("&([a-f0-9])", ChatColor.COLOR_CHAR + "$1"));
				event.setCancelled(true);
			}			
		}
		
		}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		if(!AcceptRulesMain.players.contains(event.getPlayer().getName())){
			if(AcceptRulesMain.TpOnJoin==true){
				Location loc = new Location(Bukkit.getWorld("world"), 0, 0, 0, 0, 0);
				if(!AcceptRulesMain.SpawnPosition.equals(loc)){
					event.getPlayer().teleport(AcceptRulesMain.SpawnPosition);
				}
			}
			
			if(AcceptRulesMain.Inform==true){
				final Player player = event.getPlayer();
				
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(new AcceptRulesMain(), new Runnable() {
				    public void run() {
				    	player.sendMessage(AcceptRulesMain.InformMsg.replaceAll("&([a-f0-9])", ChatColor.COLOR_CHAR + "$1"));
				    }
				}, 10L);
				
			}
		}
	
	}
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();	
		if(AcceptRulesMain.AllowBuild==false){
			if (AcceptRulesMain.players.contains(player.getName())){
			}else{
				event.setCancelled(true);
				player.sendMessage(AcceptRulesMain.CantBuildMsg.replaceAll("&([a-f0-9])", ChatColor.COLOR_CHAR + "$1"));
			}
		}
	}
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		if(AcceptRulesMain.AllowBuild==false){
			if (AcceptRulesMain.players.contains(player.getName())){
			}else{
				event.setCancelled(true);
				player.sendMessage(AcceptRulesMain.CantBuildMsg.replaceAll("&([a-f0-9])", ChatColor.COLOR_CHAR + "$1"));
			}
		}
	}
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event){
		if((!AcceptRulesMain.players.contains(event.getPlayer().getName()))&& (AcceptRulesMain.AllowMove == false)){
			event.setCancelled(true);
		}
	}
}
