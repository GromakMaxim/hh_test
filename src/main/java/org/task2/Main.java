package org.task2;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static int totalOperations = 0;
    private static HashMap<Integer, Integer> stat = new HashMap<>();
    static ArrayList<String> stringPool = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        parse(line);
    }

    public static void parse(String input){
        if (!input.contains("d")){
            int result = doMath(input);
            System.out.println(result + " " + "100.00");
        } else {
            String[] temp = generateStrings(input);
            gen(temp);
            statistics(stringPool);
        }
    }

    public static void statistics(ArrayList<String> strings) {
        for (String str : strings) {
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
            double result =  ((double) entry.getValue() / (double) totalOperations) * 100;
            String formatted = new DecimalFormat("#0.00").format(result).replace(',', '.');
            System.out.println(entry.getKey() + " " + formatted);
        }
    }

    public static void gen(String[] arr) {
        ArrayList<String> list = new ArrayList<>();
        for (String item : arr) {
            String[] temp = generateStrings(item);
            list.addAll(Arrays.asList(temp));
        }
        String[] tempResult = list.toArray(new String[0]);
        if (tempResult[0].contains("d")) {
            gen(tempResult);
        } else {
            stringPool.addAll(Arrays.asList(tempResult));
        }
    }


    public static String[] generateStrings(String input) {
        ArrayList<String> strings = new ArrayList<>();

        Pattern pattern = Pattern.compile("[d]\\d+");//try to find things like: d*
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            int value = Integer.parseInt(matcher.group().replace("d", ""));
            for (int i = 1; i <= value; i++) {
                String target = matcher.group();
                String replacement = String.valueOf(i);
                String newString = input.replaceFirst(target, replacement);
                strings.add(newString);
            }
            break;
        }
        return strings.toArray(new String[0]);

    }

    public static int doMath(String input) {
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("nashorn");

        try {
            Object obj = engine.eval(input);
            if (obj instanceof Number) {
                return (int) obj;
            }

            if (obj instanceof Boolean) {
                if ((Boolean) obj) return 1;
                return -1;
            }
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return -123;
    }
}


