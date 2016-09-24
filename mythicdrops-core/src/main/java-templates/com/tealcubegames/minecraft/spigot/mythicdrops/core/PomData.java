package com.tealcubegames.minecraft.spigot.mythicdrops.core;

/**
 * Class that contains data from the POM.
 * @author Richard Harrah
 */
public final class PomData {

    public static final String ARTIFACT_ID = "${project.artifactId}";
    public static final String GROUP_ID = "${project.groupId}";
    public static final String VERSION = "${project.version}-${git.commit.id.abbrev}";

    private PomData() {
        // do nothing
    }

}
