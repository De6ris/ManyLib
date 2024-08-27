package fi.dy.masa.malilib.gui.screen.util;

import fi.dy.masa.malilib.config.interfaces.*;
import fi.dy.masa.malilib.config.options.*;
import fi.dy.masa.malilib.gui.button.*;
import fi.dy.masa.malilib.gui.button.interfaces.IButtonPeriodic;
import fi.dy.masa.malilib.gui.button.interfaces.ISliderButton;
import fi.dy.masa.malilib.gui.screen.DefaultConfigScreen;
import fi.dy.masa.malilib.gui.screen.interfaces.SearchableScreen;
import fi.dy.masa.malilib.hotkeys.EnumKeybindSettingsPreSet;
import net.minecraft.FontRenderer;
import net.minecraft.GuiScreen;
import net.minecraft.I18n;

public class ScreenConstants {
    private static final int commonButtonXFromRight = -200;
    private static final int commonButtonWidth = 115;
    private static final int hotKeyFirstButtonXFromRight = -300;
    private static final int shortHotkeyButtonWidth = 110;
    private static final int commonHotkeyButtonWidth = 155;
    private static final int keySettingButtonXFromRight = -140;
    private static final int keySettingButtonXWidth = 55;
    private static final int resetButtonXFromRight = -80;
    private static final int scrollBarXFromRight = -40;
    private static final int configToggleButtonXWidth = 40;
    private static final int nameX = 20;
    private static final int scrollBarHeight = 152;
    private static final int pullDownButtonXFromRight = -120;
    public static final int pageCapacity = 7;
    public static final int oneScroll = 3;
    public static final int confirmFlag = 0;
    public static final int commonButtonHeight = 20;
    public static final int commentedTextShift = 6;

    static int getYPos(int index, GuiScreen screen) {
        return screen.height / 6 + 22 * index + 32;
    }

    static <T extends ConfigBase<?> & IConfigDisplay> CommentedText getCommentedText(int index, T config, GuiScreen screen) {
        CommentedText commentedText = new CommentedText(nameX, getYPos(index, screen) + commentedTextShift, config.getConfigGuiDisplayName(), config.getConfigGuiDisplayComment(), screen.fontRenderer, config.getDisplayColor());
        ConfigType type = config.getType();
        int right;
        if (type == ConfigType.HOTKEY || type == ConfigType.TOGGLE) {
            right = screen.width + hotKeyFirstButtonXFromRight;
        } else {
            right = screen.width + commonButtonXFromRight;
        }
        commentedText.setCommentIntervalX(-nameX, right - nameX);
        return commentedText;
    }

    static ResetButton getResetButton(int index, GuiScreen screen, ButtonWidget.PressAction onPress) {
        return new ResetButton(screen.width + resetButtonXFromRight, getYPos(index, screen), I18n.getString("manyLib.gui.button.reset"), onPress);
    }

    static <T extends ConfigBase<?> & IStringRepresentable> InputBox<T> getInputBox(int index, T config, GuiScreen screen) {
        return new InputBox<>(config, screen.fontRenderer, screen.width + commonButtonXFromRight + 2, getYPos(index, screen) + 1, commonButtonWidth - 2, 18);
    }

    static <T extends ConfigBase<T> & IStringRepresentable> InputBox<T> getInputBoxForSlideable(int index, T config, GuiScreen screen) {
        return new InputBox<>(config, screen.fontRenderer, screen.width + commonButtonXFromRight + 2, getYPos(index, screen) + 1, commonButtonWidth - 22, 18);
    }

    static InputBox<ConfigColor> getInputBoxForColor(int index, ConfigColor config, GuiScreen screen) {
        return new InputBox<>(config, screen.fontRenderer, screen.width + commonButtonXFromRight + 2, getYPos(index, screen) + 1, commonButtonWidth - 22, 18);
    }

    static ColorBoard getColorBoard(int index, ConfigColor configColor, GuiScreen screen) {
        return new ColorBoard(configColor, screen.width + commonButtonXFromRight + commonButtonWidth - 15, getYPos(index, screen) + 2, 16, 16);
    }

    static <T extends ConfigBase<T> & IConfigPeriodic & IConfigDisplay> IButtonPeriodic getPeriodicButton(int index, T config, GuiScreen screen) {
        return new PeriodicButton<>(screen.width + commonButtonXFromRight, getYPos(index, screen), commonButtonWidth, commonButtonHeight, config);
    }

