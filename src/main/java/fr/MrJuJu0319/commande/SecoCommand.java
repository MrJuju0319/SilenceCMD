package fr.MrJuJu0319.commande;

import com.earth2me.essentials.Essentials;
import com.earth2me.essentials.User;
import net.ess3.api.MaxMoneyException;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.math.BigDecimal;

public class SecoCommand implements CommandExecutor {

    private final Essentials essentials;

    public SecoCommand(Essentials essentials) {
        this.essentials = essentials;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof ConsoleCommandSender)) {
            sender.sendMessage("Cette commande ne peut être exécutée que depuis la console.");
            return true;
        }

        if (args.length < 2) {
            sender.sendMessage("Usage: /seco <player> <amount>");
            return true;
        }

        User user = essentials.getUser(args[0]);
        if (user == null) {
            sender.sendMessage("Le joueur spécifié n'existe pas.");
            return true;
        }

        try {
            // Convertir la quantité en BigDecimal
            BigDecimal amount = new BigDecimal(args[1]);
            if (amount.compareTo(BigDecimal.ZERO) <= 0) {
                sender.sendMessage("La quantité doit être un nombre positif.");
                return true;
            }

            // Obtenez l'argent actuel du joueur
            BigDecimal currentBalance = user.getMoney();

            // Ajoutez l'argent au joueur sans déclencher le message de confirmation
            user.setMoney(currentBalance.add(amount));
        } catch (NumberFormatException e) {
            sender.sendMessage("La quantité spécifiée est invalide. Assurez-vous que la quantité est un nombre.");
        } catch (MaxMoneyException e) {
            sender.sendMessage("Le joueur a atteint la limite maximale d'argent. L'argent n'a pas été ajouté.");
        } catch (Exception e) {
            sender.sendMessage("Une erreur inconnue est survenue : " + e.getMessage());
        }

        return true;
    }
}
