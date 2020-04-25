package net.fexcraft.mod.famm;

import net.fexcraft.lib.mc.registry.FCLRegistry;
import net.fexcraft.mod.famm.blocks.FAMMBlocks;
import net.fexcraft.mod.famm.gui.GuiHandler;
import net.fexcraft.mod.famm.items.FAMMItems;
import net.fexcraft.mod.famm.util.FAMMEventHandler;
import net.fexcraft.mod.famm.util.UpdateHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = FAMM.MODID, name = "Fex's Alphabet & More Mod", version = FAMM.VERSION, updateJSON = "http://fexcraft.net/minecraft/fcl/request?mode=getForgeUpdateJson&modid=famm", dependencies = "required-after:fcl")
public class FAMM {
	
	public static final String MODID = "famm";
	public static final String VERSION = "3.3.0";
	
	//public static boolean conf1;
	public static boolean conf2;
	public static boolean conf3;
	public static boolean conf4;
	public static boolean conf5;
	public static boolean conf6;
	//
    @Mod.Instance(MODID)
	public static FAMM INSTANCE;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event){
		FCLRegistry.newAutoRegistry("famm");
		
		//Config Begin
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		String category = "###{> Blocks <]###";
	    config.load();
	    //conf1 = config.getBoolean("enable_update_checker", "###[> General <]###", true, "Update Checker");
	    conf2 = config.getBoolean("enable_default_blocks", category, true, "Should default FAMM Blocks be enabled?");
	    conf3 = config.getBoolean("enable_half_blocks", category, true, "Should FAMM HalfBlocks be enabled?.");
	    conf4 = config.getBoolean("enable_centered_half_blocks", category, true, "Should FAMM Centered HalfBlocks be enabled?");
	    conf5 = config.getBoolean("enable_slabs", category, true, "Should FAMM Slabs be enabled?");
	    conf6 = config.getBoolean("enable_tiles", category, true, "Should FAMM Tiles be enabled?");
	    config.save();
	    //Config End
		
		if(FAMM.conf2 == true){
			FAMMBlocks.initializeDefault();
		}
		if(FAMM.conf3 == true){
			FAMMBlocks.initializeHalfBlocks();
		}
		
		FAMMItems.init();
	}
	
	public static CreativeTabs tabFAMM = new CreativeTabs("tabFAMM"){
		@Override
		public ItemStack createIcon(){
			return new ItemStack(Item.getItemFromBlock(FAMMBlocks.get("f")));
		}
	};
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event){
		MinecraftForge.EVENT_BUS.register(new FAMMEventHandler());
		UpdateHandler.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new GuiHandler());
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event){
		
	}
}
