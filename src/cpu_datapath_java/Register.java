package cpu_datapath_java;

import java.util.HashMap;

//流水线寄存器

public class Register {
    private HashMap value = new HashMap();

    public void clock_posedge_save(HashMap value) {
        this.value.putAll(value);
    }

    //输出寄存器当前值
    public HashMap Register_out() {
        return value;
    }

    public void put(String key, int num) {
        value.put(key, num);
    }

    public int get_register_num(String key) {
        return Integer.parseInt(value.get(key).toString());
    }

    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put("AAedion", 100);
        Register pipe_line_D = new Register();
        pipe_line_D.clock_posedge_save(map);
        //System.out.println(pipe_line_D.Register_out());

    }
}
