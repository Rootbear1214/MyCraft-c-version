## Copy vanilla textures into the mod (so you can edit them)

Run these from the **hellobrick-template-1.21.11** folder.

### Chicken Chaser skin (entity)
```sh
mkdir -p src/main/resources/assets/hellobrick/textures/entity/chicken
unzip -p ~/.gradle/caches/fabric-loom/1.21.11/minecraft-client.jar \
  assets/minecraft/textures/entity/chicken/temperate_chicken.png \
  > src/main/resources/assets/hellobrick/textures/entity/chicken/chicken_chaser.png
```

### Item icons (inventory)
```sh
mkdir -p src/main/resources/assets/hellobrick/textures/item

# Chicken Chaser Egg icon (copied from vanilla egg)
unzip -p ~/.gradle/caches/fabric-loom/1.21.11/minecraft-client.jar \
  assets/minecraft/textures/item/egg.png \
  > src/main/resources/assets/hellobrick/textures/item/chicken_chaser_egg.png

# Cluster Bomb icon (copied from vanilla TNT side)
unzip -p ~/.gradle/caches/fabric-loom/1.21.11/minecraft-client.jar \
  assets/minecraft/textures/block/tnt_side.png \
  > src/main/resources/assets/hellobrick/textures/item/cluster_bomb.png

# Hello Brick icon (reuse your existing block texture as a starting point)
cp src/main/resources/assets/hellobrick/textures/block/hello_brick.png \
  src/main/resources/assets/hellobrick/textures/item/hello_brick.png
```

### Block textures (in-world)
Cluster Bomb block textures are currently set up to be mod-local too.
```sh
mkdir -p src/main/resources/assets/hellobrick/textures/block

unzip -p ~/.gradle/caches/fabric-loom/1.21.11/minecraft-client.jar \
  assets/minecraft/textures/block/tnt_bottom.png \
  > src/main/resources/assets/hellobrick/textures/block/cluster_bomb_bottom.png

unzip -p ~/.gradle/caches/fabric-loom/1.21.11/minecraft-client.jar \
  assets/minecraft/textures/block/tnt_side.png \
  > src/main/resources/assets/hellobrick/textures/block/cluster_bomb_side.png

unzip -p ~/.gradle/caches/fabric-loom/1.21.11/minecraft-client.jar \
  assets/minecraft/textures/block/tnt_top.png \
  > src/main/resources/assets/hellobrick/textures/block/cluster_bomb_top.png
```

### After copying
- Restart the client, or press `F3 + T` in-game to reload assets.
