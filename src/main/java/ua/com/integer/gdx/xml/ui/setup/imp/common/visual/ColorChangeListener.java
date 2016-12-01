package ua.com.integer.gdx.xml.ui.setup.imp.common.visual;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class ColorChangeListener extends InputListener {
        private Color changeToColor = new Color(Color.GRAY);
        private Color originalActorColor;
        private boolean touchingNow;

        public ColorChangeListener() {
            this(Color.GRAY);
        }

        public ColorChangeListener(Color toColor) {
            changeToColor.set(toColor);
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (touchingNow) {
                return false;
            }
            touchingNow = true;
            if (originalActorColor == null) {
                if (event.getListenerActor() instanceof Group) {
                    Group group = (Group) event.getListenerActor();
                    if (group.getChildren().size > 0) {
                        originalActorColor = new Color(group.getChildren().first().getColor());
                    } else {
                        originalActorColor = new Color(event.getListenerActor().getColor());
                    }
                } else {
                    originalActorColor = new Color(event.getListenerActor().getColor());
                }
            }

            if (event.getListenerActor() instanceof Group) {
                Group group = (Group) event.getListenerActor();
                if (group.getChildren().size > 0) {
                    group.getChildren().first().addAction(Actions.color(changeToColor, 0.1f));
                } else {
                    event.getListenerActor().addAction(Actions.color(changeToColor, 0.1f));
                }
            } else {
                event.getListenerActor().addAction(Actions.color(changeToColor, 0.1f));
            }
            return true;
        }

        @Override
        public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            touchingNow = false;
            if (event.getListenerActor() instanceof Group) {
                Group group = (Group) event.getListenerActor();
                if (group.getChildren().size > 0) {
                    group.getChildren().first().addAction(Actions.color(originalActorColor, 0.1f));
                } else {
                    event.getListenerActor().addAction(Actions.color(originalActorColor, 0.1f));
                }
            } else {
                event.getListenerActor().addAction(Actions.color(originalActorColor, 0.1f));
            }
        }
}
