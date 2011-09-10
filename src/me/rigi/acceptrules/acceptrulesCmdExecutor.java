package me.rigi.acceptrules;



import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class acceptrulesCmdExecutor implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel,
			String[] args) {
		Player player = (Player)sender;
		if (CommandLabel.equalsIgnoreCase("acceptrules")) {
			if (sender instanceof Player){
				if(args.length == 0){
				if (AcceptRulesMain.players.contains(player.getName())){
					sender.sendMessage(ChatColor.DARK_RED+AcceptRulesMain.AcceptedAllreadyMsg);
				}else{	
					
					if(AcceptRulesMain.readed.contains(sender)){
						AcceptRulesPreferences.UserWriter(player.getName());
						sender.sendMessage(ChatColor.GREEN+AcceptRulesMain.AcceptedMsg);
						AcceptRulesPreferences.UserReader();
						if(AcceptRulesMain.TpAfterAccept == true){
							player.teleport(AcceptRulesMain.TpPosition);
						}
						 System.out.println("[AcceptRules]Player: "+player.getName()+" have accepted rules!");
					}else{
						sender.sendMessage(ChatColor.DARK_RED+AcceptRulesMain.MustReadRules);
					}
					
					
					
					
				}
				}else{
				if(args[0].equalsIgnoreCase("settp")){
					
					if (!(player.hasPermission("acceptrules.settp") || player.isOp())){
						sender.sendMessage("You dont have permission to do that!");
						
					}else{
					//	Player player = (Player)sender;
						String World = player.getWorld().getName();
						Location location = player.getLocation();
						double x = location.getX();
						double y = location.getY();
						double z = location.getZ();
						
						AcceptRulesMain.savePosToConfig(World, x, y, z);
						sender.sendMessage("Teleport position succesfuly changed!");
					}
					
				}
			}
			}
		}		
		// TODO Auto-generated method stub
		return true;
	}
	
	

}
