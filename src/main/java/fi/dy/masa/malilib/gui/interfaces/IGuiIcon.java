package fi.dy.masa.malilib.gui.interfaces;

import net.minecraft.ResourceLocation;

public interface IGuiIcon {
    int getWidth();

    int getHeight();

    int getU();

    int getV();

    void renderAt(int x, int y, float zLevel, boolean enabled, boolean selected);

    ResourceLocation getTexture();
}