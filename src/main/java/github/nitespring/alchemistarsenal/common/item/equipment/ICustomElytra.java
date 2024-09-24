package github.nitespring.alchemistarsenal.common.item.equipment;

import github.nitespring.alchemistarsenal.core.init.DataComponentInit;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public interface ICustomElytra {


    public default ResourceLocation getElytraTexture(ItemStack stack) {
        if(stack.has(DataComponentInit.CUSTOM_WINGS_TEXTURE)){
            return DataComponentInit.CUSTOM_WINGS_TEXTURE.getId();
        }else {
            return getDefaultElytraTexture();
        }
    }
    public ResourceLocation getDefaultElytraTexture();

}
