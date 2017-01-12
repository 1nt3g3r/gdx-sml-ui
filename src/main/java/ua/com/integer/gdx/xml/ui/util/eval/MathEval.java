package ua.com.integer.gdx.xml.ui.util.eval;

import com.badlogic.gdx.math.MathUtils;

import java.util.SortedMap;
import java.util.TreeMap;

// Created by Lawrence PC Dol.  Released into the public domain.
// http://tech.dolhub.com
//
// Contributions by Carlos Gómez of Asturias, Spain, in the area of unary operators
// and right-to-left evaluations proved invaluable to implementing these features.
// Thanks Carlos!
//
// Source is licensed for any use, provided this copyright notice is retained.
// No warranty for any purpose whatsoever is implied or expressed.  The author
// is not liable for any losses of any kind, direct or indirect, which result
// from the use of this software.

public class MathEval extends Object {
    static public final int LEFT_SIDE = 'L';
    static public final int RIGHT_SIDE = 'R';
    static public final int NO_SIDE = 'B';
    static private final Operator OPERAND = new Operator('\0', 0, 0, NO_SIDE, false, null);

    private Operator[] operators;
    private final SortedMap<String, Float> constants;
    private final SortedMap<String, Float> variables;
    private final SortedMap<String, FunctionHandler> pureFunctions;
    private final SortedMap<String, FunctionHandler> impureFunctions;
    private boolean relaxed;
    private String expression;
    private int offset;

    public MathEval() {
        super();

        operators = new Operator[256];
        DefaultImpl.registerOperators(this);

        constants = new TreeMap<String, Float>(String.CASE_INSENSITIVE_ORDER);
        variables = new TreeMap<String, Float>(String.CASE_INSENSITIVE_ORDER);

        constants.put("PI", MathUtils.PI);

        pureFunctions = new TreeMap<String, FunctionHandler>(String.CASE_INSENSITIVE_ORDER);
        impureFunctions = new TreeMap<String, FunctionHandler>(String.CASE_INSENSITIVE_ORDER);
        DefaultImpl.registerFunctions(this);

        relaxed = false;

        offset = 0;
    }

    public MathEval setOperator(Operator opr) {
        if (opr.symbol >= operators.length) {
            Operator[] noa = new Operator[opr.symbol + (opr.symbol % 255) + 1];
            System.arraycopy(operators, 0, noa, 0, operators.length);
            operators = noa;
        }
        operators[opr.symbol] = opr;
        return this;
    }

    public MathEval setFunctionHandler(String functionName, FunctionHandler functionHandler) {
        return setFunctionHandler(functionName, functionHandler, false);
    }

    public MathEval setFunctionHandler(String name, FunctionHandler functionHandler, boolean impure) {
        validateName(name);
        if (functionHandler == null) {
            pureFunctions.remove(name);
            impureFunctions.remove(name);
        } else if (impure) {
            pureFunctions.remove(name);
            impureFunctions.put(name, functionHandler);
        } else {
            pureFunctions.put(name, functionHandler);
            impureFunctions.remove(name);
        }
        return this;
    }

    public MathEval setVariable(String nam, float val) {
        validateName(nam);
        variables.put(nam, val);
        return this;
    }

    private void validateName(String nam) {
        if (!Character.isLetter(nam.charAt(0))) {
            throw new IllegalArgumentException("Names for constants, variables and functions must start with a letter");
        }
        if (nam.indexOf('(') != -1 || nam.indexOf(')') != -1) {
            throw new IllegalArgumentException(
                    "Names for constants, variables and functions may not contain a parenthesis");
        }
    }

    public float evaluate(String exp) throws NumberFormatException, ArithmeticException {
        expression = exp;
        offset = 0;
        return _evaluate(0, (exp.length() - 1));
    }

    private float _evaluate(int begin, int end) throws NumberFormatException, ArithmeticException {
        return _evaluate(begin, end, 0.0f, OPERAND, getOperator('='));
    }

