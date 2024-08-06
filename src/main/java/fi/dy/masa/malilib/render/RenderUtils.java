package fi.dy.masa.malilib.render;

import fi.dy.masa.malilib.api.ManyLibGuiIngame;
import net.minecraft.*;
import org.lwjgl.opengl.GL11;

import java.util.Arrays;
import java.util.List;

public class RenderUtils {
    public static void setGuiIngameInfo(String string) {
        setGuiIngameInfo(string, 60);
    }

    public static void setGuiIngameInfo(String string, int duration) {
        ((ManyLibGuiIngame) Minecraft.getMinecraft().ingameGUI).manyLib$setInfo(string, duration);
    }

    public static void drawCreativeTabHoveringText(GuiScreen screen, String string, int x, int y) {
        string = string.replace('（', '(').replace('）', ')');
        List<String> list = Arrays.stream(string.split("\\\\n")).map(String::trim).toList();
        if (list.size() == 1 && screen.fontRenderer.getStringWidth(list.get(0)) > 300) {
            list = Arrays.stream(string.split("[,.，。]")).map(String::trim).toList();
        }
        drawTextList(screen, list, x, y);
    }

    public static void drawTextList(GuiScreen screen, List<String> stringList, int x, int y) {
        drawTextList(screen, stringList, x, y, true);
    }

    public static void drawTextList(GuiScreen screen, List<String> stringList, int x, int y, boolean has_title) {
        if (stringList.isEmpty()) return;
        GL11.glDisable(32826);
//        RenderHelper.disableStandardItemLighting();// bad
//        GL11.glDisable(2896);// bad
        GL11.glDisable(2929);// good
        int boxWidth = 0;
        int stringWidth;
        for (String textPart : stringList) {
            stringWidth = screen.fontRenderer.getStringWidth(textPart);
            if (stringWidth > boxWidth) {
                boxWidth = stringWidth;
            }
        }
        int stringXPos = x + 12;
        int stringYPos = y - 12;
        int boxHeight = 8;
        if (stringList.size() > 1) {
            boxHeight += 2 + (stringList.size() - 1) * 10;
        }
        if (!has_title) {
            boxHeight -= 2;
        }
        if (stringXPos + boxWidth > screen.width) {
            stringXPos -= 28 + boxWidth;
        }
        if (stringYPos + boxHeight + 6 > screen.height) {
            stringYPos = screen.height - boxHeight - 6;
        }
        screen.zLevel = 300.0F;
        int color_1 = -267386864;
        color_1 = color_1 & 16777215 | -369098752;
        screen.drawGradientRect(stringXPos - 3, stringYPos - 4, stringXPos + boxWidth + 3, stringYPos - 3, color_1, color_1);
        screen.drawGradientRect(stringXPos - 3, stringYPos + boxHeight + 3, stringXPos + boxWidth + 3, stringYPos + boxHeight + 4, color_1, color_1);
        screen.drawGradientRect(stringXPos - 3, stringYPos - 3, stringXPos + boxWidth + 3, stringYPos + boxHeight + 3, color_1, color_1);
        screen.drawGradientRect(stringXPos - 4, stringYPos - 3, stringXPos - 3, stringYPos + boxHeight + 3, color_1, color_1);
        screen.drawGradientRect(stringXPos + boxWidth + 3, stringYPos - 3, stringXPos + boxWidth + 4, stringYPos + boxHeight + 3, color_1, color_1);
        int color_2 = 1347420415;
        int color_3 = (color_2 & 16711422) >> 1 | color_2 & -16777216;
        screen.drawGradientRect(stringXPos - 3, stringYPos - 3 + 1, stringXPos - 3 + 1, stringYPos + boxHeight + 3 - 1, color_2, color_3);
        screen.drawGradientRect(stringXPos + boxWidth + 2, stringYPos - 3 + 1, stringXPos + boxWidth + 3, stringYPos + boxHeight + 3 - 1, color_2, color_3);
        screen.drawGradientRect(stringXPos - 3, stringYPos - 3, stringXPos + boxWidth + 3, stringYPos - 3 + 1, color_2, color_2);
        screen.drawGradientRect(stringXPos - 3, stringYPos + boxHeight + 2, stringXPos + boxWidth + 3, stringYPos + boxHeight + 3, color_3, color_3);

        for (int stringIndex = 0; stringIndex < stringList.size(); ++stringIndex) {
            String string = stringList.get(stringIndex);
            screen.fontRenderer.drawStringWithShadow(string, stringXPos, stringYPos, -1);
            if (stringIndex == 0 && has_title) {
                stringYPos += 2;
            }
            stringYPos += 10;
        }
        screen.zLevel = 0.0F;
//        GL11.glEnable(2896);// bad
        GL11.glEnable(2929);// good
//        RenderHelper.enableStandardItemLighting();// bad
        GL11.glEnable(32826);
    }


    //    public static final Identifier TEXTURE_MAP_BACKGROUND = new Identifier("textures/map/map_background.png");
//    public static final Identifier TEXTURE_MAP_BACKGROUND_CHECKERBOARD = new Identifier("textures/map/map_background_checkerboard.png");
//
//    private static final LocalRandom RAND = new LocalRandom(0);
//    //private static final Vec3d LIGHT0_POS = (new Vec3d( 0.2D, 1.0D, -0.7D)).normalize();
//    //private static final Vec3d LIGHT1_POS = (new Vec3d(-0.2D, 1.0D,  0.7D)).normalize();
//
//    public static void setupBlend() {
//        RenderSystem.enableBlend();
//        RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ZERO);
//    }
//
//    public static void setupBlendSimple() {
//        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA);
//    }
//
    public static void bindTexture(ResourceLocation texture) {
        mc().getTextureManager().bindTexture(texture);
    }

    //
//    public static void color(float r, float g, float b, float a) {
//        RenderSystem.setShaderColor(r, g, b, a);
//    }
//
//    public static void disableDiffuseLighting() {
//        DiffuseLighting.disableGuiDepthLighting();
//    }
//
//    public static void enableDiffuseLightingForLevel(MatrixStack matrixStack) {
//        DiffuseLighting.enableForLevel(matrixStack.peek().getPositionMatrix());
//    }
//
//    public static void enableDiffuseLightingGui3D() {
//        DiffuseLighting.enableGuiDepthLighting();
//    }
//
//    public static void drawOutlinedBox(int x, int y, int width, int height, int colorBg, int colorBorder) {
//        drawOutlinedBox(x, y, width, height, colorBg, colorBorder, 0f);
//    }
//
//    public static void drawOutlinedBox(int x, int y, int width, int height, int colorBg, int colorBorder, float zLevel) {
//        // Draw the background
//        drawRect(x, y, width, height, colorBg, zLevel);
//
//        // Draw the border
//        drawOutline(x - 1, y - 1, width + 2, height + 2, colorBorder, zLevel);
//    }
//
    public static void drawOutline(int x, int y, int width, int height, int colorBorder) {
        drawOutline(x, y, width, height, colorBorder, 0f);
    }

