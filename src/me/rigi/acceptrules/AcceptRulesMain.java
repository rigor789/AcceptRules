package me.rigi.acceptrules;


import java.io.File;
import java.util.ArrayList;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class AcceptRulesMain extends JavaPlugin {
	Logger Log = Logger.getLogger("Minecraft");
	public static ArrayList<String> players = new ArrayList<String>();
	public static Configuration config;
	public static String AcceptedMsg;
	public static String AcceptedAllreadyMsg;
	public static String MustReadRules;
	public static String CantBuildMsg;
	public static boolean TpAfterAccept;
	public static boolean AllowBuild;
	public static Location TpPosition;
	public static String TpWorld;
	public static ArrayList<Player> readed = new ArrayList<Player>();
	private AcceptRulesPlayerListener pListener = new AcceptRulesPlayerListener(this);
	private AcceptRulesBlockListener bListener = new AcceptRulesBlockListener(this);
	

	
	
	//@Override
	public void onDisable() {
		Log.info("[AcceptRules] AcceptRules plugin succesfully disabled!");
	}
	//@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		AcceptRulesPreferences acceptrulespreferences = new AcceptRulesPreferences();
		acceptrulespreferences.createDir();
	
		config = getConfiguration();
		AcceptedMsg = config.getString("AcceptedMsg", "You have succesfully accepted the rules! Have fun!");
		AcceptedAllreadyMsg = config.getString("AcceptedAllreadyMsg", "You have allready accepted the rules!");
		CantBuildMsg = config.getString("CantBuildMsg", "You must accept rules to build!");
		MustReadRules = config.getString("MustReadRules", "You must read the rules in order to accept them!");
		TpAfterAccept = config.getBoolean("TpAfterAccept", false);
		AllowBuild = config.getBoolean("AllowBuildBeforeAccept", false);
		TpWorld = config.getString("TpWorld", "world");		
		Double PosX = config.getDouble("TpPositionX", 0);
		Double PosY = config.getDouble("TpPositionY", 0);
		Double PosZ = config.getDouble("TpPositionZ", 0);
		World world =  Bukkit.getServer().getWorld(TpWorld);
		Location location = new Location(world, PosX, PosY, PosZ);
		TpPosition = location;			
		config.save();
		AcceptRulesPreferences.UserReader();

		pm.registerEvent(Event.Type.PLAYER_COMMAND_PREPROCESS, pListener, Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_PLACE, bListener, Priority.Normal, this);
		pm.registerEvent(Event.Type.BLOCK_BREAK, bListener, Priority.Normal, this);
		
		getCommand("acceptrules").setExecutor(new acceptrulesCmdExecutor());
		Log.info("[AcceptRules] AcceptRules plugin succesfully enabled!");
	}
	
	public static void savePosToConfig(String world, double x, double y, double z) {
	    //Save
		config.setProperty("TpWorld", world);
	    config.setProperty("TpPositionX", x);
	    config.setProperty("TpPositionY", y);
	    config.setProperty("TpPositionZ", z);	    
	    config.save();
	    //Reload
	    config = new Configuration(new File("plugins/AcceptRules/config.yml"));
	    config.load();
	    TpWorld = config.getString("TpWorld", "world");		
		Double PosX = config.getDouble("TpPositionX", 0);
		Double PosY = config.getDouble("TpPositionY", 0);
		Double PosZ = config.getDouble("TpPositionZ", 0);
		World worldd =  Bukkit.getServer().getWorld(TpWorld);
		Location location = new Location(worldd, PosX, PosY, PosZ);
		TpPosition = location;			
	    
	}
	
}
