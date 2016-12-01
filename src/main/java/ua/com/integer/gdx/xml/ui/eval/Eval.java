package ua.com.integer.gdx.xml.ui.eval;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.OrderedMap;

public class Eval {
	private static MathEval eval = new MathEval();
	private static OrderedMap<String, String> userVariables = new OrderedMap<String, String>();
	
	public static MathEval getEvalEngine() {
		return eval;
	}
	
	public static void setVars(OrderedMap<String, String> variables) {
		for(String key: variables.keys()) {
			userVariables.put(key, variables.get(key));
		}
	}
	
	public static void setVar(String name, float value) {
		setVar(name, Float.toString(value));
	}
	
	public static void setVar(String name, String value) {
		userVariables.put(name, value);
	}
	
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
		
		if (userVariables != null) {
			for(String key : userVariables.keys()) {
				eval.setVariable(key, eval.evaluate(userVariables.get(key)));
			}
		}
		
		return eval.evaluate(expression);
	}
}
