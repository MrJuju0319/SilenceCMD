package fr.MrJuJu0319;

// Commandes
import fr.MrJuJu0319.commande.SgiveCommand;
import fr.MrJuJu0319.commande.SecoCommand;

// Dépendances
import com.earth2me.essentials.Essentials;

// Autres
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private Essentials essentials;

    @Override
    public void onEnable() {
        getLogger().info("[SilenceCMD]: V1.0 est OK");

        // Enregistrer la commande /sgive
        this.getCommand("sgive").setExecutor(new SgiveCommand());

        // Initialiser Essentials
        if (getServer().getPluginManager().getPlugin("Essentials") instanceof Essentials) {
            essentials = (Essentials) getServer().getPluginManager().getPlugin("Essentials");
            getLogger().info("EssentialsX a été trouvé et initialisé.");
        } else {
            getLogger().warning("EssentialsX n'est pas trouvé ou n'est pas bien installé.");
        }

        // Enregistrer la commande /seco
        if (essentials != null) {
            this.getCommand("seco").setExecutor(new SecoCommand(essentials));
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("[SilenceCMD]: Disabled");
    }
}
