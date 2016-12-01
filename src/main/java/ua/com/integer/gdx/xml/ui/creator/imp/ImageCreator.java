package ua.com.integer.gdx.xml.ui.creator.imp;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import ua.com.integer.gdx.xml.ui.creator.ActorCreator;

public class ImageCreator extends ActorCreator {
    @Override
    protected Actor create(String packageName) {
        return new Image();
    }
}
