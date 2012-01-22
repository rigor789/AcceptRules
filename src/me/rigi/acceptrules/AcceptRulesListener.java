package me.rigi.acceptrules;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class AcceptRulesListener implements Listener {
	
	/*private AcceptRulesMain plugin;
	
	public AcceptRulesListener(AcceptRulesMain plugin) {
		// TODO Auto-generated constructor stub
		this.plugin = plugin;
	}
	*/
	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event){
		
		 String[] args = event.getMessage().split(" ");
		if(args[0].equalsIgnoreCase(AcceptRulesMain.RulesCmd)){
			Player player = event.getPlayer();
			if(!(AcceptRulesMain.readed.contains(player))){
				AcceptRulesMain.readed.add(player);	
			}
			
			
		}
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		if(!AcceptRulesMain.players.contains(event.getPlayer().getName())){
			if(AcceptRulesMain.TpOnJoin==true){
			event.getPlayer().teleport(AcceptRulesMain.SpawnPosition);
			}
			
			if(AcceptRulesMain.Inform==true){
				final Player player = event.getPlayer();
				
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(new AcceptRulesMain(), new Runnable() {
				    public void run() {
				    	player.sendMessage(AcceptRulesMain.InformMsg);
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
			player.sendMessage(ChatColor.DARK_RED+AcceptRulesMain.CantBuildMsg);
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
				player.sendMessage(ChatColor.DARK_RED+AcceptRulesMain.CantBuildMsg);
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
