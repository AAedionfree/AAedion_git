package cpu_datapath_java;
//存储器DM
public class DM {
    //最大存储为 1024*32 bit
    private int[] DataMem = new int[1024];
    //从存储器中读取数据
    //construction 用处为初始化 模拟正常存储器行为
    DM(){
        int i=0;
        for(i=0;i<1024;i++){
            DataMem[i] = 0;
        }
    }
    public int read(int offset){
        return DataMem[offset];
    }
    //permission为写使能
    public void writer(int permission,int value,int offset){
        if(permission==1){
            DataMem[offset]=value;
        }
    }
    public static void main(String[] args) {

    }
}