    public static void drawOutline(int x, int y, int width, int height, int colorBorder, float zLevel) {
        drawRect(x, y, 1, height, colorBorder, zLevel); // left edge
        drawRect(x + width - 1, y, 1, height, colorBorder, zLevel); // right edge
        drawRect(x + 1, y, width - 2, 1, colorBorder, zLevel); // top edge
        drawRect(x + 1, y + height - 1, width - 2, 1, colorBorder, zLevel); // bottom edge
    }

    //
//    public static void drawOutline(int x, int y, int width, int height, int borderWidth, int colorBorder) {
//        drawOutline(x, y, width, height, borderWidth, colorBorder, 0f);
//    }
//
//    public static void drawOutline(int x, int y, int width, int height, int borderWidth, int colorBorder, float zLevel) {
//        drawRect(x, y, borderWidth, height, colorBorder, zLevel); // left edge
//        drawRect(x + width - borderWidth, y, borderWidth, height, colorBorder, zLevel); // right edge
//        drawRect(x + borderWidth, y, width - 2 * borderWidth, borderWidth, colorBorder, zLevel); // top edge
//        drawRect(x + borderWidth, y + height - borderWidth, width - 2 * borderWidth, borderWidth, colorBorder, zLevel); // bottom edge
//    }
//
    public static void drawTexturedRect(int x, int y, int u, int v, int width, int height) {
        drawTexturedRect(x, y, u, v, width, height, 0);
    }


    public static void drawRect(int x, int y, int width, int height, int color) {
        drawRect(x, y, width, height, color, 0f);
    }

    public static void drawRect(int x, int y, int width, int height, int color, float zLevel) {
        float var10 = (float) (color >> 24 & 0xFF) / 255.0f;
        float var6 = (float) (color >> 16 & 0xFF) / 255.0f;
        float var7 = (float) (color >> 8 & 0xFF) / 255.0f;
        float var8 = (float) (color & 0xFF) / 255.0f;
        Tessellator var9 = Tessellator.instance;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(var6, var7, var8, var10);
        var9.startDrawingQuads();
        var9.addVertex(x, y, zLevel);
        var9.addVertex(x, y + height, zLevel);
        var9.addVertex(x + width, y + height, zLevel);
        var9.addVertex(x + width, y, zLevel);
        var9.draw();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
    }

    public static void drawTexturedRect(int x, int y, int u, int v, int width, int height, float zLevel) {
        float pixelWidth = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(x, y + height, zLevel, u * pixelWidth, (v + height) * pixelWidth);
        tessellator.addVertexWithUV(x + width, y + height, zLevel, (u + width) * pixelWidth, (v + height) * pixelWidth);
        tessellator.addVertexWithUV(x + width, y, zLevel, (u + width) * pixelWidth, v * pixelWidth);
        tessellator.addVertexWithUV(x, y, zLevel, u * pixelWidth, v * pixelWidth);
        tessellator.draw();
    }

