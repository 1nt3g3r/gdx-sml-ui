package ua.com.integer.gdx.xml.ui.processor.imp;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;

import ua.com.integer.gdx.xml.ui.processor.XUIProcessor;

/**
 * Setup {@link com.badlogic.gdx.scenes.scene2d.ui.ScrollPane}
 */
public class ScrollPaneProcessor extends XUIProcessor {
    private ScrollPane scrollPane;

    @Override
    public void process() {
        scrollPane = (ScrollPane) element.resultActor;

        if (hasAttribute("scrollX")) {
            scrollPane.setScrollX(eval("scrollX"));
        }

        if (hasAttribute("scrollY")) {
            scrollPane.setScrollY(eval("scrollY"));
        }

        if (hasAttribute("scrollPercentX")) {
            scrollPane.setScrollPercentX(eval("scrollPercentX"));
        }

        if (hasAttribute("scrollPercentY")) {
            scrollPane.setScrollPercentY(eval("scrollPercentY"));
        }

        if (hasAttribute("flickScroll")) {
            scrollPane.setFlickScroll(getBoolean("flickScroll"));
        }

        if (hasAttribute("halfTapSquareSize")) {
            scrollPane.setFlickScrollTapSquareSize(eval("halfTapSquareSize"));
        }

        boolean scrollXDisabled = false;
        boolean scrollYDisabled = false;
        if (hasAttribute("disableScrollX")) {
            scrollXDisabled = getBoolean("disableScrollX");
        }
        if (hasAttribute("disableScrollY")) {
            scrollYDisabled = getBoolean("disableScrollY");
        }
        scrollPane.setScrollingDisabled(scrollXDisabled, scrollYDisabled);

        if (hasAttribute("velocityX")) {
            scrollPane.setVelocityX(eval("velocityX"));
        }

        if (hasAttribute("velocityY")) {
            scrollPane.setVelocityY(eval("velocityY"));
        }

        boolean overscrollX = true;
        boolean overscrollY = true;
        if (hasAttribute("overscrollX")) {
            overscrollX = getBoolean("overscrollX");
        }
        if (hasAttribute("overscrollY")) {
            overscrollY = getBoolean("overscrollY");
        }
        scrollPane.setOverscroll(overscrollX, overscrollY);


        float overscrollDistance = 50, overscrollSpeedMin = 30, overscrollSpeedMax = 200;
        if (hasAttribute("overscrollDistance")) {
            overscrollDistance = getFloat("overscrollDistance");
        }
        if (hasAttribute("overscrollSpeedMin")) {
            overscrollSpeedMin = getFloat("overscrollSpeedMin");
        }
        if (hasAttribute("overscrollSpeedMax")) {
            overscrollSpeedMax = getFloat("overscrollSpeedMax");
        }
        scrollPane.setupOverscroll(overscrollDistance, overscrollSpeedMin, overscrollSpeedMax);

        boolean forceScrollX = false, forceScrollY = false;
        if (hasAttribute("forceScrollX")) {
            forceScrollX = getBoolean("forceScrollX");
        }
        if (hasAttribute("forceScrollY")) {
            forceScrollY = getBoolean("forceScrollY");
        }
        scrollPane.setForceScroll(forceScrollX, forceScrollY);

        if (hasAttribute("flingTime")) {
            scrollPane.setFlingTime(getFloat("flingTime"));
        }

        if (hasAttribute("clamp")) {
            scrollPane.setClamp(getBoolean("clamp"));
        }

        boolean vScrollOnRight = true;
        boolean hScrollOnBottom = true;
        if (hasAttribute("vScrollOnRight")) {
            vScrollOnRight = getBoolean("vScrollOnRight");
        }
        if (hasAttribute("hScrollOnBottom")) {
            hScrollOnBottom = getBoolean("hScrollOnBottom");
        }
        scrollPane.setScrollBarPositions(hScrollOnBottom, vScrollOnRight);

        if (hasAttribute("fadeScrollBars")) {
            scrollPane.setFadeScrollBars(getBoolean("fadeScrollBars"));
        }

        float fadeAlphaSeconds = 1, fadeDelaySeconds = 1;
        if (hasAttribute("fadeAlphaSeconds")) {
            fadeAlphaSeconds = getFloat("fadeAlphaSeconds");
        }
        if (hasAttribute("fadeDelaySeconds")) {
            fadeDelaySeconds = getFloat("fadeDelaySeconds");
        }
        scrollPane.setupFadeScrollBars(fadeAlphaSeconds, fadeDelaySeconds);

        if (hasAttribute("smoothScrolling")) {
            scrollPane.setSmoothScrolling(getBoolean("smoothScrolling"));
        }

        if (hasAttribute("scrollbarsOnTop")) {
            scrollPane.setScrollbarsOnTop(getBoolean("scrollbarsOnTop"));
        }

        if (hasAttribute("variableSizeKnobs")) {
            scrollPane.setVariableSizeKnobs(getBoolean("variableSizeKnobs"));
        }

        if (hasAttribute("cancelTouchFocus")) {
            scrollPane.setCancelTouchFocus(getBoolean("cancelTouchFocus"));
        }

        setupStyle();
    }

    private void setupStyle() {
        ScrollPane.ScrollPaneStyle style = scrollPane.getStyle();

        if (hasAttribute("background")) {
            style.background = getDrawable("background");
        }
        setupDrawableParams(style.background, "background");

        if (hasAttribute("corner")) {
            style.corner = getDrawable("corner");
        }
        setupDrawableParams(style.corner, "corner");

        if (hasAttribute("hScroll")) {
            style.hScroll = getDrawable("hScroll");
        }
        setupDrawableParams(style.hScroll, "hScroll");

        if (hasAttribute("hScrollKnob")) {
            style.hScrollKnob = getDrawable("hScrollKnob");
        }
        setupDrawableParams(style.hScrollKnob, "hScrollKnob");

        if (hasAttribute("vScroll")) {
            style.vScroll = getDrawable("vScroll");
        }
        setupDrawableParams(style.vScroll, "vScroll");

        if (hasAttribute("vScrollKnob")) {
            style.vScrollKnob = getDrawable("vScrollKnob");
        }
        setupDrawableParams(style.vScrollKnob, "vScrollKnob");
    }
}
