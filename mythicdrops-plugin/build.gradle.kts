import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.exclude
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow") version "4.0.1"
}

dependencies {
    implementation("org.spigotmc:spigot-api:1.13.1-R0.1-SNAPSHOT")
    implementation(kotlin("stdlib-jdk8"))
    implementation(project(":hilt"))
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
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

tasks.findByName("build")?.dependsOn("shadowJar")