plugins {
    id("java")
}

group = "fr.MrJuJu0319"
version = "1.0"

repositories {
    mavenCentral()
    mavenLocal()
    maven { url = uri("https://repo.essentialsx.net/releases/") } // EssentialsX API
    maven { url = uri("https://repo.papermc.io/repository/maven-public/") } // Paper API
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.1-R0.1-SNAPSHOT") // Paper API
    compileOnly("net.essentialsx:EssentialsX:2.20.1") // EssentialsX API
    // Dépendance Velocity pour les fonctionnalités supplémentaires, si nécessaire
    compileOnly("com.velocitypowered:velocity-api:3.3.0-SNAPSHOT")
    annotationProcessor("com.velocitypowered:velocity-api:3.3.0-SNAPSHOT")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.compileJava {
    options.encoding = "UTF-8"
}

tasks.compileTestJava {
    options.encoding = "UTF-8"
}

// Enregistrement explicite de la tâche de copie
tasks.register<Copy>("copyJar") {
    dependsOn(tasks.jar)
    from(tasks.jar.get().archiveFile)
    into("./server/plugins")
}

tasks.named("build") {
    dependsOn(tasks.named("copyJar"))
}
