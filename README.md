# Minecraft Insane Difficulty

<div align="center">
  <img src="https://img.shields.io/github/v/release/aaronrai24/MinecraftInsaneDifficulty" alt="GitHub Release"/>
  <img src="https://img.shields.io/github/actions/workflow/status/aaronrai24/MinecraftInsaneDifficulty/release.yml" alt="GitHub Workflow Status"/>
</div>

- [Minecraft Insane Difficulty](#minecraft-insane-difficulty)
  - [Introduction](#introduction)
  - [Installation](#installation)
    - [Method 1: Using Docker(Recommended)](#method-1-using-dockerrecommended)
    - [Method 2: Manual Installation](#method-2-manual-installation)
  - [Usage](#usage)
  - [Contributing](#contributing)

## Introduction
This is a Minecraft plugin that makes the game harder by adjusting mob spawning, item usage, and other game mechanics. 
This plugin is designed to be used on a server, the goal is to make the game more challenging while keeping the same vanilla
feel. The end goal is to beat the Ender Dragon in under 1.5 hours. Can you do it?

## Installation

### Method 1: Using Docker(Recommended)
1. Install Docker on your machine, for a guide on how to do this visit [docker.com](https://docs.docker.com/get-docker/)

2. Clone the [SpigotDocker repository](https://github.com/aaronrai24/SpigotDocker) with the plugin baked in by running the following command:

```bash
git clone https://github.com/aaronrai24/SpigotDocker.git
```

3. Navigate to the SpigotDocker directory and run the following command to build the Docker image:

```bash
docker-compose up --build
```
4. Start the server and connect to it using the domain provided by ngrok. You can obtain the domain viewing the logs of the ngrok container:
 
  ```bash
  docker logs ngrok
  ```
  It should look something like this:
`X.tcp.ngrok.io:YYYY`

5. Connect to the server using the domain provided by ngrok, this domain is accessible from anywhere in the world too!

### Method 2: Manual Installation
1. Download the latest release from the [releases page](https://github.com/aaronrai24/MinecraftInsaneDifficulty/releases)
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
- Minecraft version 1.21

## LICENSE
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.