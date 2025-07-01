package com.omgisa.examplemod.item;

import com.omgisa.examplemod.ExampleMod;
import com.omgisa.examplemod.item.custom.ChiselItem;
import com.omgisa.examplemod.item.custom.FuelItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ExampleMod.MOD_ID);

    public static final DeferredItem<Item> BISMUTH = ITEMS.registerItem("bismuth", Item::new, new Item.Properties());
    public static final DeferredItem<Item> RAW_BISMUTH = ITEMS.registerItem("raw_bismuth", Item::new, new Item.Properties());

    public static final DeferredItem<Item> CHISEL =
            ITEMS.registerItem("chisel", ChiselItem::new, new Item.Properties().durability(32));

    public static final DeferredItem<Item> FROSTFIRE_ICE = ITEMS.registerItem("frostfire_ice",
                                                                              (props) -> new FuelItem(props, 800),
                                                                              new Item.Properties());
    public static final DeferredItem<Item> STARLIGHT_ASHES = ITEMS.registerItem("starlight_ashes", Item::new, new Item.Properties());

    public static final DeferredItem<Item> RADISH =
            ITEMS.registerItem("radish", (props) -> new Item(props) {
                                   @Override
                                   public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext context, @NotNull TooltipDisplay tooltipDisplay, @NotNull Consumer<Component> tooltipAdder, @NotNull TooltipFlag flag) {
                                       tooltipAdder.accept(Component.translatable("tooltip.examplemod.radish"));
                                       super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);
                                   }
                               },
                               new Item.Properties().food(ModFoodProperties.RADISH)
                                                    .component(DataComponents.CONSUMABLE, Consumable.builder()
                                                                                                    .consumeSeconds(2F)
                                                                                                    .animation(ItemUseAnimation.EAT)
                                                                                                    .hasConsumeParticles(false)
                                                                                                    .onConsume(new ApplyStatusEffectsConsumeEffect(new MobEffectInstance(MobEffects.HEALTH_BOOST, 600, 0), 0.35F))
                                                                                                    .build()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
