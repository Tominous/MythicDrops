/**
 * This file is part of mythicdrops-core, licensed under the MIT License.
 *
 * Copyright (C) 2016 - 2017 Pixel Outlaw <topplethenun@pixeloutlaw.io>
 *
 * Permission is hereby granted, free of charge,
 * to any person obtaining a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.pixeloutlaw.minecraft.spigot.mythicdrops.core.utils;

import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.utils.LoggerManipulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class LoggerManipulatorImpl implements LoggerManipulator {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerManipulatorImpl.class);
    private static final List<String> ROOT_LOGGER_LIST = Collections.singletonList(Logger.ROOT_LOGGER_NAME);

    @Inject
    public LoggerManipulatorImpl() {
        // don't need to do anything here
    }

    @Override
    public void setLoggerLevel(org.slf4j.event.Level level, String... loggers) {
        List<String> loggersToSet = loggers.length > 0 ? Arrays.asList(loggers) : ROOT_LOGGER_LIST;
        ch.qos.logback.classic.Level levelToSet = slf4jToLogback(level);
        LOGGER.debug("Changing the logging level to {} for loggers ({}): [{}]",
                levelToSet.levelStr, loggersToSet.size(), String.join(", ", loggersToSet));
        loggersToSet.stream()
                .map(LoggerFactory::getLogger)
                .map(ch.qos.logback.classic.Logger.class::cast)
                .forEach(logger -> logger.setLevel(levelToSet));
    }

    private ch.qos.logback.classic.Level slf4jToLogback(org.slf4j.event.Level level) {
        switch (level) {
            case ERROR:
                return ch.qos.logback.classic.Level.ERROR;
            case WARN:
                return ch.qos.logback.classic.Level.WARN;
            case INFO:
                return ch.qos.logback.classic.Level.INFO;
            case DEBUG:
                return ch.qos.logback.classic.Level.DEBUG;
            default:
                return ch.qos.logback.classic.Level.TRACE;
        }
    }

    private org.slf4j.event.Level logbackToSlf4j(ch.qos.logback.classic.Level level) {
        if (level == ch.qos.logback.classic.Level.ERROR) {
            return org.slf4j.event.Level.ERROR;
        }
        if (level == ch.qos.logback.classic.Level.WARN) {
            return org.slf4j.event.Level.WARN;
        }
        if (level == ch.qos.logback.classic.Level.INFO) {
            return org.slf4j.event.Level.INFO;
        }
        if (level == ch.qos.logback.classic.Level.DEBUG) {
            return org.slf4j.event.Level.DEBUG;
        }
        return org.slf4j.event.Level.TRACE;
    }

}