    static ButtonWidget getHotkeyButton(int index, ConfigHotkey config, GuiScreen screen, ButtonWidget.PressAction onPress) {
        boolean isShort = config.getType() == ConfigType.TOGGLE;
        int xPos = isShort ? screen.width + hotKeyFirstButtonXFromRight + configToggleButtonXWidth + 5 : screen.width + hotKeyFirstButtonXFromRight;
        int width = isShort ? shortHotkeyButtonWidth : commonHotkeyButtonWidth;
        return ButtonWidget.builder("", onPress).dimensions(xPos, getYPos(index, screen), width, commonButtonHeight).build();
    }

    static PeriodicButton<?> getKeySettingButton(int index, ConfigEnum<EnumKeybindSettingsPreSet> config, GuiScreen screen, ButtonWidget.PressAction onPress) {
        return new PeriodicButton<>(screen.width + keySettingButtonXFromRight, getYPos(index, screen), keySettingButtonXWidth, commonButtonHeight, config, onPress);
    }

    static ButtonWidget getConfigToggleButton(int index, ConfigToggle config, GuiScreen screen, ButtonWidget.PressAction onPress) {
        return ButtonWidget.builder("", onPress).dimensions(screen.width + hotKeyFirstButtonXFromRight, getYPos(index, screen), configToggleButtonXWidth, commonButtonHeight).build();
    }

    static <T extends ConfigBase<T> & IConfigSlideable & IConfigDisplay & IStringRepresentable> ISliderButton getSliderButton(int index, T config, GuiScreen screen) {
        return new SliderButton<>(screen.width + commonButtonXFromRight, getYPos(index, screen), commonButtonWidth - 20, commonButtonHeight, config);
    }

    static SlideableToggleButton getSlieableToggleButton(int index, boolean useSlider, GuiScreen screen, ButtonWidget.PressAction onPress) {
        return new SlideableToggleButton(screen.width + commonButtonXFromRight + commonButtonWidth - 15, getYPos(index, screen) + 2, useSlider, onPress);
    }

    public static PullDownButton getPullDownButton(GuiScreen screen) {
        return new PullDownButton(screen.width + pullDownButtonXFromRight, 10, 100, commonButtonHeight, I18n.getString("manyLib.gui.button.other_mods"));
    }

    public static ScrollBar<?> getScrollBar(DefaultConfigScreen screen, int pageCapacity, int maxStatus) {
        return new ScrollBar<>(screen.width + scrollBarXFromRight, getYPos(0, screen), 8, scrollBarHeight, pageCapacity, maxStatus, screen);
    }

    public static ResetButton getResetAllButton(WidthAdder widthAdder, ButtonWidget.PressAction onPress) {
        ResetButton resetButton = new ResetButton(widthAdder.getWidth(), 30, I18n.getString("manyLib.gui.button.reset_all"), onPress);
        widthAdder.addWidth(25);
        return resetButton;
    }

    public static PeriodicButton<?> getSortButton(GuiScreen screen, WidthAdder widthAdder, ConfigEnum<SortCategory> sortCategory, ButtonWidget.PressAction onPress) {
        int stringWidth = getMaxStringWidth(screen.fontRenderer, sortCategory);
        int width = widthAdder.getWidth();
        widthAdder.addWidth(stringWidth + 15);
        return new PeriodicButton<>(width, 30, stringWidth + 10, commonButtonHeight, sortCategory, onPress);
    }

    private static int getMaxStringWidth(FontRenderer fontRenderer, ConfigEnum<SortCategory> sortCategory) {
        int maxWidth = 0;
        for (SortCategory value : SortCategory.values()) {
            ConfigEnum<SortCategory> temp = new ConfigEnum<>(sortCategory.getName(), value);
            int stringWidth = fontRenderer.getStringWidth(temp.getDisplayText());
            if (stringWidth > maxWidth) {
                maxWidth = stringWidth;
            }
        }
        return maxWidth;
    }

    public static <T extends GuiScreen & SearchableScreen> SearchField<T> getSearchButton(T screen) {
        return new SearchField<>(23, 57, screen.width - 95, 13, screen);
    }
}
