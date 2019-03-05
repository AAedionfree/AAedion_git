package cpu_datapath_java;

public class MUX {
    private int[] select_list;
    //construction
    MUX(int[] select_list){
        this.select_list = select_list;
    }
    //get number
    public int select(int[] select_list,int select){
        this.select_list = select_list;
        if(select<select_list.length){
            return select_list[select];
        }
        else {
            //发生错误
            return -1111;
        }
    }

    public static void main(String[] args) {

    }
}
