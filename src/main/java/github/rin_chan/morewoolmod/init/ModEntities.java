package github.rin_chan.morewoolmod.init;

import github.rin_chan.morewoolmod.MoreWoolMod;
import github.rin_chan.morewoolmod.entity.animal.CustomSheep;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = MoreWoolMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MoreWoolMod.MODID);

	public static RegistryObject<EntityType<CustomSheep>> CUSTOM_SHEEP = ENTITY_TYPES.register("custom_sheep", 
			() -> EntityType.Builder.of(CustomSheep::new, MobCategory.CREATURE)
			.sized(1f, 1f)
			.build(new ResourceLocation(MoreWoolMod.MODID, "custom_sheep").toString()));
	
	public static void register(IEventBus eventBus) {
		ENTITY_TYPES.register(eventBus);
	}
}
