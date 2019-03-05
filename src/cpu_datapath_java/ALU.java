package cpu_datapath_java;

public class ALU {
    private int result = 0;

    //ALU_OP为运算编码
    public int get_result(String ALU_OP, int ALU_input_1, int ALU_input_2) {
        switch (ALU_OP) {
            case "000":
                result = plus(ALU_input_1, ALU_input_2);
            case "001":
                result = sub(ALU_input_1, ALU_input_2);
            case "010":
                result = xor(ALU_input_1, ALU_input_2);
            case "111":
                result = or(ALU_input_1, ALU_input_2);
            case "110":
                result = xor(ALU_input_1, ALU_input_2);
        }
        return result;
    }

    private int plus(int input_1, int input_2) {
        return input_1 + input_2;
    }

    private int sub(int input_1, int input_2) {
        return input_1 - input_2;
    }

    private int xor(int input_1, int input_2) {
        return input_1 ^ input_2;
    }

    private int or(int input_1, int input_2) {
        return input_1 | input_2;
    }

    private int and(int input_1, int input_2) {
        return input_1 & input_2;
    }
}
