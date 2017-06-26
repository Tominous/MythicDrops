package io.pixeloutlaw.minecraft.spigot.mythicdrops.core.loaders.tiers;

import com.google.common.io.Files;
import com.google.inject.assistedinject.Assisted;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.loaders.LoaderManager;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers.MythicTier;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers.MythicTierEnchantments;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.api.tiers.MythicTierLore;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.loaders.AbstractConfigurateMythicLoader;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.common.loaders.MythicLoader;
import io.pixeloutlaw.minecraft.spigot.mythicdrops.core.MythicDropsPlugin;
import ninja.leaping.configurate.ConfigurationNode;
import ninja.leaping.configurate.SimpleConfigurationNode;
import org.bukkit.ChatColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Implementation of MythicLoader for loading MythicTiers.
 */
public final class MythicTierLoader extends AbstractConfigurateMythicLoader<MythicTier> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MythicTierLoader.class);
    private MythicLoader<MythicTierEnchantments> mythicTierEnchantmentsLoader;
    private MythicLoader<MythicTierLore> mythicTierLoreLoader;

    @Inject
    public MythicTierLoader(MythicDropsPlugin plugin, LoaderManager loaderManager, @Assisted String fileName) {
        super(plugin, fileName);
        this.mythicTierEnchantmentsLoader = loaderManager.createMythicTierEnchantmentsLoader(fileName);
        this.mythicTierLoreLoader = loaderManager.createMythicTierLoreLoader(fileName);
    }

    @Override
    public MythicTier load() {
        LOGGER.debug("Loading tier from {}", fileName);
        MythicTier.Builder builder = MythicTier.builder();
        ConfigurationNode configurationNode = SimpleConfigurationNode.root();
        try {
            configurationNode = fileConfigurationLoader.load();
        } catch (IOException e) {
            // do nothing :)
        }
        String key = Files.getNameWithoutExtension(this.fileName);
        builder = builder.key(key)
                .displayName(configurationNode.getNode("displayName").getString(key))
                .displayColor(ChatColor.valueOf(configurationNode.getNode("displayColor").getString("WHITE")))
                .identifierColor(ChatColor.valueOf(configurationNode.getNode("identifierColor").getString("WHITE")))
                .itemTypes(new ArrayList<>()) // TODO: actually get the item types
                .broadcastOnFind(configurationNode.getNode("broadcastOnFind").getBoolean(true))
                .infiniteDurability(configurationNode.getNode("infiniteDurability").getBoolean(false))
                .minimumDurabilityPercentage(configurationNode.getNode("minimumDurabilityPercentage").getDouble(100))
                .maximumDurabilityPercentage(configurationNode.getNode("maximumDurabilityPercentage").getDouble(100))
                .chanceToSpawnOnAMonster(configurationNode.getNode("chanceToSpawnOnAMonster").getDouble(0))
                .chanceToDropOnMonsterDeath(configurationNode.getNode("chanceToDropOnMonsterDeath").getDouble(100))
                .mythicTierEnchantments(mythicTierEnchantmentsLoader.load())
                .mythicTierLore(mythicTierLoreLoader.load());
        //  TODO: UNCOMMENT THE BELOW WHEN MYTHICTIERCOMPONENTLOADERS WORK
        //        List<MythicTierComponent> componentList = loaderManager.getMythicTierComponentLoaders().stream()
        //                .map(MythicLoader::load)
        //                .collect(Collectors.toList());
        //        builder = builder.components(componentList);
        return builder.build();
    }

    @Override
    public void save(MythicTier mythicTier) {
        ConfigurationNode configurationNode = SimpleConfigurationNode.root();
        try {
            configurationNode = fileConfigurationLoader.load();
        } catch (IOException e) {
            // do nothing :)
        }
        configurationNode.getNode("displayName").setValue(mythicTier.displayName());
        configurationNode.getNode("displayColor").setValue(mythicTier.displayColor().name());
        configurationNode.getNode("identifierColor").setValue(mythicTier.identifierColor().name());
        configurationNode.getNode("broadcastOnFind").setValue(mythicTier.broadcastOnFind());
        configurationNode.getNode("infiniteDurability").setValue(mythicTier.infiniteDurability());
        configurationNode.getNode("minimumDurabilityPercentage").setValue(mythicTier.minimumDurabilityPercentage());
        configurationNode.getNode("maximumDurabilityPercentage").setValue(mythicTier.maximumDurabilityPercentage());
        configurationNode.getNode("chanceToSpawnOnAMonster").setValue(mythicTier.chanceToSpawnOnAMonster());
        configurationNode.getNode("chanceToDropOnMonsterDeath").setValue(mythicTier.chanceToDropOnMonsterDeath());
        mythicTierEnchantmentsLoader.save(mythicTier.mythicTierEnchantments());
        mythicTierLoreLoader.save(mythicTier.mythicTierLore());
        //  TODO: UNCOMMENT THE BELOW WHEN MYTHICTIERCOMPONENTLOADERS WORK
        //  mythicTier.components().forEach(mythicTierComponent -> mythicTierComponent.loader().save(mythicTierComponent));
        try {
            fileConfigurationLoader.save(configurationNode);
        } catch (IOException e) {
            LOGGER.debug("Unable to save tier enchantments to {}", fileName);
        }
    }

}