package essenceMod.items;

import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import essenceMod.help.Reference;
import essenceMod.tabs.ModTabs;

public class ItemModSword extends ItemSword implements IModItem
{
	public final ToolMaterial toolMaterial;
	protected float weaponDamage;
	
	int level, fire, poison, pierce, lifesteal, knockback, regen, blind, slow, damage, wither;

	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int i, boolean b)
	{
		if (itemStack.stackTagCompound == null) onCreated(itemStack, world, (EntityPlayer) entity);
	}

	public ItemModSword(ToolMaterial material)
	{
		super(material);
		toolMaterial = material;
		setCreativeTab(ModTabs.tabEssence);
		setMaxDamage(0);
	}
	
	public ItemModSword(ToolMaterial material, String[] upgrades)
	{
		this(material);
		
		level = upgrades.length;
		for (String str: upgrades)
		{
			if (str.equals("Fire")) fire++;
			else if (str.equals("Poison")) poison++;
			else if (str.equals("Pierce")) pierce++;
			else if (str.equals("Lifesteal")) lifesteal++;
			else if (str.equals("Knockback")) knockback++;
			else if (str.equals("Regen")) regen++;
			else if (str.equals("Blind")) blind++;
			else if (str.equals("Slow")) slow++;
			else if (str.equals("Damage")) damage++;
			else if (str.equals("Wither")) wither++;
		}
	}

	@Override
	public Multimap getAttributeModifiers(ItemStack stack)
	{
		Multimap multimap = HashMultimap.create();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(), new AttributeModifier(field_111210_e, "Weapon modifier", stack.stackTagCompound.getFloat("Damage"), 0));
		return multimap;
	}

	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer entityPlayer)
	{
		if (itemStack.stackTagCompound == null) itemStack.setTagCompound(new NBTTagCompound());
		weaponDamage = 4.0F + toolMaterial.getDamageVsEntity() * (level + 1) + damage * 2.5F;
		weaponDamage = 4.0F + toolMaterial.getDamageVsEntity() * (level + 1);

		itemStack.stackTagCompound.setInteger("Level", level);
		itemStack.stackTagCompound.setFloat("Damage", weaponDamage);
	}

	public boolean hitEntity(ItemStack stack, EntityLivingBase enemy, EntityLivingBase player)
	{
		DamageSource playerDamage = DamageSource.causePlayerDamage((EntityPlayer) player);
		DamageSource fireDamage = DamageSource.onFire;
		DamageSource witherDamage = DamageSource.wither;
		DamageSource magicDamage = DamageSource.magic;
		if (stack.stackTagCompound.hasKey("Fire"))
		{
			float fireMult = stack.stackTagCompound.getInteger("Fire") / 5F;
			enemy.attackEntityFrom(fireDamage, weaponDamage * fireMult);
		}
		if (stack.stackTagCompound.hasKey("Wither"))
		{
			float witherMult = stack.stackTagCompound.getInteger("Wither") / 5F;
			enemy.attackEntityFrom(witherDamage, weaponDamage * witherMult);
		}
		if (stack.stackTagCompound.hasKey("Poison"))
		{
			
		}
//		if (stack.stackTagCompound.hasKey("Levels"))
//		{
//			int[] levels = stack.stackTagCompound.getIntArray("Levels");
//			enemy.setFire(levels[0]);
//			enemy.addPotionEffect(new PotionEffect(Potion.poison.id, 25 * levels[0], levels[0] - 1));
//			enemy.addPotionEffect(new PotionEffect(Potion.blindness.id, 20 * levels[6], levels[6] - 1));
//			enemy.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 20 * levels[7], levels[7] - 1));
//			enemy.addPotionEffect(new PotionEffect(Potion.wither.id, 20 * levels[9], levels[9] - 1));
//			player.heal(levels[3]);
//			player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 25, levels[5] - 1));
//			if (levels[4] != 0)
//			{
//				enemy.addVelocity((double) (-MathHelper.sin(player.rotationYaw * (float) Math.PI / 180.0F) * (float) levels[4] * 0.5F), 0.1D, (double) (MathHelper.cos(player.rotationYaw * (float) Math.PI / 180.0F) * (float) levels[4] * 0.5F));
//			}
//			float pierceMultiplier = ((1F / (1F - (enemy.getTotalArmorValue() * 0.04F)) - 1F) * levels[2] / 5F);
//			enemy.attackEntityFrom(damageSource, weaponDamage * pierceMultiplier);
//		}
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		this.itemIcon = iconRegister.registerIcon(Reference.MODID + ":" + getUnlocalizedName().substring(5));
	}

	@Override
	public boolean hasEffect(ItemStack itemStack)
	{
		return true;
	}

	public static int getLevel(ItemStack item)
	{
		return item.stackTagCompound.getInteger("Level");
	}

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool)
	{
		if (itemStack.stackTagCompound == null) onCreated(itemStack, entityPlayer.worldObj, entityPlayer);

		if (itemStack.stackTagCompound.hasKey("Level")) list.add("Level: " + itemStack.stackTagCompound.getInteger("Level"));
		if (itemStack.stackTagCompound.hasKey("Levels") && itemStack.stackTagCompound.hasKey("Kills"))
		{
			int[] levels = itemStack.stackTagCompound.getIntArray("Levels");
			int[] kills = itemStack.stackTagCompound.getIntArray("Kills");
			String[] enchants = new String[] { "Flame", "Poison", "Pierce", "Leech", "Knockback", "Regeneration", "Blind", "Slow", "Sharpness", "Wither" };
			String[] mobs = new String[] { "Blaze", "Cave Spider", "Enderman", "Zombie Pigman", "Skeleton", "Slime", "Spider", "Witch", "Zombie", "Wither Skeleton" };
			String[] code = new String[] { "c", "2", "5", "4", "f", "a", "8", "7", "9", "0" };
			for (int i = 0; i < levels.length; i++)
			{
				if (levels[i] != 0 || kills[i] != 0)
				{
					list.add("�" + code[i] + enchants[i] + " " + levels[i] + ", " + mobs[i] + ": " + kills[i]);
				}
			}
		}
	}
}