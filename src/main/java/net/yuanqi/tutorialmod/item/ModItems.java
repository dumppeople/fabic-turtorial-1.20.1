package net.yuanqi.tutorialmod.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
//必须是这个
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.yuanqi.tutorialmod.TutorialMod;

public class ModItems {

    public static final Item RUBY = registerItem("ruby",new Item(new FabricItemSettings()));

    private static void addItemsToIngredientTableGroup(FabricItemGroupEntries entries){
        entries.add(RUBY);
    }


    private static Item registerItem(String name,Item item){
        return Registry.register(Registries.ITEM,new Identifier(TutorialMod.MOD_ID),item);

    }

    public static void registerModItems(){
        TutorialMod.LOGGER.info("Registering Mod Items for "+TutorialMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientTableGroup);
    }
}
