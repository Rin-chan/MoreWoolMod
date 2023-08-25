package github.rin_chan.morewoolmod.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class MoreWoolModCommonConfigs {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> MAX_SHEEP_SIZE;

    static {
        BUILDER.push("Configs for More Wool Mod");

        MAX_SHEEP_SIZE = BUILDER.comment("Maximum size of sheep")
                .defineInRange("Max Size", 20, 1, 101);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
