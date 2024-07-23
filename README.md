# SilenceCMD

SilenceCMD est un plugin Minecraft permettant aux administrateurs de donner des objets et de l'argent aux joueurs via des commandes exécutées depuis la console. Il utilise l'API EssentialsX et PaperMC.

## Fonctionnalités

- Ajouter des objets à l'inventaire d'un joueur avec la commande `/sgive`
- Ajouter de l'argent au solde d'un joueur avec la commande `/seco`

## Prérequis

- Minecraft 1.20.1+
- EssentialsX 2.20.1+ (Pour la commande /seco)
- PaperMC 1.20.1+
- Java 21

## Installation

1. Téléchargez le plugin EssentialsX et placez-le dans le dossier `plugins` de votre serveur Minecraft.
2. Téléchargez le plugin SilenceCMD et placez-le également dans le dossier `plugins` de votre serveur Minecraft.
3. Redémarrez votre serveur Minecraft.

## Commandes

### `/sgive`

- **Description**: Donne un objet au joueur spécifié.
- **Usage**: `/sgive <player> <item> <amount>`
- **Exécution**: Doit être exécutée depuis la console.

### `/seco`

- **Description**: Ajoute de l'argent au solde du joueur spécifié.
- **Usage**: `/seco <player> <amount>`
- **Exécution**: Doit être exécutée depuis la console.

## Développement

### Prérequis

- Java 21
- Gradle

### Compilation

1. Clonez ce dépôt : `git clone <URL_DU_DEPOT>`
2. Naviguez dans le répertoire du projet : `cd SilenceCMD`
3. Compilez le projet avec Gradle : `./gradlew build`
4. Le fichier JAR généré se trouvera dans le répertoire `build/libs`.

## Contributions

Les contributions sont les bienvenues ! Veuillez soumettre un pull request pour toute modification ou amélioration.

