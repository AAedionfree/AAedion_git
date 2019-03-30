package oothree;

import sun.reflect.generics.tree.Tree;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class TreeNode {
    public Term data;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
        left = null;
        right = null;
    }

    public TreeNode(TreeNode left,String op,TreeNode right) {
        data = new Operation(op);
        this.left = left;
        this.right = right;
    }

    public TreeNode(Term newdata) {
        data = newdata;
        left = null;
        right = null;
    }
    static TreeNode der(TreeNode current) {
//        String result = null;
//        if (current != null) {
//            if (current.data.isleaf == 0) {
//                if (current.data.str.equals("+") || current.data.str.equals("-")) {
//                    result = TreeNode.der(current.left) + current.data.str + TreeNode.der(current.right);
//                } else if (current.data.str.equals("*")) {
//                    result = TreeNode.der(current.left) + current.data.str + TreeNode.get_context(current.right)
//                            + "+" + TreeNode.der(current.right) + current.data.str + TreeNode.get_context(current.left);
//                } else if (current.data.str.equals("^")) {
//                    result = "(" + TreeNode.get_context(current.right) + ")" + "*" + TreeNode.get_context(current.left)
//                            + "^(" + TreeNode.get_context(current.right) + "-1)" + "*" + TreeNode.der(current.left);
//                } else if (current.data.str.equals(" qt ")){
//                    result = TreeNode.der(current.left)+"("+TreeNode.get_context(current.right)+")"
//                            +"*"+"("+TreeNode.der(current.right)+")";
//                }
//            }
//            else if(current.data.isleaf == 1){
//                return current.data.der();
//            }
//            return result;
//        }
        TreeNode result = new TreeNode();
        if(current!=null){
            if(current.data.isleaf == 0){
                String op = current.data.str;
                if(op.equals("+")||op.equals("-")){
                    result = new TreeNode(
                            TreeNode.der(current.left),
                            op,
                            TreeNode.der(current.right)
                    );
                }else if(op.equals("*")){
                    TreeNode left = new TreeNode(
                            TreeNode.der(current.left),
                            op,
                            current.right
                    );
                    TreeNode right = new TreeNode(
                            current.left,
                            op,
                            TreeNode.der(current.right)
                    );
                    result = new TreeNode(left,"+",right);
                }else if(op.equals(" qt ")){
                    result = new TreeNode(
                      new TreeNode(TreeNode.der(current.left)," qt ",current.right),
                      "*",
                      TreeNode.der(current.right)
                    );
                }else if(op.equals("^")){
                    result = new TreeNode(
                            new TreeNode(new TreeNode(current.left,"^",new TreeNode(new Final(new BigInteger(current.right.data.str).subtract(new BigInteger("1")).toString()))),
                                    "*",TreeNode.der(current.left)),
                            "*",
                            current.right
                    );
                }
            }else{
                return new TreeNode(current.data.der());
            }
            return result;
        }
        return null;
    }

    private static String postOrder(TreeNode currNode) {
        if (currNode != null) {
            String temp = "";
                temp = temp + "(";
            temp = temp + postOrder(currNode.left);
            if(!currNode.data.str.equals(" qt ")){
                temp = temp + currNode.data.str;
            }
            temp = temp + postOrder(currNode.right);
                temp = temp + ")";
            return temp;
        }
        return "";
    }

    static String get_context(TreeNode currNode) {
        String deal = "";
        deal = deal + currNode.postOrder(currNode);
        deal = deal.replace("(si)", "sin");
        deal = deal.replace("(-1*si)", "-1*sin");
        deal = deal.replace("(co)", "cos");
        return deal;
    }
}

public class


