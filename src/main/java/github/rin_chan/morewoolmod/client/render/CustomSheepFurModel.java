package github.rin_chan.morewoolmod.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import github.rin_chan.morewoolmod.entity.animal.CustomSheep;
import net.minecraft.client.model.QuadrupedModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class CustomSheepFurModel<T extends CustomSheep> extends QuadrupedModel<T> {
   private float headXRot;
   private final boolean scaleHead;
   private final float babyYHeadOffset;
   private final float babyZHeadOffset;
   private final float babyHeadScale;
   private final float babyBodyScale;
   private final float bodyYOffset;

   public CustomSheepFurModel(ModelPart p_170900_) {
      super(p_170900_, false, 8.0F, 4.0F, 2.0F, 2.0F, 24);
      this.scaleHead = false;
      this.babyYHeadOffset = 8.0F;
      this.babyZHeadOffset = 4.0F;
      this.babyHeadScale = 2.0F;
      this.babyBodyScale = 2.0F;
      this.bodyYOffset = 24;
   }

   public static LayerDefinition createFurLayer() {
      MeshDefinition meshdefinition = new MeshDefinition();
      PartDefinition partdefinition = meshdefinition.getRoot();
      partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -4.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.6F)), PartPose.offset(0.0F, 6.0F, -8.0F));
      partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(28, 8).addBox(-4.0F, -10.0F, -7.0F, 8.0F, 16.0F, 6.0F, new CubeDeformation(1.75F)), PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, ((float)Math.PI / 2F), 0.0F, 0.0F));
      CubeListBuilder cubelistbuilder = CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.5F));
      partdefinition.addOrReplaceChild("right_hind_leg", cubelistbuilder, PartPose.offset(-3.0F, 12.0F, 7.0F));
      partdefinition.addOrReplaceChild("left_hind_leg", cubelistbuilder, PartPose.offset(3.0F, 12.0F, 7.0F));
      partdefinition.addOrReplaceChild("right_front_leg", cubelistbuilder, PartPose.offset(-3.0F, 12.0F, -5.0F));
      partdefinition.addOrReplaceChild("left_front_leg", cubelistbuilder, PartPose.offset(3.0F, 12.0F, -5.0F));
      return LayerDefinition.create(meshdefinition, 64, 32);
   }

   public void prepareMobModel(T p_117424_, float p_103662_, float p_103663_, float p_103664_) {
      super.prepareMobModel(p_117424_, p_103662_, p_103663_, p_103664_);
      this.head.y = 6.0F + p_117424_.getHeadEatPositionScale(p_103664_) * 9.0F;
      this.headXRot = p_117424_.getHeadEatAngleScale(p_103664_);
   }

   public void setupAnim(T p_103666_, float p_103667_, float p_103668_, float p_103669_, float p_103670_, float p_103671_) {
      super.setupAnim(p_103666_, p_103667_, p_103668_, p_103669_, p_103670_, p_103671_);
      this.head.xRot = this.headXRot;
   }
   
   public void renderToBuffer(PoseStack p_102034_, VertexConsumer p_102035_, int p_102036_, int p_102037_, float p_102038_, float p_102039_, float p_102040_, float p_102041_) {
      if (this.young) {
         p_102034_.pushPose();
         if (this.scaleHead) {
            float f = 1.5F / this.babyHeadScale;
            p_102034_.scale(f, f, f);
         }

         p_102034_.translate(0.0F, this.babyYHeadOffset / 16.0F, this.babyZHeadOffset / 16.0F);
         this.headParts().forEach((p_102081_) -> {
            p_102081_.render(p_102034_, p_102035_, p_102036_, p_102037_, p_102038_, p_102039_, p_102040_, p_102041_);
         });
         p_102034_.popPose();
         p_102034_.pushPose();
         float f1 = 1.0F / this.babyBodyScale;
         p_102034_.scale(f1, f1, f1);
         p_102034_.translate(0.0F, this.bodyYOffset / 16.0F, 0.0F);
         this.bodyParts().forEach((p_102071_) -> {
            p_102071_.render(p_102034_, p_102035_, p_102036_, p_102037_, p_102038_, p_102039_, p_102040_, p_102041_);
         });
         p_102034_.popPose();
      } else {
    	  this.headParts().forEach((p_102061_) -> {
              p_102061_.render(p_102034_, p_102035_, p_102036_, p_102037_, p_102038_, p_102039_, p_102040_, p_102041_);
           });
           this.bodyParts().forEach((p_102051_) -> {
              p_102051_.render(p_102034_, p_102035_, p_102036_, p_102037_, p_102038_, p_102039_, p_102040_, p_102041_);
           });
      }

   }
}