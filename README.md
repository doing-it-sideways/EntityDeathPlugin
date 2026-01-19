# Entity Death Replication Hytale Plugin
The eventual goal of this project is to provide my friends with a plugin that can broadcast information about player deaths and specific entity deaths to the server (e.g. someone has a NPC naming plugin and names an npc, someone kills a big monster or something), where info about that death will be printed to the text chat.

## Future Goals:
I would like to make this somewhat extensible and maybe split up the core tracking logic into some API plugin that other plugins can use. For now the mod remains very basic, with only player deaths functioning.

## Thanks
Credit to Zoltus for making [TinyMessage](https://github.com/Zoltus/TinyMessage/) and allowing me to easily format messages. Because Hytale lacks a plugin repo as of now, the library is included directly in /libs/

Additionally, check out the [Hytale modding community](https://hytalemodding.dev/en)!