package control_floor_java;
//生成暂停信号
public class produce_stall {
    //分别代表
    // D级流水线寄存器使能端
    // E级流水线寄存器是否清零
    // PC是否可以变化
    public boolean D_en,E_clr,PC_en;

    public void produce(boolean stall){
        D_en = !stall;
        E_clr = stall;
        PC_en = !stall;
    }

    public boolean get_D_en(){
        return D_en;
    };
    public boolean get_E_clr(){
        return E_clr;
    };
    public boolean get_PC_en(){
        return PC_en;
    };
}
