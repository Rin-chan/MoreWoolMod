package github.rin_chan.morewoolmod.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import github.rin_chan.morewoolmod.entity.animal.CustomSheep;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;

public class CustomSheepFurBodyLayerModel<T extends CustomSheep> extends QuadrupedModel<T> {
   public CustomSheepFurBodyLayerModel(ModelPart p_170900_) {
      super(p_170900_, false, 8.0F, 4.0F, 2.0F, 2.0F, 24);
   }

   public static LayerDefinition createFurLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.getRoot();
      partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(28, 8).addBox(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F, new CubeDeformation(1.75F)), PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, ((float)Math.PI / 2F), 0.0F, 0.0F));
      return LayerDefinition.create(meshdefinition, 64, 32);
   }

   public void prepareMobModel(T p_117424_, float p_103662_, float p_103663_, float p_103664_) {
      super.prepareMobModel(p_117424_, p_103662_, p_103663_, p_103664_);
   }

   public void setupAnim(T p_103666_, float p_103667_, float p_103668_, float p_103669_, float p_103670_, float p_103671_) {
      super.setupAnim(p_103666_, p_103667_, p_103668_, p_103669_, p_103670_, p_103671_);
   }
   
   public void renderToBuffer(PoseStack p_102034_, VertexConsumer p_102035_, int p_102036_, int p_102037_, float p_102038_, float p_102039_, float p_102040_, float p_102041_, int WoolLevel) {
      if (!(this.young)) {
    	  int WoolSize = WoolLevel - 1;
    	  float resize = 1.0F + (0.1F * WoolSize);
    	  
    	  /* Wool Size = translateY (For graphing and equation reference points)
    	   * 0 = 0
    	   * 10 = -0.38
    	   * 20 = -0.56
    	   * 35 = -0.66
    	   * 50 = -0.71
    	   * 100 = -0.78
    	   */
    	  double translateY = 0.012852 - 0.196078 * Math.pow(WoolSize, 0.30379);
    	  
          p_102034_.scale(resize, resize, 1.0F);
          p_102034_.translate(0.0F, translateY, 0.0F);
          
          this.body.render(p_102034_, p_102035_, p_102036_, p_102037_, p_102038_, p_102039_, p_102040_, p_102041_);
      }

   }
}