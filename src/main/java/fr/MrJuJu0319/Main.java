package fr.MrJuJu0319;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import fr.MrJuJu0319.commande.SgiveCommand;
import fr.MrJuJu0319.commande.SecoCommand;
import fr.MrJuJu0319.commande.ReloadConfigCommand;
import com.earth2me.essentials.Essentials;

import java.io.File;

public class Main extends JavaPlugin {

    private Essentials essentials;
    private FileConfiguration messagesConfig;
    private boolean debugMode;

    @Override
    public void onEnable() {
        getLogger().info("Lancement en cours ...");
        // Charge la configuration par défaut et les messages sans recharger entièrement
        saveDefaultConfig(); // Assure que config.yml est chargé ou créé s'il n'existe pas
        loadConfigValues(); // Charge seulement les valeurs nécessaires de config.yml
        loadMessages(); // Charge les messages depuis messages.yml

        getLogger().info("Mode debug " + (debugMode ? "activé" : "désactivé"));

        // Initialisation d'Essentials
        essentials = (Essentials) getServer().getPluginManager().getPlugin("Essentials");
        if (essentials != null) {
            getLogger().info("EssentialsX a été trouvé et initialisé.");
        } else {
            getLogger().warning("EssentialsX n'est pas trouvé ou n'est pas bien installé.");
        }

        // Enregistrement des commandes
        registerCommands();

        getLogger().info("Lancement terminé.");
    }

    private void loadConfigValues() {
        debugMode = getConfig().getBoolean("debug", false); // Charge la valeur du mode debug
    }

    private void registerCommands() {
        //Commande /sgive
        this.getCommand("sgive").setExecutor(new SgiveCommand(this));

        //Commande /seco si Essentials est OK
        if (essentials != null) {
            this.getCommand("seco").setExecutor(new SecoCommand(essentials, this));
        }
        //Commande /sreloadconfig
        this.getCommand("sreloadconfig").setExecutor(new ReloadConfigCommand(this));
    }


    public boolean isDebugMode() {
        return debugMode;
    }
    @Override
    public void onDisable() {
        getLogger().info("Disabled");
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

    @Override
    public void reloadConfig() {
        super.reloadConfig(); // Appel à la méthode parent pour recharger config.yml
        debugMode = getConfig().getBoolean("debug", false); // Mise à jour du mode debug
        reloadMessages(); // Rechargement des messages.yml
    }
    public void reloadMessages() {
        File messagesFile = new File(getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            saveResource("messages.yml", false);
        }
        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
    }


}
