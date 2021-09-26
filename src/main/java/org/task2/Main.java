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
            int result = process(input);
            System.out.println(result + " " + "100.00");
        }
    }

    public static int process(String input) {
        List<Main.Lexeme> lexemes = lexicalAnalyzer(input);
        LexemeBuffer lb = new LexemeBuffer(lexemes);
        List<Lexeme> tempList = new ArrayList<>(lb.lexemes);

        return findExpressionInBrackets(tempList);
    }

    public static int findExpressionInBrackets(List<Main.Lexeme> list) {
        Optional<Lexeme> optional = list.stream()
                .filter(l -> l.type == Main.LexemeTypes.LBRACKET).findFirst();

        if (optional.isPresent()) {
            List<Main.Lexeme> resultList = new ArrayList<>();

            int lbracketPos = 0;
            int rbracketPos = 0;
            //ищем самую последнюю левую скобку и самую первую правую
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).type == Main.LexemeTypes.LBRACKET) {
                    lbracketPos = i;
                }
                if (list.get(i).type == Main.LexemeTypes.RBRACKET) {
                    rbracketPos = i;
                    break;
                }
            }

            int index = lbracketPos + 1;
            while (index < rbracketPos) {
                resultList.add(list.get(index));
                index++;
            }


            int value = doMagic(resultList);
            index = rbracketPos;
            while (index >= lbracketPos) {
                list.remove(index);
                index--;
            }
            list.add(lbracketPos, new Main.Lexeme(LexemeTypes.NUM, String.valueOf(value)));

            if (isBracketsStillExist(list)) {
                return findExpressionInBrackets(list);
            }
        }
        return doMagic(list);
    }

    public static int doMagic(List<Main.Lexeme> list) {
        int value = 0;
        Optional multdivCount = list.stream()
                .filter(l -> l.type == Main.LexemeTypes.MULT || l.type == Main.LexemeTypes.DIV)
                .findFirst();

        if (multdivCount.isPresent()) {
            for (int i = 0; i < list.size(); i++) {
                Main.Lexeme l = list.get(i);
                switch (l.type) {
                    case MULT:
                        value = calcMULT(i, list);
                        list.set(i, new Main.Lexeme(Main.LexemeTypes.NUM, String.valueOf(value)));
                        list.remove(i + 1);
                        list.remove(i - 1);
                        i = 0;
                        break;
                    case DIV:
                        value = calcDIV(i, list);
                        list.set(i, new Main.Lexeme(Main.LexemeTypes.NUM, String.valueOf(value)));
                        list.remove(i + 1);
                        list.remove(i - 1);
                        i = 0;
                        break;
                }
            }
        }

        Optional plusminus = list.stream()
                .filter(l -> l.type == Main.LexemeTypes.PLUS || l.type == Main.LexemeTypes.MINUS)
                .findFirst();

        if (plusminus.isPresent()) {
            for (int i = 0; i < list.size() - 1; i++) {
                Main.Lexeme l = list.get(i);
                switch (l.type) {
                    case PLUS:
                        value = calcPLUS(i, list);
                        list.set(i, new Main.Lexeme(Main.LexemeTypes.NUM, String.valueOf(value)));
                        list.remove(i + 1);
                        list.remove(i - 1);
                        i = 0;
                        break;
                    case MINUS:
                        value = calcMINUS(i, list);
                        if (list.get(0).type != Main.LexemeTypes.NUM) {
                            list.set(i, new Main.Lexeme(Main.LexemeTypes.NUM, String.valueOf(value)));
                            list.remove(i + 1);
                        } else {
                            list.set(i, new Main.Lexeme(Main.LexemeTypes.NUM, String.valueOf(value)));
                            list.remove(i + 1);
                            list.remove(i - 1);
                        }
                        i = 0;
                        break;
                }
            }
        }

        Optional moreless = list.stream()
                .filter(l -> l.type == Main.LexemeTypes.MORE || l.type == Main.LexemeTypes.LESS)
                .findFirst();

        if (moreless.isPresent()) {
            for (int i = 0; i < list.size(); i++) {
                Main.Lexeme l = list.get(i);
                switch (l.type) {
                    case MORE:
                        value = calcMORE(i, list);
                        list.set(i, new Main.Lexeme(Main.LexemeTypes.NUM, String.valueOf(value)));
                        list.remove(i + 1);
                        list.remove(i - 1);
                        i = 0;
                        break;
                    case LESS:
                        value = calcLESS(i, list);
                        list.set(i, new Main.Lexeme(Main.LexemeTypes.NUM, String.valueOf(value)));
                        list.remove(i + 1);
                        list.remove(i - 1);
                        i = 0;
                        break;
                }
            }
        }

        return value;
    }

    public static boolean isBracketsStillExist(List<Main.Lexeme> list) {
        long bracketsNumber = list.stream().filter(l -> l.type == Main.LexemeTypes.LBRACKET).count();
        return bracketsNumber > 0;
    }


    public static int calcMORE(int pos, List<Main.Lexeme> list) {
        int v1 = Integer.parseInt(list.get(pos-1).value);
        int v2 = Integer.parseInt(list.get(pos+1).value);

        if (v1 > v2) {
            return 1;
        }
        return 0;
    }

    public static int calcLESS(int pos, List<Main.Lexeme> list) {
        int v1 = Integer.parseInt(list.get(pos-1).value);
        int v2 = Integer.parseInt(list.get(pos+1).value);

        if (v1 < v2) {
            return 1;
        }
        return 0;
    }


    public static int calcPLUS(int pos, List<Main.Lexeme> list) {
        Main.Lexeme prev = null;
        Main.Lexeme next = null;

        try {
            prev = list.get(pos - 1);
        } catch (Exception e) {

        }

        try {
            next = list.get(pos + 1);
        } catch (Exception e) {

        }

        if (prev == null && next == null) {
            return 0;
        }
        if (prev == null) {
            return Integer.parseInt(next.value);
        }
        if (next == null) {
            return Integer.parseInt(next.value);
        }
        return Integer.parseInt(prev.value) + Integer.parseInt(next.value);
    }

    public static int calcMINUS(int pos, List<Main.Lexeme> list) {
        int prevPos = pos - 1;
        int nextPos = pos + 1;

        Main.Lexeme prev = null;
        Main.Lexeme next = null;

        if (prevPos >= 0 && nextPos < list.size()) {
            prev = list.get(prevPos);
            next = list.get(nextPos);

            return Integer.parseInt(prev.value) - Integer.parseInt(next.value);
        } else {
            if (prevPos < 0) {
                prev = new Main.Lexeme(Main.LexemeTypes.NUM, String.valueOf(0));
                next = list.get(nextPos);
            }
            if (nextPos > list.size()) {
                prev = new Main.Lexeme(Main.LexemeTypes.NUM, String.valueOf(prevPos));
                next = new Main.Lexeme(Main.LexemeTypes.NUM, String.valueOf(0));
            }

        }

        return Integer.parseInt(prev.value) - Integer.parseInt(next.value);
    }

    public static int calcMULT(int pos, List<Main.Lexeme> list) {
        Main.Lexeme l1 = new Main.Lexeme(Main.LexemeTypes.NUM, list.get(pos - 1).value);
        Main.Lexeme l2 = new Main.Lexeme(Main.LexemeTypes.NUM, list.get(pos + 1).value);
        int v1 = Integer.parseInt(l1.value);
        int v2 = Integer.parseInt(l2.value);
        return v1 * v2;
    }

    public static int calcDIV(int pos, List<Main.Lexeme> list) {
        Main.Lexeme l1 = new Main.Lexeme(Main.LexemeTypes.NUM, list.get(pos - 1).value);
        Main.Lexeme l2 = new Main.Lexeme(Main.LexemeTypes.NUM, list.get(pos + 1).value);
        int v1 = Integer.parseInt(l1.value);
        int v2 = Integer.parseInt(l2.value);
        return v1 / v2;
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
            int result = process(str);
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