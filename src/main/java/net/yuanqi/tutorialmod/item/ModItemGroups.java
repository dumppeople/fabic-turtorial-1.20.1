package net.yuanqi.tutorialmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.yuanqi.tutorialmod.TutorialMod;
import net.yuanqi.tutorialmod.block.ModBlocks;
import net.yuanqi.tutorialmod.item.custom.SoundBlock;

public class ModItemGroups {
//    这里是开了一个itemgroup 其中我们将icon设置为了Ruby的icon 不知道是否是
//    这个是不是所谓的lambda写法啊 是lambda的写法 （形参列表）-》 {代码块}
//    mc的源码中大量的使用了泛型类的方法
    public static final ItemGroup RUBY_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(TutorialMod.MOD_ID,"ruby"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.ruby"))
                    .icon(() -> new ItemStack(ModItems.RUBY)).entries((displayContext, entries) -> {
                        entries.add(ModItems.RAW_RUBY);
                        entries.add(ModItems.RUBY);
                        entries.add(Items.DIAMOND);
                        entries.add(ModBlocks.RUBY_BLOCK);
                        entries.add(ModBlocks.RAW_RUBY_BLOCK);

                        entries.add(ModItems.METAL_DETECTOR);
                        entries.add(ModBlocks.SOUND_BLOCK);

                        entries.add(ModItems.COAL_BRIQUETTE);
                        entries.add(ModItems.TOMATO);
                        entries.add(ModBlocks.DEEPSLATE_RAW_ORE);
                        entries.add(ModBlocks.RAW_ORE);
                        entries.add(ModBlocks.END_STONE_RAW_ORE);
                        entries.add(ModBlocks.NETHER_RAW_ORE);
                    }).build());

    public static void registerItemGroups(){
        TutorialMod.LOGGER.info("Registering Item Groups for "+TutorialMod.MOD_ID);
    }
}
