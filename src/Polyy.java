package ooone;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Polyy {
    public void console(List<Nape> t, int isFirst) {
        int i;
        int isfirst = isFirst;
        for (i = 0; i < t.size(); i++) {
            if (t.size() > 1) {
                if (isfirst == 0) {
                    isfirst = print(t.get(i).get_coeff(),
                            t.get(i).get_index(), 0, t.size());
                } else {
                    isfirst = print(t.get(i).get_coeff(),
                            t.get(i).get_index(), 1, t.size());
                }
            } else if (t.size() == 1) {
                //print_one(t.get(i).get_coeff(), t.get(i).get_index());
                print(t.get(i).get_coeff(),
                        t.get(i).get_index(), 0, t.size());
            }
        }
    }

    public static int print(BigInteger coeff, BigInteger index, int t, int s) {
        int coeffclass;
        int indexclass;
        if (coeff.toString().equals("0")) {
            coeffclass = 0;
        } else if (coeff.toString().equals("1")) {
            coeffclass = 1;
        } else if (coeff.toString().equals("-1")) {
            coeffclass = -1;
        } else {
            coeffclass = 2;
        }
        if (index.toString().equals("0")) {
            indexclass = 0;
        } else if (index.toString().equals("1")) {
            indexclass = 1;
        } else {
            indexclass = 2;
        }
        String sign;
        if (coeff.compareTo(new BigInteger("0")) > 0 && t == 1) {
            sign = "+";
        } else {
            sign = "";
        }
        if (coeffclass == 0 && s == 1) {
            System.out.print("0");
            return 1;
        } else if (coeffclass == 0) {
            return 1;
        } else if (indexclass == 0) {
            System.out.print(sign + coeff);
            return 1;
        } else if (coeffclass == 1 && indexclass != 1) {
            System.out.print(sign + "x^" + index);
            return 1;
        } else if (coeffclass == 1 && indexclass == 1) {
            System.out.print(sign + "x");
            return 1;
        }  else if (coeffclass != 1 && indexclass == 1) {
            System.out.print(sign + coeff + "*x");
            return 1;
        } else if (coeffclass != 0 && indexclass != 0) {
            System.out.print(sign + coeff + "*x^" + index);
            return 1;
        }
        return 111;
    }

    public static int adjust_illegal(int k, int haveSpace, String init) {
        if (Character.isDigit(init.charAt(k)) && haveSpace == 1) {
            return 1;
        }
        return 0;
    }

    public static int cond1(String init, int i) {
        int k;
        int illegal;
        int haveSpace;
        if (Character.isDigit(init.charAt(i)) && i + 1 < init.length()) {
            k = i + 1;
            haveSpace = 0;
            while (init.charAt(k) == ' ' || init.charAt(k) == '\t') {
                haveSpace = 1;
                k++;
                if (k >= init.length()) {
                    break;
                }
            }
            if (k >= init.length()) {
                return 0;
            }
            if ((illegal = adjust_illegal(k, haveSpace, init)) == 1) {
                return 1;
            }
        }
        return 0;
    }

    public static int cond2(String init, int i) {
        int j;
        int k;
        int haveSpace;
        int illegal;
        if ((init.charAt(i) == '+' || init.charAt(i) == '-')
                && i + 1 < init.length()) {
            j = i + 1;
            while (init.charAt(j) == ' ' || init.charAt(j) == '\t') {
                j++;
                if (j >= init.length()) {
                    break;
                }
            }
            if ((init.charAt(j) == '+' || init.charAt(j) == '-')) {
                haveSpace = 0;
                k = j + 1;
                if (k >= init.length()) {
                    return 0;
                }
                while (init.charAt(k) == ' ' || init.charAt(k) == '\t') {
                    haveSpace = 1;
                    k++;
                    if (k >= init.length()) {
                        break;
                    }
                }
                if (k >= init.length()) {
                    return 0;
                }
                if ((illegal = adjust_illegal(k, haveSpace, init)) == 1) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public static int cond3(String init, int i) {
        int j;
        int k;
        int illegal;
        int haveSpace;
        if ((init.charAt(i) == '^') && i + 1 < init.length()) {
            j = i + 1;
            while (init.charAt(j) == ' ' || init.charAt(j) == '\t') {
                j++;
                if (j >= init.length()) {
                    break;
                }
            }
            if (init.charAt(j) == '+' || init.charAt(j) == '-') {
                haveSpace = 0;
                k = j + 1;
                if (k >= init.length()) {
                    return 0;
                }
                while (init.charAt(k) == ' ' || init.charAt(k) == '\t') {
                    haveSpace = 1;
                    k++;
                    if (k >= init.length()) {
                        break;
                    }
                }
                //haveSpace = adjust_space(k,init)
                if (k >= init.length()) {
                    return 0;
                }
                if ((illegal = adjust_illegal(k, haveSpace, init)) == 1) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public int check_illegal(String init) {
        int illegal = 0;
        int i = 0;
        for (i = 0; i < init.length(); i++) {
            char ch = init.charAt(i);
            illegal = cond1(init, i) | cond2(init, i) | cond3(init, i);
            if (illegal == 1) {
                break;
            }
        }
        return illegal;
    }

    public static List<Nape> combine(List<Nape> napes) {
        HashMap find = new HashMap();
        HashMap pos = new HashMap();
        int i;
        for (i = 0; i < napes.size(); i++) {
            if (find.get(napes.get(i).get_index()) == null) {
                find.put(napes.get(i).get_index(), 1);
                pos.put(napes.get(i).get_index(), i);
            } else {
                int beforePos = Integer.parseInt(pos.get(napes.get(i)
                        .get_index()).toString());
                Nape temp = new Nape(napes.get(beforePos).get_coeff()
                        .add(napes.get(i).get_coeff()),
                        napes.get(i).get_index());
                napes.remove(i);
                napes.set(beforePos, temp);
                i--;
            }
        }
        return napes;
    }

    public static void main(String[] args) {
        int i;
        int illegal = 0;
        Scanner in = new Scanner(System.in);
        String init = in.nextLine();
        Polyy ploy = new Polyy();
        if (init.length() == 0) {
            System.out.print("WRONG FORMAT!");
            return;
        }
        illegal = ploy.check_illegal(init);
        List<Nape> napes = new ArrayList<>();
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
            String spt = "(([-+]([0-9]+\\*)(x(\\^[-+]?[0-9]+)?))" +
                    "|([-+](x(\\^[-+]?[0-9]+)?))|([-+]([0-9]+)))+";
            if (init.matches(spt)) {
                String[] sptResult = init.split("(?<=(x|[0-9])(?=[-+]))");
                for (i = 0; i < sptResult.length; i++) {
                    napes.add(new Nape(sptResult[i]));
                }
                List<Nape> t = combine(napes);
                for (i = 0; i < t.size(); i++) {
                    t.set(i, t.get(i).dervation());
                }
                ploy.console(t, 0);
            } else {
                System.out.print("WRONG FORMAT!");
            }
        }
    }
}