Experssion {

    static TreeNode root = null;

    static boolean hasOper(String checkOper) {
        if (checkOper.indexOf("+") == -1 && checkOper.indexOf("-") == -1
                && checkOper.indexOf("*") == -1 && checkOper.indexOf("/") == -1
                && checkOper.indexOf("^") == -1 && checkOper.indexOf("sin(") == -1
                && checkOper.indexOf("cos(") == -1) {
            return false;
        } else {
            return true;
        }
    }

    static TreeNode buildTree(String expression) {
        if (expression.indexOf("(") == 0) {
            for (int i = 0; i < expression.length(); i++) {
                if (expression.startsWith("(") == true) {
                    if (expression.endsWith(")") == true) {
                        expression = expression.substring(1, expression.length() - 1);
                    } else {
                        /*
                         * 抛出异常   括号不比配
                         */
                        System.out.print("WRONG FORMAT!");
                        System.exit(-1);
                    }

                }
            }
        }
        char[] expre = expression.toCharArray();

        TreeNode newNode = new TreeNode();
        String leftString = new String();
        String rightString = new String();
        String stack = new String();
        if (hasOper(expression) == true) {
            int index = -1;
            int multi_div = -1;
            int pow = -1;
            int trigpos = -1;
            for (int i = expre.length - 1; i >= 0; i--) {
                if (expre[i] == ')') {
                    stack = stack + expre[i];
                } else if (expre[i] == '(') {
                    if (stack.length() > 0) {
                        stack = stack.substring(0, stack.length() - 1);
                    }
                } else if (expre[i] == '+' && stack.length() == 0 || expre[i] == '-' && stack.length() == 0) {
                    index = i;
                    break;
                } else if (expre[i] == '/' && stack.length() == 0 || expre[i] == '*' && stack.length() == 0) {
                    multi_div = i;
                } else if (expre[i] == '^' && stack.length() == 0) {
                    pow = i;
                } else if (i >= 2 && stack.length() == 0) {
                    String trig = expression.substring(i - 2, i + 1);
                    if (trig.equals("sin")) {
                        trigpos = i;
                    } else if (trig.equals("cos")) {
                        trigpos = i;
                    }
                } else if ((expre[i] < 48 || expre[i] > 57) &&
                        expre[i] != '+' && expre[i] != '-' &&
                        expre[i] != '*' && expre[i] != '/' &&
                        expre[i] != '^' && expre[i] != 's' &&
                        expre[i] != 'i' && expre[i] != 'n' &&
                        expre[i] != 'c' && expre[i] != 'o' && expre[i] != 'x') {
                    System.out.print("WRONG FORMAT!");
                }
            }
            if (stack.length() != 0) {
                System.out.print("WRONG FORMAT!");
            }
            int separator = 0;
            if (index != -1) {             //说明表达式的最后运算的为加法或减法
                separator = index;
                newNode.data = new Operation(expre[separator] + "");
            } else if (multi_div != -1) {
                separator = multi_div;
                newNode.data = new Operation(expre[separator] + "");
            } else if (pow != -1) {
                separator = pow;
                newNode.data = new Operation(expre[separator] + "");
            } else if (trigpos != -1) {
                separator = trigpos;
                newNode.data = new Operation(" qt ");
            }
            int pos = 0;
            for (; pos < separator; pos++) {
                leftString = leftString + expre[pos];
            }
            pos++;
            for (; pos < expre.length; pos++) {
                rightString = rightString + expre[pos];
            }
            if (root == null) {
                root = newNode;
            }
            newNode.left = buildTree(leftString);
            newNode.right = buildTree(rightString);
        } else {
            char[] temp = expression.toCharArray();
            if (temp.length == 0) {
                newNode.data = new Final("0");
            } else {
                if (expression.contains("si") || expression.contains("co")) {
                    newNode.data = new Trig(expression.substring(0, expression.length()));
                } else if (expression.contains("x")) {
                    newNode.data = new Nape(expression.substring(0, expression.length()));
                } else {
                    newNode.data = new Final(expression.substring(0, expression.length()));
                }
            }
        }
        return newNode;
    }
    private void checkstr(String str){
        Pattern illSpace = Pattern.compile("\\f|\\v|"
                + "\\d\\s+\\d|"
                + "s\\s+i|i\\s+n|"
                + "c\\s+o+i|o\\s+s|"
                + "[+-][+-][+-]\\s+\\d|"
                + "^\\s+$");

        Matcher spaceMatch = illSpace.matcher(str);

        if (spaceMatch.find()) {
            System.out.println("WRONG FORMAT!");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        try{
            Scanner in = new Scanner(System.in);
            String str = in.nextLine();
            str = str.replace(" ","").replace("\t","");
            while(str.charAt(0)=='('&&str.charAt(str.length()-1)==')'){
                str = str.substring(1,str.length()-1);
            }
            new Experssion().checkstr(str);
            TreeNode lastRoot = null;
            lastRoot = buildTree(str);
            //System.out.println("原表达式为:  " + str);
            //System.out.print("后续遍历的结果为:  ");
            Experssion init = new Experssion();
            Deal.deal(TreeNode.get_context(TreeNode.der(root)));
//            System.out.println(TreeNode.der(root));
        }
        catch (Exception e){
            System.out.print("WRONG FORMAT!");
        }
    }

}
