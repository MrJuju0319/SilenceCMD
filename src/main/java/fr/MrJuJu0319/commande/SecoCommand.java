package fr.MrJuJu0319.commande;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;
import me.clip.placeholderapi.PlaceholderAPI;
import fr.MrJuJu0319.Main;
import net.ess3.api.MaxMoneyException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.math.BigDecimal;

public class SecoCommand implements CommandExecutor {

    private final Essentials essentials;
    private final Main plugin;

    public SecoCommand(Essentials essentials, Main plugin) {
        this.essentials = essentials;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof ConsoleCommandSender)) {
            sender.sendMessage(plugin.getMessage("console_only"));
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage(plugin.getMessage("usage_seco"));
            return true;
        }

        // Utiliser PlaceholderAPI pour résoudre le nom du joueur si nécessaire
        String playerName = PlaceholderAPI.setPlaceholders(null, args[0]);
        User user = essentials.getUser(playerName);

        if (user == null) {
            sender.sendMessage(plugin.getMessage("player_not_found"));
            return true;
        }

        try {
            BigDecimal amount = new BigDecimal(args[1]);
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                sender.sendMessage(plugin.getMessage("positive_amount"));
                return true;
            }

            BigDecimal currentBalance = user.getMoney();
            user.setMoney(currentBalance.add(amount));
            if (plugin.isDebugMode()) {
                String logMessage = plugin.getMessage("received_money_log")
                        .replace("%player%", playerName)
                        .replace("%amount%", amount.toString());
                plugin.getLogger().info(logMessage);

            }
        } catch (NumberFormatException e) {
            sender.sendMessage(plugin.getMessage("invalid_amount"));
        } catch (MaxMoneyException e) {
            sender.sendMessage(plugin.getMessage("max_money_reached"));
        } catch (Exception e) {
            sender.sendMessage(plugin.getMessage("unknown_error") + e.getMessage());
        }

        return true;
    }
}
