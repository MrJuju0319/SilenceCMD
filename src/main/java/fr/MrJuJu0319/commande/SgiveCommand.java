package fr.MrJuJu0319.commande;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SgiveCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender) {
            if (args.length < 3) {
                sender.sendMessage("Usage: /sgive <player> <item> <amount>");
                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage("Le joueur n'est pas en ligne.");
                return true;
            }

            ItemStack item;
            try {
                Material material = Material.valueOf(args[1].toUpperCase()); // Convertit le nom de l'élément en matériau
                int amount = Integer.parseInt(args[2]); // Convertit la quantité en entier
                item = new ItemStack(material, amount);
            } catch (IllegalArgumentException e) {
                sender.sendMessage("L'élément spécifié est invalide. Assurez-vous que le nom de l'élément est correct.");
                return true;
            }

            // Ajoute l'objet à l'inventaire du joueur
            target.getInventory().addItem(item);

        } else {
            sender.sendMessage("Cette commande ne peut être exécutée que depuis la console.");
        }
        return true;
    }
}
