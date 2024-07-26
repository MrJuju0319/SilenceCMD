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
import me.clip.placeholderapi.PlaceholderAPI;


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

            String playerName = args[0];
            Player target = Bukkit.getPlayer(playerName);
            if (target == null) {
                sender.sendMessage(plugin.getMessage("player_not_online"));
                return true;
            }

            String parsedItemName = PlaceholderAPI.setPlaceholders(target, args[1].toUpperCase());
            Material material = Material.matchMaterial(parsedItemName);
            if (material == null) {
                sender.sendMessage(plugin.getMessage("invalid_item"));
                return true;
            }

            int amount = Integer.parseInt(args[2]);
            ItemStack item = new ItemStack(material, amount);
            target.getInventory().addItem(item);

            if (plugin.isDebugMode()) {
                String logMessage = plugin.getMessage("received_item_log")
                        .replace("%player%", target.getName())
                        .replace("%item%", item.getType().toString())
                        .replace("%quantity%", String.valueOf(item.getAmount()));
                plugin.getLogger().info(logMessage);
            }
        } else {
            sender.sendMessage(plugin.getMessage("console_only"));
        }
        return true;
    }
}