import com.diffplug.gradle.spotless.SpotlessExtension

plugins {
    base
    kotlin("jvm") version Versions.org_jetbrains_kotlin_jvm_gradle_plugin apply false
    id("nebula.project") version Versions.nebula_project_gradle_plugin
    id("com.diffplug.gradle.spotless") version Versions.com_diffplug_gradle_spotless_gradle_plugin apply false
    id("jmfayard.github.io.gradle-kotlin-dsl-libs") version Versions.jmfayard_github_io_gradle_kotlin_dsl_libs_gradle_plugin
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
        maven { url = uri("https://jitpack.io") }
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

        this.tasks.findByPath("compileKotlin")?.dependsOn("spotlessKotlinApply")
    }
}
