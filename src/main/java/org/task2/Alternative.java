package org.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.task2.Main.lexicalAnalyzer;

public class Alternative {
    private static Main.LexemeBuffer lb;

    public static void main(String[] args) {
        String input = "((3+2)+1)+2";
        int result = process(input);
        System.out.println(result);
    }

    public static int process(String input) {
        List<Main.Lexeme> lexemes = lexicalAnalyzer(input);
        lb = new Main.LexemeBuffer(lexemes);
        List<Main.Lexeme> tempList = new ArrayList<>(lb.lexemes);

        int result = findExpressionInBrackets(tempList);
        return result;
    }

    public static int findExpressionInBrackets(List<Main.Lexeme> list) {
        int startPos = 0;
        int endPos = 0;
        int brackets = 0;
        Optional<Main.Lexeme> optional = list.stream().filter(l -> l.type == Main.LexemeTypes.LBRACKET).findFirst();

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
            list.add(lbracketPos, new Main.Lexeme(Main.LexemeTypes.NUM, String.valueOf(value)));

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


}
