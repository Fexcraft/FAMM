package net.fexcraft.mod.famm.items;

import net.fexcraft.lib.mc.registry.FCLRegistry;
import net.fexcraft.mod.famm.FAMM;
import net.minecraft.item.Item;

public class PlatePainted extends Item {
	
	public PlatePainted(){
		setCreativeTab(FAMM.tabFAMM);
		FCLRegistry.getAutoRegistry("famm").addItem("plate_painted", this, 0, null);
	}
	
}