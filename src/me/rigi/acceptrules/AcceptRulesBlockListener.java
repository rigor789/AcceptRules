package me.rigi.acceptrules;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockPlaceEvent;

public class AcceptRulesBlockListener extends BlockListener {
	@SuppressWarnings("unused")
	private AcceptRulesMain plugin;

	public AcceptRulesBlockListener(AcceptRulesMain plugin) {
		this.plugin = plugin;
	}

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
}
