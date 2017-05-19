package io.pixeloutlaw.minecraft.spigot.mythicdrops.common.utils;

import com.google.common.base.Preconditions;
import org.bukkit.command.CommandSender;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A utility class for sending non-fancy messages to players.
 */
public final class MessageUtils {

  private final TextManipulator textManipulator;

  @Inject
  public MessageUtils(TextManipulator textManipulator) {
    this.textManipulator = textManipulator;
  }

  public TextManipulator getTextManipulator() {
    return textManipulator;
  }

  /**
   * Sends a message to the given player after replacing any color codes.
   *
   * @param sender  Player or Console
   * @param message message to send
   */
  public void sendMessage(CommandSender sender, String message) {
    Preconditions.checkNotNull(sender, "sender cannot be null");
    Preconditions.checkNotNull(message, "message cannot be null");
    sender.sendMessage(textManipulator.color(message));
  }

  /**
   * Sends a message to the given player after replacing any color codes and replacing any tokens in the message.
   * <p>
   * Example:
   * {@code MessageUtils.sendMessage(player, "<red>Your name is %name%!", new String[][]{{"%name%", player.getName()})}
   *
   * @param sender  Player or Console
   * @param message message to send
   * @param args    tokens to replace and their values
   */
  public void sendMessage(CommandSender sender, String message, String[][] args) {
    Preconditions.checkNotNull(sender, "sender cannot be null");
    Preconditions.checkNotNull(message, "message cannot be null");
    Preconditions.checkNotNull(args, "args cannot be null");
    sendMessage(sender, message, Arrays.stream(args)
        .filter(strings -> strings.length >= 2)
        .collect(Collectors.toMap(e -> e[0], e -> e[1])));
  }

  public void sendMessage(CommandSender sender, String message, Map<String, Object> args) {
    Preconditions.checkNotNull(sender, "sender cannot be null");
    Preconditions.checkNotNull(message, "message cannot be null");
    Preconditions.checkNotNull(args, "args cannot be null");
    sender.sendMessage(textManipulator.template(message, args));
  }

}
