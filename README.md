# Minecraft Insane Difficulty

- [Minecraft Insane Difficulty](#minecraft-insane-difficulty)
  - [Introduction](#introduction)
  - [Installation](#installation)
  - [Usage](#usage)
  - [Contributing](#contributing)

## Introduction
This is a Minecraft plugin that makes the game harder by adjusting mob spawning, item usage, and other game mechanics. 
This plugin is designed to be used on a server, the goal is to make the game more challenging while keeping the same vanilla
feel. The end goal is to beat the Ender Dragon in under 1.5 hours. Can you do it?

## Installation
1. Download the latest release from the [releases page](insert github link)
2. Download a plugin capable server such as [Spigot](https://getbukkit.org/download/spigot)
3. Place the jar file in the plugins folder of your server
4. Enable port forwarding on your router and port forward port 25565 to your server, for a guide on how to do this visit [portforward.com](https://portforward.com/)
5. Start the server(agree to EULA) and connect to it using your public IP address

## Usage
The plugin is designed to be used on a server, the server owner can configure their server like any other Minecraft server
by tweaking the server.properties file. The server difficulty should be set to hard.

## Contributing
If you would like to contribute to this project please fork the repository and submit a pull request. For this repository:
- I used the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html) for code style. 
- Maven is used for dependency management and building the project.
- Spigot is used for the Minecraft server API. JavaDocs can be found [here](https://hub.spigotmc.org/javadocs/spigot/overview-summary.html)
- Java version 21 is used for this project.
- Minecraft version 1.20.4

## LICENSE
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.