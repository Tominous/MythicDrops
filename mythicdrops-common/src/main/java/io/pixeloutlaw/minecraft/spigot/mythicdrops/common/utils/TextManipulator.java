package io.pixeloutlaw.minecraft.spigot.mythicdrops.common.utils;

import com.google.common.base.Preconditions;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.collections.CaselessMap;
import org.bukkit.ChatColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.stringtemplate.v4.ST;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A utility class for modifying text and lists of text.
 */
public final class TextManipulator {

  private static final Logger LOGGER = LoggerFactory.getLogger(TextManipulator.class);
  private static final Map<String, Object> COLOR_MAP_TEMPLATE_READY = new CaselessMap<>();

  static {
    for (ChatColor cc : ChatColor.values()) {
      String name = cc.name();
      COLOR_MAP_TEMPLATE_READY.put(name, cc + "");
      COLOR_MAP_TEMPLATE_READY.put(name.replace("_", " "), cc + "");
      COLOR_MAP_TEMPLATE_READY.put(name.replace("_", ""), cc + "");
    }
    LOGGER.debug("Template ready color tokens ({}): [{}]",
        COLOR_MAP_TEMPLATE_READY.size(), String.join(", ", COLOR_MAP_TEMPLATE_READY.keySet()));
  }

  @Inject
  public TextManipulator() {
    // don't need to do anything here
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
    return template(pString, COLOR_MAP_TEMPLATE_READY);
  }

  public List<String> template(List<String> pList, Map<String, Object> values) {
    Preconditions.checkNotNull(pList, "pList cannot be null");
    Preconditions.checkNotNull(values, "values cannot be null");
    return pList.stream().map(s -> template(s, values)).collect(Collectors.toList());
  }

  public List<String> template(List<String> pList, Map<String, Object> values, int maximumLength) {
    Preconditions.checkNotNull(pList, "pList cannot be null");
    Preconditions.checkNotNull(values, "values cannot be null");
    return pList.stream().map(s -> template(s, values, maximumLength)).collect(Collectors.toList());
  }

  public String template(String pTemplate, Map<String, Object> values) {
    Preconditions.checkNotNull(pTemplate, "pTemplate cannot be null");
    Preconditions.checkNotNull(values, "values cannot be null");
    ST template = new ST(pTemplate);
    joinMaps(values, COLOR_MAP_TEMPLATE_READY).forEach(template::add);
    return template.render();
  }

  public String template(String pTemplate, Map<String, Object> values, int maximumLength) {
    Preconditions.checkNotNull(pTemplate, "pString cannot be null");
    Preconditions.checkNotNull(values, "values cannot be null");
    ST template = new ST(pTemplate);
    values.forEach(template::add);
    return template.render(maximumLength);
  }

  private <K, V> Map<K, V> joinMaps(Map<K, V> mapOne, Map<K, V> mapTwo) {
    return Stream.of(mapOne, mapTwo)
        .map(Map::entrySet) // converts each map into an entry set
        .flatMap(Collection::stream) // converts each set into an entry stream
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)); // concatenates into map
  }

}
