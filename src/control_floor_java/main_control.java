package control_floor_java;

import java.awt.image.renderable.ContextualRenderedImageFactory;
import java.util.HashMap;

public class main_control {
    private HashMap control_list = new HashMap();
    //支持的指令
    Boolean R, ADDU, SUBU, NOP, ORI, LUI, LW, SW, JAL, JR, BEQ, ADDI, J, SLT, JALR, SLTI, BGTZ, LH;

    public void run(String opcode, String func) {
        //判断类型
        R = opcode.equals("000000");
        //判读是R型的具体那一条指令
        ADDU = R && func.equals("100001");
        SUBU = R && func.equals("100011");
        NOP = R && func.equals("100001");
        JR = R && func.equals("001000");
        SLT = R && func.equals("101010");
        JALR = R && func.equals("001001");
        //若不是R型指令 则判断该指令为非R型指令中的哪一个
        J = opcode.equals("000010");
        LH = opcode.equals("100001");
        SLTI = opcode.equals("001010");
        BGTZ = opcode.equals("000111");
        ADDI = opcode.equals("001000");
        ORI = opcode.equals("001101");
        LUI = opcode.equals("001111");
        LW = opcode.equals("100011");
        SW = opcode.equals("101011");
        JAL = opcode.equals("000011");
        BEQ = opcode.equals("000100");

        control_list.put("RegDst", ADDU || SUBU || SLT || JALR);
        control_list.put("RegWrite", ADDU || SUBU || ORI || LUI || LW || JAL || ADDI || SLT || JALR || SLTI || LH);
        control_list.put("ALUsrc", ORI || LUI || LW || SW || ADDI || BGTZ || SLTI || LH);
        control_list.put("Branch", BEQ || BGTZ);
        control_list.put("MemWrite", SW);
        control_list.put("MemToReg", LW || LH);
        control_list.put("ext_op", LW || SW || BEQ || ADDI || BGTZ || SLTI || LH);
        control_list.put("ext_result", LUI);
        control_list.put("ALUop", "" + ((SUBU || BEQ) ? 1 : 0)
                + ((ADDU || SUBU || LUI || LW || SW || BEQ || ADDI || SLT || SLTI || LH) ? 1 : 0)
                + ((ORI || SLT || SLTI) ? 1 : 0));
        control_list.put("Branch_equal", BEQ);
        control_list.put("jal", JAL || J);
        control_list.put("Write_PC", JAL || JALR);
        control_list.put("PC_jump", JAL || JR || J || JALR);
        control_list.put("RegToPC", JR || JALR);
    }

    public HashMap get_control_list() {
        return control_list;
    }

    public static void main(String[] args) {
        main_control control = new main_control();
        control.run("000000", "100001");
        System.out.println(control.get_control_list());
    }
}
