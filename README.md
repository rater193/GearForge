````markdown name=README.md
```markdown
# GearForge (Starter)

A starter Neoforge/Forge-style mod for Minecraft 1.21.1 that adds:

- Gear Infuser: unlocks items so they gain XP when used (weapons on hit, armor on receive damage).
- Upgrade Station: spend gear points to purchase procedurally generated upgrades from card-style packs.
- NBT-backed gear data (xp, level, gear points, upgrades) so data survives mod reloads.

This repository contains a starter implementation with server and client scaffolding, resources, and placeholder textures. It is configured as a Gradle project; update the build configuration to use Neoforge mappings and tooling for 1.21.1.

How to build
-----------

1. Install required Forge/Neoforge toolchain for 1.21.1 and configure gradle accordingly.
2. Run `./gradlew build` after configuring the toolchain and dependencies.

Notes
-----
- This is a starter project: some client-side UI (card reveal animations and networking) are included conceptually; you may need to adjust mappings for Neoforge and register client event handlers.
- Replace placeholder textures in `src/main/resources/assets/gearforge/textures` with your own art.

License: MIT
```
``` 
