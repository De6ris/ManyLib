package fi.dy.masa.malilib.gui.button;

import fi.dy.masa.malilib.config.interfaces.ConfigType;
import fi.dy.masa.malilib.config.interfaces.IConfigDisplay;
import fi.dy.masa.malilib.config.interfaces.IConfigSlideable;
import fi.dy.masa.malilib.config.interfaces.IStringRepresentable;
import fi.dy.masa.malilib.config.options.ConfigBase;
import fi.dy.masa.malilib.config.options.ConfigDouble;
import fi.dy.masa.malilib.gui.button.interfaces.ISliderButton;
import net.minecraft.MathHelper;
import net.minecraft.Minecraft;
import org.lwjgl.opengl.GL11;

public class SliderButton<T extends ConfigBase<T> & IConfigSlideable & IConfigDisplay & IStringRepresentable> extends ButtonWidget implements ISliderButton {
    protected float sliderRatio;
    protected boolean dragging;
    protected final T config;
    private final boolean isDouble;

    public SliderButton(int x, int y, int width, int height, T config) {
        super(x, y, width, height, config.getDisplayText(), button -> {
        });
        this.config = config;
        this.updateSliderRatioByConfig();
        this.isDouble = config.getType() == ConfigType.DOUBLE;
        if (this.isDouble) this.setTooltip(this.castDoubleString());
    }

    @Override
    protected int getHoverState(boolean par1) {
        return 0;
    }

    @Override
    protected void mouseDragged(Minecraft par1Minecraft, int par2, int par3) {
        if (this.enabled) {
            if (this.drawButton) {
                if (this.dragging) {
                    this.sliderRatio = this.getRatioFromSlider(par2);
                    this.config.setValueByRatio(this.sliderRatio);
                    this.updateString();
                }
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                this.drawTexturedModalRect(this.xPosition + (int) (this.sliderRatio * (float) (this.width - 8)), this.yPosition, 0, 66, 4, 20);
                this.drawTexturedModalRect(this.xPosition + (int) (this.sliderRatio * (float) (this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
            }
        }
    }

    @Override
    public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3) {
        if (super.mousePressed(par1Minecraft, par2, par3)) {
            this.sliderRatio = this.getRatioFromSlider(par2);
            this.config.setValueByRatio(this.sliderRatio);
            this.dragging = true;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void mouseReleased(int par1, int par2) {
        this.dragging = false;
    }

    @Override
    public void updateString() {
        this.displayString = this.config.getDisplayText();
        if (this.isDouble) this.setTooltip(this.castDoubleString());
    }

    @Override
    public void updateSliderRatioByConfig() {
        this.sliderRatio = (float) this.config.getRatio();
    }

    @Override
    public float getRatioFromSlider(int mouseX) {
        return MathHelper.clamp_float((float) (mouseX - (this.xPosition + 4)) / (float) (this.width - 8), 0.0F, 1.0F);
    }

    private String castDoubleString() {
        String text = this.config.getStringValue();
        if (text.length() > 11) {
            double doubleValue = ((ConfigDouble) this.config).getDoubleValue();
            text = String.format("%e", doubleValue);
        }
        return text;
    }
}
