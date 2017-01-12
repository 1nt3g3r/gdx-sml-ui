package ua.com.integer.gdx.xml.ui.util.eval;

public final class Operator extends Object {
    final char symbol;
    final int precedenceL;
    final int precedenceR;
    final int unary;
    final boolean internal;
    final OperatorHandler handler;

    public Operator(char sym, int prc, OperatorHandler hnd) {
        this(sym, prc, prc, MathEval.NO_SIDE, false, hnd);
    }

    Operator(char sym, int prclft, int prcrgt, int unibnd, boolean intern, OperatorHandler hnd) {
        symbol = sym;
        precedenceL = prclft;
        precedenceR = prcrgt;
        unary = unibnd;
        internal = intern;
        handler = hnd;
    }

    public String toString() {
        return ("MathOperator['" + symbol + "']");
    }
}
