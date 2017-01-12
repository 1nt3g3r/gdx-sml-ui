package ua.com.integer.gdx.xml.ui.util.eval;

import com.badlogic.gdx.math.MathUtils;

class DefaultImpl extends Object implements OperatorHandler, FunctionHandler {
    private DefaultImpl() {
    }

    public float evaluateOperator(float lft, char opr, float rgt) {
        switch (opr) {
            case '=':
                return rgt;
            case '^':
                return (float) Math.pow(lft, rgt);
            case '±':
                return -rgt;
            case '*':
                return lft * rgt;
            case '(':
                return lft * rgt;
            case '/':
                return lft / rgt;
            case '÷':
                return lft / rgt;
            case '%':
                return lft % rgt;
            case '+':
                return lft + rgt;
            case '-':
                return lft - rgt;
            default:
                throw new UnsupportedOperationException(
                        "MathEval internal operator setup is incorrect - internal operator \"" + opr
                                + "\" not handled");
        }
    }

    public float evaluateFunction(String fncnam, MathEval.ArgParser fncargs) throws ArithmeticException {
        switch (Character.toLowerCase(fncnam.charAt(0))) {
            case 'a': {
                if (fncnam.equalsIgnoreCase("abs")) return Math.abs(fncargs.next());
            }
            break;
            case 'c': {
                if (fncnam.equalsIgnoreCase("cos")) return MathUtils.cosDeg(fncargs.next());
            }
            break;
            case 'm': {
                if (fncnam.equalsIgnoreCase("max"))
                    return Math.max(fncargs.next(), fncargs.next());
                if (fncnam.equalsIgnoreCase("min"))
                    return Math.min(fncargs.next(), fncargs.next());
            }
            break;
            case 'r': {
                if (fncnam.equalsIgnoreCase("random")) return MathUtils.random();
                if (fncnam.equalsIgnoreCase("round")) return (float) Math.round(fncargs.next());

            }
            break;
            case 's': {
                if (fncnam.equalsIgnoreCase("sin")) return MathUtils.sinDeg(fncargs.next());
                if (fncnam.equalsIgnoreCase("sqrt")) return (float) Math.sqrt(fncargs.next());

            }
            break;
            case 't': {
                if (fncnam.equalsIgnoreCase("toDegrees"))
                    return (float) Math.toDegrees(fncargs.next());
                if (fncnam.equalsIgnoreCase("toRadians"))
                    return (float) Math.toRadians(fncargs.next());
            }
            break;
        }
        throw new UnsupportedOperationException(
                "MathEval internal function setup is incorrect - internal function \"" + fncnam + "\" not handled");
    }

    static final DefaultImpl INSTANCE = new DefaultImpl();

    static private final Operator OPR_EQU = new Operator('=', 99, 99, MathEval.RIGHT_SIDE, true, DefaultImpl.INSTANCE);
    static private final Operator OPR_PWR = new Operator('^', 80, 81, MathEval.NO_SIDE, false, DefaultImpl.INSTANCE);
    static private final Operator OPR_NEG = new Operator('±', 60, 60, MathEval.RIGHT_SIDE, true, DefaultImpl.INSTANCE);
    static private final Operator OPR_MLT1 = new Operator('*', 40, DefaultImpl.INSTANCE);
    static private final Operator OPR_MLT2 = new Operator('×', 40, DefaultImpl.INSTANCE);
    static private final Operator OPR_MLT3 = new Operator('·', 40, DefaultImpl.INSTANCE);
    static private final Operator OPR_BKT = new Operator('(', 40, DefaultImpl.INSTANCE);
    static private final Operator OPR_DIV1 = new Operator('/', 40, DefaultImpl.INSTANCE);
    static private final Operator OPR_DIV2 = new Operator('÷', 40, DefaultImpl.INSTANCE);
    static private final Operator OPR_MOD = new Operator('%', 40, DefaultImpl.INSTANCE);
    static private final Operator OPR_ADD = new Operator('+', 20, DefaultImpl.INSTANCE);
    static private final Operator OPR_SUB = new Operator('-', 20, DefaultImpl.INSTANCE);

    static void registerOperators(MathEval tgt) {
        tgt.setOperator(OPR_EQU);
        tgt.setOperator(OPR_PWR);
        tgt.setOperator(OPR_NEG);
        tgt.setOperator(OPR_MLT1);
        tgt.setOperator(OPR_MLT2);
        tgt.setOperator(OPR_MLT3);
        tgt.setOperator(OPR_BKT);
        tgt.setOperator(OPR_DIV1);
        tgt.setOperator(OPR_DIV2);
        tgt.setOperator(OPR_MOD);
        tgt.setOperator(OPR_ADD);
        tgt.setOperator(OPR_SUB);
    }

    static void registerFunctions(MathEval tgt) {
        tgt.setFunctionHandler("abs", INSTANCE);
        tgt.setFunctionHandler("cos", INSTANCE);
        tgt.setFunctionHandler("max", INSTANCE);
        tgt.setFunctionHandler("min", INSTANCE);
        tgt.setFunctionHandler("random", INSTANCE, true);
        tgt.setFunctionHandler("round", INSTANCE);
        tgt.setFunctionHandler("sin", INSTANCE);
        tgt.setFunctionHandler("sqrt", INSTANCE);
        tgt.setFunctionHandler("toDegrees", INSTANCE);
        tgt.setFunctionHandler("toRadians", INSTANCE);
    }
}
