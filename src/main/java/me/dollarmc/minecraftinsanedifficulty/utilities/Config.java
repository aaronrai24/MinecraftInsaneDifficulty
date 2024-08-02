package me.dollarmc.minecraftinsanedifficulty.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private Properties properties = new Properties();

    public Config() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns the GitHub token from the config.properties file.
     * 
     * @return The GitHub token.
     */
    public String getGithubToken() {
        return properties.getProperty("github.token");
    }

    /**
     * This method returns the GitHub username from the config.properties file.
     * 
     * @return The GitHub username.
     */
    public String getGithubUsername() {
        return properties.getProperty("github.username");
    }

    /**
     * This method returns the GitHub repository from the config.properties file.
     * 
     * @return The GitHub repository.
     */
    public String getGithubRepo() {
        return properties.getProperty("github.repo");
    }
}
