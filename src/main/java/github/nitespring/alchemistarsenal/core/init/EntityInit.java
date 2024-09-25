package github.nitespring.alchemistarsenal.core.init;

import github.nitespring.alchemistarsenal.AlchemistArsenal;
import github.nitespring.alchemistarsenal.common.entity.projectile.ElytraBooster;
import github.nitespring.alchemistarsenal.common.entity.projectile.Shrapnel;
import github.nitespring.alchemistarsenal.common.entity.projectile.arrow.*;
import github.nitespring.alchemistarsenal.common.entity.projectile.throwable.ExplosiveBottle;
import github.nitespring.alchemistarsenal.common.entity.projectile.throwable.FireBottle;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
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
    public static final DeferredHolder<EntityType<?>, EntityType<FragmentingArrow>> AMETHYST_ARROW = ENTITY_TYPES.register("amethyst_arrow",
            ()->EntityType.Builder.<FragmentingArrow>of(FragmentingArrow::new, MobCategory.MISC)
                    .sized(0.4F, 0.4F)
                    .build("amethyst_arrow"));
    public static final DeferredHolder<EntityType<?>, EntityType<WindArrow>> WIND_ARROW = ENTITY_TYPES.register("wind_arrow",
            ()->EntityType.Builder.<WindArrow>of(WindArrow::new, MobCategory.MISC)
                    .sized(0.4F, 0.4F)
                    .build("wind_arrow"));
    public static final DeferredHolder<EntityType<?>, EntityType<FireBottle>> FIRE_BOTTLE = ENTITY_TYPES.register("bottle_of_flame",
            ()->EntityType.Builder.<FireBottle>of(FireBottle::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10)
                    .build("bottle_of_flame"));
    public static final DeferredHolder<EntityType<?>, EntityType<ExplosiveBottle>> EXPLOSIVE_BOTTLE = ENTITY_TYPES.register("unstable_concoction",
            ()->EntityType.Builder.<ExplosiveBottle>of(ExplosiveBottle::new, MobCategory.MISC)
                    .sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10)
                    .build("unstable_concoction"));
    public static final DeferredHolder<EntityType<?>, EntityType<Shrapnel>> SHRAPNEL = ENTITY_TYPES.register("crystal_shard",
            ()->EntityType.Builder.<Shrapnel>of(Shrapnel::new, MobCategory.MISC)
                    .sized(0.35F, 0.35F)
                    .build("crystal_shard"));
    public static final DeferredHolder<EntityType<?>, EntityType<ElytraBooster>> ELYTRA_BOOSTER = ENTITY_TYPES.register("elytra_booster",
            ()->EntityType.Builder.<ElytraBooster>of(ElytraBooster::new, MobCategory.MISC)
                    .sized(0.35F, 0.35F)
                    .build("elytra_booster"));


}
