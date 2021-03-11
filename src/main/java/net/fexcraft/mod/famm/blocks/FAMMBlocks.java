package net.fexcraft.mod.famm.blocks;

import java.util.ArrayList;

import net.fexcraft.mod.famm.FAMM;
import net.minecraft.block.Block;

public final class FAMMBlocks{
	
	public static String[] all_blocknames =
		new String[]{ "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
					"n0", "n1", "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9", "plus", "minus", "empty", "questionmark", "exclamationmark", "point",
					"pointdown", "comma", "colon", "space", "stop", "arrowright", "arrowleft", "arrowtop", "arrowbot", "ri", "rii", "riii", "riv", "rv",
					"rvi", "rvii", "rviii", "rix", "rx", "rr", "cr", "eh", "es", "ea", "ec", "d1", "d2",
					"cy_a", "cy_b", "cy_v", "cy_g", "cy_d", "cy_ie", "cy_io", "cy_zh", "cy_z", "cy_i", "cy_j", "cy_k", "cy_l",
					"cy_m", "cy_n", "cy_o", "cy_p", "cy_r", "cy_s", "cy_t", "cy_u", "cy_f", "cy_h", "cy_ts", "cy_ch", "cy_sh", "cy_shch",
					"cy_hard_sign", "cy_y", "cy_soft_sign", "cy_e", "cy_yu", "cy_ya" };
	public static String[] latin = new String[]{ "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
	public static String[] numbers = new String[]{ "n0", "n1", "n2", "n3", "n4", "n5", "n6", "n7", "n8", "n9" };
	public static String[] signs = new String[]{ "plus", "minus", "empty", "questionmark", "exclamationmark", "point", "pointdown", "comma", "colon", "space", "stop", "arrowright", "arrowleft", "arrowtop", "arrowbot" };
	public static String[] ronum = new String[]{ "ri", "rii", "riii", "riv", "rv", "rvi", "rvii", "rviii", "rix", "rx" };
	public static String[] deco = new String[]{ "rr", "cr", "eh", "es", "ea", "ec", "d1", "d2" };
	public static String[] cyrillic = new String[]{ "cy_a", "cy_b", "cy_v", "cy_g", "cy_d", "cy_ie", "cy_io", "cy_zh", "cy_z", "cy_i", "cy_j", "cy_k", "cy_l",
					"cy_m", "cy_n", "cy_o", "cy_p", "cy_r", "cy_s", "cy_t", "cy_u", "cy_f", "cy_h", "cy_ts", "cy_ch", "cy_sh", "cy_shch",
					"cy_hard_sign", "cy_y", "cy_soft_sign", "cy_e", "cy_yu", "cy_ya" };
	
	public static ArrayList<Block> blocks = new ArrayList<Block>();
	
	public static Block get(String id){
		Block result = null;
		for(Block block : blocks){
			if(block.getRegistryName().getPath().equals(id)){
				result = block;
				break;
			}
		}
		return result;
	}
  
	public static void initialize(boolean full){
		if(FAMM.latin) register(latin, full);
		if(FAMM.cyrillic) register(cyrillic, full);
		if(FAMM.numbers) register(numbers, full);
		if(FAMM.signs) register(signs, full);
		if(FAMM.ronum) register(ronum, full);
		if(FAMM.deco) register(deco, full);
    }
	
	private static void register(String[] array, boolean full){
		if(full) registerF(array);
		else registerH(array);
	}
	
	private static void registerF(String[] array){
		for(int i = 0; i < array.length; i++){
			Block block = new FAMMBLK(array[i]);
			blocks.add(block);
		}
	}
	
	private static void registerH(String[] array){
		for(int i = 0; i < array.length; i++){
			Block block = new FAMMBLKH(array[i] + "_hb");
			blocks.add(block);
		}
	}
	
}