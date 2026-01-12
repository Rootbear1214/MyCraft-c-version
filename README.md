# MyCraft

This repo is a from-zero “hello world” introduction to building Minecraft Java mods using **Fabric**.

Reference: https://fabricmc.net

## 0) Pick versions (important)

Minecraft versioning is a little confusing. Minecraft releases are typically like `1.21`, `1.21.1`, etc.

In the Fabric template generator, pick:

- **Minecraft Version**: choose the **latest available** in the dropdown (the closest match to what you’re running)
- **Loader Version**: latest
- **Fabric API Version**: latest

If you think you’re on “1.21.11”, you likely mean **1.21.1**. If you tell me the exact version shown in your Minecraft launcher, we can align it precisely.

## 1) Install prerequisites (macOS)

- **Java (JDK)**
  - Install **JDK 21** (recommended for modern Minecraft).
  - Verify:
    - `java -version`
- **IDE**
  - IntelliJ IDEA Community or Ultimate.

## 2) Generate the Fabric mod project (creates all files)

1. Open the Fabric website and go to the **Fabric mod development** / **Template generator**.
2. Fill in:
   - **Mod Name**: `MyCraft`
   - **Mod ID**: `mycraft`
   - **Package**: something like `com.example.mycraft` (you can change it later, but it’s easiest to decide now)
3. Download the generated ZIP.

## 3) Put the generated project into this repo

This repo currently only has this `README.md`.

1. Unzip the downloaded template.
2. Copy the contents **into this repo folder** (`MyCraft/`).
   - After this, you should see files like:
     - `build.gradle` (or `build.gradle.kts`)
     - `settings.gradle`
     - `gradle.properties`
     - `src/main/java/...`
     - `src/main/resources/fabric.mod.json`
     - `gradlew`, `gradlew.bat`, and `gradle/wrapper/...`

## 4) Open in IntelliJ

1. Open IntelliJ.
2. **Open** the repo folder (`MyCraft/`).
3. When prompted, **Import as Gradle project**.
4. Wait for Gradle sync and dependency download.

## 5) Run Minecraft in dev (client)

From the project root:

- `./gradlew runClient`

This should launch a Minecraft client with your mod loaded.

If this fails:

- Make sure you installed **JDK 21** and IntelliJ/Gradle is using it.

## 6) Our first mod feature: “Hello Brick” block

Goal: create a placeable block (a “brick”) that sends a chat message to the player who right-clicks it.

Once the template is in place, we will:

- Add a custom block class (override `onUse`)
- Send a message only on the server side
- Register the block + block item so it appears in Creative inventory

### What I need from you next

After you copy the generated template into this repo, tell me:

- The exact **Minecraft Version** you picked in the generator
- Whether the generated project uses `build.gradle` or `build.gradle.kts`

Then I’ll implement the Hello Brick code directly in the repo.
