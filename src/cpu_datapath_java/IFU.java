package cpu_datapath_java;

import java.util.List;

public class IFU {
    //最多存储1024条指令
    //12288代表mips pc起始地址 对应16进制0x3000
    private List<String> IM = null;
    private int PC= 12288;
    //异步复位
    private void print_IM(){
        System.out.println(IM);
    }
    public List<String> getall(){
        return IM;
    }
    public void init(){
        read_file read = new read_file();
        IM=read.read_text_txt();
    }
    public void reset(){
        this.PC = 12288;
    }
    //PCsrc为1对应 B型指令 发生 PC_jump为1 对应J型指令发生
    public void change_pc(int PCsrc,int PCBranch,int PC_jump,int reg_toPC){
        if(PCsrc == 1){
            this.PC = PCBranch;
        }
        else if(PC_jump == 1){
            this.PC = reg_toPC;
        }
        else{
            PC = PC + 4;
        }
    }
    public int get_PC(){
        return PC;
    }
    public instruction get_instruction(){
        instruction AAedion = new instruction(IM.get((PC-12288)/4));
        return AAedion;
    }

    public static void main(String[] args) {
        IFU ifu = new IFU();
        ifu.init();
        System.out.println(ifu.getall().size());
    }
}