    private float _evaluate(int begin, int end, float left, Operator pendingOperator, Operator currentOperator)
            throws NumberFormatException, ArithmeticException {
        Operator nextOperator = OPERAND;
        int expressionOffset;

        for (expressionOffset = begin; (expressionOffset = skipWhitespace(expression, expressionOffset, end)) <= end; expressionOffset++) {
            float rgt;

            for (begin = expressionOffset; expressionOffset <= end; expressionOffset++) {
                char chr = expression.charAt(expressionOffset);
                if ((nextOperator = getOperator(chr)) != OPERAND) {
                    if (nextOperator.internal) {
                        nextOperator = OPERAND;
                    } else {
                        break;
                    }
                } else if (chr == ')' || chr == ',') {
                    break;
                }
            }

            {
                char ch0 = expression.charAt(begin);
                boolean alp = Character.isLetter(ch0);

                if (currentOperator.unary != LEFT_SIDE) {
                    if (ch0 == '+') {
                        continue;
                    } else if (ch0 == '-') {
                        nextOperator = getOperator('±');
                    }
                }

                if (begin == expressionOffset && (currentOperator.unary == LEFT_SIDE || nextOperator.unary == RIGHT_SIDE)) {
                    rgt = Float.NaN;
                } else if (ch0 == '(') {
                    rgt = _evaluate(begin + 1, end);
                    expressionOffset = skipWhitespace(expression, offset + 1, end);
                    nextOperator = (expressionOffset <= end ? getOperator(expression.charAt(expressionOffset)) : OPERAND);
                } else if (alp && nextOperator.symbol == '(') {
                    rgt = doFunction(begin, end);
                    expressionOffset = skipWhitespace(expression, offset + 1, end);
                    nextOperator = (expressionOffset <= end ? getOperator(expression.charAt(expressionOffset)) : OPERAND);
                } else if (alp) {
                    rgt = doNamedVal(begin, (expressionOffset - 1));
                } else {
                    try {
                        if (stringOfsEq(expression, begin, "0x")) {
                            rgt = (float) Long.parseLong(expression.substring(begin + 2, expressionOffset).trim(), 16);
                        } else {
                            rgt = Float.parseFloat(expression.substring(begin, expressionOffset).trim());
                        }
                    } catch (NumberFormatException thr) {
                        throw exception(begin, "Invalid numeric value \"" + expression.substring(begin, expressionOffset).trim() + "\"");
                    }
                }
            }

            if (opPrecedence(currentOperator, LEFT_SIDE) < opPrecedence(nextOperator, RIGHT_SIDE)) {
                rgt = _evaluate((expressionOffset + 1), end, rgt, currentOperator, nextOperator);
                expressionOffset = offset;
                nextOperator = (expressionOffset <= end ? getOperator(expression.charAt(expressionOffset)) : OPERAND);
            }

            left = doOperation(begin, left, currentOperator, rgt);

            currentOperator = nextOperator;
            if (opPrecedence(pendingOperator, LEFT_SIDE) >= opPrecedence(currentOperator, RIGHT_SIDE)) {
                break;
            }
            if (currentOperator.symbol == '(') {
                expressionOffset--;
            }
        }
        if (expressionOffset > end && currentOperator != OPERAND) {
            if (currentOperator.unary == LEFT_SIDE) {
                left = doOperation(begin, left, currentOperator, Float.NaN);
            } else {
                throw exception(expressionOffset, "Expression ends with a blank operand after operator '" + nextOperator.symbol + "'");
            }
        }
        offset = expressionOffset;
        return left;
    }

    private Operator getOperator(char chr) {
        if (chr < operators.length) {
            Operator opr = operators[chr];
            if (opr != null) {
                return opr;
            }
        }
        return OPERAND;
    }

