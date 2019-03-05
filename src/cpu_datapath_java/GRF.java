package cpu_datapath_java;


public class GRF {
    private Register[] grf = new Register[32];
    //construction function
    GRF() {
        int i = 0;
        //init
        for (i = 0; i < 32; i++) {
            grf[i] = new Register();
            grf[i].put("GRF_"+i, 0);
            //System.out.println(grf[i].Register_out());
        }

    }
    private void print(){
        int i;
        for(i=0;i<32;i++){
            System.out.println(grf[i].Register_out());
        }
    }
    public void change_grf_value(int Register_num,int value) {
        //如果不是0号寄存器
        if(value != 0){
            grf[Register_num].put("GRF_"+Register_num,value);
        }
    }

    public static void main(String[] args) {
        GRF a = new GRF();
        a.change_grf_value(17,132);
        a.print();
    }
}
