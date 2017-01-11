package ua.com.integer.gdx.xml.ui.element;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Window;

import ua.com.integer.gdx.xml.ui.creator.XUICreator;
import ua.com.integer.gdx.xml.ui.util.ActorMathEval;
import ua.com.integer.gdx.xml.ui.processor.XUIProcessor;

/**
 * This class inflates {@link XUIElement} into {@link com.badlogic.gdx.scenes.scene2d.Group} or {@link com.badlogic.gdx.scenes.scene2d.Stage}
 */
public class XUIElementInflater {
    /**
     * Recursively inflates given element into group
     * @param def element to inflate
     * @param parent target group to inflate
     */
    public static void inflate(XUIElement def, Group parent) {
        if (def.type.equals("var")) {
            ActorMathEval.setVar(def.attributes.get("name"), def.attributes.get("value"));
            return;
        }

        def.resultActor = XUICreator.createActor(def.type, def);

        if (parent instanceof ScrollPane) {
            ((ScrollPane) parent).setWidget(def.resultActor);
        } else if (parent instanceof Window) {
            Window window = (Window) parent;
            window.add(def.resultActor).expand().fill();
        } else {
            parent.addActor(def.resultActor);
        }

        XUIProcessor.process(def);

        if(def.resultActor instanceof Group) {
            Group g = (Group) def.resultActor;
            for(XUIElement child : def.children) {
                inflate(child, g);
            }
        }
    }

    /**
     * Recursivery inflates givent element into stage.getRoot() group
     * @param def element to inflate
     * @param stage target stage to inflate
     */
    public static void inflate(XUIElement def, Stage stage) {
        inflate(def, stage.getRoot());
    }
}
