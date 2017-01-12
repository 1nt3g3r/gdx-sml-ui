package ua.com.integer.gdx.xml.ui.util.eval;

public interface OperatorHandler {
    float evaluateOperator(float lft, char opr, float rgt) throws ArithmeticException;
}
