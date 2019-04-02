rootProject.name = "mythicdropskt"

gradle.allprojects {
    group = "io.pixeloutlaw.minecraft.spigot"
    version = "420.0.0-SNAPSHOT"

    repositories {
        mavenCentral()
        jcenter()
        maven {
            url = uri("https://hub.spigotmc.org/nexus/content/groups/public")
        }
        maven { url = uri("https://jitpack.io") }
    }
}

include("hilt", "mythicdrops")