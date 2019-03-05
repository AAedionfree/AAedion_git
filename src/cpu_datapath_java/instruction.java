package cpu_datapath_java;

import java.util.Scanner;

public class instruction {
    public String instruction;
    //construction
    public instruction(String instruction){
        this.instruction = instruction;
    }

    public String get_opcode(){
        return instruction.substring(0,6);
    }

    public String get_rs(){
        return instruction.substring(6,11);
    }

    public String get_rt(){
        return instruction.substring(11,16);
    }

    public String get_rd(){
        return instruction.substring(16,21);
    }

    public String get_shamt(){
        return instruction.substring(21,26);
    }

    public String get_function(){
        return instruction.substring(26,32);
    }
    public static void main(String[] args){
        String a;
        Scanner in = new Scanner(System.in);
        a = in.nextLine();
        instruction AAedion = new instruction(a);
        System.out.println(AAedion.get_opcode()+AAedion.get_rs()+AAedion.get_rt()+AAedion.get_rd()+AAedion.get_shamt()+AAedion.get_function());
    }
}
