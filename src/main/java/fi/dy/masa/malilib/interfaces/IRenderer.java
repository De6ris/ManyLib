package fi.dy.masa.malilib.interfaces;

import fi.dy.masa.malilib.gui.DrawContext;
import net.minecraft.ItemStack;

import java.util.function.Supplier;

public interface IRenderer {
    /**
     * Called after the vanilla overlays have been rendered
     */
    default void onRenderGameOverlayPost(DrawContext drawContext) {
    }

//    /**
//     * Called after vanilla world rendering
//     */
//    default void onRenderWorldLast(MatrixStack matrixStack, Matrix4f projMatrix) {
//    }

    /**
     * Called after the tooltip text of an item has been rendered
     */
    default void onRenderTooltipLast(DrawContext drawContext, ItemStack stack, int x, int y) {
    }

    /**
     * Returns a supplier for the profiler section name that should be used for this renderer
     */
    default Supplier<String> getProfilerSectionSupplier() {
        return () -> this.getClass().getName();
    }
}
