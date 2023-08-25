package github.rin_chan.morewoolmod.client.render;

import github.rin_chan.morewoolmod.entity.animal.CustomSheep;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CustomSheepRenderer extends MobRenderer<CustomSheep, CustomSheepModel<CustomSheep>> {
	private static final ResourceLocation SHEEP_LOCATION = new ResourceLocation("textures/entity/sheep/sheep.png");

	   public CustomSheepRenderer(EntityRendererProvider.Context p_174366_) {
	      super(p_174366_, new CustomSheepModel<>(p_174366_.bakeLayer(ModelLayers.SHEEP)), 0.7F);
	      this.addLayer(new CustomSheepFurLayer(this, p_174366_.getModelSet()));
	   }

	   public ResourceLocation getTextureLocation(CustomSheep p_115840_) {
	      return SHEEP_LOCATION;
	   }
}
