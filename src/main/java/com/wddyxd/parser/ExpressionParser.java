package com.wddyxd.parser;

import com.wddyxd.error.InvalidExpressionException;
import com.wddyxd.model.BinaryExpr;
import com.wddyxd.model.Expr;
import com.wddyxd.model.Fraction;
import com.wddyxd.model.NumberExpr;

/**
 * 把题目字符串解析成表达式树。
 */
public class ExpressionParser {

    /** 解析 "1/6 + 1/8 = " 返回 Expr。 */
    public static Expr parse(String text) {
        if (text == null || text.trim().isEmpty()) {
            throw new InvalidExpressionException("表达式不能为空");
        }

        try {
            // 使用递归下降解析，分别处理加减层、乘除层和操作数层。
            Parser parser = new Parser(text);
            Expr expression = parser.parseExpression();
            parser.skipWhitespace();
            if (parser.hasNext() && parser.current() == '=') {
                parser.index++;
                parser.skipWhitespace();
            }
            if (parser.hasNext()) {
                throw parser.error("等号只能出现在表达式末尾");
            }
            return expression;
        } catch (InvalidExpressionException ex) {
            throw ex;
        } catch (RuntimeException ex) {
            throw new InvalidExpressionException("表达式格式错误: " + text);
        }
    }

    private static final class Parser {
        private final String input;
        private int index;

        private Parser(String input) {
            this.input = input;
        }

        private Expr parseExpression() {
            // 加减优先级较低，因此每个操作数先解析完整的乘除表达式。
            Expr result = parseTerm();
            while (true) {
                skipWhitespace();
                if (!hasNext() || current() == ')' || current() == '=') return result;
                String operator = readAdditiveOperator();
                if (operator == null) throw error("缺少有效的运算符");
                result = new BinaryExpr(operator, result, parseTerm());
            }
        }

        private Expr parseTerm() {
            // 连续解析乘除运算，保证乘除优先于加减。
            Expr result = parsePrimary();
            while (true) {
                skipWhitespace();
                if (!hasNext() || current() == ')' || current() == '=') return result;
                String operator = readMultiplicativeOperator();
                if (operator == null) return result;
                result = new BinaryExpr(operator, result, parsePrimary());
            }
        }

        private Expr parsePrimary() {
            skipWhitespace();
            if (!hasNext()) throw error("缺少操作数");
            if (current() == '(') {
                // 括号内重新从最低优先级开始解析，以覆盖默认运算优先级。
                index++;
                Expr result = parseExpression();
                skipWhitespace();
                if (!hasNext() || current() != ')') throw error("括号不匹配");
                index++;
                return result;
            }
            if (!Character.isDigit(current())) throw error("操作数格式错误");
            return new NumberExpr(parseFraction());
        }

        private Fraction parseFraction() {
            // 先读取完整数字，再识别普通分数或带分数的分隔符。
            int start = index;
            while (hasNext() && Character.isDigit(current())) index++;

            if (hasNext() && (current() == '\'' || current() == '\u2019')) {
                index++;
                int numeratorStart = index;
                while (hasNext() && Character.isDigit(current())) index++;
                if (numeratorStart == index || !hasNext() || current() != '/') {
                    throw error("带分数格式错误");
                }
                index++;
                int denominatorStart = index;
                while (hasNext() && Character.isDigit(current())) index++;
                if (denominatorStart == index) throw error("带分数格式错误");
            } else if (hasNext() && current() == '/') {
                int slash = index++;
                int denominatorStart = index;
                while (hasNext() && Character.isDigit(current())) index++;
                if (denominatorStart == index) index = slash;
            }

            String value = input.substring(start, index);
            try {
                return Fraction.fromString(value.replace('\u2019', '\''));
            } catch (IllegalArgumentException ex) {
                throw error("分数格式错误");
            }
        }

        private String readAdditiveOperator() {
            if (!hasNext()) return null;
            char character = current();
            if (character == '+') {
                index++;
                return "+";
            }
            if (character == '-' || character == '\u2212') {
                index++;
                return "-";
            }
            return null;
        }

        private String readMultiplicativeOperator() {
            if (!hasNext()) return null;
            char character = current();
            if (character == '*' || character == '\u00d7') {
                index++;
                return "*";
            }
            if (character == '/' || character == '\u00f7') {
                index++;
                return "/";
            }
            return null;
        }

        private void skipWhitespace() {
            // 题目文本允许运算符、括号和等号两侧存在空白。
            while (hasNext() && Character.isWhitespace(current())) index++;
        }

        private boolean hasNext() {
            return index < input.length();
        }

        private char current() {
            return input.charAt(index);
        }

        private InvalidExpressionException error(String message) {
            return new InvalidExpressionException(message + "，位置: " + index);
        }
    }
}
