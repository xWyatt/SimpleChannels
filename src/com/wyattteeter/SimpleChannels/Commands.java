package com.wyattteeter.SimpleChannels;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
		
		String cmd = command.getName().toLowerCase();
		
		if (cmd.equals("sc")) {
			if (args.length == 0) {
				if (!sender.hasPermission("simplechannels.default")) {
					sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to perform this action");
					return true;
				} else {
					sender.sendMessage(ChatColor.DARK_RED + "simplechannels: /sc [channel] <message>");
					return true;
				}
			}

			// Lists all channels (and appropriate perms)
			else if ((args.length == 1) && (args[0].equals("list"))) {
				if (!sender.hasPermission("simplechannels.list")) {
					sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to perform this action");
					return true;
				} else {
				
					sender.sendMessage(ChatColor.GRAY + "List of channels and associated permissions:");
					for (String channel : SimpleChannels.channels) {
						sender.sendMessage(ChatColor.GRAY + "Channel: " + ChatColor.DARK_GRAY + channel + " (simplechannels." + channel + ")");
					}
					
					return true;
				}
			}

			// Reload handler
			else if ((args.length == 1) && (args[0].equals("reload"))) {
				if (!sender.hasPermission("simplechannels.reload")) {
					sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to perform this action");
					return true;
				} else {
					SimpleChannels.configConfiguration = SimpleChannels.plugin.getConfig();
					SimpleChannels.plugin.loadConfig();
					sender.sendMessage(ChatColor.GREEN + "Configuration files have been reloaded!");
					return true;
				}
			}

			// Chat Handler
			else {
				String channel = args[0];
				Boolean channelExists = false;
				if (SimpleChannels.channels.contains(channel)) {
					channelExists = true;
				}
				
				// Verify channel permission
				if (channelExists && !sender.hasPermission("simplechannels." + channel)) {
					sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to the " + channel + " channel");
					return true;
				}
				
				// Form message
				String message = "";
				Integer index = 0;
				
				// Skip channel name if provided
				if (channelExists) {
					index = 1;
				} else {
					channel = "default";
				}
				
				for (Integer i = index ; i < args.length; i++) {
					message += args[i] + " ";
				}
				
				// Send message
				SimpleChannels.sendMessage(channel, message, sender.getName());
				
				return true;
			}
		}
		return false;
	}
}
