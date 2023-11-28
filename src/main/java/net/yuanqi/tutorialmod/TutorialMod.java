package net.yuanqi.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.yuanqi.tutorialmod.block.ModBlocks;
import net.yuanqi.tutorialmod.item.ModItemGroups;
import net.yuanqi.tutorialmod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
//onIntialize不是是一个抽象方法吧？还真是 毕竟是继承了一个接口
	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		FuelRegistry.INSTANCE.add(ModItems.COAL_BRIQUETTE,200);
	}
}