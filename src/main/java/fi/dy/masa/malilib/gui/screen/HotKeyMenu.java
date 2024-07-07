package fi.dy.masa.malilib.gui.screen;

import fi.dy.masa.malilib.config.ConfigManager;
import fi.dy.masa.malilib.config.interfaces.IConfigHandler;
import fi.dy.masa.malilib.gui.button.PageButton;
import fi.dy.masa.malilib.gui.button.interfaces.ICommentedElement;
import fi.dy.masa.malilib.gui.screen.interfaces.GuiScreenPaged;
import fi.dy.masa.malilib.gui.screen.interfaces.IMenu;
import net.minecraft.GuiButton;
import net.minecraft.GuiScreen;
import net.minecraft.I18n;

import java.util.List;

public class HotKeyMenu extends GuiScreenPaged implements IMenu {
    private static final HotKeyMenu Instance = new HotKeyMenu();
    private final List<IConfigHandler> configs;

    public HotKeyMenu() {
        super(null, I18n.getString("manyLib.gui.title.controls"), 7, 2);
        this.configs = ConfigManager.getInstance().getNonNullHotKeyConfigs().values().stream().sorted((x, y) -> x.getName().compareToIgnoreCase(y.getName())).toList();
        this.updatePageCount(this.configs.size());
    }

    public static HotKeyMenu getInstance(GuiScreen parentScreen) {
        Instance.parentScreen = parentScreen;
        return Instance;
    }

    @Override
    public void initGui() {
        this.buttonList.clear();
        for (int i = 0; i < this.configs.size(); i++) {
            IConfigHandler configHandler = configs.get(i);
            this.buttonList.add(this.getButton(i, this.getButtonPosX(i), this.getButtonPosY(i), configHandler.getName(), configHandler.getHotKeysComment()));
        }
        this.setVisibilities();
        this.buttonList.add(new GuiButton(200, this.width / 2 - 100, this.height / 6 + 168, I18n.getString("gui.done")));
        if (this.pageCount > 1) {
            this.buttonList.add(new PageButton(202, this.width / 2 + 132, this.height / 6 + 168, false));
            this.buttonList.add(new PageButton(203, this.width / 2 + 154, this.height / 6 + 168, true));
        }
    }

    public void setVisibilities() {
        for (int i = 0; i < this.configs.size(); ++i) {
            ((GuiButton) this.buttonList.get(i)).drawButton = this.isVisible(i);
        }
    }

    @Override
    public void drawScreen(int i, int j, float f) {
        super.drawScreen(i, j, f);
        this.buttonList.stream().filter(x -> x instanceof ICommentedElement)
                .anyMatch(x -> ((ICommentedElement) x).tryDrawComment(this, i, j));// TODO
    }

    @Override
    protected void actionPerformed(GuiButton par1GuiButton) {
        int id = par1GuiButton.id;
        switch (id) {
            case 200 -> this.leaveThisScreen();
            case 202 -> this.scroll(false);
            case 203 -> this.scroll(true);
            default -> {
                IConfigHandler simpleConfigs = this.configs.get(id);
                this.mc.displayGuiScreen(simpleConfigs.getHotKeyScreen(this));
            }
        }
    }
}