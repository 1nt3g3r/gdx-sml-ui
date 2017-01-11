package ua.com.integer.gdx.xml.ui.processor.imp.common.effect;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import ua.com.integer.gdx.xml.ui.XUI;

public class SoundClickListener extends ClickListener {
    private Sound sound;

    public SoundClickListener(Sound sound) {
        this.sound = sound;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        if (!XUI.variables().getBoolean("sound.enabled", true)) {
            return;
        }

        if (sound == null) {
            return;
        }

        sound.play();
    }
}
