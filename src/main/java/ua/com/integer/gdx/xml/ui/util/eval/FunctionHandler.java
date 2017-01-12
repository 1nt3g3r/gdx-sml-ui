package ua.com.integer.gdx.xml.ui.util.eval;

public interface FunctionHandler {
    float evaluateFunction(String fncnam, MathEval.ArgParser fncargs) throws ArithmeticException;
}
