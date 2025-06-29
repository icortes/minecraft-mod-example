package com.omgisa.examplemod.item;

import com.omgisa.examplemod.ExampleMod;
import com.omgisa.examplemod.item.custom.ChiselItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ExampleMod.MOD_ID);

    public static final DeferredItem<Item> BISMUTH = ITEMS.registerItem("bismuth", Item::new, new Item.Properties());
    public static final DeferredItem<Item> RAW_BISMUTH = ITEMS.registerItem("raw_bismuth", Item::new, new Item.Properties());

    public static final DeferredItem<Item> CHISEL =
            ITEMS.registerItem("chisel", ChiselItem::new, new Item.Properties().durability(32));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
