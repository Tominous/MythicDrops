/*
 * The MIT License
 * Copyright © 2013 Pixel Outlaw
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package io.pixeloutlaw.minecraft.spigot.mythicdrops.common.utils;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.Map;

/**
 * A utility class for modifying text and lists of text.
 */
public interface TextManipulator {

    ChatColor findFirstColor(String pString);

    String removeFirstColors(String pString);

    String keepFirstColors(String pString);

    /**
     * Returns a colored copy of the passed-in {@code List<String>}.
     *
     * @param pList List to color
     * @return colored copy of passed-in List
     */
    List<String> color(List<String> pList);

    /**
     * Returns a colored copy of the passed-in String.
     *
     * @param pString String to color
     * @return colored copy of passed-in String
     */
    String color(String pString);

    List<String> template(List<String> pList, Map<String, Object> values);

    List<String> template(List<String> pList, Map<String, Object> values, int maximumLength);

    String template(String pTemplate, Map<String, Object> values);

    String template(String pTemplate, Map<String, Object> values, int maximumLength);

}
