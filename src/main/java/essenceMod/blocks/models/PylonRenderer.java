package essenceMod.blocks.models;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import essenceMod.entities.tileEntities.TileEntityEssencePylon;
import essenceMod.utility.Reference;

public class PylonRenderer extends TileEntitySpecialRenderer
{
	private RenderManager manager;
	
	public PylonRenderer()
	{
		manager = Minecraft.getMinecraft().getRenderManager();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale, int i)
	{
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x, (float) y, (float) z + 1);
		ResourceLocation textures = new ResourceLocation(Reference.MODID + ":textures/blocks/essencePylon.png");
		Minecraft.getMinecraft().renderEngine.bindTexture(textures);
		drawPylon(x, y, z);
		GL11.glPopMatrix();
		
		if (te instanceof TileEntityEssencePylon)
		{
			TileEntityEssencePylon pylon = (TileEntityEssencePylon) te;
			GL11.glPushMatrix();
			GL11.glTranslated(x + 0.5, y + 1.0, z + 0.5);
			if (pylon.getStackInSlot(0) != null)
			{
				EntityItem item = new EntityItem(pylon.getWorld());
				item.hoverStart = 0.0F;
				item.setEntityItemStack(pylon.getStackInSlot(0));
				manager.doRenderEntity(item, 0, 0, 0, 0, 0, true);
			}
			GL11.glPopMatrix();
		}
	}
	
	private void drawPylon(double x, double y, double z)
	{
		WorldRenderer wr = Tessellator.getInstance().getWorldRenderer();
		GlStateManager.pushAttrib();
		GlStateManager.pushMatrix();
		GlStateManager.color(1, 1, 1);
//		wr.setTranslation(x, y, z);
		wr.begin(GL11.GL_TRIANGLES, DefaultVertexFormats.POSITION_TEX_NORMAL);
		
		wr.pos(0.75, 0.25, -0.75).tex(0.375, -0.375).normal(1.0F, 0.0F, -0.0F);
		wr.pos(0.75, 0.75, -0.625).tex(0.34375, -0.25).normal(1.0F, 0.0F, -0.0F);
		wr.pos(0.75, 0.25, -0.625).tex(0.34375, -0.375).normal(1.0F, 0.0F, -0.0F);

		wr.pos(0.75, 0.75, -0.625).tex(0.34375, -0.25).normal(1.0F, 0.0F, -0.0F);
		wr.pos(0.75, 0.25, -0.75).tex(0.375, -0.375).normal(1.0F, 0.0F, -0.0F);
		wr.pos(0.75, 0.75, -0.75).tex(0.375, -0.25).normal(1.0F, 0.0F, -0.0F);

		wr.pos(0.75, 0.25, -0.75).tex(-0.625, -0.375).normal(0.0F, 0.0F, -1.0F);
		wr.pos(0.625, 0.75, -0.75).tex(-0.59375, -0.25).normal(0.0F, 0.0F, -1.0F);
		wr.pos(0.75, 0.75, -0.75).tex(-0.625, -0.25).normal(0.0F, 0.0F, -1.0F);

		wr.pos(0.625, 0.75, -0.75).tex(-0.59375, -0.25).normal(0.0F, 0.0F, -1.0F);
		wr.pos(0.75, 0.25, -0.75).tex(-0.625, -0.375).normal(0.0F, 0.0F, -1.0F);
		wr.pos(0.625, 0.25, -0.75).tex(-0.59375, -0.375).normal(0.0F, 0.0F, -1.0F);

		wr.pos(0.75, 0.25, -0.75).tex(0.1875, -0.3125).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.625, 0.25, -1.0).tex(0.15625, -0.25).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.625, 0.25, -0.75).tex(0.15625, -0.3125).normal(0.0F, 1.0F, -0.0F);

		wr.pos(0.625, 0.25, -1.0).tex(0.15625, -0.25).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.75, 0.25, -0.75).tex(0.1875, -0.3125).normal(0.0F, 1.0F, -0.0F);
		wr.pos(1.0, 0.25, -1.0).tex(0.25, -0.25).normal(0.0F, 1.0F, -0.0F);

		wr.pos(1.0, 0.25, -1.0).tex(0.25, -0.25).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.75, 0.25, -0.75).tex(0.1875, -0.3125).normal(0.0F, 1.0F, -0.0F);
		wr.pos(1.0, 0.25, -0.625).tex(0.25, -0.34375).normal(0.0F, 1.0F, -0.0F);

		wr.pos(1.0, 0.25, -0.625).tex(0.25, -0.34375).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.75, 0.25, -0.75).tex(0.1875, -0.3125).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.75, 0.25, -0.625).tex(0.1875, -0.34375).normal(0.0F, 1.0F, -0.0F);

		wr.pos(0.75, 0.75, -0.625).tex(-0.375, -0.25).normal(0.0F, 0.0F, -1.0F);
		wr.pos(1.0, 0.25, -0.625).tex(-0.3125, -0.125).normal(0.0F, 0.0F, -1.0F);
		wr.pos(0.75, 0.25, -0.625).tex(-0.375, -0.125).normal(0.0F, 0.0F, -1.0F);

		wr.pos(1.0, 0.25, -0.625).tex(-0.3125, -0.125).normal(0.0F, 0.0F, -1.0F);
		wr.pos(0.75, 0.75, -0.625).tex(-0.375, -0.25).normal(0.0F, 0.0F, -1.0F);
		wr.pos(0.875, 0.75, -0.625).tex(-0.34375, -0.25).normal(0.0F, 0.0F, -1.0F);

		wr.pos(0.625, 1.0, -0.5625).tex(-0.3125, -0.375).normal(0.0F, 0.242536F, -0.970143F);
		wr.pos(0.875, 0.75, -0.625).tex(-0.375, -0.4375).normal(0.0F, 0.242536F, -0.970143F);
		wr.pos(0.75, 0.75, -0.625).tex(-0.34375, -0.4375).normal(0.0F, 0.242536F, -0.970143F);

		wr.pos(0.625, 1.0, -0.4375).tex(0.578125, -0.234375).normal(0.707107F, 0.707107F, 0.0F);
		wr.pos(0.875, 0.75, -0.625).tex(0.625, -0.3125).normal(0.707107F, 0.707107F, 0.0F);
		wr.pos(0.625, 1.0, -0.5625).tex(0.609375, -0.234375).normal(0.707107F, 0.707107F, 0.0F);

		wr.pos(0.875, 0.75, -0.625).tex(0.625, -0.3125).normal(0.707107F, 0.707107F, 0.0F);
		wr.pos(0.625, 1.0, -0.4375).tex(0.578125, -0.234375).normal(0.707107F, 0.707107F, 0.0F);
		wr.pos(0.875, 0.75, -0.375).tex(0.5625, -0.3125).normal(0.707107F, 0.707107F, 0.0F);

		wr.pos(0.625, 1.0, -0.4375).tex(0.625, -0.5).normal(0.0F, 0.242536F, 0.970143F);
		wr.pos(0.75, 0.75, -0.375).tex(0.59375, -0.4375).normal(0.0F, 0.242536F, 0.970143F);
		wr.pos(0.875, 0.75, -0.375).tex(0.5625, -0.4375).normal(0.0F, 0.242536F, 0.970143F);

		wr.pos(0.625, 1.0, -0.4375).tex(-0.390625, -0.3125).normal(-0.894427F, -0.447214F, 0.0F);
		wr.pos(0.75, 0.75, -0.625).tex(-0.4375, -0.375).normal(-0.894427F, -0.447214F, 0.0F);
		wr.pos(0.75, 0.75, -0.375).tex(-0.375, -0.375).normal(-0.894427F, -0.447214F, 0.0F);

		wr.pos(0.75, 0.75, -0.625).tex(-0.4375, -0.375).normal(-0.894427F, -0.447214F, 0.0F);
		wr.pos(0.625, 1.0, -0.4375).tex(-0.390625, -0.3125).normal(-0.894427F, -0.447214F, 0.0F);
		wr.pos(0.625, 1.0, -0.5625).tex(-0.421875, -0.3125).normal(-0.894427F, -0.447214F, 0.0F);

		wr.pos(0.75, 0.75, -0.75).tex(0.1875, -0.3125).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.75, 0.75, -0.375).tex(0.1875, -0.40625).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.75, 0.75, -0.625).tex(0.1875, -0.34375).normal(0.0F, 1.0F, -0.0F);

		wr.pos(0.75, 0.75, -0.75).tex(0.1875, -0.3125).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.75, 0.75, -0.25).tex(0.1875, -0.4375).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.75, 0.75, -0.375).tex(0.1875, -0.40625).normal(0.0F, 1.0F, -0.0F);

		wr.pos(0.625, 0.75, -0.75).tex(0.15625, -0.3125).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.75, 0.75, -0.25).tex(0.1875, -0.4375).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.75, 0.75, -0.75).tex(0.1875, -0.3125).normal(0.0F, 1.0F, -0.0F);

		wr.pos(0.625, 0.75, -0.75).tex(0.15625, -0.3125).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.625, 0.75, -0.25).tex(0.15625, -0.4375).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.75, 0.75, -0.25).tex(0.1875, -0.4375).normal(0.0F, 1.0F, -0.0F);

		wr.pos(0.375, 0.75, -0.75).tex(0.09375, -0.3125).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.625, 0.75, -0.25).tex(0.15625, -0.4375).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.625, 0.75, -0.75).tex(0.15625, -0.3125).normal(0.0F, 1.0F, -0.0F);

		wr.pos(0.375, 0.75, -0.75).tex(0.09375, -0.3125).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.375, 0.75, -0.25).tex(0.09375, -0.4375).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.625, 0.75, -0.25).tex(0.15625, -0.4375).normal(0.0F, 1.0F, -0.0F);

		wr.pos(0.25, 0.75, -0.75).tex(0.0625, -0.3125).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.375, 0.75, -0.25).tex(0.09375, -0.4375).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.375, 0.75, -0.75).tex(0.09375, -0.3125).normal(0.0F, 1.0F, -0.0F);

		wr.pos(0.25, 0.75, -0.625).tex(0.0625, -0.34375).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.375, 0.75, -0.25).tex(0.09375, -0.4375).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.25, 0.75, -0.75).tex(0.0625, -0.3125).normal(0.0F, 1.0F, -0.0F);

		wr.pos(0.25, 0.75, -0.375).tex(0.0625, -0.40625).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.375, 0.75, -0.25).tex(0.09375, -0.4375).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.25, 0.75, -0.625).tex(0.0625, -0.34375).normal(0.0F, 1.0F, -0.0F);

		wr.pos(0.375, 0.75, -0.25).tex(0.09375, -0.4375).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.25, 0.75, -0.375).tex(0.0625, -0.40625).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.25, 0.75, -0.25).tex(0.0625, -0.4375).normal(0.0F, 1.0F, -0.0F);

		wr.pos(0.25, 0.75, -0.375).tex(-0.53125, -0.25).normal(-1.0F, 0.0F, -0.0F);
		wr.pos(0.25, 0.25, -0.25).tex(-0.5, -0.375).normal(-1.0F, 0.0F, -0.0F);
		wr.pos(0.25, 0.75, -0.25).tex(-0.5, -0.25).normal(-1.0F, 0.0F, -0.0F);

		wr.pos(0.25, 0.25, -0.25).tex(-0.5, -0.375).normal(-1.0F, 0.0F, -0.0F);
		wr.pos(0.25, 0.75, -0.375).tex(-0.53125, -0.25).normal(-1.0F, 0.0F, -0.0F);
		wr.pos(0.25, 0.25, -0.375).tex(-0.53125, -0.375).normal(-1.0F, 0.0F, -0.0F);

		wr.pos(0.25, 0.75, -0.375).tex(0.5, -0.25).normal(0.0F, 0.0F, 1.0F);
		wr.pos(0.0, 0.25, -0.375).tex(0.5625, -0.125).normal(0.0F, 0.0F, 1.0F);
		wr.pos(0.25, 0.25, -0.375).tex(0.5, -0.125).normal(0.0F, 0.0F, 1.0F);

		wr.pos(0.0, 0.25, -0.375).tex(0.5625, -0.125).normal(0.0F, 0.0F, 1.0F);
		wr.pos(0.25, 0.75, -0.375).tex(0.5, -0.25).normal(0.0F, 0.0F, 1.0F);
		wr.pos(0.125, 0.75, -0.375).tex(0.53125, -0.25).normal(0.0F, 0.0F, 1.0F);

		wr.pos(0.375, 1.0, -0.4375).tex(0.5625, -0.375).normal(0.0F, 0.242536F, 0.970143F);
		wr.pos(0.125, 0.75, -0.375).tex(0.5, -0.4375).normal(0.0F, 0.242536F, 0.970143F);
		wr.pos(0.25, 0.75, -0.375).tex(0.53125, -0.4375).normal(0.0F, 0.242536F, 0.970143F);

		wr.pos(0.375, 1.0, -0.4375).tex(-0.265625, 0.75).normal(-0.707107F, 0.707107F, 0.0F);
		wr.pos(0.125, 0.75, -0.625).tex(-0.3125, 0.6875).normal(-0.707107F, 0.707107F, 0.0F);
		wr.pos(0.125, 0.75, -0.375).tex(-0.25, 0.6875).normal(-0.707107F, 0.707107F, 0.0F);

		wr.pos(0.125, 0.75, -0.625).tex(-0.3125, 0.6875).normal(-0.707107F, 0.707107F, 0.0F);
		wr.pos(0.375, 1.0, -0.4375).tex(-0.265625, 0.75).normal(-0.707107F, 0.707107F, 0.0F);
		wr.pos(0.375, 1.0, -0.5625).tex(-0.296875, 0.75).normal(-0.707107F, 0.707107F, 0.0F);

		wr.pos(0.375, 1.0, -0.4375).tex(0.703125, -0.3125).normal(0.894427F, -0.447214F, 0.0F);
		wr.pos(0.25, 0.75, -0.625).tex(0.75, -0.375).normal(0.894427F, -0.447214F, 0.0F);
		wr.pos(0.375, 1.0, -0.5625).tex(0.734375, -0.3125).normal(0.894427F, -0.447214F, 0.0F);

		wr.pos(0.25, 0.75, -0.625).tex(0.75, -0.375).normal(0.894427F, -0.447214F, 0.0F);
		wr.pos(0.375, 1.0, -0.4375).tex(0.703125, -0.3125).normal(0.894427F, -0.447214F, 0.0F);
		wr.pos(0.25, 0.75, -0.375).tex(0.6875, -0.375).normal(0.894427F, -0.447214F, 0.0F);

		wr.pos(0.375, 1.0, -0.5625).tex(-0.25, -0.5).normal(0.0F, 0.242536F, -0.970143F);
		wr.pos(0.25, 0.75, -0.625).tex(-0.28125, -0.4375).normal(0.0F, 0.242536F, -0.970143F);
		wr.pos(0.125, 0.75, -0.625).tex(-0.3125, -0.4375).normal(0.0F, 0.242536F, -0.970143F);

		wr.pos(0.25, 0.25, -0.625).tex(-0.375, -0.125).normal(0.0F, 0.0F, -1.0F);
		wr.pos(0.125, 0.75, -0.625).tex(-0.34375, 0.0).normal(0.0F, 0.0F, -1.0F);
		wr.pos(0.25, 0.75, -0.625).tex(-0.375, 0.0).normal(0.0F, 0.0F, -1.0F);

		wr.pos(0.125, 0.75, -0.625).tex(-0.34375, 0.0).normal(0.0F, 0.0F, -1.0F);
		wr.pos(0.25, 0.25, -0.625).tex(-0.375, -0.125).normal(0.0F, 0.0F, -1.0F);
		wr.pos(0.0, 0.25, -0.625).tex(-0.3125, -0.125).normal(0.0F, 0.0F, -1.0F);

		wr.pos(0.25, 0.25, -0.625).tex(0.0625, -0.34375).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.0, 0.25, -1.0).tex(0.0, -0.25).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.0, 0.25, -0.625).tex(0.0, -0.34375).normal(0.0F, 1.0F, -0.0F);

		wr.pos(0.0, 0.25, -1.0).tex(0.0, -0.25).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.25, 0.25, -0.625).tex(0.0625, -0.34375).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.25, 0.25, -0.75).tex(0.0625, -0.3125).normal(0.0F, 1.0F, -0.0F);

		wr.pos(0.25, 0.25, -0.625).tex(-0.53125, -0.375).normal(-1.0F, 0.0F, -0.0F);
		wr.pos(0.25, 0.75, -0.75).tex(-0.5625, -0.25).normal(-1.0F, 0.0F, -0.0F);
		wr.pos(0.25, 0.25, -0.75).tex(-0.5625, -0.375).normal(-1.0F, 0.0F, -0.0F);

		wr.pos(0.25, 0.75, -0.75).tex(-0.5625, -0.25).normal(-1.0F, 0.0F, -0.0F);
		wr.pos(0.25, 0.25, -0.625).tex(-0.53125, -0.375).normal(-1.0F, 0.0F, -0.0F);
		wr.pos(0.25, 0.75, -0.625).tex(-0.53125, -0.25).normal(-1.0F, 0.0F, -0.0F);

		wr.pos(0.25, 0.75, -0.75).tex(-0.5625, -0.25).normal(0.0F, 0.0F, -1.0F);
		wr.pos(0.375, 0.25, -0.75).tex(-0.59375, -0.375).normal(0.0F, 0.0F, -1.0F);
		wr.pos(0.25, 0.25, -0.75).tex(-0.5625, -0.375).normal(0.0F, 0.0F, -1.0F);

		wr.pos(0.375, 0.25, -0.75).tex(-0.59375, -0.375).normal(0.0F, 0.0F, -1.0F);
		wr.pos(0.25, 0.75, -0.75).tex(-0.5625, -0.25).normal(0.0F, 0.0F, -1.0F);
		wr.pos(0.375, 0.75, -0.75).tex(-0.59375, -0.25).normal(0.0F, 0.0F, -1.0F);

		wr.pos(0.375, 0.75, -0.875).tex(-0.28125, -0.25).normal(-1.0F, 0.0F, -0.0F);
		wr.pos(0.375, 0.25, -0.75).tex(-0.3125, -0.125).normal(-1.0F, 0.0F, -0.0F);
		wr.pos(0.375, 0.75, -0.75).tex(-0.3125, -0.25).normal(-1.0F, 0.0F, -0.0F);

		wr.pos(0.375, 0.25, -0.75).tex(-0.3125, -0.125).normal(-1.0F, 0.0F, -0.0F);
		wr.pos(0.375, 0.75, -0.875).tex(-0.28125, -0.25).normal(-1.0F, 0.0F, -0.0F);
		wr.pos(0.375, 0.25, -1.0).tex(-0.25, -0.125).normal(-1.0F, 0.0F, -0.0F);

		wr.pos(0.375, 0.75, -0.875).tex(-0.5625, -0.375).normal(-0.0F, 0.242536F, -0.970143F);
		wr.pos(0.625, 0.25, -1.0).tex(-0.625, -0.5).normal(-0.0F, 0.242536F, -0.970143F);
		wr.pos(0.375, 0.25, -1.0).tex(-0.5625, -0.5).normal(-0.0F, 0.242536F, -0.970143F);

		wr.pos(0.625, 0.25, -1.0).tex(-0.625, -0.5).normal(-0.0F, 0.242536F, -0.970143F);
		wr.pos(0.375, 0.75, -0.875).tex(-0.5625, -0.375).normal(-0.0F, 0.242536F, -0.970143F);
		wr.pos(0.625, 0.75, -0.875).tex(-0.625, -0.375).normal(-0.0F, 0.242536F, -0.970143F);

		wr.pos(0.4375, 1.0, -0.625).tex(-0.328125, -0.25).normal(0.0F, 0.707107F, -0.707107F);
		wr.pos(0.625, 0.75, -0.875).tex(-0.375, -0.3125).normal(0.0F, 0.707107F, -0.707107F);
		wr.pos(0.375, 0.75, -0.875).tex(-0.3125, -0.3125).normal(0.0F, 0.707107F, -0.707107F);

		wr.pos(0.625, 0.75, -0.875).tex(-0.375, -0.3125).normal(0.0F, 0.707107F, -0.707107F);
		wr.pos(0.4375, 1.0, -0.625).tex(-0.328125, -0.25).normal(0.0F, 0.707107F, -0.707107F);
		wr.pos(0.5625, 1.0, -0.625).tex(-0.359375, -0.25).normal(0.0F, 0.707107F, -0.707107F);

		wr.pos(0.375, 0.75, -0.75).tex(0.625, -0.375).normal(-0.0F, -0.447214F, 0.894427F);
		wr.pos(0.5625, 1.0, -0.625).tex(0.671875, -0.3125).normal(-0.0F, -0.447214F, 0.894427F);
		wr.pos(0.4375, 1.0, -0.625).tex(0.640625, -0.3125).normal(-0.0F, -0.447214F, 0.894427F);

		wr.pos(0.5625, 1.0, -0.625).tex(0.671875, -0.3125).normal(-0.0F, -0.447214F, 0.894427F);
		wr.pos(0.375, 0.75, -0.75).tex(0.625, -0.375).normal(-0.0F, -0.447214F, 0.894427F);
		wr.pos(0.625, 0.75, -0.75).tex(0.6875, -0.375).normal(-0.0F, -0.447214F, 0.894427F);

		wr.pos(0.625, 0.75, -0.875).tex(0.625, -0.4375).normal(0.970143F, 0.242536F, -0.0F);
		wr.pos(0.5625, 1.0, -0.625).tex(0.6875, -0.5).normal(0.970143F, 0.242536F, -0.0F);
		wr.pos(0.625, 0.75, -0.75).tex(0.65625, -0.4375).normal(0.970143F, 0.242536F, -0.0F);

		wr.pos(0.625, 0.75, -0.75).tex(0.5625, 0.0).normal(1.0F, 0.0F, -0.0F);
		wr.pos(0.625, 0.25, -1.0).tex(0.625, -0.125).normal(1.0F, 0.0F, -0.0F);
		wr.pos(0.625, 0.75, -0.875).tex(0.59375, 0.0).normal(1.0F, 0.0F, -0.0F);

		wr.pos(0.625, 0.25, -1.0).tex(0.625, -0.125).normal(1.0F, 0.0F, -0.0F);
		wr.pos(0.625, 0.75, -0.75).tex(0.5625, 0.0).normal(1.0F, 0.0F, -0.0F);
		wr.pos(0.625, 0.25, -0.75).tex(0.5625, -0.125).normal(1.0F, 0.0F, -0.0F);

		wr.pos(0.375, 0.75, -0.875).tex(-0.3125, -0.4375).normal(-0.970143F, 0.242536F, -0.0F);
		wr.pos(0.375, 0.75, -0.75).tex(-0.28125, -0.4375).normal(-0.970143F, 0.242536F, -0.0F);
		wr.pos(0.4375, 1.0, -0.625).tex(-0.25, -0.375).normal(-0.970143F, 0.242536F, -0.0F);

		wr.pos(1.0, 0.25, -1.0).tex(-0.75, -0.125).normal(0.0F, 0.0F, -1.0F);
		wr.pos(0.375, 0.25, -1.0).tex(-0.59375, -0.125).normal(0.0F, 0.0F, -1.0F);
		wr.pos(0.625, 0.25, -1.0).tex(-0.65625, -0.125).normal(0.0F, 0.0F, -1.0F);

		wr.pos(1.0, 0.25, -1.0).tex(-0.75, -0.125).normal(0.0F, 0.0F, -1.0F);
		wr.pos(0.0, 0.25, -1.0).tex(-0.5, -0.125).normal(0.0F, 0.0F, -1.0F);
		wr.pos(0.375, 0.25, -1.0).tex(-0.59375, -0.125).normal(0.0F, 0.0F, -1.0F);

		wr.pos(1.0, -0.0, -1.0).tex(-0.75, -0.1875).normal(0.0F, 0.0F, -1.0F);
		wr.pos(0.0, 0.25, -1.0).tex(-0.5, -0.125).normal(0.0F, 0.0F, -1.0F);
		wr.pos(1.0, 0.25, -1.0).tex(-0.75, -0.125).normal(0.0F, 0.0F, -1.0F);

		wr.pos(0.0, 0.25, -1.0).tex(-0.5, -0.125).normal(0.0F, 0.0F, -1.0F);
		wr.pos(1.0, -0.0, -1.0).tex(-0.75, -0.1875).normal(0.0F, 0.0F, -1.0F);
		wr.pos(0.0, -0.0, -1.0).tex(-0.5, -0.1875).normal(0.0F, 0.0F, -1.0F);

		wr.pos(1.0, -0.0, -1.0).tex(-1.0, 1.0).normal(0.0F, -1.0F, -0.0F);
		wr.pos(0.0, -0.0, -0.0).tex(-0.75, 0.75).normal(0.0F, -1.0F, -0.0F);
		wr.pos(0.0, -0.0, -1.0).tex(-0.75, 1.0).normal(0.0F, -1.0F, -0.0F);

		wr.pos(0.0, -0.0, -0.0).tex(-0.75, 0.75).normal(0.0F, -1.0F, -0.0F);
		wr.pos(1.0, -0.0, -1.0).tex(-1.0, 1.0).normal(0.0F, -1.0F, -0.0F);
		wr.pos(1.0, -0.0, -0.0).tex(-1.0, 0.75).normal(0.0F, -1.0F, -0.0F);

		wr.pos(1.0, -0.0, -1.0).tex(0.5, -0.125).normal(1.0F, 0.0F, -0.0F);
		wr.pos(1.0, 0.25, -0.0).tex(0.25, -0.0625).normal(1.0F, 0.0F, -0.0F);
		wr.pos(1.0, -0.0, -0.0).tex(0.25, -0.125).normal(1.0F, 0.0F, -0.0F);

		wr.pos(1.0, 0.25, -0.0).tex(0.25, -0.0625).normal(1.0F, 0.0F, -0.0F);
		wr.pos(1.0, -0.0, -1.0).tex(0.5, -0.125).normal(1.0F, 0.0F, -0.0F);
		wr.pos(1.0, 0.25, -0.375).tex(0.34375, -0.0625).normal(1.0F, 0.0F, -0.0F);

		wr.pos(1.0, 0.25, -0.375).tex(0.34375, -0.0625).normal(1.0F, 0.0F, -0.0F);
		wr.pos(1.0, -0.0, -1.0).tex(0.5, -0.125).normal(1.0F, 0.0F, -0.0F);
		wr.pos(1.0, 0.25, -0.625).tex(0.40625, -0.0625).normal(1.0F, 0.0F, -0.0F);

		wr.pos(1.0, 0.25, -0.625).tex(0.40625, -0.0625).normal(1.0F, 0.0F, -0.0F);
		wr.pos(1.0, -0.0, -1.0).tex(0.5, -0.125).normal(1.0F, 0.0F, -0.0F);
		wr.pos(1.0, 0.25, -1.0).tex(0.5, -0.0625).normal(1.0F, 0.0F, -0.0F);

		wr.pos(1.0, 0.25, -0.625).tex(0.375, -0.5).normal(0.970143F, 0.242536F, -0.0F);
		wr.pos(0.875, 0.75, -0.375).tex(0.3125, -0.375).normal(0.970143F, 0.242536F, -0.0F);
		wr.pos(1.0, 0.25, -0.375).tex(0.3125, -0.5).normal(0.970143F, 0.242536F, -0.0F);

		wr.pos(0.875, 0.75, -0.375).tex(0.3125, -0.375).normal(0.970143F, 0.242536F, -0.0F);
		wr.pos(1.0, 0.25, -0.625).tex(0.375, -0.5).normal(0.970143F, 0.242536F, -0.0F);
		wr.pos(0.875, 0.75, -0.625).tex(0.375, -0.375).normal(0.970143F, 0.242536F, -0.0F);

		wr.pos(0.875, 0.75, -0.375).tex(0.53125, 0.0).normal(0.0F, 0.0F, 1.0F);
		wr.pos(0.75, 0.25, -0.375).tex(0.5, -0.125).normal(0.0F, 0.0F, 1.0F);
		wr.pos(1.0, 0.25, -0.375).tex(0.5625, -0.125).normal(0.0F, 0.0F, 1.0F);

		wr.pos(0.75, 0.25, -0.375).tex(0.5, -0.125).normal(0.0F, 0.0F, 1.0F);
		wr.pos(0.875, 0.75, -0.375).tex(0.53125, 0.0).normal(0.0F, 0.0F, 1.0F);
		wr.pos(0.75, 0.75, -0.375).tex(0.5, 0.0).normal(0.0F, 0.0F, 1.0F);

		wr.pos(0.75, 0.75, -0.25).tex(0.3125, -0.25).normal(1.0F, 0.0F, -0.0F);
		wr.pos(0.75, 0.25, -0.375).tex(0.34375, -0.375).normal(1.0F, 0.0F, -0.0F);
		wr.pos(0.75, 0.75, -0.375).tex(0.34375, -0.25).normal(1.0F, 0.0F, -0.0F);

		wr.pos(0.75, 0.25, -0.375).tex(0.34375, -0.375).normal(1.0F, 0.0F, -0.0F);
		wr.pos(0.75, 0.75, -0.25).tex(0.3125, -0.25).normal(1.0F, 0.0F, -0.0F);
		wr.pos(0.75, 0.25, -0.25).tex(0.3125, -0.375).normal(1.0F, 0.0F, -0.0F);

		wr.pos(0.75, 0.75, -0.25).tex(0.3125, -0.25).normal(0.0F, 0.0F, 1.0F);
		wr.pos(0.625, 0.25, -0.25).tex(0.28125, -0.375).normal(0.0F, 0.0F, 1.0F);
		wr.pos(0.75, 0.25, -0.25).tex(0.3125, -0.375).normal(0.0F, 0.0F, 1.0F);

		wr.pos(0.625, 0.25, -0.25).tex(0.28125, -0.375).normal(0.0F, 0.0F, 1.0F);
		wr.pos(0.75, 0.75, -0.25).tex(0.3125, -0.25).normal(0.0F, 0.0F, 1.0F);
		wr.pos(0.625, 0.75, -0.25).tex(0.28125, -0.25).normal(0.0F, 0.0F, 1.0F);

		wr.pos(0.625, 0.75, -0.125).tex(0.59375, -0.25).normal(1.0F, 0.0F, -0.0F);
		wr.pos(0.625, 0.25, -0.25).tex(0.5625, -0.125).normal(1.0F, 0.0F, -0.0F);
		wr.pos(0.625, 0.75, -0.25).tex(0.5625, -0.25).normal(1.0F, 0.0F, -0.0F);

		wr.pos(0.625, 0.25, -0.25).tex(0.5625, -0.125).normal(1.0F, 0.0F, -0.0F);
		wr.pos(0.625, 0.75, -0.125).tex(0.59375, -0.25).normal(1.0F, 0.0F, -0.0F);
		wr.pos(0.625, 0.25, -0.0).tex(0.625, -0.125).normal(1.0F, 0.0F, -0.0F);

		wr.pos(0.625, 0.75, -0.125).tex(0.3125, -0.375).normal(0.0F, 0.242536F, 0.970143F);
		wr.pos(0.375, 0.25, -0.0).tex(0.25, -0.5).normal(0.0F, 0.242536F, 0.970143F);
		wr.pos(0.625, 0.25, -0.0).tex(0.3125, -0.5).normal(0.0F, 0.242536F, 0.970143F);

		wr.pos(0.375, 0.25, -0.0).tex(0.25, -0.5).normal(0.0F, 0.242536F, 0.970143F);
		wr.pos(0.625, 0.75, -0.125).tex(0.3125, -0.375).normal(0.0F, 0.242536F, 0.970143F);
		wr.pos(0.375, 0.75, -0.125).tex(0.25, -0.375).normal(0.0F, 0.242536F, 0.970143F);

		wr.pos(0.625, 0.75, -0.125).tex(0.5625, 0.6875).normal(-0.0F, 0.707107F, 0.707107F);
		wr.pos(0.4375, 1.0, -0.375).tex(0.515625, 0.75).normal(-0.0F, 0.707107F, 0.707107F);
		wr.pos(0.375, 0.75, -0.125).tex(0.5, 0.6875).normal(-0.0F, 0.707107F, 0.707107F);

		wr.pos(0.4375, 1.0, -0.375).tex(0.515625, 0.75).normal(-0.0F, 0.707107F, 0.707107F);
		wr.pos(0.625, 0.75, -0.125).tex(0.5625, 0.6875).normal(-0.0F, 0.707107F, 0.707107F);
		wr.pos(0.5625, 1.0, -0.375).tex(0.546875, 0.75).normal(-0.0F, 0.707107F, 0.707107F);

		wr.pos(0.5625, 1.0, -0.375).tex(0.625, -0.375).normal(0.970143F, 0.242536F, -0.0F);
		wr.pos(0.625, 0.75, -0.125).tex(0.5625, -0.4375).normal(0.970143F, 0.242536F, -0.0F);
		wr.pos(0.625, 0.75, -0.25).tex(0.59375, -0.4375).normal(0.970143F, 0.242536F, -0.0F);

		wr.pos(0.625, 0.75, -0.25).tex(-0.5, -0.375).normal(-0.0F, -0.447214F, -0.894427F);
		wr.pos(0.4375, 1.0, -0.375).tex(-0.453125, -0.3125).normal(-0.0F, -0.447214F, -0.894427F);
		wr.pos(0.5625, 1.0, -0.375).tex(-0.484375, -0.3125).normal(-0.0F, -0.447214F, -0.894427F);

		wr.pos(0.4375, 1.0, -0.375).tex(-0.453125, -0.3125).normal(-0.0F, -0.447214F, -0.894427F);
		wr.pos(0.625, 0.75, -0.25).tex(-0.5, -0.375).normal(-0.0F, -0.447214F, -0.894427F);
		wr.pos(0.375, 0.75, -0.25).tex(-0.4375, -0.375).normal(-0.0F, -0.447214F, -0.894427F);

		wr.pos(0.4375, 1.0, -0.375).tex(-0.4375, -0.5).normal(-0.970143F, 0.242536F, -0.0F);
		wr.pos(0.375, 0.75, -0.25).tex(-0.46875, -0.4375).normal(-0.970143F, 0.242536F, -0.0F);
		wr.pos(0.375, 0.75, -0.125).tex(-0.5, -0.4375).normal(-0.970143F, 0.242536F, -0.0F);

		wr.pos(0.375, 0.25, -0.25).tex(-0.3125, -0.125).normal(-1.0F, 0.0F, -0.0F);
		wr.pos(0.375, 0.75, -0.125).tex(-0.28125, 0.0).normal(-1.0F, 0.0F, -0.0F);
		wr.pos(0.375, 0.75, -0.25).tex(-0.3125, 0.0).normal(-1.0F, 0.0F, -0.0F);

		wr.pos(0.375, 0.75, -0.125).tex(-0.28125, 0.0).normal(-1.0F, 0.0F, -0.0F);
		wr.pos(0.375, 0.25, -0.25).tex(-0.3125, -0.125).normal(-1.0F, 0.0F, -0.0F);
		wr.pos(0.375, 0.25, -0.0).tex(-0.25, -0.125).normal(-1.0F, 0.0F, -0.0F);

		wr.pos(0.25, 0.25, -0.25).tex(0.0625, -0.4375).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.375, 0.25, -0.0).tex(0.09375, -0.5).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.375, 0.25, -0.25).tex(0.09375, -0.4375).normal(0.0F, 1.0F, -0.0F);

		wr.pos(0.0, 0.25, -0.375).tex(0.0, -0.40625).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.375, 0.25, -0.0).tex(0.09375, -0.5).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.25, 0.25, -0.25).tex(0.0625, -0.4375).normal(0.0F, 1.0F, -0.0F);

		wr.pos(0.375, 0.25, -0.0).tex(0.09375, -0.5).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.0, 0.25, -0.375).tex(0.0, -0.40625).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.0, 0.25, -0.0).tex(0.0, -0.5).normal(0.0F, 1.0F, -0.0F);

		wr.pos(0.0, 0.25, -0.375).tex(-0.59375, -0.1875).normal(-1.0F, 0.0F, -0.0F);
		wr.pos(0.0, -0.0, -0.0).tex(-0.5, -0.25).normal(-1.0F, 0.0F, -0.0F);
		wr.pos(0.0, 0.25, -0.0).tex(-0.5, -0.1875).normal(-1.0F, 0.0F, -0.0F);

		wr.pos(0.0, -0.0, -0.0).tex(-0.5, -0.25).normal(-1.0F, 0.0F, -0.0F);
		wr.pos(0.0, 0.25, -0.375).tex(-0.59375, -0.1875).normal(-1.0F, 0.0F, -0.0F);
		wr.pos(0.0, -0.0, -1.0).tex(-0.75, -0.25).normal(-1.0F, 0.0F, -0.0F);

		wr.pos(0.0, -0.0, -1.0).tex(-0.75, -0.25).normal(-1.0F, 0.0F, -0.0F);
		wr.pos(0.0, 0.25, -0.375).tex(-0.59375, -0.1875).normal(-1.0F, 0.0F, -0.0F);
		wr.pos(0.0, 0.25, -0.625).tex(-0.65625, -0.1875).normal(-1.0F, 0.0F, -0.0F);

		wr.pos(0.0, 0.25, -0.375).tex(-0.5, -0.5).normal(-0.970143F, 0.242536F, -0.0F);
		wr.pos(0.125, 0.75, -0.625).tex(-0.5625, -0.375).normal(-0.970143F, 0.242536F, -0.0F);
		wr.pos(0.0, 0.25, -0.625).tex(-0.5625, -0.5).normal(-0.970143F, 0.242536F, -0.0F);

		wr.pos(0.125, 0.75, -0.625).tex(-0.5625, -0.375).normal(-0.970143F, 0.242536F, -0.0F);
		wr.pos(0.0, 0.25, -0.375).tex(-0.5, -0.5).normal(-0.970143F, 0.242536F, -0.0F);
		wr.pos(0.125, 0.75, -0.375).tex(-0.5, -0.375).normal(-0.970143F, 0.242536F, -0.0F);

		wr.pos(0.0, -0.0, -1.0).tex(-0.75, -0.25).normal(-1.0F, 0.0F, -0.0F);
		wr.pos(0.0, 0.25, -0.625).tex(-0.65625, -0.1875).normal(-1.0F, 0.0F, -0.0F);
		wr.pos(0.0, 0.25, -1.0).tex(-0.75, -0.1875).normal(-1.0F, 0.0F, -0.0F);

		wr.pos(0.0, -0.0, -0.0).tex(0.25, -0.0625).normal(0.0F, 0.0F, 1.0F);
		wr.pos(1.0, 0.25, -0.0).tex(0.5, 0.0).normal(0.0F, 0.0F, 1.0F);
		wr.pos(0.0, 0.25, -0.0).tex(0.25, 0.0).normal(0.0F, 0.0F, 1.0F);

		wr.pos(1.0, 0.25, -0.0).tex(0.5, 0.0).normal(0.0F, 0.0F, 1.0F);
		wr.pos(0.0, -0.0, -0.0).tex(0.25, -0.0625).normal(0.0F, 0.0F, 1.0F);
		wr.pos(1.0, -0.0, -0.0).tex(0.5, -0.0625).normal(0.0F, 0.0F, 1.0F);

		wr.pos(0.0, 0.25, -0.0).tex(0.25, 0.0).normal(0.0F, 0.0F, 1.0F);
		wr.pos(1.0, 0.25, -0.0).tex(0.5, 0.0).normal(0.0F, 0.0F, 1.0F);
		wr.pos(0.625, 0.25, -0.0).tex(0.40625, 0.0).normal(0.0F, 0.0F, 1.0F);

		wr.pos(1.0, 0.25, -0.0).tex(0.25, -0.5).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.625, 0.25, -0.25).tex(0.15625, -0.4375).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.625, 0.25, -0.0).tex(0.15625, -0.5).normal(0.0F, 1.0F, -0.0F);

		wr.pos(0.625, 0.25, -0.25).tex(0.15625, -0.4375).normal(0.0F, 1.0F, -0.0F);
		wr.pos(1.0, 0.25, -0.0).tex(0.25, -0.5).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.75, 0.25, -0.25).tex(0.1875, -0.4375).normal(0.0F, 1.0F, -0.0F);

		wr.pos(0.75, 0.25, -0.25).tex(0.1875, -0.4375).normal(0.0F, 1.0F, -0.0F);
		wr.pos(1.0, 0.25, -0.0).tex(0.25, -0.5).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.75, 0.25, -0.375).tex(0.1875, -0.40625).normal(0.0F, 1.0F, -0.0F);

		wr.pos(0.75, 0.25, -0.375).tex(0.1875, -0.40625).normal(0.0F, 1.0F, -0.0F);
		wr.pos(1.0, 0.25, -0.0).tex(0.25, -0.5).normal(0.0F, 1.0F, -0.0F);
		wr.pos(1.0, 0.25, -0.375).tex(0.25, -0.40625).normal(0.0F, 1.0F, -0.0F);

		wr.pos(0.0, 0.25, -0.0).tex(0.25, 0.0).normal(0.0F, 0.0F, 1.0F);
		wr.pos(0.625, 0.25, -0.0).tex(0.40625, 0.0).normal(0.0F, 0.0F, 1.0F);
		wr.pos(0.375, 0.25, -0.0).tex(0.34375, 0.0).normal(0.0F, 0.0F, 1.0F);

		wr.pos(0.25, 0.25, -0.375).tex(0.0625, -0.40625).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.0, 0.25, -0.375).tex(0.0, -0.40625).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.25, 0.25, -0.25).tex(0.0625, -0.4375).normal(0.0F, 1.0F, -0.0F);

		wr.pos(0.375, 0.75, -0.25).tex(0.28125, -0.25).normal(0.0F, 0.0F, 1.0F);
		wr.pos(0.25, 0.25, -0.25).tex(0.25, -0.375).normal(0.0F, 0.0F, 1.0F);
		wr.pos(0.375, 0.25, -0.25).tex(0.28125, -0.375).normal(0.0F, 0.0F, 1.0F);

		wr.pos(0.25, 0.25, -0.25).tex(0.25, -0.375).normal(0.0F, 0.0F, 1.0F);
		wr.pos(0.375, 0.75, -0.25).tex(0.28125, -0.25).normal(0.0F, 0.0F, 1.0F);
		wr.pos(0.25, 0.75, -0.25).tex(0.25, -0.25).normal(0.0F, 0.0F, 1.0F);

		wr.pos(0.0, 0.25, -1.0).tex(0.0, -0.25).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.25, 0.25, -0.75).tex(0.0625, -0.3125).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.375, 0.25, -1.0).tex(0.09375, -0.25).normal(0.0F, 1.0F, -0.0F);

		wr.pos(0.375, 0.25, -1.0).tex(0.09375, -0.25).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.25, 0.25, -0.75).tex(0.0625, -0.3125).normal(0.0F, 1.0F, -0.0F);
		wr.pos(0.375, 0.25, -0.75).tex(0.09375, -0.3125).normal(0.0F, 1.0F, -0.0F);

		Tessellator.getInstance().draw();
		
		GlStateManager.popMatrix();
		GlStateManager.popAttrib();
	}
}