package ootwo;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;

public class Poly {
    private String init;
    private static int zero = 0;

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
        String[] result = init.split("(?<![\\*\\^])(?=[-+])");
        return result;
    }

    private static HashMap com(HashMap temp, String problem, String factor) {
        int len = factor.length();
        if (problem.equals("sin") || problem.equals("cos")
                || problem.equals("x")) {
            if (factor.contains("^")) {
                int t = factor.indexOf("^");
                BigInteger index = new BigInteger(factor.substring(t + 1, len));
                if (temp.get(problem) == null) {
                    temp.put(problem, index);
                } else {
                    BigInteger before = new BigInteger(temp.get(problem)
                            .toString());
                    temp.put(problem, before.add(index));
                }
            } else {
                BigInteger sinindex = new BigInteger("1");
                if (temp.get(problem) == null) {
                    temp.put(problem, sinindex);
                } else {
                    BigInteger before =
                            new BigInteger(temp.get(problem).toString());
                    temp.put(problem, before.add(sinindex));
                }
            }
        } else if (problem.equals("final")) {
            BigInteger t = new BigInteger(factor);
            if (temp.get("final") == null) {
                temp.put(problem, t);
            } else {
                BigInteger before =
                        new BigInteger(temp.get(problem).toString());
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
                if (j == 0 && factor[j].charAt(0) == '-' &&
                        (factor[j].contains("cos") || factor[j].contains("sin")
                                || factor[j].contains("x"))) {
                    BigInteger fin = new BigInteger("-1");
                    temp.put("final", fin);
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
        String illeage1 = ".*([0-9]\\s+[0-9]).*";
        String illeage2 = ".*(\\^\\s*\\+\\s+[0-9+]).*";
        String illeage3 = ".*(\\*\\s*\\+\\s+[0-9+]).*";
        //String illeage4 = ".*([-+][-+]\\s+[-+]).*";
        String illeage5 = ".*([-+][-+][-+]\\s+).*";
        String illeage6 = ".*([-+][-+][-+]\\s*\\D).*";
        boolean condition1 = init.matches(illeage1);
        boolean condition2 = init.matches(illeage2);
        boolean condition3 = init.matches(illeage3);
        //boolean condition4 = init.matches(illeage4);
        boolean condition5 = init.matches(illeage5);
        boolean condition6 = init.matches(illeage6);
        if (condition1 || condition2
                || condition3  || condition5 || condition6) {
            return false;
        }
        String head = "((((\\s*[-+]?([0-9]+\\*)(x(\\^\\s*[-+]?[0-9]+)?))|" +
                "(\\s*[-+]?(x(\\^\\s*[-+]?[0-9]+)?))|" +
                "(\\s*[-+]?([0-9]+)))|" +
                "((sin\\s*\\(\\s*x\\s*\\)(\\^\\s*[-+]?[0-9]+)?)+)|" +
                "((cos\\s*\\(\\s*x\\s*\\)(\\^\\s*[-+]?[0-9]+)?)+))" +
                "\\s*)";
        String tail = "(\\*\\s*(((\\s*[-+]?([0-9]+\\*)" +
                "(x(\\^\\s*[-+]?[0-9]+)?))|" +
                "(\\s*[-+]?(x(\\^\\s*[-+]?[0-9]+)?))|" +
                "(\\s*[-+]?([0-9]+)))|" +
                "((sin\\s*\\(\\s*x\\s*\\)(\\^\\s*[-+]?[0-9]+)?)+)|" +
                "((cos\\s*\\(\\s*x\\s*\\)(\\^\\s*[-+]?[0-9]+)?)+))\\s*)*+";
        String termhead = "([-+]?\\s*[-+]?[-+]?" + head + tail + "){1}+";
        String term1 = "([-+]\\s*[-+]?[-+]?" + head + tail + ")*+";
        String term = "(" + termhead + term1 + ")";
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
            BigInteger[] a = new BigInteger[4];
            String[] ret = key.toString().split(" ");
            a[0] = new BigInteger(ret[0]);
            a[1] = new BigInteger(ret[1]);
            a[2] = new BigInteger(ret[2]);
            Object val = entry.getValue();
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
        int aclass;
        int bclass;
        int cclass;
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
            aclass = 0;
        } else if (info[0].toString().equals("1")) {
            aclass = 1;
        } else {
            aclass = 2;
        }
        if (info[1].toString().equals("0")) {
            bclass = 0;
        } else if (info[1].toString().equals("1")) {
            bclass = 1;
        } else {
            bclass = 2;
        }
        if (info[2].toString().equals("0")) {
            cclass = 0;
        } else if (info[2].toString().equals("1")) {
            cclass = 1;
        } else {
            cclass = 2;
        }
        Poly.print1(info[3].toString(), aclass, bclass, cclass,
                t, info[0], info[1], info[2]);
    }

    private static void print1(String coeff, int aclass, int bclass, int cclass,
                               int t, BigInteger a,
                               BigInteger b, BigInteger c) {
        String coef;
        String sign = "";
        String nape;
        String sin;
        String cos;
        if (new BigInteger(coeff)
                .compareTo(new BigInteger("0")) > 0 && t == 0) {
            sign = "";
        } else if (new BigInteger(coeff)
                .compareTo(new BigInteger("0")) > 0 && t == 1) {
            sign = "+";
        } else {
            sign = "";
        }
        if (aclass == 0 && bclass == 0 && cclass == 0) {
            if (!coeff.toString().equals("0")) {
                System.out.print(sign + coeff);
                zero = 1;
            }
            return;
        }
        if (coeff.equals("-1")) {
            coef = "-";
        } else if (coeff.equals("1")) {
            coef = "+";
        } else {
            coef = sign + coeff;
        }
        if (aclass == 0) {
            nape = "";
        } else if (aclass == 1) {
            nape = "x";
        } else {
            nape = "x^" + a.toString();
        }
        if (bclass == 0) {
            sin = "";
        } else if (bclass == 1) {
            sin = "sin(x)";
        } else {
            sin = "sin(x)^" + b.toString();
        }
        if (cclass == 0) {
            cos = "";
        } else if (cclass == 1) {
            cos = "cos(x)";
        } else {
            cos = "cos(x)^" + c.toString();
        }
        Poly.print_real(coef, nape, sin, cos);
    }

    private static int print_real(String coef, String nape,
                                  String sin, String cos) {
        int cont = 0;
        if (!coef.equals("0")) {
            System.out.print(coef);
            if (!coef.equals("") && !coef.equals("+") && !coef.equals("-")) {
                cont = 1;
            }
        } else {
            return 0;
        }
        if (!nape.equals("") && cont == 1) {
            System.out.print("*" + nape);
            cont = 1;
            zero = 1;
        } else if (!nape.equals("") && cont == 0) {
            System.out.print(nape);
            cont = 1;
            zero = 1;
        }
        if (!sin.equals("") && cont == 1) {
            System.out.print("*" + sin);
            zero = 1;
        } else if (!sin.equals("") && cont == 0) {
            System.out.print(sin);
            cont = 1;
            zero = 1;
        }
        if (!cos.equals("") && cont == 1) {
            System.out.print("*" + cos);
            zero = 1;
        } else if (!cos.equals("") && cont == 0) {
            System.out.print(cos);
            cont = 1;
            zero = 1;
        }
        return zero;
    }

    public static void main(String[] args) {
        try {
            Single[] a;
            Single[] single = new Single[500];
            Single[] derresult = new Single[1500];
            Scanner in = new Scanner(System.in);
            String init;
            init = in.nextLine();
            Poly poly = new Poly(init);
            if (!poly.check_illeage()) {
                System.out.print("WRONG FORMAT!");
                return;
            }
            poly.deal();
            List<HashMap> edion = new ArrayList<>();
            edion = poly.Integration(poly);
            int i = 0;
            for (i = 0; i < edion.size(); i++) {
                single[i] = new Single(edion.get(i));
                a = single[i].der();
                derresult[3 * i] = a[0];
                derresult[3 * i + 1] = a[1];
                derresult[3 * i + 2] = a[2];
            }
            HashMap result;
            result = Poly.result(derresult);
            poly.print_all(result);
            if (zero == 0) {
                System.out.print("0");
            }
        } catch (Exception e) {
            System.out.print("WRONG FORMAT!");
            return;
        }
    }
}