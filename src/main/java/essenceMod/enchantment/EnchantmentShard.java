package essenceMod.enchantment;

import essenceMod.utility.Reference;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.ResourceLocation;

public class EnchantmentShard extends Enchantment
{
	public EnchantmentShard(int id, int rarity)
	{
		super(id, new ResourceLocation(Reference.MODID + ":shardLoot"), rarity, EnumEnchantmentType.WEAPON);
		this.setName("shardLoot");
	}
	
	public int getMinEnchantability(int level)
	{
		return 1;
	}
	
	public int getMaxEnchantability(int level)
	{
		return Integer.MAX_VALUE;
	}
}