package ua.com.integer.gdx.xml.ui.setup.imp.common.effect;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SoundClickListener extends ClickListener {
    private Sound sound;

    public SoundClickListener(Sound sound) {
        this.sound = sound;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        if (sound != null) {
            sound.play();
        }
    }
}
