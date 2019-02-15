package net.fexcraft.mod.famm.items;

import net.fexcraft.lib.mc.registry.FCLRegistry;
import net.fexcraft.mod.famm.FAMM;
import net.minecraft.item.Item;

public class PlateEmpty extends Item {
	
	public PlateEmpty(){
		setCreativeTab(FAMM.tabFAMM);
		FCLRegistry.getAutoRegistry("famm").addItem("plate_empty", this, 0, null);
	}
	
}