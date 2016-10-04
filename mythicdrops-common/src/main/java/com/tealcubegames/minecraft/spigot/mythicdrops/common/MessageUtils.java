package com.tealcubegames.minecraft.spigot.mythicdrops.common;

import com.google.common.base.Preconditions;
import com.tealcube.minecraft.bukkit.TextUtils;
import org.bukkit.command.CommandSender;

/**
 * A utility class for sending non-fancy messages to players.
 */
public final class MessageUtils {

    private MessageUtils() {
        // do nothing
    }

    /**
     * Sends a message to the given player after replacing any color codes.
     *
     * @param sender  Player or Console
     * @param message message to send
     */
    public static void sendMessage(CommandSender sender, String message) {
        Preconditions.checkNotNull(sender, "sender cannot be null");
        Preconditions.checkNotNull(message, "message cannot be null");
        sender.sendMessage(TextUtils.color(message));
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
    public static void sendMessage(CommandSender sender, String message, String[][] args) {
        Preconditions.checkNotNull(sender, "sender cannot be null");
        Preconditions.checkNotNull(message, "message cannot be null");
        Preconditions.checkNotNull(args, "args cannot be null");
        sender.sendMessage(TextUtils.color(TextUtils.args(message, args)));
    }

}
