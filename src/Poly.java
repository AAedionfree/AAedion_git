package ootwo;

import java.math.BigInteger;
import java.util.*;

public class Poly {
    private String init;

    Poly(String init) {
        this.init = init;
    }

    private String get() {
        return init;
    }

    private void set() {
        this.init = init;
    }

    private String[] Split() {
        String result[] = init.split("(?<![\\*\\^])(?=\\+)");
        return result;
    }

    private static HashMap com(HashMap temp, String problem, String factor) {
        int len = factor.length();
        if (problem.equals("sin") || problem.equals("cos") || problem.equals("x")) {
            if (factor.contains("^")) {
                int t = factor.indexOf("^");
                BigInteger index = new BigInteger(factor.substring(t + 1, len));
                if (temp.get(problem) == null) {
                    temp.put(problem, index);
                } else {
                    BigInteger before = new BigInteger(temp.get(problem).toString());
                    temp.put(problem, before.add(index));
                }
            } else {
                BigInteger sin_index = new BigInteger("1");
                if (temp.get(problem) == null) {
                    temp.put(problem, sin_index);
                } else {
                    BigInteger before = new BigInteger(temp.get(problem).toString());
                    temp.put(problem, before.add(sin_index));
                }
            }
        } else if (problem.equals("final")) {
            BigInteger t = new BigInteger(factor);
            if (temp.get("final") == null) {
                temp.put(problem, t);
            } else {
                BigInteger before = new BigInteger(temp.get(problem).toString());
                temp.put(problem, before.multiply(t));
            }
        }
        return temp;
    }

    private List<HashMap> Integration(Poly poly) {
        int i;
        int j;
        List<HashMap> edion = new ArrayList<>();
        String[] result = poly.Split();
        for (i = 0; i < result.length; i++) {
            HashMap temp = new HashMap();
            String[] factor = result[i].split("\\*");
            for (j = 0; j < factor.length; j++) {
                int len = factor[j].length();
                if (j == 0 && factor[j].charAt(0) == '-') {
                    BigInteger fin = new BigInteger("-1");
                    temp.put("final", fin);
                    continue;
                }
                if (factor[j].contains("sin")) {
                    temp = com(temp, "sin", factor[j]);
                } else if (factor[j].contains("cos")) {
                    temp = com(temp, "cos", factor[j]);
                } else if (factor[j].contains("x")) {
                    temp = com(temp, "x", factor[j]);
                } else {
                    temp = com(temp, "final", factor[j]);
                }
            }
            if (temp.get("sin") == null) {
                temp.put("sin", 0);
            }
            if (temp.get("cos") == null) {
                temp.put("cos", 0);
            }
            if (temp.get("x") == null) {
                temp.put("x", 0);
            }
            if (temp.get("final") == null) {
                temp.put("final", 1);
            }
            edion.add(temp);
        }
        return edion;
    }

    private void deal() {
        init = init.replace(" ", "");
        init = init.replace("\t", "");

        init = init.replace("+++", "+");
        init = init.replace("++-", "-");
        init = init.replace("+-+", "-");
        init = init.replace("+--", "+");
        init = init.replace("-++", "-");
        init = init.replace("-+-", "+");
        init = init.replace("--+", "+");
        init = init.replace("---", "-");

        init = init.replace("++", "+");
        init = init.replace("+-", "-");
        init = init.replace("--", "+");
        init = init.replace("-+", "-");
        this.init = init;
    }

    private boolean check_illeage() {
        String illeage_1 = ".*([0-9]\\s+[0-9]).*";
        String illeage_2 = ".*(\\^\\s*\\+\\s+[0-9+]).*";
        String illeage_3 = ".*(\\*\\s*\\+\\s+[0-9+]).*";
        String illeage_4 = ".*([-+][-+]\\s+[-+]).*";
        String illeage_5 = ".*([-+][-+][-+]\\s+).*";
        boolean condition_1 = init.matches(illeage_1);
        boolean condition_2 = init.matches(illeage_2);
        boolean condition_3 = init.matches(illeage_3);
        boolean condition_4 = init.matches(illeage_4);
        boolean condition_5 = init.matches(illeage_5);

        if (condition_1 || condition_2 || condition_3 || condition_4 || condition_5) {
            return false;
        }
        String head = "((((\\s*[-+]?([0-9]+\\*)(x(\\^[-+]?[0-9]+)?))|" +
                "(\\s*[-+]?(x(\\^[-+]?[0-9]+)?))|" +
                "(\\s*[-+]?([0-9]+)))|" +
                "((sin\\(\\s*x\\s*\\)(\\^\\s*[-+]?[0-9]+)?)+)|" +
                "((cos\\(\\s*x\\s*\\)(\\^\\s*[-+]?[0-9]+)?)+))\\s*\\*\\s*)*";
        String tail = "((((\\s*[-+]?([0-9]+\\*)(x(\\^[-+]?[0-9]+)?))|" +
                "(\\s*[-+]?(x(\\^[-+]?[0-9]+)?))|" +
                "(\\s*[-+]?([0-9]+)))|" +
                "((sin\\(\\s*x\\s*\\)(\\^\\s*[-+]?[0-9]+)?)+)|" +
                "((cos\\(\\s*x\\s*\\)(\\^\\s*[-+]?[0-9]+)?)+))\\s*)+";
        Scanner in = new Scanner(System.in);
        String term_head = "([-+]?\\s*[-+]?[-+]?" + head + tail + ")+";
        String term1 = "([-+]\\s*[-+]?[-+]?" + head + tail + ")*";
        String term = term_head + term1;
        if (init.matches(term)) {
            return true;
        } else {
            return false;
        }
    }

