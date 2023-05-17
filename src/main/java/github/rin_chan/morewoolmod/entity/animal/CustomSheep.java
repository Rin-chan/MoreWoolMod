package github.rin_chan.morewoolmod.entity.animal;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.level.Level;

public class CustomSheep extends Sheep {
	private int WoolLevel = 1;
	
	public CustomSheep(EntityType<? extends Sheep> entityType, Level level) {
		super(entityType, level);
		//setAttributeValue(WoolLevel);
	}
	
	public static AttributeSupplier setAttributes() {
		return Sheep.createAttributes().build();
	}
	
    public void setAttributeValue(int value) {
        WoolLevel = value;
    }

    public int getSheepAttributes() {
        return WoolLevel;
    }

	@Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("wool_level", WoolLevel);
    }
}
