package com.omgisa.examplemod.block;

import com.omgisa.examplemod.ExampleMod;
import com.omgisa.examplemod.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ExampleMod.MOD_ID);

    public static final DeferredBlock<Block> BISMUTH_BLOCK =
            registerBlock("bismuth_block", Block::new, BlockBehaviour.Properties.of().strength(4F).requiresCorrectToolForDrops().sound(SoundType.AMETHYST));
    public static final DeferredBlock<Block> BISMUTH_ORE =
            registerBlock("bismuth_ore", properties -> new DropExperienceBlock(UniformInt.of(2, 4), properties), BlockBehaviour.Properties.of().strength(3F).requiresCorrectToolForDrops().sound(SoundType.STONE).randomTicks());
    public static final DeferredBlock<Block> BISMUTH_DEEPSLATE_ORE =
            registerBlock("bismuth_deepslate_ore",
                          properties -> new DropExperienceBlock(UniformInt.of(3, 6), properties), BlockBehaviour.Properties.of().strength(4F).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE).randomTicks());


    private static <B extends Block> DeferredBlock<B> registerBlock(String name, Function<BlockBehaviour.Properties, ? extends B> blockFactory, BlockBehaviour.Properties blockProperties) {
        DeferredBlock<B> block = BLOCKS.registerBlock(name, blockFactory, blockProperties);
        registerBlockItem(name, block);
        return block;
    }

    private static <B extends Block> void registerBlockItem(String name, DeferredBlock<B> block) {
        ModItems.ITEMS.registerSimpleBlockItem(name, block);
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
