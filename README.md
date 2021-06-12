# BukkitReflectionUtil
Utility to make working with NMS code easier on Spigot

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
    // Minecraft 1.17+
    entityHumanClass = ReflectionUtil.getMinecraftClass("world.entity.player.EntityHuman");
} else {
    // Minecraft 1.16-
    // This method is also marked as @Depricated !
    entityHumanClass = ReflectionUtil.getNmsClass("EntityHuman");
}
```