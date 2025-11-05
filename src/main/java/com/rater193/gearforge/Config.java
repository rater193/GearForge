package com.rater193.gearforge;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    
    // Example configuration options
    public static final ModConfigSpec.BooleanValue EXAMPLE_BOOLEAN = BUILDER
            .comment("This is an example boolean configuration option")
            .define("exampleBoolean", true);
    
    public static final ModConfigSpec.IntValue EXAMPLE_INT = BUILDER
            .comment("This is an example integer configuration option")
            .defineInRange("exampleInt", 42, 0, 100);
    
    public static final ModConfigSpec SPEC = BUILDER.build();
}
