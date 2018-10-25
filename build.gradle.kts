import com.diffplug.gradle.spotless.SpotlessExtension

plugins {
    base
    kotlin("jvm") version "1.2.61" apply false
    id("nebula.project") version "5.1.2"
    id("com.diffplug.gradle.spotless") version "3.15.0" apply false
}

allprojects {
    group = "io.pixeloutlaw.minecraft.spigot"
    version = "420.0.0-SNAPSHOT"

    repositories {
        mavenCentral()
        jcenter()
        maven {
            url = uri("https://hub.spigotmc.org/nexus/content/groups/public")
        }
        maven {
            url = uri("https://artifactory.pixeloutlaw.io/artifactory/public")
        }
    }
}

subprojects {
    afterEvaluate {
        this.plugins.apply("com.diffplug.gradle.spotless")
        configure<SpotlessExtension> {
            kotlin {
                ktlint()
            }
        }
    }
}
