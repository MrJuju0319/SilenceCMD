package fr.MrJuJu0319.commande;

import fr.MrJuJu0319.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

public class ReloadConfigCommand implements CommandExecutor {
    private Main plugin;

    public ReloadConfigCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            plugin.reloadConfig(); // Cela rechargera config.yml et messages.yml
            sender.sendMessage(ChatColor.GREEN + "Configuration et messages rechargés.");

        }
        return true;
    }

}

