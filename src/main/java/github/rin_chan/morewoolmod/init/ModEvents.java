package github.rin_chan.morewoolmod.init;

import github.rin_chan.morewoolmod.MoreWoolMod;
import github.rin_chan.morewoolmod.entity.animal.CustomSheep;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MoreWoolMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {
	@SubscribeEvent
	public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
		event.put(ModEntities.CUSTOM_SHEEP.get(), CustomSheep.setAttributes());
	}
}
