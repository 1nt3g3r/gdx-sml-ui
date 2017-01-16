package ua.com.integer.gdx.xml.ui.creator.imp;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import ua.com.integer.gdx.xml.ui.creator.XUICreator;
import ua.com.integer.gdx.xml.ui.res.XUIAssetsAccess;
import ua.com.integer.gdx.xml.ui.util.ActorMathEval;

public class SliderCreator extends XUICreator {
    @Override
    protected Actor create(String type) {
        float min = ActorMathEval.eval(null, element.attributes.get("min"));
        float max = ActorMathEval.eval(null, element.attributes.get("max"));
        float step = ActorMathEval.eval(null, element.attributes.get("step"));
        boolean vertical = false;

        if (element.attributes.containsKey("vertical")) {
            vertical = Boolean.parseBoolean(element.attributes.get("vertical"));
        }

        Drawable background = XUIAssetsAccess.getDrawableCopy(element.attributes.get("background"));
        Drawable knob = XUIAssetsAccess.getDrawableCopy(element.attributes.get("knob"));

        Slider.SliderStyle sliderStyle = new Slider.SliderStyle(background, knob);

        Slider slider = new Slider(min, max, step, vertical, sliderStyle);
        slider.setName("Slider");
        return slider;
    }
}
