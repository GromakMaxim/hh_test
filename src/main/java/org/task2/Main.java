package org.task2;

import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static int totalOperations = 0;
    private static TreeMap<Integer, Integer> stat = new TreeMap<>();
    public static ArrayList<String> stringPool = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        parse(line);

    }

    public static void parse(String input) {
        if (input.contains("d")) {
            generateStrings(input);
            statistics();
        } else {
            int result = doMath(input);
            System.out.println(result + " " + "100.00");
        }
    }

    public static void generateStrings(String input) {
        Pattern pattern = Pattern.compile("[d]\\d+");//try to find things like: d*
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            int value = Integer.parseInt(matcher.group().replace("d", ""));
            for (int i = 1; i <= value; i++) {
                String target = matcher.group();
                String replacement = String.valueOf(i);
                String newString = input.replaceFirst(target, replacement);
                stringPool.add(newString);
            }
            break;
        }

        for (int i = 0; i < stringPool.size(); i++) {
            String current = stringPool.get(i);
            if (current.contains("d")) {
                stringPool.remove(i);
                generateStrings(current);
            }
        }
    }

    public static void statistics() {
        for (String str : stringPool) {
            int result = doMath(str);
            totalOperations++;

            boolean isExist = stat.containsKey(result);
            if (!isExist) {
                stat.put(result, 1);
            } else {
                stat.put(result, stat.get(result) + 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : stat.entrySet()) {
            double result = ((double) entry.getValue() / (double) totalOperations) * 100;
            String formatted = new DecimalFormat("#0.00").format(result).replace(',', '.');
            System.out.println(entry.getKey() + " " + formatted);
        }
    }

    public static int doMath(String expression) {
        List<Lexeme> lexemes = lexicalAnalyzer(expression);
        LexemeBuffer lb = new LexemeBuffer(lexemes);
        return expr(lb);
    }

    public static int expr(LexemeBuffer lexemeBuffer) {
        Lexeme l = lexemeBuffer.getNext();
        if (l.type == LexemeTypes.EOL) {
            return 0;
        } else {
            lexemeBuffer.goToPrevious();
            return moreless(lexemeBuffer);
        }
    }

    public static int moreless(LexemeBuffer lexemeBuffer) {
        int value = plusminus(lexemeBuffer);
        while (true) {
            Lexeme l = lexemeBuffer.getNext();
            boolean b;
            int v;
            switch (l.type) {
                case LESS:
                    v = plusminus(lexemeBuffer);
                    b = value < v;
                    if (b) {
                        value = 1;
                    } else {
                        value = 0;
                    }
                    break;
                case MORE:
                    v = plusminus(lexemeBuffer);
                    b = value > v;
                    if (b) {
                        value = 1;
                    } else {
                        value = 0;
                    }
                    break;
                default:
                    lexemeBuffer.goToPrevious();
                    return value;
            }
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
            case MINUS: //warn! '-' might be first in parsed line
                l = lexemeBuffer.getNext();
                if (l.type != LexemeTypes.LBRACKET) {
                    return -1 * Integer.parseInt(l.value);
                } else {
                    int brackets = 0;
                    int curPos = lexemeBuffer.getCurrentPosition() - 1;
                    do {
                        switch (l.type) {
                            case LBRACKET:
                                brackets++;
                                lexemeBuffer.lexemes.remove(curPos);
                                break;
                            case RBRACKET:
                                brackets--;
                                lexemeBuffer.lexemes.remove(curPos);
                                curPos--;
                                lexemeBuffer.goToPrevious();
                                break;
                            case PLUS:
                            case MINUS:
                                if (brackets % 2 != 0) {
                                    lexemeBuffer.swap(curPos);
                                }
                                break;
                        }
                        l = lexemeBuffer.getNext();
                        curPos++;
                    } while (brackets != 0);
                    lexemeBuffer.position = 0;
                    return factor(lexemeBuffer);
                }
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

        public void replace(int pos, Lexeme lex) {
            lexemes.set(pos, lex);
        }

        public void swap(int pos) {
            Lexeme l = lexemes.get(pos);

            switch (l.type) {
                case PLUS:
                    replace(pos, new Lexeme(LexemeTypes.MINUS, '-'));
                    break;
                case MINUS:
                    replace(pos, new Lexeme(LexemeTypes.PLUS, '+'));
                    break;
            }
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
                case '>':
                    lexemes.add(new Lexeme(LexemeTypes.MORE, ch));
                    pos++;
                    continue;
                case '<':
                    lexemes.add(new Lexeme(LexemeTypes.LESS, ch));
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
                        sb.setLength(0);
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
        LESS, MORE,
        NUM,
        EOL
    }
}