    //
//    public static void drawTexturedRectBatched(int x, int y, int u, int v, int width, int height, BufferBuilder buffer) {
//        drawTexturedRectBatched(x, y, u, v, width, height, 0, buffer);
//    }
//
//    public static void drawTexturedRectBatched(int x, int y, int u, int v, int width, int height, float zLevel, BufferBuilder buffer) {
//        float pixelWidth = 0.00390625F;
//
//        buffer.vertex(x, y + height, zLevel).texture(u * pixelWidth, (v + height) * pixelWidth).next();
//        buffer.vertex(x + width, y + height, zLevel).texture((u + width) * pixelWidth, (v + height) * pixelWidth).next();
//        buffer.vertex(x + width, y, zLevel).texture((u + width) * pixelWidth, v * pixelWidth).next();
//        buffer.vertex(x, y, zLevel).texture(u * pixelWidth, v * pixelWidth).next();
//    }
//
//    public static void drawHoverText(int x, int y, List<String> textLines, DrawContext drawContext) {
//        MinecraftClient mc = mc();
//
//        if (textLines.isEmpty() == false && GuiUtils.getCurrentScreen() != null) {
//            RenderSystem.enableDepthTest();
//            TextRenderer font = mc.textRenderer;
//            int maxLineLength = 0;
//            int maxWidth = GuiUtils.getCurrentScreen().width;
//            List<String> linesNew = new ArrayList<>();
//
//            for (String lineOrig : textLines) {
//                String[] lines = lineOrig.split("\\n");
//
//                for (String line : lines) {
//                    int length = font.getWidth(line);
//
//                    if (length > maxLineLength) {
//                        maxLineLength = length;
//                    }
//
//                    linesNew.add(line);
//                }
//            }
//
//            textLines = linesNew;
//
//            final int lineHeight = font.fontHeight + 1;
//            int textHeight = textLines.size() * lineHeight - 2;
//            int textStartX = x + 4;
//            int textStartY = Math.max(8, y - textHeight - 6);
//
//            if (textStartX + maxLineLength + 6 > maxWidth) {
//                textStartX = Math.max(2, maxWidth - maxLineLength - 8);
//            }
//
//            MatrixStack matrixStack = drawContext.getMatrices();
//            matrixStack.push();
//            matrixStack.translate(0, 0, 300);
//            RenderSystem.applyModelViewMatrix();
//
//            double zLevel = 300;
//            int borderColor = 0xF0100010;
//            drawGradientRect(textStartX - 3, textStartY - 4, textStartX + maxLineLength + 3, textStartY - 3, zLevel, borderColor, borderColor);
//            drawGradientRect(textStartX - 3, textStartY + textHeight + 3, textStartX + maxLineLength + 3, textStartY + textHeight + 4, zLevel, borderColor, borderColor);
//            drawGradientRect(textStartX - 3, textStartY - 3, textStartX + maxLineLength + 3, textStartY + textHeight + 3, zLevel, borderColor, borderColor);
//            drawGradientRect(textStartX - 4, textStartY - 3, textStartX - 3, textStartY + textHeight + 3, zLevel, borderColor, borderColor);
//            drawGradientRect(textStartX + maxLineLength + 3, textStartY - 3, textStartX + maxLineLength + 4, textStartY + textHeight + 3, zLevel, borderColor, borderColor);
//
//            int fillColor1 = 0x505000FF;
//            int fillColor2 = 0x5028007F;
//            drawGradientRect(textStartX - 3, textStartY - 3 + 1, textStartX - 3 + 1, textStartY + textHeight + 3 - 1, zLevel, fillColor1, fillColor2);
//            drawGradientRect(textStartX + maxLineLength + 2, textStartY - 3 + 1, textStartX + maxLineLength + 3, textStartY + textHeight + 3 - 1, zLevel, fillColor1, fillColor2);
//            drawGradientRect(textStartX - 3, textStartY - 3, textStartX + maxLineLength + 3, textStartY - 3 + 1, zLevel, fillColor1, fillColor1);
//            drawGradientRect(textStartX - 3, textStartY + textHeight + 2, textStartX + maxLineLength + 3, textStartY + textHeight + 3, zLevel, fillColor2, fillColor2);
//
//            for (int i = 0; i < textLines.size(); ++i) {
//                String str = textLines.get(i);
//
//                drawContext.drawText(font, str, textStartX, textStartY, 0xFFFFFFFF, false);
//                textStartY += lineHeight;
//            }
//
//            matrixStack.pop();
//            RenderSystem.applyModelViewMatrix();
//
//            //RenderSystem.enableDepthTest();
//            //enableDiffuseLightingGui3D();
//        }
//    }
//
//    public static void drawGradientRect(int left, int top, int right, int bottom, double zLevel, int startColor, int endColor) {
//        int sa = (startColor >> 24 & 0xFF);
//        int sr = (startColor >> 16 & 0xFF);
//        int sg = (startColor >> 8 & 0xFF);
//        int sb = (startColor & 0xFF);
//
//        int ea = (endColor >> 24 & 0xFF);
//        int er = (endColor >> 16 & 0xFF);
//        int eg = (endColor >> 8 & 0xFF);
//        int eb = (endColor & 0xFF);
//
//        setupBlend();
//        RenderSystem.setShader(GameRenderer::getPositionColorProgram);
//        RenderSystem.applyModelViewMatrix();
//
//        Tessellator tessellator = Tessellator.getInstance();
//        BufferBuilder buffer = tessellator.getBuffer();
//        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
//
//        buffer.vertex(right, top, zLevel).color(sr, sg, sb, sa).next();
//        buffer.vertex(left, top, zLevel).color(sr, sg, sb, sa).next();
//        buffer.vertex(left, bottom, zLevel).color(er, eg, eb, ea).next();
//        buffer.vertex(right, bottom, zLevel).color(er, eg, eb, ea).next();
//
//        tessellator.draw();
//
//        RenderSystem.disableBlend();
//    }
//
//    public static void drawCenteredString(int x, int y, int color, String text, DrawContext drawContext) {
//        TextRenderer textRenderer = mc().textRenderer;
//        drawContext.drawCenteredTextWithShadow(textRenderer, text, x, y, color);
//    }
//
//    public static void drawHorizontalLine(int x, int y, int width, int color) {
//        drawRect(x, y, width, 1, color);
//    }
//
//    public static void drawVerticalLine(int x, int y, int height, int color) {
//        drawRect(x, y, 1, height, color);
//    }
//
//    public static void renderSprite(int x, int y, int width, int height, Identifier atlas, Identifier texture, DrawContext drawContext) {
//        if (texture != null) {
//            Sprite sprite = mc().getSpriteAtlas(atlas).apply(texture);
//            drawContext.drawSprite(x, y, 0, width, height, sprite);
//        }
//    }
//
//    public static void renderText(int x, int y, int color, String text, DrawContext drawContext) {
//        String[] parts = text.split("\\\\n");
//        TextRenderer textRenderer = mc().textRenderer;
//
//        for (String line : parts) {
//            drawContext.drawText(textRenderer, line, x, y, color, true);
//            y += textRenderer.fontHeight + 1;
//        }
//    }
//
//    public static void renderText(int x, int y, int color, List<String> lines, DrawContext drawContext) {
//        if (lines.isEmpty() == false) {
//            TextRenderer textRenderer = mc().textRenderer;
//
//            for (String line : lines) {
//                drawContext.drawText(textRenderer, line, x, y, color, false);
//                y += textRenderer.fontHeight + 2;
//            }
//        }
//    }
//
//    public static int renderText(int xOff, int yOff, double scale, int textColor, int bgColor,
//                                 HudAlignment alignment, boolean useBackground, boolean useShadow, List<String> lines,
//                                 DrawContext drawContext) {
//        TextRenderer fontRenderer = mc().textRenderer;
//        final int scaledWidth = GuiUtils.getScaledWindowWidth();
//        final int lineHeight = fontRenderer.fontHeight + 2;
//        final int contentHeight = lines.size() * lineHeight - 2;
//        final int bgMargin = 2;
//
//        // Only Chuck Norris can divide by zero
//        if (scale < 0.0125) {
//            return 0;
//        }
//
//        MatrixStack globalStack = RenderSystem.getModelViewStack();
//        boolean scaled = scale != 1.0;
//
//        if (scaled) {
//            if (scale != 0) {
//                xOff = (int) (xOff * scale);
//                yOff = (int) (yOff * scale);
//            }
//
//            globalStack.push();
//            globalStack.scale((float) scale, (float) scale, 1.0f);
//            RenderSystem.applyModelViewMatrix();
//        }
//
//        double posX = xOff + bgMargin;
//        double posY = yOff + bgMargin;
//
//        posY = getHudPosY((int) posY, yOff, contentHeight, scale, alignment);
//        posY += getHudOffsetForPotions(alignment, scale, mc().player);
//
//        for (String line : lines) {
//            final int width = fontRenderer.getWidth(line);
//
//            switch (alignment) {
//                case TOP_RIGHT:
//                case BOTTOM_RIGHT:
//                    posX = (scaledWidth / scale) - width - xOff - bgMargin;
//                    break;
//                case CENTER:
//                    posX = (scaledWidth / scale / 2) - (width / 2) - xOff;
//                    break;
//                default:
//            }
//
//            final int x = (int) posX;
//            final int y = (int) posY;
//            posY += lineHeight;
//
//            if (useBackground) {
//                drawRect(x - bgMargin, y - bgMargin, width + bgMargin, bgMargin + fontRenderer.fontHeight, bgColor);
//            }
//
//            drawContext.drawText(fontRenderer, line, x, y, textColor, useShadow);
//        }
//
//        if (scaled) {
//            globalStack.pop();
//            RenderSystem.applyModelViewMatrix();
//        }
//
//        return contentHeight + bgMargin * 2;
//    }
//
//    public static int getHudOffsetForPotions(HudAlignment alignment, double scale, PlayerEntity player) {
//        if (alignment == HudAlignment.TOP_RIGHT) {
//            // Only Chuck Norris can divide by zero
//            if (scale == 0d) {
//                return 0;
//            }
//
//            Collection<StatusEffectInstance> effects = player.getStatusEffects();
//
//            if (effects.isEmpty() == false) {
//                int y1 = 0;
//                int y2 = 0;
//
//                for (StatusEffectInstance effectInstance : effects) {
//                    StatusEffect effect = effectInstance.getEffectType();
//
//                    if (effectInstance.shouldShowParticles() && effectInstance.shouldShowIcon()) {
//                        if (effect.isBeneficial()) {
//                            y1 = 26;
//                        } else {
//                            y2 = 52;
//                            break;
//                        }
//                    }
//                }
//
//                return (int) (Math.max(y1, y2) / scale);
//            }
//        }
//
//        return 0;
//    }
//
//    public static int getHudPosY(int yOrig, int yOffset, int contentHeight, double scale, HudAlignment alignment) {
//        int scaledHeight = GuiUtils.getScaledWindowHeight();
//        int posY = yOrig;
//
//        switch (alignment) {
//            case BOTTOM_LEFT:
//            case BOTTOM_RIGHT:
//                posY = (int) ((scaledHeight / scale) - contentHeight - yOffset);
//                break;
//            case CENTER:
//                posY = (int) ((scaledHeight / scale / 2.0d) - (contentHeight / 2.0d) + yOffset);
//                break;
//            default:
//        }
//
//        return posY;
//    }
//
//    /**
//     * Assumes a BufferBuilder in GL_QUADS mode has been initialized
//     */
//    public static void drawBlockBoundingBoxSidesBatchedQuads(BlockPos pos, Color4f color, double expand, BufferBuilder buffer) {
//        double minX = pos.getX() - expand;
//        double minY = pos.getY() - expand;
//        double minZ = pos.getZ() - expand;
//        double maxX = pos.getX() + expand + 1;
//        double maxY = pos.getY() + expand + 1;
//        double maxZ = pos.getZ() + expand + 1;
//
//        drawBoxAllSidesBatchedQuads(minX, minY, minZ, maxX, maxY, maxZ, color, buffer);
//    }
//
//    /**
//     * Assumes a BufferBuilder in GL_LINES mode has been initialized
//     */
//    public static void drawBlockBoundingBoxOutlinesBatchedLines(BlockPos pos, Color4f color, double expand, BufferBuilder buffer) {
//        drawBlockBoundingBoxOutlinesBatchedLines(pos, Vec3d.ZERO, color, expand, buffer);
//    }
//
//    /**
//     * Assumes a BufferBuilder in GL_LINES mode has been initialized.
//     * The cameraPos value will be subtracted from the absolute coordinate values of the passed in BlockPos.
//     *
//     * @param pos
//     * @param cameraPos
//     * @param color
//     * @param expand
//     * @param buffer
//     */
//    public static void drawBlockBoundingBoxOutlinesBatchedLines(BlockPos pos, Vec3d cameraPos, Color4f color, double expand, BufferBuilder buffer) {
//        double minX = pos.getX() - expand - cameraPos.x;
//        double minY = pos.getY() - expand - cameraPos.y;
//        double minZ = pos.getZ() - expand - cameraPos.z;
//        double maxX = pos.getX() + expand - cameraPos.x + 1;
//        double maxY = pos.getY() + expand - cameraPos.y + 1;
//        double maxZ = pos.getZ() + expand - cameraPos.z + 1;
//
//        drawBoxAllEdgesBatchedLines(minX, minY, minZ, maxX, maxY, maxZ, color, buffer);
//    }
//
//    /**
//     * Assumes a BufferBuilder in GL_QUADS mode has been initialized
//     */
//    public static void drawBoxAllSidesBatchedQuads(double minX, double minY, double minZ, double maxX, double maxY, double maxZ,
//                                                   Color4f color, BufferBuilder buffer) {
//        drawBoxHorizontalSidesBatchedQuads(minX, minY, minZ, maxX, maxY, maxZ, color, buffer);
//        drawBoxTopBatchedQuads(minX, minZ, maxX, maxY, maxZ, color, buffer);
//        drawBoxBottomBatchedQuads(minX, minY, minZ, maxX, maxZ, color, buffer);
//    }
//
//    /**
//     * Draws a box with outlines around the given corner positions.
//     * Takes in buffers initialized for GL_QUADS and GL_LINES modes.
//     *
//     * @param posMin
//     * @param posMax
//     * @param colorLines
//     * @param colorSides
//     * @param bufferQuads
//     * @param bufferLines
//     */
//    public static void drawBoxWithEdgesBatched(BlockPos posMin, BlockPos posMax, Color4f colorLines, Color4f colorSides, BufferBuilder bufferQuads, BufferBuilder bufferLines) {
//        drawBoxWithEdgesBatched(posMin, posMax, Vec3d.ZERO, colorLines, colorSides, bufferQuads, bufferLines);
//    }
//
//    /**
//     * Draws a box with outlines around the given corner positions.
//     * Takes in buffers initialized for GL_QUADS and GL_LINES modes.
//     * The cameraPos value will be subtracted from the absolute coordinate values of the passed in block positions.
//     *
//     * @param posMin
//     * @param posMax
//     * @param cameraPos
//     * @param colorLines
//     * @param colorSides
//     * @param bufferQuads
//     * @param bufferLines
//     */
//    public static void drawBoxWithEdgesBatched(BlockPos posMin, BlockPos posMax, Vec3d cameraPos, Color4f colorLines, Color4f colorSides, BufferBuilder bufferQuads, BufferBuilder bufferLines) {
//        final double x1 = posMin.getX() - cameraPos.x;
//        final double y1 = posMin.getY() - cameraPos.y;
//        final double z1 = posMin.getZ() - cameraPos.z;
//        final double x2 = posMax.getX() + 1 - cameraPos.x;
//        final double y2 = posMax.getY() + 1 - cameraPos.y;
//        final double z2 = posMax.getZ() + 1 - cameraPos.z;
//
//        fi.dy.masa.malilib.render.RenderUtils.drawBoxAllSidesBatchedQuads(x1, y1, z1, x2, y2, z2, colorSides, bufferQuads);
//        fi.dy.masa.malilib.render.RenderUtils.drawBoxAllEdgesBatchedLines(x1, y1, z1, x2, y2, z2, colorLines, bufferLines);
//    }
//
//    /**
//     * Assumes a BufferBuilder in GL_QUADS mode has been initialized
//     */
//    public static void drawBoxHorizontalSidesBatchedQuads(double minX, double minY, double minZ, double maxX, double maxY, double maxZ,
//                                                          Color4f color, BufferBuilder buffer) {
//        // West side
//        buffer.vertex(minX, minY, minZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(minX, minY, maxZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(minX, maxY, maxZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(minX, maxY, minZ).color(color.r, color.g, color.b, color.a).next();
//
//        // East side
//        buffer.vertex(maxX, minY, maxZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(maxX, minY, minZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(maxX, maxY, minZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(maxX, maxY, maxZ).color(color.r, color.g, color.b, color.a).next();
//
//        // North side
//        buffer.vertex(maxX, minY, minZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(minX, minY, minZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(minX, maxY, minZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(maxX, maxY, minZ).color(color.r, color.g, color.b, color.a).next();
//
//        // South side
//        buffer.vertex(minX, minY, maxZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(maxX, minY, maxZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(maxX, maxY, maxZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(minX, maxY, maxZ).color(color.r, color.g, color.b, color.a).next();
//    }
//
//    /**
//     * Assumes a BufferBuilder in GL_QUADS mode has been initialized
//     */
//    public static void drawBoxTopBatchedQuads(double minX, double minZ, double maxX, double maxY, double maxZ, Color4f color, BufferBuilder buffer) {
//        // Top side
//        buffer.vertex(minX, maxY, maxZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(maxX, maxY, maxZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(maxX, maxY, minZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(minX, maxY, minZ).color(color.r, color.g, color.b, color.a).next();
//    }
//
//    /**
//     * Assumes a BufferBuilder in GL_QUADS mode has been initialized
//     */
//    public static void drawBoxBottomBatchedQuads(double minX, double minY, double minZ, double maxX, double maxZ, Color4f color, BufferBuilder buffer) {
//        // Bottom side
//        buffer.vertex(maxX, minY, maxZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(minX, minY, maxZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(minX, minY, minZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(maxX, minY, minZ).color(color.r, color.g, color.b, color.a).next();
//    }
//
//    /**
//     * Assumes a BufferBuilder in GL_LINES mode has been initialized
//     */
//    public static void drawBoxAllEdgesBatchedLines(double minX, double minY, double minZ, double maxX, double maxY, double maxZ,
//                                                   Color4f color, BufferBuilder buffer) {
//        // West side
//        buffer.vertex(minX, minY, minZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(minX, minY, maxZ).color(color.r, color.g, color.b, color.a).next();
//
//        buffer.vertex(minX, minY, maxZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(minX, maxY, maxZ).color(color.r, color.g, color.b, color.a).next();
//
//        buffer.vertex(minX, maxY, maxZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(minX, maxY, minZ).color(color.r, color.g, color.b, color.a).next();
//
//        buffer.vertex(minX, maxY, minZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(minX, minY, minZ).color(color.r, color.g, color.b, color.a).next();
//
//        // East side
//        buffer.vertex(maxX, minY, maxZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(maxX, minY, minZ).color(color.r, color.g, color.b, color.a).next();
//
//        buffer.vertex(maxX, minY, minZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(maxX, maxY, minZ).color(color.r, color.g, color.b, color.a).next();
//
//        buffer.vertex(maxX, maxY, minZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(maxX, maxY, maxZ).color(color.r, color.g, color.b, color.a).next();
//
//        buffer.vertex(maxX, maxY, maxZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(maxX, minY, maxZ).color(color.r, color.g, color.b, color.a).next();
//
//        // North side (don't repeat the vertical lines that are done by the east/west sides)
//        buffer.vertex(maxX, minY, minZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(minX, minY, minZ).color(color.r, color.g, color.b, color.a).next();
//
//        buffer.vertex(minX, maxY, minZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(maxX, maxY, minZ).color(color.r, color.g, color.b, color.a).next();
//
//        // South side (don't repeat the vertical lines that are done by the east/west sides)
//        buffer.vertex(minX, minY, maxZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(maxX, minY, maxZ).color(color.r, color.g, color.b, color.a).next();
//
//        buffer.vertex(maxX, maxY, maxZ).color(color.r, color.g, color.b, color.a).next();
//        buffer.vertex(minX, maxY, maxZ).color(color.r, color.g, color.b, color.a).next();
//    }
//
//    public static void drawBox(IntBoundingBox bb, Vec3d cameraPos, Color4f color, BufferBuilder bufferQuads, BufferBuilder bufferLines) {
//        double minX = bb.minX - cameraPos.x;
//        double minY = bb.minY - cameraPos.y;
//        double minZ = bb.minZ - cameraPos.z;
//        double maxX = bb.maxX + 1 - cameraPos.x;
//        double maxY = bb.maxY + 1 - cameraPos.y;
//        double maxZ = bb.maxZ + 1 - cameraPos.z;
//
//        drawBoxAllSidesBatchedQuads(minX, minY, minZ, maxX, maxY, maxZ, color, bufferQuads);
//        drawBoxAllEdgesBatchedLines(minX, minY, minZ, maxX, maxY, maxZ, color, bufferLines);
//    }
//
//    /**
//     * Renders a text plate/billboard, similar to the player name plate.<br>
//     * The plate will always face towards the viewer.
//     *
//     * @param text
//     * @param x
//     * @param y
//     * @param z
//     * @param scale
//     */
//    public static void drawTextPlate(List<String> text, double x, double y, double z, float scale) {
//        Entity entity = mc().getCameraEntity();
//
//        if (entity != null) {
//            drawTextPlate(text, x, y, z, entity.getYaw(), entity.getPitch(), scale, 0xFFFFFFFF, 0x40000000, true);
//        }
//    }
//
//    public static void drawTextPlate(List<String> text, double x, double y, double z, float yaw, float pitch,
//                                     float scale, int textColor, int bgColor, boolean disableDepth) {
//        Vec3d cameraPos = mc().gameRenderer.getCamera().getPos();
//        double cx = cameraPos.x;
//        double cy = cameraPos.y;
//        double cz = cameraPos.z;
//        TextRenderer textRenderer = mc().textRenderer;
//
//        MatrixStack globalStack = RenderSystem.getModelViewStack();
//        globalStack.push();
//        globalStack.translate(x - cx, y - cy, z - cz);
//
//        Quaternionf rot = new Quaternionf().rotationYXZ(-yaw * (float) (Math.PI / 180.0), pitch * (float) (Math.PI / 180.0), 0.0F);
//        globalStack.multiply(rot);
//
//        globalStack.scale(-scale, -scale, scale);
//        RenderSystem.applyModelViewMatrix();
//        RenderSystem.disableCull();
//
//        setupBlend();
//
//        RenderSystem.setShader(GameRenderer::getPositionColorProgram);
//        Tessellator tessellator = Tessellator.getInstance();
//        BufferBuilder buffer = tessellator.getBuffer();
//        int maxLineLen = 0;
//
//        for (String line : text) {
//            maxLineLen = Math.max(maxLineLen, textRenderer.getWidth(line));
//        }
//
//        int strLenHalf = maxLineLen / 2;
//        int textHeight = textRenderer.fontHeight * text.size() - 1;
//        int bga = ((bgColor >>> 24) & 0xFF);
//        int bgr = ((bgColor >>> 16) & 0xFF);
//        int bgg = ((bgColor >>> 8) & 0xFF);
//        int bgb = (bgColor & 0xFF);
//
//        if (disableDepth) {
//            RenderSystem.depthMask(false);
//            RenderSystem.disableDepthTest();
//        }
//
//        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
//        buffer.vertex(-strLenHalf - 1, -1, 0.0D).color(bgr, bgg, bgb, bga).next();
//        buffer.vertex(-strLenHalf - 1, textHeight, 0.0D).color(bgr, bgg, bgb, bga).next();
//        buffer.vertex(strLenHalf, textHeight, 0.0D).color(bgr, bgg, bgb, bga).next();
//        buffer.vertex(strLenHalf, -1, 0.0D).color(bgr, bgg, bgb, bga).next();
//        tessellator.draw();
//
//        int textY = 0;
//
//        // translate the text a bit infront of the background
//        if (disableDepth == false) {
//            RenderSystem.enablePolygonOffset();
//            RenderSystem.polygonOffset(-0.6f, -1.2f);
//        }
//
//        Matrix4f modelMatrix = new Matrix4f();
//        modelMatrix.identity();
//
//        for (String line : text) {
//            if (disableDepth) {
//                RenderSystem.depthMask(false);
//                RenderSystem.disableDepthTest();
//                VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.immediate(buffer);
//                textRenderer.draw(line, -strLenHalf, textY, 0x20000000 | (textColor & 0xFFFFFF), false, modelMatrix, immediate, TextRenderer.TextLayerType.SEE_THROUGH, 0, 15728880);
//                immediate.draw();
//                RenderSystem.enableDepthTest();
//                RenderSystem.depthMask(true);
//            }
//
//            VertexConsumerProvider.Immediate immediate = VertexConsumerProvider.immediate(buffer);
//            textRenderer.draw(line, -strLenHalf, textY, textColor, false, modelMatrix, immediate, TextRenderer.TextLayerType.SEE_THROUGH, 0, 15728880);
//            immediate.draw();
//            textY += textRenderer.fontHeight;
//        }
//
//        if (disableDepth == false) {
//            RenderSystem.polygonOffset(0f, 0f);
//            RenderSystem.disablePolygonOffset();
//        }
//
//        color(1f, 1f, 1f, 1f);
//        RenderSystem.enableCull();
//        RenderSystem.disableBlend();
//        globalStack.pop();
//    }
//
//    public static void renderBlockTargetingOverlay(Entity entity, BlockPos pos, Direction side, Vec3d hitVec,
//                                                   Color4f color, MatrixStack matrixStack, MinecraftClient mc) {
//        Direction playerFacing = entity.getHorizontalFacing();
//        HitPart part = PositionUtils.getHitPart(side, playerFacing, pos, hitVec);
//        Vec3d cameraPos = mc.gameRenderer.getCamera().getPos();
//
//        double x = pos.getX() + 0.5d - cameraPos.x;
//        double y = pos.getY() + 0.5d - cameraPos.y;
//        double z = pos.getZ() + 0.5d - cameraPos.z;
//
//        MatrixStack globalStack = RenderSystem.getModelViewStack();
//        globalStack.push();
//        blockTargetingOverlayTranslations(x, y, z, side, playerFacing, globalStack);
//        RenderSystem.applyModelViewMatrix();
//
//        RenderSystem.setShader(GameRenderer::getPositionColorProgram);
//
//        Tessellator tessellator = Tessellator.getInstance();
//        BufferBuilder buffer = tessellator.getBuffer();
//        int quadAlpha = (int) (0.18f * 255f);
//        int hr = (int) (color.r * 255f);
//        int hg = (int) (color.g * 255f);
//        int hb = (int) (color.b * 255f);
//        int ha = (int) (color.a * 255f);
//        int c = 255;
//
//        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
//
//        // White full block background
//        buffer.vertex(x - 0.5, y - 0.5, z).color(c, c, c, quadAlpha).next();
//        buffer.vertex(x + 0.5, y - 0.5, z).color(c, c, c, quadAlpha).next();
//        buffer.vertex(x + 0.5, y + 0.5, z).color(c, c, c, quadAlpha).next();
//        buffer.vertex(x - 0.5, y + 0.5, z).color(c, c, c, quadAlpha).next();
//
//        switch (part) {
//            case CENTER:
//                buffer.vertex(x - 0.25, y - 0.25, z).color(hr, hg, hb, ha).next();
//                buffer.vertex(x + 0.25, y - 0.25, z).color(hr, hg, hb, ha).next();
//                buffer.vertex(x + 0.25, y + 0.25, z).color(hr, hg, hb, ha).next();
//                buffer.vertex(x - 0.25, y + 0.25, z).color(hr, hg, hb, ha).next();
//                break;
//            case LEFT:
//                buffer.vertex(x - 0.50, y - 0.50, z).color(hr, hg, hb, ha).next();
//                buffer.vertex(x - 0.25, y - 0.25, z).color(hr, hg, hb, ha).next();
//                buffer.vertex(x - 0.25, y + 0.25, z).color(hr, hg, hb, ha).next();
//                buffer.vertex(x - 0.50, y + 0.50, z).color(hr, hg, hb, ha).next();
//                break;
//            case RIGHT:
//                buffer.vertex(x + 0.50, y - 0.50, z).color(hr, hg, hb, ha).next();
//                buffer.vertex(x + 0.25, y - 0.25, z).color(hr, hg, hb, ha).next();
//                buffer.vertex(x + 0.25, y + 0.25, z).color(hr, hg, hb, ha).next();
//                buffer.vertex(x + 0.50, y + 0.50, z).color(hr, hg, hb, ha).next();
//                break;
//            case TOP:
//                buffer.vertex(x - 0.50, y + 0.50, z).color(hr, hg, hb, ha).next();
//                buffer.vertex(x - 0.25, y + 0.25, z).color(hr, hg, hb, ha).next();
//                buffer.vertex(x + 0.25, y + 0.25, z).color(hr, hg, hb, ha).next();
//                buffer.vertex(x + 0.50, y + 0.50, z).color(hr, hg, hb, ha).next();
//                break;
//            case BOTTOM:
//                buffer.vertex(x - 0.50, y - 0.50, z).color(hr, hg, hb, ha).next();
//                buffer.vertex(x - 0.25, y - 0.25, z).color(hr, hg, hb, ha).next();
//                buffer.vertex(x + 0.25, y - 0.25, z).color(hr, hg, hb, ha).next();
//                buffer.vertex(x + 0.50, y - 0.50, z).color(hr, hg, hb, ha).next();
//                break;
//            default:
//        }
//
//        tessellator.draw();
//
//        RenderSystem.lineWidth(1.6f);
//
//        buffer.begin(VertexFormat.DrawMode.DEBUG_LINE_STRIP, VertexFormats.POSITION_COLOR);
//
//        // Middle small rectangle
//        buffer.vertex(x - 0.25, y - 0.25, z).color(c, c, c, c).next();
//        buffer.vertex(x + 0.25, y - 0.25, z).color(c, c, c, c).next();
//        buffer.vertex(x + 0.25, y + 0.25, z).color(c, c, c, c).next();
//        buffer.vertex(x - 0.25, y + 0.25, z).color(c, c, c, c).next();
//        buffer.vertex(x - 0.25, y - 0.25, z).color(c, c, c, c).next();
//        tessellator.draw();
//
//        buffer.begin(VertexFormat.DrawMode.DEBUG_LINES, VertexFormats.POSITION_COLOR);
//        // Bottom left
//        buffer.vertex(x - 0.50, y - 0.50, z).color(c, c, c, c).next();
//        buffer.vertex(x - 0.25, y - 0.25, z).color(c, c, c, c).next();
//
//        // Top left
//        buffer.vertex(x - 0.50, y + 0.50, z).color(c, c, c, c).next();
//        buffer.vertex(x - 0.25, y + 0.25, z).color(c, c, c, c).next();
//
//        // Bottom right
//        buffer.vertex(x + 0.50, y - 0.50, z).color(c, c, c, c).next();
//        buffer.vertex(x + 0.25, y - 0.25, z).color(c, c, c, c).next();
//
//        // Top right
//        buffer.vertex(x + 0.50, y + 0.50, z).color(c, c, c, c).next();
//        buffer.vertex(x + 0.25, y + 0.25, z).color(c, c, c, c).next();
//        tessellator.draw();
//
//        globalStack.pop();
//        RenderSystem.applyModelViewMatrix();
//    }
//
//    public static void renderBlockTargetingOverlaySimple(Entity entity, BlockPos pos, Direction side,
//                                                         Color4f color, MatrixStack matrixStack, MinecraftClient mc) {
//        Direction playerFacing = entity.getHorizontalFacing();
//        Vec3d cameraPos = mc.gameRenderer.getCamera().getPos();
//
//        double x = pos.getX() + 0.5d - cameraPos.x;
//        double y = pos.getY() + 0.5d - cameraPos.y;
//        double z = pos.getZ() + 0.5d - cameraPos.z;
//
//        MatrixStack globalStack = RenderSystem.getModelViewStack();
//        globalStack.push();
//
//        blockTargetingOverlayTranslations(x, y, z, side, playerFacing, globalStack);
//        RenderSystem.applyModelViewMatrix();
//        RenderSystem.setShader(GameRenderer::getPositionColorProgram);
//
//        Tessellator tessellator = Tessellator.getInstance();
//        BufferBuilder buffer = tessellator.getBuffer();
//
//        int a = (int) (color.a * 255f);
//        int r = (int) (color.r * 255f);
//        int g = (int) (color.g * 255f);
//        int b = (int) (color.b * 255f);
//        int c = 255;
//
//        buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
//
//        // Simple colored quad
//        buffer.vertex(x - 0.5, y - 0.5, z).color(r, g, b, a).next();
//        buffer.vertex(x + 0.5, y - 0.5, z).color(r, g, b, a).next();
//        buffer.vertex(x + 0.5, y + 0.5, z).color(r, g, b, a).next();
//        buffer.vertex(x - 0.5, y + 0.5, z).color(r, g, b, a).next();
//
//        tessellator.draw();
//
//        RenderSystem.lineWidth(1.6f);
//
//        buffer.begin(VertexFormat.DrawMode.DEBUG_LINE_STRIP, VertexFormats.POSITION_COLOR);
//
//        // Middle rectangle
//        buffer.vertex(x - 0.375, y - 0.375, z).color(c, c, c, c).next();
//        buffer.vertex(x + 0.375, y - 0.375, z).color(c, c, c, c).next();
//        buffer.vertex(x + 0.375, y + 0.375, z).color(c, c, c, c).next();
//        buffer.vertex(x - 0.375, y + 0.375, z).color(c, c, c, c).next();
//
//        tessellator.draw();
//
//        globalStack.pop();
//        RenderSystem.applyModelViewMatrix();
//    }
//
//    private static void blockTargetingOverlayTranslations(double x, double y, double z,
//                                                          Direction side, Direction playerFacing, MatrixStack matrixStack) {
//        matrixStack.translate(x, y, z);
//
//        switch (side) {
//            case DOWN:
//                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180f - playerFacing.asRotation()));
//                matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(90f));
//                break;
//            case UP:
//                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180f - playerFacing.asRotation()));
//                matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-90f));
//                break;
//            case NORTH:
//                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180f));
//                break;
//            case SOUTH:
//                break;
//            case WEST:
//                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90f));
//                break;
//            case EAST:
//                matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90f));
//                break;
//        }
//
//        matrixStack.translate(-x, -y, -z + 0.510);
//    }
//
//    public static void renderMapPreview(ItemStack stack, int x, int y, int dimensions) {
//        if (stack.getItem() instanceof FilledMapItem && GuiBase.isShiftDown()) {
//            color(1f, 1f, 1f, 1f);
//
//            int y1 = y - dimensions - 20;
//            int y2 = y1 + dimensions;
//            int x1 = x + 8;
//            int x2 = x1 + dimensions;
//            int z = 300;
//
//            Integer mapId = FilledMapItem.getMapId(stack);
//            MapState mapState = FilledMapItem.getMapState(mapId, mc().world);
//
//            Identifier bgTexture = mapState == null ? TEXTURE_MAP_BACKGROUND : TEXTURE_MAP_BACKGROUND_CHECKERBOARD;
//            bindTexture(bgTexture);
//            setupBlend();
//
//            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
//            RenderSystem.applyModelViewMatrix();
//            Tessellator tessellator = Tessellator.getInstance();
//            BufferBuilder buffer = tessellator.getBuffer();
//            buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_TEXTURE);
//            buffer.vertex(x1, y2, z).texture(0.0f, 1.0f).next();
//            buffer.vertex(x2, y2, z).texture(1.0f, 1.0f).next();
//            buffer.vertex(x2, y1, z).texture(1.0f, 0.0f).next();
//            buffer.vertex(x1, y1, z).texture(0.0f, 0.0f).next();
//            tessellator.draw();
//            RenderSystem.disableBlend();
//
//            if (mapState != null) {
//                x1 += 8;
//                y1 += 8;
//                z = 310;
//                VertexConsumerProvider.Immediate consumer = VertexConsumerProvider.immediate(buffer);
//                double scale = (double) (dimensions - 16) / 128.0D;
//                MatrixStack matrixStack = new MatrixStack();
//                matrixStack.push();
//                matrixStack.translate(x1, y1, z);
//                matrixStack.scale((float) scale, (float) scale, 0);
//                mc().gameRenderer.getMapRenderer().draw(matrixStack, consumer, mapId, mapState, false, 0xF000F0);
//                consumer.draw();
//                matrixStack.pop();
//            }
//        }
//    }
//
//    public static void renderShulkerBoxPreview(ItemStack stack, int baseX, int baseY, boolean useBgColors, DrawContext drawContext) {
//        if (stack.hasNbt()) {
//            DefaultedList<ItemStack> items = InventoryUtils.getStoredItems(stack, -1);
//
//            if (items.size() == 0) {
//                return;
//            }
//
//            InventoryOverlay.InventoryRenderType type = InventoryOverlay.getInventoryType(stack);
//            InventoryOverlay.InventoryProperties props = InventoryOverlay.getInventoryPropsTemp(type, items.size());
//
//            int screenWidth = GuiUtils.getScaledWindowWidth();
//            int screenHeight = GuiUtils.getScaledWindowHeight();
//            int height = props.height + 18;
//            int x = MathHelper.clamp(baseX + 8, 0, screenWidth - props.width);
//            int y = MathHelper.clamp(baseY - height, 0, screenHeight - height);
//
//            if (stack.getItem() instanceof BlockItem && ((BlockItem) stack.getItem()).getBlock() instanceof ShulkerBoxBlock) {
//                setShulkerboxBackgroundTintColor((ShulkerBoxBlock) ((BlockItem) stack.getItem()).getBlock(), useBgColors);
//            } else {
//                color(1f, 1f, 1f, 1f);
//            }
//
//            disableDiffuseLighting();
//            MatrixStack matrixStack = RenderSystem.getModelViewStack();
//            matrixStack.push();
//            matrixStack.translate(0, 0, 500);
//            RenderSystem.applyModelViewMatrix();
//
//            InventoryOverlay.renderInventoryBackground(type, x, y, props.slotsPerRow, items.size(), mc());
//
//            enableDiffuseLightingGui3D();
//
//            Inventory inv = fi.dy.masa.malilib.util.InventoryUtils.getAsInventory(items);
//            InventoryOverlay.renderInventoryStacks(type, inv, x + props.slotOffsetX, y + props.slotOffsetY, props.slotsPerRow, 0, -1, mc(), drawContext);
//
//            matrixStack.pop();
//            RenderSystem.applyModelViewMatrix();
//        }
//    }
//
//    /**
//     * Calls RenderUtils.color() with the dye color of the provided shulker box block's color
//     *
//     * @param block
//     * @param useBgColors
//     */
//    public static void setShulkerboxBackgroundTintColor(@Nullable ShulkerBoxBlock block, boolean useBgColors) {
//        if (block != null && useBgColors) {
//            // In 1.13+ there is the uncolored Shulker Box variant, which returns null from getColor()
//            final DyeColor dye = block.getColor() != null ? block.getColor() : DyeColor.PURPLE;
//            final float[] colors = dye.getColorComponents();
//            color(colors[0], colors[1], colors[2], 1f);
//        } else {
//            color(1f, 1f, 1f, 1f);
//        }
//    }
//
//    public static void renderModelInGui(int x, int y, BakedModel model, BlockState state, float zLevel) {
//        if (state.getBlock() == Blocks.AIR) {
//            return;
//        }
//
//        MatrixStack matrixStack = RenderSystem.getModelViewStack();
//        matrixStack.push();
//        bindTexture(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE);
//        mc().getTextureManager().getTexture(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).setFilter(false, false);
//
//        RenderSystem.enableBlend();
//        setupBlendSimple();
//        color(1f, 1f, 1f, 1f);
//
//        setupGuiTransform(x, y, model.hasDepth(), zLevel);
//        //model.getItemCameraTransforms().applyTransform(ItemCameraTransforms.TransformType.GUI);
//        matrixStack.multiply(RotationAxis.POSITIVE_X.rotationDegrees(30));
//        matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(225));
//        matrixStack.scale(0.625f, 0.625f, 0.625f);
//
//        renderModel(model, state);
//
//        matrixStack.pop();
//    }
//
//    public static void setupGuiTransform(int xPosition, int yPosition, boolean isGui3d, float zLevel) {
//        setupGuiTransform(RenderSystem.getModelViewStack(), xPosition, yPosition, zLevel);
//    }
//
//    public static void setupGuiTransform(MatrixStack matrixStack, int xPosition, int yPosition, float zLevel) {
//        matrixStack.translate(xPosition + 8.0, yPosition + 8.0, zLevel + 100.0);
//        matrixStack.scale(16, -16, 16);
//    }
//
//    private static void renderModel(BakedModel model, BlockState state) {
//        MatrixStack matrixStack = RenderSystem.getModelViewStack();
//        matrixStack.push();
//        matrixStack.translate(-0.5, -0.5, -0.5);
//        int color = 0xFFFFFFFF;
//
//        if (model.isBuiltin() == false) {
//            RenderSystem.setShader(GameRenderer::getRenderTypeSolidProgram);
//            RenderSystem.applyModelViewMatrix();
//            Tessellator tessellator = Tessellator.getInstance();
//            BufferBuilder bufferbuilder = tessellator.getBuffer();
//            bufferbuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR_TEXTURE_LIGHT_NORMAL);
//
//            for (Direction face : Direction.values()) {
//                RAND.setSeed(0);
//                renderQuads(bufferbuilder, model.getQuads(state, face, RAND), state, color);
//            }
//
//            RAND.setSeed(0);
//            renderQuads(bufferbuilder, model.getQuads(state, null, RAND), state, color);
//            tessellator.draw();
//        }
//
//        matrixStack.pop();
//    }
//
//    private static void renderQuads(BufferBuilder renderer, List<BakedQuad> quads, BlockState state, int color) {
//        final int quadCount = quads.size();
//
//        for (int i = 0; i < quadCount; ++i) {
//            BakedQuad quad = quads.get(i);
//            renderQuad(renderer, quad, state, 0xFFFFFFFF);
//        }
//    }
//
//    private static void renderQuad(BufferBuilder buffer, BakedQuad quad, BlockState state, int color) {
//        /*
//        buffer.putVertexData(quad.getVertexData());
//        buffer.setQuadColor(color);
//
//        if (quad.hasColor())
//        {
//            BlockColors blockColors = mc().getBlockColorMap();
//            int m = blockColors.getColorMultiplier(state, null, null, quad.getColorIndex());
//
//            float r = (float) (m >>> 16 & 0xFF) / 255F;
//            float g = (float) (m >>>  8 & 0xFF) / 255F;
//            float b = (float) (m        & 0xFF) / 255F;
//            buffer.multiplyColor(r, g, b, 4);
//            buffer.multiplyColor(r, g, b, 3);
//            buffer.multiplyColor(r, g, b, 2);
//            buffer.multiplyColor(r, g, b, 1);
//        }
//
//        putQuadNormal(buffer, quad);
//    }
//
//    private static void putQuadNormal(BufferBuilder renderer, BakedQuad quad)
//    {
//        Vec3i direction = quad.getFace().getVector();
//        renderer.normal(direction.getX(), direction.getY(), direction.getZ());
//        */
//    }
//
    private static Minecraft mc() {
        return Minecraft.getMinecraft();
    }
//
//    /*
//    public static void enableGUIStandardItemLighting(float scale)
//    {
//        RenderSystem.pushMatrix();
//        RenderSystem.rotate(-30.0F, 0.0F, 1.0F, 0.0F);
//        RenderSystem.rotate(165.0F, 1.0F, 0.0F, 0.0F);
//
//        enableStandardItemLighting(scale);
//
//        RenderSystem.popMatrix();
//    }
//
//    public static void enableStandardItemLighting(float scale)
//    {
//        RenderSystem.enableLighting();
//        RenderSystem.enableLight(0);
//        RenderSystem.enableLight(1);
//        RenderSystem.enableColorMaterial();
//        RenderUtils.colorMaterial(GL11.GL_FRONT_AND_BACK, GL11.GL_AMBIENT_AND_DIFFUSE);
//        RenderSystem.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, RenderHelper.setColorBuffer((float) LIGHT0_POS.x, (float) LIGHT0_POS.y, (float) LIGHT0_POS.z, 0.0f));
//
//        float lightStrength = 0.3F * scale;
//        RenderSystem.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, RenderHelper.setColorBuffer(lightStrength, lightStrength, lightStrength, 1.0F));
//        RenderSystem.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, RenderHelper.setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
//        RenderSystem.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, RenderHelper.setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
//        RenderSystem.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION, RenderHelper.setColorBuffer((float) LIGHT1_POS.x, (float) LIGHT1_POS.y, (float) LIGHT1_POS.z, 0.0f));
//        RenderSystem.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, RenderHelper.setColorBuffer(lightStrength, lightStrength, lightStrength, 1.0F));
//        RenderSystem.glLight(GL11.GL_LIGHT1, GL11.GL_AMBIENT, RenderHelper.setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
//        RenderSystem.glLight(GL11.GL_LIGHT1, GL11.GL_SPECULAR, RenderHelper.setColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
//
//        RenderSystem.shadeModel(GL11.GL_FLAT);
//
//        float ambientLightStrength = 0.4F;
//        RenderSystem.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, RenderHelper.setColorBuffer(ambientLightStrength, ambientLightStrength, ambientLightStrength, 1.0F));
//    }
//    */
}