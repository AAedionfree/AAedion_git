package oothree;

import sun.security.util.Length;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Deal {
    public static String deal(String init) {
        int count = 0;
        String factor = "\\(([0-9]+)\\)";
        Pattern pattern = Pattern.compile(factor);
        Matcher matcher = pattern.matcher(init);
        while (matcher.find()) {
            count++;
            //System.out.println("found: " + count + " : " + matcher.start() + " - " + matcher.end());
            if (matcher.start() != 0 && matcher.end() != init.length()) {
                init = init.substring(0, matcher.start())
                        + init.substring(matcher.start() + 1, matcher.end() - 1)
                        + init.substring(matcher.end(), init.length());
            } else if (matcher.start() == 0 && matcher.end() == init.length()) {
                init = init.substring(1, init.length() - 1);
            } else if (matcher.start() == 0) {
                init = init.substring(1, matcher.end())
                        + init.substring(matcher.end() + 1, init.length());
            } else if (matcher.end() == init.length()) {
                init = init.substring(0, matcher.start())
                        + init.substring(matcher.start() + 1, matcher.end());
            }
            matcher = pattern.matcher(init);
        }
        System.out.print(init);
        return init;
    }

}
