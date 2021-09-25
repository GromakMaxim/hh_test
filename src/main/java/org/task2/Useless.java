package org.task2;

import java.util.ArrayList;
import java.util.List;

public class Useless {
    public static void main(String[] args) {


    }

    public static int doMath(String expression){
        List<Lexeme> lexemes = lexicalAnalyzer(expression);
        System.out.println(lexemes);
        LexemeBuffer lb = new LexemeBuffer(lexemes);
        return expr(lb);
    }

    public static int expr(LexemeBuffer lexemeBuffer) {
        Lexeme l = lexemeBuffer.getNext();
        if (l.type == LexemeTypes.EOL) {
            return 0;
        } else {
            lexemeBuffer.goToPrevious();
            return plusminus(lexemeBuffer);
        }
    }

    public static int plusminus(LexemeBuffer lexemeBuffer) {
        int value = multdiv(lexemeBuffer);
        while (true) {
            Lexeme l = lexemeBuffer.getNext();
            switch (l.type) {
                case PLUS:
                    value += multdiv(lexemeBuffer);
                    break;
                case MINUS:
                    value -= multdiv(lexemeBuffer);
                    break;
                default:
                    lexemeBuffer.goToPrevious();
                    return value;
            }
        }
    }

    public static int multdiv(LexemeBuffer lexemeBuffer) {
        int value = factor(lexemeBuffer);
        while (true) {
            Lexeme l = lexemeBuffer.getNext();
            switch (l.type) {
                case MULT:
                    value *= factor(lexemeBuffer);
                    break;
                case DIV:
                    value /= factor(lexemeBuffer);
                    break;
                default:
                    lexemeBuffer.goToPrevious();
                    return value;
            }

        }
    }

    public static int factor(LexemeBuffer lexemeBuffer) {
        Lexeme l = lexemeBuffer.getNext();
        switch (l.type) {
            case NUM:
                return Integer.parseInt(l.value);
            case LBRACKET:
                int value = expr(lexemeBuffer);
                l = lexemeBuffer.getNext();

                if (l.type != LexemeTypes.RBRACKET) {
                    throw new RuntimeException("Illegal expr at: " + lexemeBuffer.getCurrentPosition() + ". Did you miss the bracket?");
                }
                return value;
            default:
                throw new RuntimeException("Illegal expr at: " + lexemeBuffer.getCurrentPosition());
        }
    }

    public static class LexemeBuffer {
        private int position;
        public List<Lexeme> lexemes;

        public LexemeBuffer(List<Lexeme> lexemes) {
            this.lexemes = lexemes;
        }

        public Lexeme getNext() {
            return lexemes.get(position++);
        }

        public void goToPrevious() {
            position--;
        }

        public int getCurrentPosition() {
            return position;
        }
    }


    public static List<Lexeme> lexicalAnalyzer(String expression) {
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        int pos = 0;
        while (pos < expression.length()) {
            char ch = expression.charAt(pos);
            switch (ch) {
                case '(':
                    lexemes.add(new Lexeme(LexemeTypes.LBRACKET, ch));
                    pos++;
                    continue;
                case ')':
                    lexemes.add(new Lexeme(LexemeTypes.RBRACKET, ch));
                    pos++;
                    continue;
                case '+':
                    lexemes.add(new Lexeme(LexemeTypes.PLUS, ch));
                    pos++;
                    continue;
                case '-':
                    lexemes.add(new Lexeme(LexemeTypes.MINUS, ch));
                    pos++;
                    continue;
                case '*':
                    lexemes.add(new Lexeme(LexemeTypes.MULT, ch));
                    pos++;
                    continue;
                case '/':
                    lexemes.add(new Lexeme(LexemeTypes.DIV, ch));
                    pos++;
                    continue;
                default:
                    if (ch <= '9' && ch >= '0') {
                        StringBuilder sb = new StringBuilder();
                        do {
                            sb.append(ch);
                            pos++;
                            if (pos >= expression.length()) {
                                break;
                            }
                            ch = expression.charAt(pos);
                        } while (ch <= '9' && ch >= '0');

                        lexemes.add(new Lexeme(LexemeTypes.NUM, sb.toString()));
                        //sb.setLength(0);
                    } else {
                        if (ch != ' ') {
                            throw new RuntimeException("Invalid char: " + ch);
                        }
                        pos++;
                    }
            }
        }
        lexemes.add(new Lexeme(LexemeTypes.EOL, ""));
        return lexemes;
    }

    static class Lexeme {
        LexemeTypes type;
        String value;

        public Lexeme(LexemeTypes type, String value) {
            this.type = type;
            this.value = value;
        }

        public Lexeme(LexemeTypes type, Character ch) {
            this.type = type;
            this.value = ch.toString();
        }

        @Override
        public String toString() {
            return "Lexeme{" +
                    "type=" + type +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    enum LexemeTypes {
        LBRACKET, RBRACKET,
        PLUS, MINUS, MULT, DIV,
        NUM,
        EOL
    }
}





