/**
 * This file is part of mythicdrops-hilt, licensed under the MIT License.
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
package io.pixeloutlaw.minecraft.spigot.mythicdrops.hilt.items;

import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.meta.BlockStateMeta;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class HiltBlockState extends HiltItemStack {

    private static final Set<Material> MATERIALS_WITH_BLOCKSTATES = parseAndConvertToSetOfMaterials(
            "FURNACE",
            "CHEST",
            "TRAPPED_CHEST",
            "JUKEBOX",
            "DISPENSER",
            "DROPPER",
            "SIGN",
            "MOB_SPAWNER",
            "NOTE_BLOCK",
            "PISTON_BASE",
            "BREWING_STAND_ITEM",
            "ENCHANTMENT_TABLE",
            "COMMAND",
            "COMMAND_REPEATING",
            "COMMAND_CHAIN",
            "BEACON",
            "DAYLIGHT_DETECTOR",
            "DAYLIGHT_DETECTOR_INVERTED",
            "HOPPER",
            "REDSTONE_COMPARATOR",
            "FLOWER_POT_ITEM",
            "SHIELD",
            "STRUCTURE_BLOCK",
            "WHITE_SHULKER_BOX",
            "ORANGE_SHULKER_BOX",
            "MAGENTA_SHULKER_BOX",
            "LIGHT_BLUE_SHULKER_BOX",
            "YELLOW_SHULKER_BOX",
            "LIME_SHULKER_BOX",
            "PINK_SHULKER_BOX",
            "GRAY_SHULKER_BOX",
            "SILVER_SHULKER_BOX",
            "CYAN_SHULKER_BOX",
            "PURPLE_SHULKER_BOX",
            "BLUE_SHULKER_BOX",
            "BROWN_SHULKER_BOX",
            "GREEN_SHULKER_BOX",
            "RED_SHULKER_BOX",
            "BLACK_SHULKER_BOX"
    );

    public HiltBlockState(Material material, BlockState blockState) {
        super(material);
        Preconditions.checkNotNull(material);
        Preconditions.checkNotNull(blockState);
        Preconditions.checkArgument(canMaterialHaveBlockState(material));
        setBlockState(blockState);
    }

    private static Set<Material> parseAndConvertToSetOfMaterials(String... keys) {
        return Sets.immutableEnumSet(
                Arrays.stream(keys)
                        .map(HiltBlockState::parseMaterial)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toList())
        );
    }

    private static Optional<Material> parseMaterial(String key) {
        try {
            return Optional.of(Enum.valueOf(Material.class, key));
        } catch (Exception ignored) {
            // do nothing here, we want to use the empty Optional<Material> if it can't be parsed
            return Optional.empty();
        }
    }

    public BlockState getBlockState() {
        createItemMeta();
        if (getItemMeta() instanceof BlockStateMeta && ((BlockStateMeta) getItemMeta()).hasBlockState()) {
            return ((BlockStateMeta) getItemMeta()).getBlockState();
        }
        return null;
    }

    public HiltBlockState setBlockState(BlockState blockState) {
        createItemMeta();
        if (getItemMeta() instanceof BlockStateMeta) {
            ((BlockStateMeta) getItemMeta()).setBlockState(blockState);
        }
        return this;
    }

    private boolean canMaterialHaveBlockState(Material material) {
        return MATERIALS_WITH_BLOCKSTATES.contains(material);
    }

}
