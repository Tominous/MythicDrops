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
package io.pixeloutlaw.minecraft.spigot.mythicdrops.core.loaders.tiers;

import com.google.inject.assistedinject.Assisted;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.items.MythicEnchantment;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers.MythicTierEnchantments;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.loaders.AbstractConfigurateMythicLoader;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.core.MythicDropsPlugin;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.SimpleConfigurationNode;
import ninja.leaping.configurate.Types;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

public final class MythicTierEnchantmentsLoader extends AbstractConfigurateMythicLoader<MythicTierEnchantments> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MythicTierEnchantmentsLoader.class);

    @Inject
    public MythicTierEnchantmentsLoader(MythicDropsPlugin plugin, @Assisted String fileName) {
        super(plugin, fileName);
    }

    @Override
    public MythicTierEnchantments load() {
        LOGGER.debug("Loading tier enchantments from {}", fileName);
        ConfigurationNode configurationNode = SimpleConfigurationNode.root();
        try {
            configurationNode = fileConfigurationLoader.load();
        } catch (IOException e) {
            // do nothing :)
        }
        return MythicTierEnchantments.builder()
                .minimumBonusEnchantments(configurationNode.getNode("enchantments", "minimumBonusEnchantments").getInt(0))
                .maximumBonusEnchantments(configurationNode.getNode("enchantments", "maximumBonusEnchantments").getInt(0))
                .baseEnchantments(convertNodeToMythicEnchantments(configurationNode.getNode("enchantments", "baseEnchantments")))
                .bonusEnchantments(convertNodeToMythicEnchantments(configurationNode.getNode("enchantments", "bonusEnchantments")))
                .build();
    }

    @Override
    public void save(MythicTierEnchantments mythicTierEnchantments) {
        ConfigurationNode configurationNode = SimpleConfigurationNode.root();
        try {
            configurationNode = fileConfigurationLoader.load();
        } catch (IOException e) {
            // do nothing :)
        }
        ConfigurationNode enchantmentsNode = configurationNode.getNode("enchantments");
        enchantmentsNode.getNode("minimumBonusEnchantments").setValue(mythicTierEnchantments.minimumBonusEnchantments());
        enchantmentsNode.getNode("maximumBonusEnchantments").setValue(mythicTierEnchantments.maximumBonusEnchantments());
        enchantmentsNode.getNode("baseEnchantments").setValue(mythicTierEnchantments.baseEnchantments());
        enchantmentsNode.getNode("bonusEnchantments").setValue(mythicTierEnchantments.bonusEnchantments());
        try {
            fileConfigurationLoader.save(configurationNode);
        } catch (IOException e) {
            LOGGER.debug("Unable to save tier enchantments to {}", fileName);
        }
    }

    private Collection<MythicEnchantment> convertNodeToMythicEnchantments(ConfigurationNode node) {
        return node.getList(Types::asString).stream()
                .map(MythicEnchantment::builder)
                .map(MythicEnchantment.Builder::build)
                .collect(Collectors.toList());
    }

}