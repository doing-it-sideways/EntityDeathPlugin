# Entity Death Replication Hytale Plugin
The eventual goal of this project is to provide my friends with a plugin that can broadcast information about player deaths and specific entity deaths to the server (e.g. someone has a NPC naming plugin and names an npc, someone kills a big monster or something), where info about that death will be printed to the text chat.

This is still a <code style="color: cyan">Work In Progress</code> and code will likely be split into an death notifier API plugin alongside the actual reporting plugin you see here.

# Contents
- [Server Intallation Guide](#server-installation-guide)
    - [Dependencies](#dependencies)
- [Configuration](#configuration)
- [Future Goals](#future-goals)
- [Special Thanks](#special-thanks)

## Server Installation Guide
### Dependencies:
- [TinyMessage](https://github.com/Zoltus/TinyMessage/)

1. Download the [latest release](https://github.com/doing-it-sideways/EntityDeathPlugin/releases).
2.
    - For your world: Place the .jar inside `{Your Hytale Folder}/UserData/Mods`
    - For your server: Place the .jar inside `{Your Server Folder}/mods`
3. The mod will now be usable with the default configuration, enjoy!
4. (Optional) If you would like to change the configuration of how the mod behaves:
    - For your world: Go to `{Your Hytale Folder}/UserData/Saves/New World/mods/Sideways_EntityDeathPlugin` and edit the `EntityDeathConfig.json` file.
    - For your server: Go to `{Your Server Folder}/mods/Sideways_EntityDeathPlugin` and edit the `EntityDeathConfig.json` file.
5. (Optional) To reload the plugin with your configuration changes, type `/plugin reload Sidways:EntityDeathPlugin`
    - Note: Reloading may not work for a singleplayer world, so simply exit and re-enter the world.

## Configuration
When the plugin is loaded on a server/world for the first time, the configuration file will be generated (see: [Server Installation Guide](#server-installation-guide), Step 4)

The default configuration will look like this:
```json
{
  "PlayerBroadcastToOtherPlayers": true,
  "PlayerShowDeathCoordinates": true,
  "NPCBroadcastDeathMessage": false,
  "NPCShowDeathCoordinates": true
}
```

- `PlayerBroadcastToOtherPlayers`: (Player Death) Decides on whether to broadcast the message to all players on the server or just to the player who died.
    - Default: true
- `PlayerShowDeathCoordinates`: (Player Death) Shows coordinates of where a player died in the message.
    - Default: true

The other two settings are currently non-functional.

## Future Goals
I would like to make this somewhat extensible and maybe split up the core tracking logic into some API plugin that other plugins can use. For now the mod remains very basic, with only player deaths functioning.

## Special Thanks
Credit to Zoltus for making [TinyMessage](https://github.com/Zoltus/TinyMessage/) and allowing me to easily format messages. Because Hytale lacks a plugin repo as of now, the library is included directly in /libs/

Additionally, check out the [Hytale modding community](https://hytalemodding.dev/en)!