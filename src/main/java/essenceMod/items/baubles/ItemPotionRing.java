package essenceMod.items.baubles;

import java.util.List;
import java.util.UUID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import baubles.api.BaubleType;
import baubles.common.lib.PlayerHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import essenceMod.help.UtilityHelper;

public class ItemPotionRing extends ItemBauble
{
	int level, potionID, cooldown;

	private final AttributeModifier speed = new AttributeModifier(UUID.fromString("91AEAA56-376B-4498-935B-2F7F68070635"), "EssenceArmoryRingSpeed", 0.2D, 2);

	public ItemPotionRing()
	{
		this(0, 0);
	}

	public ItemPotionRing(int level, int potionID)
	{
		super();
		this.level = level;
		this.potionID = potionID;
		MinecraftForge.EVENT_BUS.register(this);
	}

	@Override
	public BaubleType getBaubleType(ItemStack item)
	{
		return BaubleType.RING;
	}

	@Override
	public void onUnequipped(ItemStack item, EntityLivingBase player)
	{
		super.onUnequipped(item, player);

		if (item.hasTagCompound())
		{
			if (item.stackTagCompound.getInteger("PotionID") == Potion.nightVision.id) player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 1, 1));
		}

		if (player instanceof EntityPlayer)
		{
			EntityPlayer p = (EntityPlayer) player;
			UUID playerID = p.getGameProfile().getId();
			IAttributeInstance attribute = p.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.movementSpeed);
			if (attribute != null)
			{
				if (potionID == Potion.moveSpeed.id)
				{
					attribute.removeModifier(speed);
				}
			}
		}
	}

	@Override
	public void onCreated(ItemStack item, World world, EntityPlayer player)
	{
		super.onCreated(item, world, player);

		item.stackTagCompound.setInteger("Level", level);
		item.stackTagCompound.setInteger("PotionID", potionID);
	}

	@Override
	public void onWornTick(ItemStack item, EntityLivingBase player)
	{
		super.onWornTick(item, player);

		int level = item.stackTagCompound.getInteger("Level");
		int potionID = item.stackTagCompound.getInteger("PotionID");
		if (potionID == Potion.regeneration.id)
		{
			if (cooldown != 0) cooldown--;
			else if (cooldown == 0)
			{
				player.heal(1);
				cooldown = 60 / ((ItemPotionRing) item.getItem()).getLevel(item);
			}
		}
		if (potionID == Potion.nightVision.id) player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 220, 0));
		if (potionID == Potion.waterBreathing.id) player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 20, 0));
		if (potionID == Potion.fireResistance.id) player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 20, 0));
	}

	public int getLevel(ItemStack item)
	{
		return item.stackTagCompound.getInteger("Level");
	}

	public int getPotionID(ItemStack item)
	{
		return item.stackTagCompound.getInteger("PotionID");
	}

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean bool)
	{
		String info = "";
		if (itemStack.stackTagCompound == null) onCreated(itemStack, entityPlayer.worldObj, entityPlayer);

		if (itemStack.stackTagCompound.hasKey("Level"))
		{
			int level = itemStack.stackTagCompound.getInteger("Level");
			info = UtilityHelper.toRoman(level);
		}

		if (itemStack.stackTagCompound.hasKey("PotionID"))
		{
			int potionID = itemStack.stackTagCompound.getInteger("PotionID");
			String potionName;
			switch (potionID)
			{
				case 1:
					potionName = "Swiftness";
					break;
				case 3:
					potionName = "Haste";
					break;
				case 5:
					potionName = "Strength";
					break;
				case 8:
					potionName = "Jump Boost";
					break;
				case 10:
					potionName = "Regeneration";
					break;
				case 12:
					potionName = "Fire Resistance";
					break;
				case 13:
					potionName = "Water Breathing";
					break;
				case 16:
					potionName = "Night Vision";
					break;
				default:
					potionName = "No Effect";
			}
			info = potionName + " " + info;
		}
		list.add(info);
	}

	@SubscribeEvent
	public void onLivingHurt(LivingHurtEvent event)
	{
		if (event.source.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.source.getEntity();
			ItemStack ring1 = PlayerHandler.getPlayerBaubles(player).getStackInSlot(1);
			ItemStack ring2 = PlayerHandler.getPlayerBaubles(player).getStackInSlot(2);
			int strengthLevel = 0;
			if (ring1 != null && ring1.getItem() instanceof ItemPotionRing)
			{
				int potionID = ((ItemPotionRing) ring1.getItem()).getPotionID(ring1);
				if (potionID == Potion.damageBoost.id) strengthLevel = ((ItemPotionRing) ring1.getItem()).getLevel(ring1);
			}
			if (ring2 != null && ring2.getItem() instanceof ItemPotionRing)
			{
				int potionID = ((ItemPotionRing) ring2.getItem()).getPotionID(ring2);
				if (potionID == Potion.damageBoost.id) strengthLevel = Math.max(strengthLevel, ((ItemPotionRing) ring2.getItem()).getLevel(ring2));
			}
			event.ammount += event.ammount * strengthLevel / 40.0F;
		}
		if (event.entityLiving instanceof EntityPlayer && event.source.getDamageType() == event.source.fall.getDamageType())
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			ItemStack ring1 = PlayerHandler.getPlayerBaubles(player).getStackInSlot(1);
			ItemStack ring2 = PlayerHandler.getPlayerBaubles(player).getStackInSlot(2);
			int jumpLevel = 0;
			if (ring1 != null && ring1.getItem() instanceof ItemPotionRing)
			{
				int potionID = ((ItemPotionRing) ring1.getItem()).getPotionID(ring1);
				if (potionID == Potion.jump.id) jumpLevel = ((ItemPotionRing) ring1.getItem()).getLevel(ring1);
			}
			if (ring2 != null && ring2.getItem() instanceof ItemPotionRing)
			{
				int potionID = ((ItemPotionRing) ring2.getItem()).getPotionID(ring2);
				if (potionID == Potion.jump.id) jumpLevel = Math.max(jumpLevel, ((ItemPotionRing) ring2.getItem()).getLevel(ring2));
			}
			event.ammount -= jumpLevel / 20;
		}
	}

	@SubscribeEvent
	public void updatePlayerSwiftness(LivingUpdateEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			ItemStack ring1 = PlayerHandler.getPlayerBaubles(player).getStackInSlot(1);
			ItemStack ring2 = PlayerHandler.getPlayerBaubles(player).getStackInSlot(2);
			int swiftnessLevel = 0;
			if (ring1 != null && ring1.getItem() instanceof ItemPotionRing)
			{
				int potionID = ((ItemPotionRing) ring1.getItem()).getPotionID(ring1);
				if (potionID == Potion.moveSpeed.id) swiftnessLevel = ((ItemPotionRing) ring1.getItem()).getLevel(ring1);
			}
			if (ring2 != null && ring2.getItem() instanceof ItemPotionRing)
			{
				int potionID = ((ItemPotionRing) ring2.getItem()).getPotionID(ring2);
				if (potionID == Potion.moveSpeed.id) swiftnessLevel = Math.max(swiftnessLevel, ((ItemPotionRing) ring2.getItem()).getLevel(ring2));
			}
			UUID playerID = player.getGameProfile().getId();
			IAttributeInstance attribute = player.getAttributeMap().getAttributeInstance(SharedMonsterAttributes.movementSpeed);
			if (attribute != null)
			{
				if (potionID == Potion.moveSpeed.id)
				{
					double current = attribute.getAttributeValue();
					double next = speed.getAmount() * level;
					attribute.removeModifier(speed);
					attribute.applyModifier(new AttributeModifier(speed.getID(), speed.getName() + swiftnessLevel, speed.getAmount() * swiftnessLevel, speed.getOperation()));
				}
			}
		}
	}

	@SubscribeEvent
	public void onPlayerMine(BreakSpeed event)
	{
		EntityPlayer player = event.entityPlayer;
		ItemStack ring1 = PlayerHandler.getPlayerBaubles(player).getStackInSlot(1);
		ItemStack ring2 = PlayerHandler.getPlayerBaubles(player).getStackInSlot(2);
		int hasteLevel = 0;
		if (ring1 != null && ring1.getItem() instanceof ItemPotionRing)
		{
			int potionID = ((ItemPotionRing) ring1.getItem()).getPotionID(ring1);
			if (potionID == Potion.digSpeed.id) hasteLevel = ((ItemPotionRing) ring1.getItem()).getLevel(ring1);
		}
		if (ring2 != null && ring2.getItem() instanceof ItemPotionRing)
		{
			int potionID = ((ItemPotionRing) ring2.getItem()).getPotionID(ring2);
			if (potionID == Potion.digSpeed.id) hasteLevel = Math.max(hasteLevel, ((ItemPotionRing) ring2.getItem()).getLevel(ring2));
		}
		event.newSpeed *= (1 + 0.01F * hasteLevel);
	}

	@SubscribeEvent
	public void onPlayerJump(LivingJumpEvent event)
	{
		if (event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			ItemStack ring1 = PlayerHandler.getPlayerBaubles(player).getStackInSlot(1);
			ItemStack ring2 = PlayerHandler.getPlayerBaubles(player).getStackInSlot(2);
			int jumpLevel = 0;
			if (ring1 != null && ring1.getItem() instanceof ItemPotionRing)
			{
				int potionID = ((ItemPotionRing) ring1.getItem()).getPotionID(ring1);
				if (potionID == Potion.jump.id) jumpLevel = ((ItemPotionRing) ring1.getItem()).getLevel(ring1);
			}
			if (ring2 != null && ring2.getItem() instanceof ItemPotionRing)
			{
				int potionID = ((ItemPotionRing) ring2.getItem()).getPotionID(ring2);
				if (potionID == Potion.jump.id) jumpLevel = Math.max(jumpLevel, ((ItemPotionRing) ring2.getItem()).getLevel(ring2));
			}
			switch (jumpLevel)
			{
				case 1:
					player.motionY += 0.01F;
					break;
				case 2:
					player.motionY += 0.016F;
					break;
				case 3:
					player.motionY += 0.023F;
					break;
			}
		}
	}
}