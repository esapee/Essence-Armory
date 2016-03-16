package essenceMod.handlers;

import net.minecraftforge.oredict.OreDictionary;
import essenceMod.registry.ModBlocks;
import essenceMod.registry.ModItems;

public class OreDictHandler
{
	public OreDictHandler()
	{
		OreDictionary.registerOre("nuggetEssenceInfused", ModItems.infusedNugget);
		OreDictionary.registerOre("ingotEssenceInfused", ModItems.infusedIngot);
		OreDictionary.registerOre("blockEssenceInfused", ModBlocks.infusedBlock);
		OreDictionary.registerOre("blockInfusedStarmetal", ModBlocks.infusedStarmetal);
	}
}
