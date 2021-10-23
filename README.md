# BukkitReflectionUtil
Utility to make working with NMS code easier on Spigot. Compatible with Minecraft pre-1.16 and post-1.17

[![Maven Central](https://img.shields.io/maven-central/v/dev.array21/bukkit-reflection-util.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22dev.array21%22%20AND%20a:%22bukkit-reflection-util%22)
[![Build Status](https://drone.k8s.array21.dev/api/badges/TheDutchMC/BukkitReflectionUtil/status.svg)](https://drone.k8s.array21.dev/TheDutchMC/BukkitReflectionUtil)

## Installation
BukkitReflectionUtil is available on MavenCentral:
```groovy
repositories {
    mavenCentral()
}

dependencies {
    implementation 'dev.array21:bukkit-reflection-util:VERSION'
}
```

## Notes about 1.17
BukkitReflectionUtil works with both pre-1.17 and post-1.17. However there are some things you should pay attention to.  
As of 1.17, Spigot does not flatten `net.minecraft` anymore. You could solve it like this:
```java
import dev.array21.bukkitreflectionutil.ReflectionUtil;

Class<?> entityHumanClass;
if(ReflectionUtil.isUseNewSpigotPackaging()) {
    // >= Minecraft 1.17
    entityHumanClass = ReflectionUtil.getMinecraftClass("world.entity.player.EntityHuman");
} else {
    // =< Minecraft1.16
    // This method is also marked as @Deprecated !
    entityHumanClass = ReflectionUtil.getNmsClass("EntityHuman");
}
```
