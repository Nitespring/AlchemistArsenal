package github.nitespring.alchemistarsenal.core.init;

import github.nitespring.alchemistarsenal.AlchemistArsenal;
import github.nitespring.alchemistarsenal.common.entity.projectile.*;
import github.nitespring.alchemistarsenal.common.item.weapons.AutomaticCrossbow;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE,
            AlchemistArsenal.MODID);


    public static final DeferredHolder<EntityType<?>, EntityType<BouncyArrow>> BOUNCY_ARROW = ENTITY_TYPES.register("bouncy_arrow",
            ()->EntityType.Builder.<BouncyArrow>of(BouncyArrow::new, MobCategory.MISC)
                    .sized(0.4F, 0.4F)
                    .build("bouncy_arrow"));
    public static final DeferredHolder<EntityType<?>, EntityType<InfernalArrow>> INFERNAL_ARROW = ENTITY_TYPES.register("infernal_arrow",
            ()->EntityType.Builder.<InfernalArrow>of(InfernalArrow::new, MobCategory.MISC)
                    .sized(0.4F, 0.4F)
                    .build("infernal_arrow"));
    public static final DeferredHolder<EntityType<?>, EntityType<LightningArrow>> LIGHTNING_ARROW = ENTITY_TYPES.register("lightning_arrow",
            ()->EntityType.Builder.<LightningArrow>of(LightningArrow::new, MobCategory.MISC)
                    .sized(0.4F, 0.4F)
                    .build("lightning_arrow"));
    public static final DeferredHolder<EntityType<?>, EntityType<DragonArrow>> DRAGON_ARROW = ENTITY_TYPES.register("dragon_arrow",
            ()->EntityType.Builder.<DragonArrow>of(DragonArrow::new, MobCategory.MISC)
                    .sized(0.4F, 0.4F)
                    .build("dragon_arrow"));
    public static final DeferredHolder<EntityType<?>, EntityType<ExplosiveArrow>> EXPLOSIVE_ARROW = ENTITY_TYPES.register("explosive_arrow",
            ()->EntityType.Builder.<ExplosiveArrow>of(ExplosiveArrow::new, MobCategory.MISC)
                    .sized(0.4F, 0.4F)
                    .build("explosive_arrow"));


}
