package com.wyattteeter.SimpleChannels;

import java.io.File;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class SimpleChannels extends JavaPlugin implements Listener {
	
	private static final Logger log = Logger.getLogger("channelchat");
	private static File configFile = null;
	
	public static FileConfiguration configConfiguration = null;
	public static SimpleChannels plugin;
	public static Set<String> channels; 
	
	public static Object config;
	
	@Override
	public void onDisable() {
		log.info(this + " is now disabled.");
	}

	@Override
	public void onEnable() {
		plugin = this;
		this.saveDefaultConfig();
		loadConfig();
		
		this.getCommand("sc").setExecutor(new Commands());
	}
	
	// Loads configuration in from file
	public void loadConfig() {
		if (configConfiguration == null) {
			if (configFile == null) {
				configFile = new File(getDataFolder(), "config.yml");
			}
			configConfiguration = YamlConfiguration.loadConfiguration(configFile);
		}	
		
		channels = configConfiguration.getConfigurationSection("channels").getKeys(false);
		if (channels.isEmpty() || !channels.contains("default")) {
			channels.add("default");
		}
	}

	// Sends requested message to the requested channel
	public static void sendMessage(String channel, String message, String sender) {
		String channelPerm = "simplechannels." + channel;
		
		// Pull in channel-specific configuration
		String prefix = configConfiguration.getString("channels." + channel + ".prefix", "&4Chat&8 ");
		String chatcolor = configConfiguration.getString("channels." + channel + ".chatcolor", "&e");
		String seperator = configConfiguration.getString("channels." + channel + ".seperator", ": ");
		Boolean hideChannelName = configConfiguration.getBoolean("channels." + channel + ".hideChannelName", false);
		
		message = ChatColor.translateAlternateColorCodes('&', message);
		
		// For each player, send them the message if they have the permission
		for (Player player : Bukkit.getOnlinePlayers()) { 
			if (player.hasPermission(channelPerm)) {
				
				String chatMessage;
				if (hideChannelName) {
					chatMessage = prefix + sender + seperator + chatcolor + message;
				} else {
					chatMessage = prefix + "<" + channel + "> " + sender + seperator + chatcolor + message;
				}
				
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', chatMessage));
			}
		}
	}
}
