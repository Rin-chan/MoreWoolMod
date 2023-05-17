package github.rin_chan.morewoolmod;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import github.rin_chan.morewoolmod.client.render.CustomSheepRenderer;
import github.rin_chan.morewoolmod.entity.animal.CustomSheep;
import github.rin_chan.morewoolmod.init.ModEntities;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MoreWoolMod.MODID)
public class MoreWoolMod {
	public static final String MODID = "morewoolmod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public MoreWoolMod()
    {
        MinecraftForge.EVENT_BUS.register(this);
        
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModEntities.register(modEventBus);
    }
    
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
    	@SubscribeEvent
    	public static void onEntityRenderSetup(EntityRenderersEvent.RegisterRenderers event) {
    		event.registerEntityRenderer(ModEntities.CUSTOM_SHEEP.get(), CustomSheepRenderer::new);
    	}
    }
    
    @SubscribeEvent
    public void OnEntityJoinLevel(EntityJoinLevelEvent event)
    {
    	if ((event.getEntity() instanceof Sheep) && !(event.getEntity() instanceof CustomSheep))
    	{
    		event.setCanceled(true);
    		
    		CustomSheep spawnEntity = new CustomSheep(ModEntities.CUSTOM_SHEEP.get(), event.getLevel());
    		spawnEntity.setPos(event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ());
    		event.getLevel().addFreshEntity(spawnEntity);
    	}
    }
}
