/**
 * This file is part of mythicdrops-common, licensed under the MIT License.
 *
 * Copyright (C) 2016 Pixel Outlaw <topplethenun@pixeloutlaw.io>
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
package io.pixeloutlaw.minecraft.spigot.mythicdrops.common.utils;

import com.google.common.base.Preconditions;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.collections.CaselessMap;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.ChatColor;
import org.stringtemplate.v4.ST;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A utility class for modifying text and lists of text.
 */
public final class TextManipulator {

    private static final Map<String, ChatColor> COLOR_MAP = new CaselessMap<>();

    static {
        for (ChatColor cc : ChatColor.values()) {
            String name = cc.name();
            COLOR_MAP.put(name, cc);
            COLOR_MAP.put(name.replace("_", " "), cc);
            COLOR_MAP.put(name.replace("_", ""), cc);
        }
    }

    @Inject
    public TextManipulator() {
        // don't need to do anything here
    }

    public ChatColor convertTag(String string) {
        for (Map.Entry<String, ChatColor> entry : COLOR_MAP.entrySet()) {
            if (entry.getKey().equals(string)) {
                return entry.getValue();
            }
        }
        return null;
    }

    public String convertTag(ChatColor chatColor) {
        for (Map.Entry<String, ChatColor> entry : COLOR_MAP.entrySet()) {
            if (entry.getValue() == chatColor) {
                return entry.getKey();
            }
        }
        return null;
    }

    public ChatColor findFirstColor(String pString) {
        Preconditions.checkNotNull(pString, "pString cannot be null");
        char[] c = pString.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '\u00A7' && i + 1 < c.length) {
                return ChatColor.getByChar(c[i + 1]);
            }
        }
        return null;
    }

    public String removeFirstColors(String pString) {
        Preconditions.checkNotNull(pString, "pString cannot be null");
        char[] c = pString.toCharArray();
        int index = 0;
        for (int i = 0; i < c.length; i += 2) {
            if (c[i] == '\u00A7' && i + 1 < c.length) {
                index = i + 2;
            }
        }
        char[] retArray = new char[c.length - index];
        System.arraycopy(c, index, retArray, 0, retArray.length);
        return new String(retArray);
    }

    public String keepFirstColors(String pString) {
        Preconditions.checkNotNull(pString, "pString cannot be null");
        char[] c = pString.toCharArray();
        int index = 0;
        for (int i = 0; i < c.length; i += 2) {
            if (c[i] == '\u00A7' && i + 1 < c.length) {
                index = i + 2;
            }
        }
        char[] retArray = new char[index];
        System.arraycopy(c, 0, retArray, 0, index);
        return new String(retArray);
    }

    /**
     * Returns a colored copy of the passed-in {@code List<String>}.
     *
     * @param pList List to color
     * @return colored copy of passed-in List
     */
    public List<String> color(List<String> pList) {
        Preconditions.checkNotNull(pList, "pList cannot be null");
        return pList.stream().map(this::color).collect(Collectors.toList());
    }

    /**
     * Returns a colored copy of the passed-in String.
     *
     * @param pString String to color
     * @return colored copy of passed-in String
     */
    public String color(String pString) {
        Preconditions.checkNotNull(pString, "pString cannot be null");
        return template(pString,
                COLOR_MAP.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue() + "")));
    }

    public String template(String pTemplate, Map<String, Object> values) {
        Preconditions.checkNotNull(pTemplate, "pTemplate cannot be null");
        Preconditions.checkNotNull(values, "values cannot be null");
        ST template = new ST(pTemplate);
        values.forEach(template::add);
        return template.render();
    }

    public String template(String pTemplate, Map<String, Object> values, int maximumLength) {
        Preconditions.checkNotNull(pTemplate, "pString cannot be null");
        Preconditions.checkNotNull(values, "values cannot be null");
        ST template = new ST(pTemplate);
        values.forEach(template::add);
        return template.render(maximumLength);
    }

}
