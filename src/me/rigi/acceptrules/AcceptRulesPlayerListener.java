package me.rigi.acceptrules;


import org.bukkit.entity.Player;
//import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerListener;

public class AcceptRulesPlayerListener  extends PlayerListener{
	@SuppressWarnings("unused")
	private AcceptRulesMain plugin;

	public AcceptRulesPlayerListener(AcceptRulesMain plugin) {
		this.plugin = plugin;
	}
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event){
	
		 String[] args = event.getMessage().split(" ");
		if(args[0].equalsIgnoreCase("/rules")){
			Player player = event.getPlayer();
			if(!(AcceptRulesMain.readed.contains(player))){
				AcceptRulesMain.readed.add(player);	
			}
			
			
		}
	}
}
