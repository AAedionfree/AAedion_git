package oo;

import com.sun.deploy.perf.NativePerfHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class polyy {
    public static void print_one(int coeff, int index) {
        if (coeff == 0) {
            System.out.print("0");
            return;
        }
        if (index == 0) {
            System.out.print(coeff);
            return;
        }
        if (coeff != 0 && index != 0) {
            System.out.print(coeff + "*x^" + index);
            return;
        }
        if (coeff == 1 && index != 1) {
            System.out.print("x^" + index);
            return;
        }
        if (coeff == 1 && index == 1) {
            System.out.print("x");
            return;
        }
        if (coeff != 1 && index == 1) {
            System.out.print(coeff + "*x");
            return;
        }
    }

    public static int print(int coeff, int index, int t) {
        if (t == 0) {
            if (coeff == 0) {
                return 0;
            }
            if (index == 0) {
                System.out.print(coeff);
                return 1;
            }
            if (coeff != 0 && index != 0) {
                System.out.print(coeff + "*x^" + index);
                return 1;
            }
            if (coeff == 1 && index != 1) {
                System.out.print("x^" + index);
                return 1;
            }
            if (coeff == 1 && index == 1) {
                System.out.print("x");
                return 1;
            }
            if (coeff != 1 && index == 1) {
                System.out.print(coeff + "*x");
                return 1;
            }
        } else {
            String sign;
            if (coeff > 0) {
                sign = "+";
            } else {
                sign = "-";
            }
            if (coeff == 0) {
                return 0;
            }
            if (index == 0) {
                System.out.print(sign + coeff);
                return 1;
            }

            if (coeff == 1 && index != 1) {
                System.out.print(sign + "x^" + index);
                return 1;
            }
            if (coeff == 1 && index == 1) {
                System.out.print(sign + "x");
                return 1;
            }
            if (coeff != 1 && index == 1) {
                System.out.print(sign + coeff + "*x");
                return 1;
            }
            if (coeff != 0 && index != 0) {
                System.out.print(sign + coeff + "*x^" + index);
                return 1;
            }
        }
        return 111;
    }

    public static List<nape> combine(List<nape> napes) {
        int[] find = new int[1000];
        int[] pos = new int[1000];
        int i;
        //init
        for (i = 0; i < 1000; i++) {
            find[i] = 0;
        }
        for (i = 0; i < napes.size(); i++) {
            if (find[napes.get(i).get_index()] == 0) {
                find[napes.get(i).get_index()] = 1;
                pos[napes.get(i).get_index()] = i;
            } else {
                int before_pos = pos[napes.get(i).get_index()];
                nape temp = new nape(napes.get(before_pos).get_coeff() + napes.get(i).get_coeff(),
                        napes.get(i).get_index());
                napes.remove(i);
                napes.set(before_pos, temp);
            }
        }
        return napes;
    }

    public static void main(String[] args) {
        int i, illegal = 0, have_space = 0, j, k;
        Scanner in = new Scanner(System.in);
        String init = in.nextLine();
        if (init.length() == 0) {
            System.out.print("WRONG FORMAT!");
            return;
        }
        for (i = 0; i < init.length(); i++) {
            //3 2
            char ch = init.charAt(i);
            if (Character.isDigit(init.charAt(i))) {
                k = i;
                k++;
                have_space = 0;
                if (k >= init.length()) {
                    break;
                }
                while (init.charAt(k) == ' ' || init.charAt(k) == '\t') {
                    have_space = 1;
                    k++;
                    if (k >= init.length()) {
                        break;
                    }
                }
                if (k >= init.length()) {
                    break;
                }
                if (Character.isDigit(init.charAt(k)) && have_space == 1) {
                    illegal = 1;
                    break;
                }
            }
            //++ 3
            //x^+ 2
            if ((init.charAt(i) == '+' || init.charAt(i) == '-') && i + 1 < init.length()) {
                j = i + 1;
                while (init.charAt(j) == ' ' || init.charAt(j) == '\t') {
                    j++;
                    if (j >= init.length()) {
                        break;
                    }
                }
                if ((init.charAt(j) == '+' || init.charAt(j) == '-')) {
                    have_space = 0;
                    k = j + 1;
                    if (k >= init.length()) {
                        break;
                    }
                    while (init.charAt(k) == ' ' || init.charAt(k) == '\t') {
                        have_space = 1;
                        k++;
                        if (k >= init.length()) {
                            break;
                        }
                    }
                    if (k >= init.length()) {
                        break;
                    }
                    if (Character.isDigit(init.charAt(k)) && have_space == 1) {
                        illegal = 1;
                        break;
                    }
                }
            }
            //^  +3
            if ((init.charAt(i) == '^') && i + 1 < init.length()) {
                j = i + 1;
                while (init.charAt(j) == ' ' || init.charAt(j) == '\t') {
                    j++;
                    if (j >= init.length()) {
                        break;
                    }
                }
                if (init.charAt(j) == '+' || init.charAt(j) == '-') {
                    have_space = 0;
                    k = j + 1;
                    if (k >= init.length()) {
                        break;
                    }
                    while (init.charAt(k) == ' ' || init.charAt(k) == '\t') {
                        have_space = 1;
                        k++;
                        if (k >= init.length()) {
                            break;
                        }
                    }
                    if (k >= init.length()) {
                        break;
                    }
                    if (Character.isDigit(init.charAt(k)) && have_space == 1) {
                        illegal = 1;
                        break;
                    }
                }
            }
        }
        List<nape> napes = new ArrayList<>();
        if (illegal == 1) {
            System.out.println("WRONG FORMAT!");
        } else {
            init = init.replace(" ", "")
                    .replace("\t", "")
                    .replace("++", "+")
                    .replace("+-", "-")
                    .replace("-+", "-")
                    .replace("--", "+");
            if (Character.isDigit(init.charAt(0)) || init.charAt(0) == 'x') {
                init = '+' + init;
            }
            String spt = "(([-+]([0-9]+\\*)(x(\\^[-+]?[0-9]+)?))|([-+](x(\\^[-+]?[0-9]+)?))|([-+]([0-9]+)))+";
            //System.out.println(init);
            if (init.matches(spt)) {
                String[] spt_result = init.split("(?<=(x|[0-9])(?=[-+]))");
                for (i = 0; i < spt_result.length; i++) {
                    System.out.println(spt_result[i]);
                    napes.add(new nape(spt_result[i]));
                }
                List<nape> t = combine(napes);
                for (i = 0; i < t.size(); i++) {
//                System.out.print(t.get(i).get_coeff()+" "+t.get(i).get_index()+" ");
                    t.set(i, t.get(i).dervation());
                }
                int is_first = 0;
                for (i = 0; i < t.size(); i++) {
                    if (t.size() > 1) {
                        if (is_first == 0) {
                            is_first = print(t.get(i).get_coeff(), t.get(i).get_index(), is_first);
                        } else {
                            is_first = print(t.get(i).get_coeff(), t.get(i).get_index(), is_first);
                        }
                    } else if (t.size() == 1) {
                        print_one(t.get(i).get_coeff(), t.get(i).get_index());
                    }
                }
            } else {
                System.out.print("WRONG FORMAT!");
            }
        }
    }
}
