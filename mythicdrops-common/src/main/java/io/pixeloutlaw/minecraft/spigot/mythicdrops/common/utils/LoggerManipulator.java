package io.pixeloutlaw.minecraft.spigot.mythicdrops.common.utils;

import org.slf4j.event.Level;

public interface LoggerManipulator {

    void setLoggerLevel(Level level, String... loggers);

}
