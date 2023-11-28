package net.yuanqi.tutorialmod.item.custom;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.yuanqi.tutorialmod.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class MetalDetectorItem extends Item {
    public MetalDetectorItem(Settings settings){
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(!context.getWorld().isClient()){
            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            boolean foundBlock = false;
//            这个是点击的位置往下0-64的情况 如果有矿物的话 会打印矿物的位置
//            我们要强化一下 从点击的位置为中心 5*5 的区域探矿 往下64和往上 64 的距离
//这里要传一个blockPos对象 5*5
//   12345 -2 -1 0 1 2
//   12345
//   12345
//   12345
//   12345
//储存种类的相同的矿在同一目录下然后输出 所有的坐标 1.0先这样后面可以用dfs进行矿脉的寻找

            Map<String, List<int[]>> mineralsFound = new HashMap<>();

            for(int x=-2;x<=2;x++){
                for(int y=-2;y<=2;y++){
                    BlockPos temp_pos = positionClicked.add(x,0,y);
                    for(int i=0;i<= positionClicked.getY()+64;i++){
//                        add似乎重写过了可以这么搞 但是这个1是否是对
                        BlockState state = context.getWorld().getBlockState(temp_pos.down(i));
                        if(isValuableBlock(state)){
//                            将name获取
                            String name = state.getBlock().asItem().getName().getString();
                            if(!mineralsFound.containsKey(name)){

                                mineralsFound.put(name, new ArrayList<>());
                                mineralsFound.get(name).add(new int[] {temp_pos.getX(),temp_pos.getY(),temp_pos.getZ()});
                            }
                            else{
                                mineralsFound.get(name).add(new int[] {temp_pos.getX(),temp_pos.getY(),temp_pos.getZ()});
                            }

                            foundBlock = true;
                        }
                    }
                }
            }
            if(!mineralsFound.isEmpty()){
                outputValuableCoordinates(player,mineralsFound);
            }

            if(!foundBlock){
                player.sendMessage(Text.literal("金属探测器没有找到相关矿石！"));
            }

        }

        context.getStack().damage(100,context.getPlayer(),
                playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
        return ActionResult.SUCCESS;
    }
    private void outputValuableCoordinates(PlayerEntity player, Map<String, List<int[]>> mineralsFound) {
        for (Map.Entry<String, List<int[]>> entry : mineralsFound.entrySet()) {
            String mineralType = entry.getKey();
            List<int[]> coordinates = entry.getValue();
//            输出内容

            player.sendMessage(Text.literal("矿石种类: " + mineralType), false);
            player.sendMessage(Text.literal("矿石个数: "+coordinates.size()), false);
            player.sendMessage(Text.literal("准确坐标"), false);
            for (int[] coordinate : coordinates) {
                player.sendMessage(Text.literal("(" + coordinate[0] + ", " + coordinate[1] + ", " + coordinate[2] + ")"), false);
            }
        }
    }
//    isIn这句话有问题
    private boolean isValuableBlock(BlockState state) {
        return state.isIn(ModTags.Blocks.METAL_DETECTOR_DETECTABLE_BLOCKS);
//                state.isOf(Blocks.IRON_ORE) ||
//                state.isOf(Blocks.DIAMOND_ORE)||
//                state.isOf(Blocks.COAL_ORE)||
//                state.isOf(Blocks.GOLD_ORE)||
//                state.isOf(Blocks.DEEPSLATE_IRON_ORE)||
//                state.isOf(Blocks.DEEPSLATE_COAL_ORE)||
//                state.isOf(Blocks.DEEPSLATE_GOLD_ORE)||
//                state.isOf(Blocks.DEEPSLATE_DIAMOND_ORE)||
//                state.isOf(Blocks.LAPIS_ORE)||
//                state.isOf(Blocks.DEEPSLATE_LAPIS_ORE)||
//                state.isOf(Blocks.NETHER_QUARTZ_ORE)||
//                state.isOf(Blocks.NETHER_GOLD_ORE);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.tutorialmod.metal_detector.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
