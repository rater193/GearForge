# GearForge

A Minecraft mod for creating custom gear and tools, built with NeoForge for Minecraft 1.21.1.

## Features

This mod is currently in early development. More features will be added soon!

## Building

To build the mod, you need:
- Java 21 or later
- No additional setup required (Gradle wrapper is included)

### Build Commands

On Linux/macOS:
```bash
./gradlew build
```

On Windows:
```cmd
gradlew.bat build
```

The built mod jar will be located in `build/libs/`.

## Development

### Running the Mod

To run the mod in a development environment:

**Client:**
```bash
./gradlew runClient
```

**Server:**
```bash
./gradlew runServer
```

**Data Generation:**
```bash
./gradlew runData
```

### Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/rater193/gearforge/
│   │       ├── GearForge.java     # Main mod class
│   │       └── Config.java        # Configuration options
│   └── resources/
│       ├── META-INF/
│       │   └── neoforge.mods.toml # Mod metadata
│       ├── data/                  # Data pack (recipes, loot tables, etc.)
│       └── pack.mcmeta            # Resource pack metadata
```

## License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.