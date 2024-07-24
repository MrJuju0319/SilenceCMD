package fr.MrJuJu0319;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import fr.MrJuJu0319.commande.SgiveCommand;
import fr.MrJuJu0319.commande.SecoCommand;
import com.earth2me.essentials.Essentials;

import java.io.File;

public class Main extends JavaPlugin {

    private Essentials essentials;
    private FileConfiguration messagesConfig;

    @Override
    public void onEnable() {
        getLogger().info("[SilenceCMD]: Lancement en cours . . .");

        // Charger le fichier de messages
        loadMessages();

        // Initialiser Essentials
        if (getServer().getPluginManager().getPlugin("Essentials") instanceof Essentials) {
            essentials = (Essentials) getServer().getPluginManager().getPlugin("Essentials");
            getLogger().info("[SilenceCMD]: EssentialsX a été trouvé et initialisé.");
        } else {
            getLogger().warning("[SilenceCMD]: EssentialsX n'est pas trouvé ou n'est pas bien installé.");
        }
        getLogger().info("[SilenceCMD]: Lancement terminé");

        // Enregistrer la commande /sgive
        this.getCommand("sgive").setExecutor(new SgiveCommand(this));

        // Enregistrer la commande /seco
        if (essentials != null) {
            this.getCommand("seco").setExecutor(new SecoCommand(essentials, this));
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("[SilenceCMD]: Disabled");
    }

    private void loadMessages() {
        File messagesFile = new File(getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            messagesFile.getParentFile().mkdirs();
            saveResource("messages.yml", false);
        }
        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
    }

    public String getMessage(String key) {
        return messagesConfig.getString("messages." + key, "Message non trouvé: " + key);
    }
}
