package me.rigi.acceptrules;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
//import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
//import org.bukkit.event.player.PlayerListener;
/****
 * Unused class! AcceptRulesListener contains player listener too!
 * @author rigor789
 *
 */
public class AcceptRulesPlayerListener  implements Listener{

	private AcceptRulesMain plugin;

	public AcceptRulesPlayerListener(AcceptRulesMain plugin) {
		this.plugin = plugin;
	}
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event){
	
		 String[] args = event.getMessage().split(" ");
		if(args[0].equalsIgnoreCase(AcceptRulesMain.RulesCmd)){
			Player player = event.getPlayer();
			if(!(AcceptRulesMain.readed.contains(player))){
				AcceptRulesMain.readed.add(player);	
			}
			
			
		}
	}
	public void onPlayerJoin(PlayerJoinEvent event){
		if(!AcceptRulesMain.players.contains(event.getPlayer().getName())){
			if(AcceptRulesMain.TpOnJoin==true){
			event.getPlayer().teleport(AcceptRulesMain.SpawnPosition);
			}
			
			if(AcceptRulesMain.Inform==true){
				final Player player = event.getPlayer();
				
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
				    public void run() {
				    	player.sendMessage(AcceptRulesMain.InformMsg);
				    }
				}, 10L);
				
			}
		}
	}
/*	public void onPlayerMove(PlayerMoveEvent event){
		if((!AcceptRulesMain.players.contains(event.getPlayer().getName()))&& (AcceptRulesMain.AllowMove == false)){
			event.setCancelled(true);
		}
	}*/
}
