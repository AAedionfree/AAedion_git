package control_floor_java;

import javax.naming.ldap.Control;
import java.util.HashMap;
//控制流水线CPU的转发和暂停
public class ATcode {
    //作为常量 用来控制暂停stall
    private final int T_ALU =1;
    private final int T_DM = 2;
    private final int T_PC = 0;
    private HashMap T_list= new HashMap();
    Boolean R,ADDU,SUBU,NOP,ORI,LUI,LW,SW,JAL,JR,BEQ,ADDI,J,SLT,JALR,SLTI,BGTZ,LH;
    public void run(String opcode,String func){
        //判断类型
        R=opcode.equals("000000");
        //判读是R型的具体那一条指令
        ADDU=R&&func.equals("100001");
        SUBU=R&&func.equals("100011");
        NOP=R&&func.equals("100001");
        JR=R&&func.equals("001000");
        SLT=R&&func.equals("101010");
        JALR=R&&func.equals("001001");
        //若不是R型指令 则判断该指令为非R型指令中的哪一个
        J=opcode.equals("000010");
        LH=opcode.equals("100001");
        SLTI=opcode.equals("001010");
        BGTZ=opcode.equals("000111");
        ADDI=opcode.equals("001000");
        ORI=opcode.equals("001101");
        LUI=opcode.equals("001111");
        LW=opcode.equals("100011");
        SW=opcode.equals("101011");
        JAL=opcode.equals("000011");
        BEQ=opcode.equals("000100");

        init_forward();
        init_stall();
    }
    //初始化 转发控制的T
    private void init_forward(){
        if(ADDU||SUBU||LUI||ORI){
            T_list.put("T_new_E",T_ALU);
            T_list.put("T_new_M",T_ALU-1);
        }
        else if(LW){
            T_list.put("T_new_E",T_DM);
            T_list.put("T_new_M",T_DM-1);
        }
        else if(JAL){
            T_list.put("T_new_E",T_PC);
            T_list.put("T_new_M",T_PC);
        }
    }
    //初始化 暂停控制的T
    private  void init_stall(){
        if(ADDU||SUBU){
            T_list.put("T_use_rs",1);
            T_list.put("T_use_rt",1);
        }
        else if(LW||LUI||ORI){
            T_list.put("T_use_rs",1);
            T_list.put("T_use_rt",3);
        }
        else if(SW){
            T_list.put("T_use_rs",1);
            T_list.put("T_use_rt",2);
        }
        else if(JR){
            T_list.put("T_use_rs",0);
            T_list.put("T_use_rt",3);
        }
        else if(BEQ){
            T_list.put("T_use_rs",0);
            T_list.put("T_use_rt",0);
        }
    }
    public HashMap get_T_list(){
        return T_list;
    }
    public static void main(String[] args) {
        ATcode code = new ATcode();
        code.run("000000","100001");
        System.out.println(code.get_T_list());
    }
}
