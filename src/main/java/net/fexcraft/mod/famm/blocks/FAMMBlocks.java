package net.fexcraft.mod.famm.blocks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
			//autogenerate(array[i]);
		}
	}
	
	/// ASSET GENERATION ///
	
	@SuppressWarnings("unused")
	private static void autogenerate(String id){
		File folder = new File(new File(".").getAbsoluteFile().getParentFile().getParentFile(), "/src/main/resources/assets/famm/");
		write(FULL_BLOCKSTATE.replace("%s", id), new File(folder, "blockstates/" + id + ".json"));
		write(HALF_BLOCKSTATE.replace("%s", id), new File(folder, "blockstates/" + id + "_hb.json"));
		write(FULL_MODEL.replace("%s", id), new File(folder, "models/block/" + id + ".json"));
		write(HALF_MODEL_0.replace("%s", id), new File(folder, "models/block/" + id + "_hb_0.json"));
		write(HALF_MODEL_1.replace("%s", id), new File(folder, "models/block/" + id + "_hb_1.json"));
		write(HALF_MODEL_2.replace("%s", id), new File(folder, "models/block/" + id + "_hb_2.json"));
		write(HALF_MODEL_3.replace("%s", id), new File(folder, "models/block/" + id + "_hb_3.json"));
		write(FULL_ITEMMODEL.replace("%s", id), new File(folder, "models/item/" + id + ".json"));
		write(HALF_ITEMMODEL.replace("%s", id), new File(folder, "models/item/" + id + "_hb.json"));
	}

	private static void write(String string, File file){
		try{
			FileWriter writer = new FileWriter(file);
			writer.write(string);
			writer.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	private static void registerH(String[] array){
		for(int i = 0; i < array.length; i++){
			Block block = new FAMMBLKH(array[i] + "_hb");
			blocks.add(block);
		}
	}
	
	private static final String FULL_BLOCKSTATE = 
		  "{\r\n"
		+ "    \"variants\": {\r\n"
		+ "        \"normal\": { \"model\": \"famm:%s\" }\r\n"
		+ "    }\r\n"
		+ "}";
	private static final String HALF_BLOCKSTATE =
		  "{\r\n"
		+ "    \"variants\": {\r\n"
		+ "        \"normal\": { \"model\": \"famm:%s_hb\" },\r\n"
		+ "        \"depth=3,facing=north\": { \"model\": \"famm:%s_hb_3\" },\r\n"
		+ "        \"depth=3,facing=south\": { \"model\": \"famm:%s_hb_3\", \"y\": 180 },\r\n"
		+ "        \"depth=3,facing=west\":  { \"model\": \"famm:%s_hb_3\", \"y\": 270 },\r\n"
		+ "        \"depth=3,facing=east\":  { \"model\": \"famm:%s_hb_3\", \"y\": 90 },\r\n"
		+ "        \"depth=2,facing=north\": { \"model\": \"famm:%s_hb_2\" },\r\n"
		+ "        \"depth=2,facing=south\": { \"model\": \"famm:%s_hb_2\", \"y\": 180 },\r\n"
		+ "        \"depth=2,facing=west\":  { \"model\": \"famm:%s_hb_2\", \"y\": 270 },\r\n"
		+ "        \"depth=2,facing=east\":  { \"model\": \"famm:%s_hb_2\", \"y\": 90 },\r\n"
		+ "        \"depth=1,facing=north\": { \"model\": \"famm:%s_hb_1\" },\r\n"
		+ "        \"depth=1,facing=south\": { \"model\": \"famm:%s_hb_1\", \"y\": 180 },\r\n"
		+ "        \"depth=1,facing=west\":  { \"model\": \"famm:%s_hb_1\", \"y\": 270 },\r\n"
		+ "        \"depth=1,facing=east\":  { \"model\": \"famm:%s_hb_1\", \"y\": 90 },\r\n"
		+ "        \"depth=0,facing=north\": { \"model\": \"famm:%s_hb_0\" },\r\n"
		+ "        \"depth=0,facing=south\": { \"model\": \"famm:%s_hb_0\", \"y\": 180 },\r\n"
		+ "        \"depth=0,facing=west\":  { \"model\": \"famm:%s_hb_0\", \"y\": 270 },\r\n"
		+ "        \"depth=0,facing=east\":  { \"model\": \"famm:%s_hb_0\", \"y\": 90 }\r\n"
		+ "    }\r\n"
		+ "}";
	private static final String FULL_MODEL =
		  "{\r\n"
		+ "    \"parent\": \"block/cube_all\",\r\n"
		+ "    \"textures\": {\r\n"
		+ "        \"all\": \"famm:blocks/%s\"\r\n"
		+ "    }\r\n"
		+ "}";
	private static final String HALF_MODEL_0 =
		  "{\r\n"
		+ "	\"__comment\": \"Designed by FEX___96 with BDcraft Cubik PRO 0.95 Beta\",\r\n"
		+ "	\"___comment\": \"Auto-generated Asset via FAMM\",\r\n"
		+ "	\"textures\": {\r\n"
		+ "	    \"texture\": \"famm:blocks/%s\",\r\n"
		+ "	    \"empty\": \"famm:blocks/empty\"\r\n"
		+ "	},\r\n"
		+ "	\"elements\": [ \r\n"
		+ "		{\r\n"
		+ "		    \"from\": [ 0, 0, 14 ],\r\n"
		+ "		    \"to\": [ 16, 16, 16 ],\r\n"
		+ "		    \"faces\": {\r\n"
		+ "		        \"down\":  { \"uv\": [ 16, 16, 0, 0 ], \"texture\": \"#empty\" },\r\n"
		+ "		        \"up\":    { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#empty\" },\r\n"
		+ "		        \"north\": { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#texture\" },\r\n"
		+ "		        \"south\": { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#texture\" },\r\n"
		+ "		        \"west\":  { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#empty\" },\r\n"
		+ "		        \"east\":  { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#empty\" }\r\n"
		+ "		    }\r\n"
		+ "		}\r\n"
		+ "	]\r\n"
		+ "}";
	private static final String HALF_MODEL_1 =
		  "{\r\n"
		+ "	\"__comment\": \"Designed by FEX___96 with BDcraft Cubik PRO 0.95 Beta\",\r\n"
		+ "	\"___comment\": \"Auto-generated Asset via FAMM\",\r\n"
		+ "	\"textures\": {\r\n"
		+ "	    \"texture\": \"famm:blocks/%s\",\r\n"
		+ "	    \"empty\": \"famm:blocks/empty\"\r\n"
		+ "	},\r\n"
		+ "	\"elements\": [ \r\n"
		+ "		{\r\n"
		+ "		    \"from\": [ 0, 0, 12 ],\r\n"
		+ "		    \"to\": [ 16, 16, 16 ],\r\n"
		+ "		    \"faces\": {\r\n"
		+ "		        \"down\":  { \"uv\": [ 16, 16, 0, 0 ], \"texture\": \"#empty\" },\r\n"
		+ "		        \"up\":    { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#empty\" },\r\n"
		+ "		        \"north\": { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#texture\" },\r\n"
		+ "		        \"south\": { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#texture\" },\r\n"
		+ "		        \"west\":  { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#empty\" },\r\n"
		+ "		        \"east\":  { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#empty\" }\r\n"
		+ "		    }\r\n"
		+ "		}\r\n"
		+ "	]\r\n"
		+ "}";
	private static final String HALF_MODEL_2 =
		  "{\r\n"
		+ "	\"__comment\": \"Designed by FEX___96 with BDcraft Cubik PRO 0.95 Beta\",\r\n"
		+ "	\"___comment\": \"Auto-generated Asset via FAMM\",\r\n"
		+ "	\"textures\": {\r\n"
		+ "	    \"texture\": \"famm:blocks/%s\",\r\n"
		+ "	    \"empty\": \"famm:blocks/empty\"\r\n"
		+ "	},\r\n"
		+ "	\"elements\": [ \r\n"
		+ "		{\r\n"
		+ "		    \"from\": [ 0, 0, 10 ],\r\n"
		+ "		    \"to\": [ 16, 16, 16 ],\r\n"
		+ "		    \"faces\": {\r\n"
		+ "		        \"down\":  { \"uv\": [ 16, 16, 0, 0 ], \"texture\": \"#empty\" },\r\n"
		+ "		        \"up\":    { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#empty\" },\r\n"
		+ "		        \"north\": { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#texture\" },\r\n"
		+ "		        \"south\": { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#texture\" },\r\n"
		+ "		        \"west\":  { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#empty\" },\r\n"
		+ "		        \"east\":  { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#empty\" }\r\n"
		+ "		    }\r\n"
		+ "		}\r\n"
		+ "	]\r\n"
		+ "}";
	private static final String HALF_MODEL_3 =
		  "{\r\n"
		+ "	\"__comment\": \"Designed by FEX___96 with BDcraft Cubik PRO 0.95 Beta\",\r\n"
		+ "	\"___comment\": \"Auto-generated Asset via FAMM\",\r\n"
		+ "	\"textures\": {\r\n"
		+ "	    \"texture\": \"famm:blocks/%s\",\r\n"
		+ "	    \"empty\": \"famm:blocks/empty\"\r\n"
		+ "	},\r\n"
		+ "	\"elements\": [ \r\n"
		+ "		{\r\n"
		+ "		    \"from\": [ 0, 0, 8 ],\r\n"
		+ "		    \"to\": [ 16, 16, 16 ],\r\n"
		+ "		    \"faces\": {\r\n"
		+ "		        \"down\":  { \"uv\": [ 16, 16, 0, 0 ], \"texture\": \"#empty\" },\r\n"
		+ "		        \"up\":    { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#empty\" },\r\n"
		+ "		        \"north\": { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#texture\" },\r\n"
		+ "		        \"south\": { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#texture\" },\r\n"
		+ "		        \"west\":  { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#empty\" },\r\n"
		+ "		        \"east\":  { \"uv\": [ 0, 0, 16, 16 ], \"texture\": \"#empty\" }\r\n"
		+ "		    }\r\n"
		+ "		}\r\n"
		+ "	]\r\n"
		+ "}";
	private static final String FULL_ITEMMODEL =
		  "{\r\n"
		+ "    \"parent\": \"famm:block/%s\",\r\n"
		+ "    \"display\": {\r\n"
		+ "        \"thirdperson\": {\r\n"
		+ "            \"rotation\": [ 10, -45, 170 ],\r\n"
		+ "            \"translation\": [ 0, 1.5, -2.75 ],\r\n"
		+ "            \"scale\": [ 0.375, 0.375, 0.375 ]\r\n"
		+ "        }\r\n"
		+ "    }\r\n"
		+ "}";
	private static final String HALF_ITEMMODEL =
		  "{\r\n"
		+ "    \"parent\": \"famm:block/%s_hb_3\",\r\n"
		+ "    \"display\": {\r\n"
		+ "        \"thirdperson\": {\r\n"
		+ "            \"rotation\": [ 10, -45, 170 ],\r\n"
		+ "            \"translation\": [ 0, 1.5, -2.75 ],\r\n"
		+ "            \"scale\": [ 0.375, 0.375, 0.375 ]\r\n"
		+ "        }\r\n"
		+ "    }\r\n"
		+ "}";
	
}