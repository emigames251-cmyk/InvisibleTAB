# InvisibleTab (Fabric 1.21.11)

Mod serveur Fabric : les joueurs qui ont l'effet vanilla `minecraft:invisibility` sont automatiquement retirés de la liste TAB. Leur entrée TAB revient quand l'effet disparaît.

## Compatibilité visée
- Minecraft Java 1.21.11
- Fabric Loader 0.18.1 ou supérieur
- Fabric API 0.141.6+1.21.11
- Java 21

## Construction du JAR
Ce dossier contient le projet source, pas un JAR compilé. Pour produire le JAR, il faut une installation Gradle et une connexion Internet afin que Loom télécharge les dépendances :

```bash
gradle build
```

Le fichier final sera dans `build/libs/` (prendre le JAR sans `-sources` ni `-dev`).

## Installation
Copier le JAR compilé dans le dossier `mods` du serveur FalixNodes, vérifier que Fabric API est également dans `mods`, puis redémarrer le serveur.

Aucun mod n'est requis côté client.

## Remarque
Le mod masque uniquement l'entrée du joueur dans le TAB. Il ne rend pas le joueur invisible dans le monde : c'est l'effet de potion vanilla qui gère cela.

## Build sans installer Java/Gradle sur votre Mac (GitHub Actions)
Le dossier contient aussi `.github/workflows/build.yml`. Si vous importez le projet dans un dépôt GitHub, l'onglet **Actions** permet de lancer **Build InvisibleTab**. À la fin, téléchargez l'artefact `InvisibleTab-Fabric-1.21.11` : il contient le JAR à placer dans `mods`.
