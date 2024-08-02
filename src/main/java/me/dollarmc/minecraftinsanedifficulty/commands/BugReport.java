package me.dollarmc.minecraftinsanedifficulty.commands;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.Label;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.IssueService;

import me.dollarmc.minecraftinsanedifficulty.utilities.Config;

public class BugReport implements CommandExecutor {

    private static final Logger LOGGER = LogManager.getLogger(BugReport.class);

    /**
     * This method listens for the /reportbug command.
     * When the command is executed, this method will create a new issue on the
     * GitHub repository with the specified title and body.
     *
     * @param sender  The command sender
     * @param command The command
     * @param label   The command label
     * @param args    The command arguments
     * @return true if the command was executed successfully, false otherwise
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String title = args[0];
        String body = String.join(" ", java.util.Arrays.copyOfRange(args, 1, args.length));

        Config config = new Config();
        String token = config.getGithubToken();
        String username = config.getGithubUsername();
        String repo = config.getGithubRepo();
        GitHubClient client = new GitHubClient();
        client.setOAuth2Token(token);
        IssueService issueService = new IssueService(client);

        try {
            Issue issue = new Issue();
            issue.setTitle(title);
            issue.setBody(body);

            List<Label> labels = Arrays.asList(new Label().setName("bug"));
            issue.setLabels(labels);

            issueService.createIssue(username, repo, issue);
            LOGGER.info("Creating issue with title: {} and body: {}", title, body);
            sender.sendMessage("Issue created successfully.");
            return true;
        } catch (Exception e) {
            sender.sendMessage("An error occurred while creating the issue: " + e.getMessage());
            LOGGER.error("An error occurred while creating the issue: {}", e.getMessage());
            return false;
        }
    }
}