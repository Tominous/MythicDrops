import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import io.pixeloutlaw.minecraft.spigot.Versions
import org.apache.tools.ant.filters.ReplaceTokens
import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.exclude
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("kapt")
    id("com.github.johnrengelman.shadow") version "4.0.1"
}

//java.sourceSets

kotlin {
    sourceSets {
        create("templates") {
            output.dir("$buildDir/templates")
        }
        this["main"].withConvention(KotlinSourceSet::class) {
            kotlin.srcDir("$buildDir/templates")
        }
    }
}

dependencies {
    implementation("org.spigotmc:spigot-api:${Versions.SPIGOT}")
    implementation("org.spigotmc:plugin-annotations:1.2.2-SNAPSHOT")
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":hilt"))

    kapt("org.spigotmc:plugin-annotations:1.2.2-SNAPSHOT")
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

tasks.register("processTemplates", Copy::class) {
    val tokens = mapOf("VERSION" to project.version, "NAME" to "MythicDrops")

    from(sourceSets.getByName("templates").allSource)
    into(sourceSets.getByName("templates").output.dirs.asPath)
    filter<ReplaceTokens>("tokens" to tokens)
}

tasks.withType(ShadowJar::class) {
    classifier = ""
    dependencyFilter.apply {
        include(project(":hilt"))
        include(dependency("org.jetbrains.kotlin:kotlin-stdlib-jdk8"))
        include(dependency("org.jetbrains.kotlin:kotlin-stdlib-jdk7"))
        include(dependency("org.jetbrains.kotlin:kotlin-stdlib"))
        include(dependency("org.jetbrains.kotlin:kotlin-stdlib-common"))
        include(dependency("org.jetbrains:annotations"))
    }
}

tasks.findByName("compileKotlin")?.dependsOn("processTemplates")
tasks.findByName("build")?.dependsOn("shadowJar")