package fr.MrJuJu0319.commande;

import fr.MrJuJu0319.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SgiveCommand implements CommandExecutor {

    private final Main plugin;

    public SgiveCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            if (args.length < 3) {
                sender.sendMessage(plugin.getMessage("usage_sgive"));
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(plugin.getMessage("player_not_online"));
                return true;
            }

            ItemStack item;
            try {
                Material material = Material.matchMaterial(args[1].toUpperCase());
                if (material == null) {
                    sender.sendMessage(plugin.getMessage("invalid_item"));
                    return true;
                }
                int amount = Integer.parseInt(args[2]);
                item = new ItemStack(material, amount);
            } catch (IllegalArgumentException e) {
                sender.sendMessage(plugin.getMessage("invalid_item"));
                return true;
            }

            target.getInventory().addItem(item);
        } else {
            sender.sendMessage(plugin.getMessage("console_only"));
        }
        return true;
    }
}
