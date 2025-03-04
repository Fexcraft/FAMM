package net.fexcraft.mod.famm.util;

import net.fexcraft.mod.famm.FAMM;
import net.fexcraft.mod.famm.blocks.FAMMBlocks;
import net.fexcraft.mod.famm.items.FAMMItems;
import net.fexcraft.mod.uni.FclRecipe;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public final class Recipes {
	
	private static final String category = "FAMM Block Recipes";
	private static final String category_hb = "FAMM HalfBlock Recipes";
	private static final String category_items = "FAMM Item Recipes";
	
	public static void init(){
		if(FAMM.conf2){
			for(String str : FAMMBlocks.all_blocknames){
				if(str.equals("stop")){
					FclRecipe.newBuilder(category)
						.add(new ItemStack(FAMMItems.plate_empty, 6))
						.add(new ItemStack(FAMMItems.ink, 2))
						.add(new ItemStack(Items.DYE, 2, 1))
						.output(new ItemStack(FAMMBlocks.get(str)))
						.register();
					continue;
				}
				if(!str.equals("empty")){
					FclRecipe.newBuilder(category)
						.add(new ItemStack(FAMMItems.plate_empty, 6))
						.add(new ItemStack(FAMMItems.ink, 2))
						.output(new ItemStack(FAMMBlocks.get(str)))
						.register();
				}
				else{
					FclRecipe.newBuilder(category)
						.add(new ItemStack(FAMMItems.plate_empty, 6))
						.output(new ItemStack(FAMMBlocks.get(str)))
						.register();
				}
			}
		}
		if(FAMM.conf3){
			for(String str : FAMMBlocks.all_blocknames){
				if(str.equals("stop")){
					FclRecipe.newBuilder(category_hb)
						.add(new ItemStack(FAMMItems.plate_empty, 6))
						.add(new ItemStack(FAMMItems.ink, 2))
						.add(new ItemStack(Items.DYE, 2, 1))
						.output(new ItemStack(FAMMBlocks.get(str + "_hb")))
						.register();
					continue;
				}
				if(!str.equals("empty")){
					FclRecipe.newBuilder(category_hb)
						.add(new ItemStack(FAMMItems.plate_empty, 6))
						.add(new ItemStack(FAMMItems.ink, 2))
						.output(new ItemStack(FAMMBlocks.get(str + "_hb")))
						.register();
				}
				else{
					FclRecipe.newBuilder(category_hb)
						.add(new ItemStack(FAMMItems.plate_empty, 6))
						.output(new ItemStack(FAMMBlocks.get(str + "_hb")))
						.register();
				}
			}
		}
		FclRecipe.newBuilder(category_items)
			.add(new ItemStack(Blocks.PLANKS, 16))
			.add(new ItemStack(Items.PAPER, 9))
			.add(new ItemStack(Items.DYE, 1, 0))
			.output(new ItemStack(FAMMItems.plate_empty, 16))
			.register();
		FclRecipe.newBuilder(category_items)
			.add(new ItemStack(Items.FLOWER_POT))
			.add(new ItemStack(Items.DYE, 1, 0))
			.output(new ItemStack(FAMMItems.ink, 1))
			.register();
	}
}