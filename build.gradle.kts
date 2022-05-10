plugins {
    java
    application
    kotlin("jvm") version "1.6.21"
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

group = "dev.vdbroek"
version = "0.0.1"

application {
    mainClass.set("$group.GitLinkKt")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("dev.kord:kord-core:0.8.0-M13")
    implementation("com.sksamuel.hoplite:hoplite-core:2.1.4")
    implementation("com.sksamuel.hoplite:hoplite-hocon:2.1.4")

    implementation("org.slf4j:slf4j-simple:1.7.36")
//    implementation("ch.qos.logback:logback-core:1.2.11")
//    implementation("ch.qos.logback:logback-classic:1.2.11")

    testImplementation(kotlin("test"))
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(JavaVersion.VERSION_17.toString()))
    }
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = JavaVersion.VERSION_17.toString()
            freeCompilerArgs += listOf(
                "-opt-in=kotlin.RequiresOptIn"
            )
        }
    }

    test {
        useJUnitPlatform()
    }
}
