package ua.com.integer.gdx.xml.ui.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.OrderedMap;

/**
 * Provides the ability to evaluate a String math expression.
 * User can provide his own variables.
 *
 * Some variables are already predefined:
 *
 * <b>screenWidth</b> - Gdx.graphics.getWidth()
 * <b>screenHeight</b> - Gdx.graphics.getHeight()
 *
 * <b>width</b> - width of actor
 * <b>height</b> - height of actor
 *
 * <b>parentWidth</b> - width of parent actor group
 * <b>parentHeight</b> - height of parent actor group
 */
public class ActorMathEval {
	private static ua.com.integer.gdx.xml.ui.util.eval.MathEval eval = new ua.com.integer.gdx.xml.ui.util.eval.MathEval();
	private static OrderedMap<String, String> userVariables = new OrderedMap<String, String>();

    /**
     * Set user variable.
     * @param name of variable
     * @param value of variable. Can be expression and include another variables
     */
	public static void setVar(String name, String value) {
		userVariables.put(name, value);
	}

    /**
     * Calculates expression for given actor
     */
	public static float eval(Actor actor, String expression) {
		if (actor != null) {
			eval.setVariable("width", actor.getWidth());
			eval.setVariable("height", actor.getHeight());
			
			if (actor.getParent() != null) {
				if (actor.getStage() != null && actor.getParent() == actor.getStage().getRoot()) {
					eval.setVariable("parentWidth", actor.getStage().getWidth());
					eval.setVariable("parentHeight", actor.getStage().getHeight());
				} else {
					eval.setVariable("parentWidth", actor.getParent().getWidth());
					eval.setVariable("parentHeight", actor.getParent().getHeight());
				}
			}
		}
		
		eval.setVariable("screenWidth", Gdx.graphics.getWidth());
		eval.setVariable("screenHeight", Gdx.graphics.getHeight());
		
        for(String key : userVariables.keys()) {
            eval.setVariable(key, eval.evaluate(userVariables.get(key)));
        }

		return eval.evaluate(expression);
	}
}
