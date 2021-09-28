package org.task2;

import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static TreeMap<Long, Long> stat;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        parse(line);
    }

    public static void parse(String input) {
        stat = new TreeMap<>();
        if (input.contains("d")) {
            generateStrings(input);
            statistics();
        } else {
            long result = process(input);
            System.out.println(result + " " + "100.00");
        }
    }

    public static long process(String input) {
        LinkedList<Lexeme> lexemes = lexicalAnalyzer(input);
        LexemeBuffer lb = new LexemeBuffer(lexemes);
        LinkedList<Lexeme> tempList = new LinkedList<>(lb.lexemes);

        return findExpressionInBrackets(tempList);
    }

    public static long findExpressionInBrackets(LinkedList<Lexeme> list) {
        Optional<Lexeme> optional = list.stream()
                .filter(l -> l.type == LexemeTypes.LBRACKET).findFirst();

        if (optional.isPresent()) {
            int lbracketPos = 0;
            int rbracketPos = 0;

            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).type == LexemeTypes.LBRACKET) {
                    lbracketPos = i;
                }
                if (list.get(i).type == LexemeTypes.RBRACKET) {
                    rbracketPos = i;
                    break;
                }
            }

            LinkedList<Lexeme> templist = new LinkedList<>(list.subList(lbracketPos + 1, rbracketPos));

            long value = calculate(templist);

            int index = rbracketPos;
            while (index >= lbracketPos) {
                list.remove(index);
                index--;
            }
            list.add(lbracketPos, new Lexeme(LexemeTypes.NUM, String.valueOf(value)));

            if (isBracketsStillExist(list)) {
                return findExpressionInBrackets(list);
            }
        }
        return calculate(list);
    }

    public static long calculate(LinkedList<Lexeme> list) {
        long value = 0;
        boolean multdivIsPresent = list.stream()
                .anyMatch(l -> l.type == LexemeTypes.MULT || l.type == LexemeTypes.DIV);

        while (multdivIsPresent) {
            for (int i = 0; i < list.size(); i++) {
                Lexeme l = list.get(i);
                switch (l.type) {
                    case MULT:
                        value = calcMULT(i, list);
                        list.set(i, new Lexeme(LexemeTypes.NUM, String.valueOf(value)));
                        list.remove(i + 1);
                        list.remove(i - 1);
                        i = 0;
                        break;
                    case DIV:
                        value = calcDIV(i, list);
                        list.set(i, new Lexeme(LexemeTypes.NUM, String.valueOf(value)));
                        list.remove(i + 1);
                        list.remove(i - 1);
                        i = 0;
                        break;
                }
            }
            multdivIsPresent = list.stream()
                    .anyMatch(l -> l.type == LexemeTypes.MULT || l.type == LexemeTypes.DIV);
        }

        boolean plusminusIsPresent = list.stream()
                .anyMatch(l -> l.type == LexemeTypes.PLUS || l.type == LexemeTypes.MINUS);

        while (plusminusIsPresent) {

            for (int i = 0; i < list.size() - 1; i++) {
                Lexeme l = list.get(i);
                switch (l.type) {
                    case PLUS:
                        value = calcPLUS(i, list);
                        list.set(i, new Lexeme(LexemeTypes.NUM, String.valueOf(value)));
                        list.remove(i + 1);
                        list.remove(i - 1);
                        i = 0;
                        break;
                    case MINUS:
                        value = calcMINUS(i, list);
                        if (list.get(0).type != LexemeTypes.NUM) {
                            list.set(i, new Lexeme(LexemeTypes.NUM, String.valueOf(value)));
                            list.remove(i + 1);
                        } else {
                            list.set(i, new Lexeme(LexemeTypes.NUM, String.valueOf(value)));
                            if (list.get(i - 1).type == LexemeTypes.MORE || list.get(i - 1).type == LexemeTypes.LESS) {
                                list.remove(3);
                            } else {
                                list.remove(i + 1);
                                list.remove(i - 1);
                            }

                        }
                        i = 0;
                        break;
                }
            }
            plusminusIsPresent = list.stream()
                    .anyMatch(l -> l.type == LexemeTypes.PLUS || l.type == LexemeTypes.MINUS);
        }

        boolean morelessIsPresent = list.stream()
                .anyMatch(l -> l.type == LexemeTypes.MORE || l.type == LexemeTypes.LESS);

        while (morelessIsPresent) {
            for (int i = 0; i < list.size(); i++) {
                Lexeme l = list.get(i);
                switch (l.type) {
                    case MORE:
                        value = calcMORE(i, list);
                        list.set(i, new Lexeme(LexemeTypes.NUM, String.valueOf(value)));
                        list.remove(i + 1);
                        list.remove(i - 1);
                        i = 0;
                        break;
                    case LESS:
                        value = calcLESS(i, list);
                        list.set(i, new Lexeme(LexemeTypes.NUM, String.valueOf(value)));
                        list.remove(i + 1);
                        list.remove(i - 1);
                        i = 0;
                        break;
                }
            }
            morelessIsPresent = list.stream()
                    .anyMatch(l -> l.type == LexemeTypes.MORE || l.type == LexemeTypes.LESS);
        }

        if (value == 0) return Long.parseLong(list.get(0).value);
        return value;
    }

    public static boolean isBracketsStillExist(List<Lexeme> list) {
        return list.stream().anyMatch(l -> l.type == LexemeTypes.LBRACKET);
    }


    public static long calcMORE(int pos, List<Lexeme> list) {
        long v1 = Long.parseLong(list.get(pos - 1).value);
        long v2 = Long.parseLong(list.get(pos + 1).value);

        return (v1 > v2) ? 1L : 0L;
    }

    public static long calcLESS(int pos, List<Lexeme> list) {
        long v1 = Long.parseLong(list.get(pos - 1).value);
        long v2 = Long.parseLong(list.get(pos + 1).value);

        return (v1 < v2) ? 1L : 0L;
    }


    //warn! need to test this method & simplify
    public static long calcPLUS(int pos, List<Lexeme> list) {
        Lexeme prev = null;
        Lexeme next = null;

        try {
            prev = list.get(pos - 1);
        } catch (Exception e) {

        }

        try {
            next = list.get(pos + 1);
        } catch (Exception e) {

        }

        if (prev == null && next == null) return 0;
        if (prev == null) return Long.parseLong(next.value);
        if (next == null) return Long.parseLong(next.value);
        return Long.parseLong(prev.value) + Long.parseLong(next.value);
    }

    public static long calcMINUS(int pos, List<Lexeme> list) {
        int prevPos = pos - 1;
        int nextPos = pos + 1;

        Lexeme prev = null;
        Lexeme next = null;

        if (prevPos >= 0 && nextPos < list.size()) {
            prev = list.get(prevPos);
            next = list.get(nextPos);

            if (prev.type == LexemeTypes.MORE || prev.type == LexemeTypes.LESS) {
                prev = list.get(pos);
                switch (prev.type) {
                    case MINUS:
                        return -1 * Long.parseLong(next.value);
                    case PLUS:
                        return Long.parseLong(next.value);
                }

            }
            return Long.parseLong(prev.value) - Long.parseLong(next.value);
        } else {
            if (list.size() == 3) {
                if (list.get(0).type == LexemeTypes.MINUS) {
                    return -1 * Long.parseLong(list.get(1).value);
                }
            }
            if (prevPos < 0) {
                return -Long.parseLong(list.get(nextPos).value);
            }
            if (nextPos > list.size()) {
                return Long.parseLong(list.get(prevPos).value);
            }

        }

        return Integer.parseInt(prev.value) - Integer.parseInt(next.value);
    }

    public static long calcMULT(int pos, List<Lexeme> list) {
        long v1 = Long.parseLong(list.get(pos - 1).value);
        long v2 = Long.parseLong(list.get(pos + 1).value);
        return v1 * v2;
    }

    public static long calcDIV(int pos, List<Lexeme> list) {
        long v1 = Long.parseLong(list.get(pos - 1).value);
        long v2 = Long.parseLong(list.get(pos + 1).value);
        return v1 / v2;
    }

    public static void generateStrings(String input) {
        Pattern pattern = Pattern.compile("[d]\\d+");//try to find things like: d*
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            long value = Long.parseLong(matcher.group().replace("d", ""));
            for (int i = 1; i <= value; i++) {
                String target = matcher.group();
                String replacement = String.valueOf(i);
                String newString = input.replaceFirst(target, replacement);
                if (!newString.contains("d")) {
                    long resultLine = process(newString);
                    statistics(resultLine);
                } else {
                    generateStrings(newString);
                }
            }
            break;
        }
    }

    public static void statistics(long value) {
        stat.computeIfPresent(value, (k, v) -> ++v);
        stat.computeIfAbsent(value, (k) -> stat.put(k, 1L));
    }

    public static void statistics() {
        double operations = stat.values().stream().mapToDouble(i -> i).sum();

        for (Map.Entry<Long, Long> entry : stat.entrySet()) {
            double value = entry.getValue();
            double div = value / operations;
            double result = div * 100.0;

            result = (double) Math.round(result * 100.0) / 100.0;

            String print;
            if (result >= 1) {
                DecimalFormat f = new DecimalFormat("##.00");
                print = f.format(result).replace(",", ".");
            } else {
                if (result == 0.0) {
                    print = result + "0";
                } else {
                    print = String.valueOf(result);
                }
            }

            System.out.println(entry.getKey() + " " + print);
        }
    }

    public static class LexemeBuffer {
        private int position;
        public LinkedList<Lexeme> lexemes;

        public LexemeBuffer(LinkedList<Lexeme> lexemes) {
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


    public static LinkedList<Lexeme> lexicalAnalyzer(String expression) {
        LinkedList<Lexeme> lexemes = new LinkedList<>();
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

    public static class Lexeme {
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