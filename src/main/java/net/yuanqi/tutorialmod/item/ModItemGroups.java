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

public class ModItemGroups {
//    这里是开了一个itemgroup 其中我们将icon设置为了Ruby的icon 不知道是否是
//    这个是不是所谓的lambda写法啊
    public static final ItemGroup RUBY_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(TutorialMod.MOD_ID,"ruby"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.ruby"))
                    .icon(() -> new ItemStack(ModItems.RUBY)).entries((displayContext, entries) -> {
                        entries.add(ModItems.RAW_RUBY);
                        entries.add(ModItems.RUBY);
                        entries.add(Items.DIAMOND);

                    }).build());

    public static void registerItemGroups(){
        TutorialMod.LOGGER.info("Registering Item Groups for "+TutorialMod.MOD_ID);
    }
}
