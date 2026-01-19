I’ll turn this empty repo into a working Fabric mod project and use [README.md](cci:7://file:///Users/satoshi/Downloads/MyCraft/README.md:0:0-0:0) as the step-by-step guide you can follow. Next I’ll read the current README, then I’ll update it to include: prerequisites (JDK), how to generate the Fabric project, how to import into IntelliJ, how to run the dev client, and then we’ll add the “Hello Brick” block that sends a chat message on right-click.




## Next step (you do this once)

You now have the checklist in [README.md](cci:7://file:///Users/satoshi/Downloads/MyCraft/README.md:0:0-0:0). The repo still doesn’t have the actual Fabric project files, so **you need to generate + copy in the Fabric template ZIP** first.

Do this:

1. Go to https://fabricmc.net and open the **Template Generator**.
2. Choose:
   - **Minecraft Version**: the newest in the dropdown (ideally matches what your launcher shows; “1.21.11” is probably **1.21.1**)
   - **Loader**: latest
   - **Fabric API**: latest
3. Fill:
   - **Mod Name**: [MyCraft](cci:7://file:///Users/satoshi/Downloads/MyCraft:0:0-0:0)
   - **Mod ID**: `mycraft`
   - **Package**: pick one now, e.g. `com.satoshi.mycraft` (anything is fine, but keep it stable)
4. Download the ZIP, unzip it, and copy its contents into this repo folder ([/Users/satoshi/Downloads/MyCraft/](cci:7://file:///Users/satoshi/Downloads/MyCraft:0:0-0:0)).

After you do that, tell me these two things (or paste the contents of `src/main/resources/fabric.mod.json`):

- **The exact Minecraft version** you picked in the generator dropdown
- Whether the template created **`build.gradle`** or **`build.gradle.kts`**

## What I’ll do immediately after

- Add a `HelloBrickBlock` that overrides right-click (`onUse`) and sends a chat message to the player
- Register the block + its item so it’s placeable and appears in Creative

## Status

- **README**: updated with the full walkthrough
- **Implementation**: blocked until the Fabric template files exist in the repo