    private static HashMap result(Single[] der) {
        int i;
        String space = " ";
        BigInteger k;
        BigInteger a;
        BigInteger b;
        BigInteger c;
        HashMap ret = new HashMap();
        for (i = 0; i < der.length; i++) {
            if (der[i] == null) {
                break;
            }
            k = der[i].get_k();
            if (k.toString().equals("0")) {
                continue;
            }
            a = der[i].get_a();
            b = der[i].get_b();
            c = der[i].get_c();
            String key = a.toString() + space +
                    b.toString() + space +
                    c.toString();
            if (ret.get(key) == null) {
                ret.put(key, k);
            } else {
                BigInteger before = new BigInteger(ret.get(key).toString());
                ret.put(key, before.add(k));
            }
        }
        return ret;
    }

    private void print_all(HashMap result) {
        int i;
        int t = 0;
        Iterator iter = result.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            BigInteger a[] = new BigInteger[4];
            String ret[] = key.toString().split(" ");
            a[0] = new BigInteger(ret[0]);
            a[1] = new BigInteger(ret[1]);
            a[2] = new BigInteger(ret[2]);
            a[3] = new BigInteger(val.toString());
            if (t == 0) {
                Poly.print(a, 0);
                t = 1;
            } else {
                Poly.print(a, 1);
            }
        }
    }

    private static void print(BigInteger[] info, int t) {
        String sign;
        int a_class;
        int b_class;
        int c_class;
        if (t == 0) {
            if (info[3].compareTo(new BigInteger("0")) > 0) {
                sign = "";
            } else {
                sign = "-";
            }
        } else {
            if (info[3].compareTo(new BigInteger("0")) > 0) {
                sign = "+";
            } else {
                sign = "-";
            }
        }
        if (info[0].toString().equals("0")) {
            a_class = 0;
        } else if (info[0].toString().equals("1")) {
            a_class = 1;
        } else {
            a_class = 2;
        }
        if (info[1].toString().equals("0")) {
            b_class = 0;
        } else if (info[1].toString().equals("1")) {
            b_class = 1;
        } else {
            b_class = 2;
        }
        if (info[2].toString().equals("0")) {
            c_class = 0;
        } else if (info[2].toString().equals("1")) {
            c_class = 1;
        } else {
            c_class = 2;
        }
        Poly.print1(info[3].toString(), a_class, b_class, c_class, t, info[0], info[1], info[2]);
    }

    private static void print1(String coeff, int a_class, int b_class, int c_class, int t, BigInteger a, BigInteger b, BigInteger c) {
        int cont = 0;
        String coef;
        String sign = "";
        String nape;
        String sin;
        String cos;
        if (new BigInteger(coeff).compareTo(new BigInteger("0")) > 0 && t == 0) {
            sign = "";
        } else if (new BigInteger(coeff).compareTo(new BigInteger("0")) > 0 && t == 1) {
            sign = "+";
        } else {
            sign = "-";
        }
        if (a_class == 0 && b_class == 0 && c_class == 0) {
            System.out.println(sign + coeff);
        }
        if (coeff.equals("-1")) {
            coef = "-";
        } else if (coeff.equals("1")) {
            coef = "+";
        } else {
            coef = coeff;
        }
        if (a_class == 0) {
            nape = "";
        } else if (a_class == 1) {
            nape = "x";
        } else {
            nape = "x^" + a.toString();
        }
        if (b_class == 0) {
            sin = "";
        } else if (b_class == 1) {
            sin = "sin(x)";
        } else {
            sin = "sin(x)^" + b.toString();
        }
        if (c_class == 0) {
            cos = "";
        } else if (c_class == 1) {
            cos = "cos(x)";
        } else {
            cos = "cos(x)^" + c.toString();
        }
        if (!coef.equals("")) {
            System.out.print(coef);
        }
        if (!nape.equals("")) {
            System.out.print(nape);
            cont = 1;
        }
        if (!sin.equals("") && cont == 1) {
            System.out.print("*" + sin);
        } else if (!sin.equals("") && cont == 0) {
            System.out.print(sin);
            cont = 1;
        }
        if (!cos.equals("") && cont == 1) {
            System.out.print("*" + cos);
        } else if (!sin.equals("") && cont == 0) {
            System.out.print(cos);
            cont = 1;
        }
    }

    public static void main(String[] args) {
        int i = 0;
        Single[] a;
        Single[] single = new Single[500];
        Single[] der_result = new Single[1500];
        List<HashMap> edion = new ArrayList<>();
        HashMap result;
        Scanner in = new Scanner(System.in);
        String init = in.nextLine();
        Poly poly = new Poly(init);
        if (!poly.check_illeage()) {
            System.out.print("WRONG FORMAT!");
            return;
        }
        poly.deal();
        edion = poly.Integration(poly);
        for (i = 0; i < edion.size(); i++) {
            single[i] = new Single(edion.get(i));
            a = single[i].der();
            der_result[3 * i] = a[0];
            der_result[3 * i + 1] = a[1];
            der_result[3 * i + 2] = a[2];
        }
        result = Poly.result(der_result);
        poly.print_all(result);
    }
}
