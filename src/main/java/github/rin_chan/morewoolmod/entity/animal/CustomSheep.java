package github.rin_chan.morewoolmod.entity.animal;

import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Maps;

import github.rin_chan.morewoolmod.config.MoreWoolModCommonConfigs;
import github.rin_chan.morewoolmod.init.ModEntities;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.TransientCraftingContainer;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;

public class CustomSheep extends Sheep {
	private static final EntityDataAccessor<Integer> DATA_SIZE = SynchedEntityData.defineId(CustomSheep.class, EntityDataSerializers.INT);
	
	private static final Map<DyeColor, ItemLike> ITEM_BY_DYE = Util.make(Maps.newEnumMap(DyeColor.class), (p_29841_) -> {
		p_29841_.put(DyeColor.WHITE, Blocks.WHITE_WOOL);
		p_29841_.put(DyeColor.ORANGE, Blocks.ORANGE_WOOL);
		p_29841_.put(DyeColor.MAGENTA, Blocks.MAGENTA_WOOL);
		p_29841_.put(DyeColor.LIGHT_BLUE, Blocks.LIGHT_BLUE_WOOL);
		p_29841_.put(DyeColor.YELLOW, Blocks.YELLOW_WOOL);
		p_29841_.put(DyeColor.LIME, Blocks.LIME_WOOL);
		p_29841_.put(DyeColor.PINK, Blocks.PINK_WOOL);
		p_29841_.put(DyeColor.GRAY, Blocks.GRAY_WOOL);
		p_29841_.put(DyeColor.LIGHT_GRAY, Blocks.LIGHT_GRAY_WOOL);
		p_29841_.put(DyeColor.CYAN, Blocks.CYAN_WOOL);
		p_29841_.put(DyeColor.PURPLE, Blocks.PURPLE_WOOL);
		p_29841_.put(DyeColor.BLUE, Blocks.BLUE_WOOL);
		p_29841_.put(DyeColor.BROWN, Blocks.BROWN_WOOL);
		p_29841_.put(DyeColor.GREEN, Blocks.GREEN_WOOL);
		p_29841_.put(DyeColor.RED, Blocks.RED_WOOL);
		p_29841_.put(DyeColor.BLACK, Blocks.BLACK_WOOL);
   });
	
	public CustomSheep(EntityType<? extends Sheep> entityType, Level level) {
		super(entityType, level);
	}
	
	public static AttributeSupplier setAttributes() {
		return Sheep.createAttributes().build();
	}
	
	protected void defineSynchedData() {
      super.defineSynchedData();
      this.entityData.define(DATA_SIZE, 1);
   }
	
    public void setWoolLevel(int value) {
    	if (value <= MoreWoolModCommonConfigs.MAX_SHEEP_SIZE.get()) {
    		this.entityData.set(DATA_SIZE, value);
    	}
    }

    public int getWoolLevel() {
        return this.entityData.get(DATA_SIZE);
    }

    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putInt("Wool_Level", this.getWoolLevel());
    }
	
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.setWoolLevel(tag.getInt("Wool_Level"));
   }
	
	@Nullable
	public CustomSheep getBreedOffspring(ServerLevel p_149044_, AgeableMob p_149045_) {
		CustomSheep sheep = new CustomSheep(ModEntities.CUSTOM_SHEEP.get(), p_149044_);
		if (sheep != null) {
			sheep.setColor(this.getOffspringColor(this, (CustomSheep)p_149045_));
		}

		return sheep;
	}
	
	private DyeColor getOffspringColor(Animal p_29824_, Animal p_29825_) {
		DyeColor dyecolor = ((Sheep)p_29824_).getColor();
		DyeColor dyecolor1 = ((Sheep)p_29825_).getColor();
		CraftingContainer craftingcontainer = makeContainer(dyecolor, dyecolor1);
		return this.level().getRecipeManager().getRecipeFor(RecipeType.CRAFTING, craftingcontainer, this.level()).map((p_269924_) -> {
			return p_269924_.assemble(craftingcontainer, this.level().registryAccess());
		}).map(ItemStack::getItem).filter(DyeItem.class::isInstance).map(DyeItem.class::cast).map(DyeItem::getDyeColor).orElseGet(() -> {
			return this.level().random.nextBoolean() ? dyecolor : dyecolor1;
		});
	}
	
	private static CraftingContainer makeContainer(DyeColor p_29832_, DyeColor p_29833_) {
		CraftingContainer craftingcontainer = new TransientCraftingContainer(new AbstractContainerMenu((MenuType)null, -1) {
	         public ItemStack quickMoveStack(Player p_218264_, int p_218265_) {
	            return ItemStack.EMPTY;
	         }

	         public boolean stillValid(Player p_29888_) {
	            return false;
	         }
	      }, 2, 1);
	      craftingcontainer.setItem(0, new ItemStack(DyeItem.byColor(p_29832_)));
	      craftingcontainer.setItem(1, new ItemStack(DyeItem.byColor(p_29833_)));
	      return craftingcontainer;
	}
	
	public void ate() {
		super.ate();
		setWoolLevel(getWoolLevel() + 1);
	}
	
	@org.jetbrains.annotations.NotNull
	@Override
	public java.util.List<ItemStack> onSheared(@Nullable Player player, @org.jetbrains.annotations.NotNull ItemStack item, Level world, BlockPos pos, int fortune) {
		world.playSound(null, this, SoundEvents.SHEEP_SHEAR, player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0F, 1.0F);
		this.gameEvent(GameEvent.SHEAR, player);
		
		if (!world.isClientSide) {
			this.setSheared(true);
			int i = (1 + this.random.nextInt(3)) * getWoolLevel();

			java.util.List<ItemStack> items = new java.util.ArrayList<>();
			for (int j = 0; j < i; ++j) {
				items.add(new ItemStack(ITEM_BY_DYE.get(this.getColor())));
			}
			
			setWoolLevel(0);
			
			return items;
		}
		return java.util.Collections.emptyList();
	}
}
