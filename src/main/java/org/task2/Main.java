package org.task2;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();


        String [] arr = generateStrings(line);

        System.out.println(doMath(line));
    }



    public static String[] generateStrings(String input){
        Pattern p = Pattern.compile("[d]\\d+");//try to find things like: d*
        input = input.replaceAll(String.valueOf(p),"***");
        System.out.println(input);


        int repeats = input.split("d", -1).length - 1;
        int [][]arr = new int[repeats][1];

        return null;
    }

    public static int doMath(String input){
        ScriptEngineManager engineManager = new ScriptEngineManager();
        ScriptEngine engine = engineManager.getEngineByName("nashorn");

        try {
            Object obj = engine.eval(input);
            if (obj instanceof Number){
                return (int)obj;
            }

            if (obj instanceof Boolean){
                if ((Boolean) obj) return 1;
                return -1;
            }
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return -123;
    }

}


