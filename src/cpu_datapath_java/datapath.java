package cpu_datapath_java;

public class datapath {
    public instruction[] Instruction = new instruction[1024];
    private IFU ifu;
    private  ALU alu;
    private DM dm;
    private GRF grf;
    //传入 control
    public void init(){
        this.ifu = new IFU();
        this.alu = new ALU();
        this.dm = new DM();
        this.grf = new GRF();
    }
    public void run_D(){

    }
    public void run_E(){

    }
    public void run_M(){

    }
    public void run_W(){

    }
}