    private int opPrecedence(Operator opr, int sid) {
        if (opr == null) {
            return Integer.MIN_VALUE;
        } else if (opr.unary == NO_SIDE || opr.unary != sid) {
            return (sid == LEFT_SIDE ? opr.precedenceL : opr.precedenceR);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    private float doOperation(int beg, float lft, Operator opr, float rgt) {
        if (opr.unary != RIGHT_SIDE && Float.isNaN(lft)) {
            throw exception(beg, "Mathematical NaN detected in right-operand");
        }
        if (opr.unary != LEFT_SIDE && Float.isNaN(rgt)) {
            throw exception(beg, "Mathematical NaN detected in left-operand");
        }

        try {
            return opr.handler.evaluateOperator(lft, opr.symbol, rgt);
        } catch (ArithmeticException thr) {
            throw exception(beg, "Mathematical expression \"" + expression + "\" failed to evaluate", thr);
        } catch (UnsupportedOperationException thr) {
            int tmp = beg;
            while (tmp > 0 && getOperator(expression.charAt(tmp)) == null) {
                tmp--;
            }
            throw exception(tmp, "Operator \"" + opr.symbol
                    + "\" not handled by math engine (Programmer error: The list of operators is inconsistent within the engine)");
        }
    }

    private float doFunction(int beg, int end) {
        int argbeg;

        for (argbeg = beg; argbeg <= end && expression.charAt(argbeg) != '('; argbeg++) {
            ;
        }

        String fncnam = expression.substring(beg, argbeg).trim();
        ArgParser fncargs = new ArgParser(argbeg, end);
        FunctionHandler fnchdl = null;

        try {
            if ((fnchdl = pureFunctions.get(fncnam)) != null) {
                return fnchdl.evaluateFunction(fncnam, fncargs);
            } else if ((fnchdl = impureFunctions.get(fncnam)) != null) {
                return fnchdl.evaluateFunction(fncnam, fncargs);
            }
            fncargs = null;
        } catch (ArithmeticException thr) {
            fncargs = null;
            throw thr;
        } catch (NoSuchMethodError thr) {
            fncargs = null;
            throw exception(beg, "Function not supported in this JVM: \"" + fncnam + "\"");
        } catch (UnsupportedOperationException thr) {
            fncargs = null;
            throw exception(beg, thr.getMessage());
        } catch (Throwable thr) {
            fncargs = null;
            throw exception(beg, "Unexpected exception parsing function arguments", thr);
        } finally {
            if (fncargs != null) {
                if (fncargs.hasNext()) {
                    throw exception(fncargs.getIndex(), "Function has too many arguments");
                }
                offset = fncargs.getIndex();
            }
        }
        throw exception(beg, "Function \"" + fncnam + "\" not recognized");
    }

    private float doNamedVal(int beg, int end) {
        while (beg < end && Character.isWhitespace(expression.charAt(end))) {
            end--;
        }

        String nam = expression.substring(beg, (end + 1));
        Float val;

        if ((val = constants.get(nam)) != null) {
            return val.floatValue();
        } else if ((val = variables.get(nam)) != null) {
            return val.floatValue();
        } else if (relaxed) {
            return 0.0f;
        }

        throw exception(beg, "Unrecognized constant or variable \"" + nam + "\"");
    }

    private ArithmeticException exception(int ofs, String txt) {
        return new ArithmeticException(txt + " at offset " + ofs + " in expression \"" + expression + "\"");
    }

    private ArithmeticException exception(int ofs, String txt, Throwable thr) {
        return new ArithmeticException(txt + " at offset " + ofs + " in expression \"" + expression + "\"" + " (Cause: "
                + (thr.getMessage() != null ? thr.getMessage() : thr.toString()) + ")");
    }

    private boolean stringOfsEq(String str, int ofs, String val) {
        return str.regionMatches(true, ofs, val, 0, val.length());
    }

    private int skipWhitespace(String exp, int ofs, int end) {
        while (ofs <= end && Character.isWhitespace(exp.charAt(ofs))) {
            ofs++;
        }
        return ofs;
    }

    public final class ArgParser {
        final int exEnd;
        int index;

        ArgParser(int excstr, int excend) {
            exEnd = excend;
            index = (excstr + 1);
            index = skipWhitespace(expression, index, exEnd - 1);
        }


        public float next() {
            if (!hasNext()) {
                throw exception(index, "Function has too few arguments");
            }
            return _next();
        }

        private float _next() {
            if (expression.charAt(index) == ',') {
                index++;
            }
            float ret = _evaluate(index, exEnd);
            index = offset;
            return ret;
        }

        public boolean hasNext() {
            return (expression.charAt(index) != ')');
        }

        int getIndex() {
            return index;
        }
    }
}