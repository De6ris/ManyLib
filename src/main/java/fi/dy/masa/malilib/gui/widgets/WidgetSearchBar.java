package fi.dy.masa.malilib.gui.widgets;

import fi.dy.masa.malilib.gui.DrawContext;
import fi.dy.masa.malilib.gui.LeftRight;
import fi.dy.masa.malilib.gui.interfaces.IGuiIcon;
import fi.dy.masa.malilib.render.RenderUtils;

public class WidgetSearchBar extends WidgetBase {
    protected final WidgetIcon iconSearch;
    protected final LeftRight iconAlignment;
    protected final WidgetTextField searchBox;
    protected boolean searchOpen;

    public WidgetSearchBar(int x, int y, int width, int height,
                           int searchBarOffsetX, IGuiIcon iconSearch, LeftRight iconAlignment) {
        super(x, y, width, height);

        int iw = iconSearch.getWidth();
        int ix = iconAlignment == LeftRight.RIGHT ? x + width - iw - 1 : x + 2;
        int tx = iconAlignment == LeftRight.RIGHT ? x - searchBarOffsetX + 1 : x + iw + 6 + searchBarOffsetX;
        this.iconSearch = new WidgetIcon(ix, y + 1, iconSearch);
        this.iconAlignment = iconAlignment;
        this.searchBox = new WidgetTextField(tx, y, width - iw - 7 - Math.abs(searchBarOffsetX), height);
//        this.searchBox.setZLevel(this.zLevel);
    }

    public String getFilter() {
        return this.searchOpen ? this.searchBox.getText().toLowerCase() : "";
    }

    public boolean hasFilter() {
        return this.searchOpen && this.searchBox.getText().isEmpty() == false;
    }

    public boolean isSearchOpen() {
        return this.searchOpen;
    }

    public void setSearchOpen(boolean isOpen) {
        this.searchOpen = isOpen;

        if (this.searchOpen) {
            this.searchBox.setFocused(true);
        }
    }

    @Override
    protected boolean onMouseClickedImpl(int mouseX, int mouseY, int mouseButton) {
        if (this.searchOpen && this.searchBox.onMouseClicked(mouseX, mouseY, mouseButton)) {
            return true;
        } else if (this.iconSearch.isMouseOver(mouseX, mouseY)) {
            this.setSearchOpen(!this.searchOpen);
            return true;
        }

        return false;
    }

//    @Override
//    protected boolean onKeyTypedImpl(int keyCode, int scanCode, int modifiers) {
//        if (this.searchOpen) {
//            if (this.searchBox.keyPressed(keyCode, scanCode, modifiers)) {
//                return true;
//            } else if (keyCode == KeyCodes.KEY_ESCAPE) {
//                if (GuiBase.isShiftDown()) {
//                    this.mc.currentScreen.close();
//                }
//
//                this.searchOpen = false;
//                this.searchBox.method_25365(false);
//                return true;
//            }
//        }
//
//        return false;
//    }

    @Override
    protected boolean onCharTypedImpl(char charIn, int modifiers) {
        if (this.searchOpen) {
            if (this.searchBox.charTyped(charIn, modifiers)) {
                return true;
            }
        }
//        else if (SharedConstants.isValidChar(charIn)) {
//            this.searchOpen = true;
//            this.searchBox.setFocused(true);
//            this.searchBox.setText("");
//            this.searchBox.charTyped(charIn, modifiers);
//
//            return true;
//        }

        return false;
    }

    @Override
    public void render(int mouseX, int mouseY, boolean selected, DrawContext drawContext) {
        RenderUtils.color(1f, 1f, 1f, 1f);
        this.iconSearch.render(false, this.iconSearch.isMouseOver(mouseX, mouseY));

        if (this.searchOpen) {
            this.searchBox.render(drawContext, mouseX, mouseY, 0);
        }
    }
}
