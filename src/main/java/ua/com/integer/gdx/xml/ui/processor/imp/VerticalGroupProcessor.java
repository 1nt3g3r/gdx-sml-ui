package ua.com.integer.gdx.xml.ui.processor.imp;

import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;

import ua.com.integer.gdx.xml.ui.processor.XUIProcessor;

public class VerticalGroupProcessor extends XUIProcessor {
    @Override
    public void process() {
        VerticalGroup vGroup = (VerticalGroup) element.resultActor;

        if (hasAttribute("round")) {
            vGroup.setRound(getBoolean("round"));
        }

        if (hasAttribute("reverse")) {
            vGroup.reverse(getBoolean("reverse"));
        }

        if (hasAttribute("space")) {
            vGroup.space(eval("space"));
        }

        if (hasAttribute("pad")) {
            vGroup.pad(eval("pad"));
        }

        if (hasAttribute("padLeft")) {
            vGroup.padLeft(eval("padLeft"));
        }

        if (hasAttribute("padRight")) {
            vGroup.padRight(eval("padRight"));
        }

        if (hasAttribute("padBottom")) {
            vGroup.padBottom(eval("padBottom"));
        }

        if (hasAttribute("padTop")) {
            vGroup.padTop(eval("padTop"));
        }

        if (hasAttribute("align")) {
            vGroup.align(getAlign("align"));
        }

        if (hasAttribute("fill")) {
            vGroup.fill(eval("fill"));
        }

    }
}
