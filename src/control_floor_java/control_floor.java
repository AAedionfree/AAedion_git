package control_floor_java;

import cpu_datapath_java.Register;
import cpu_datapath_java.instruction;

import java.util.HashMap;

public class control_floor {
    //data(input) 与数据通路之间的交互
    private int clock,reset;
    private instruction instruction_control_floor;
    private int RegWrite_E,RegWrite_M,RegWrite_W;
    private String A1_D,A2_D;
    private String A1_E,A2_E;
    private String A2_M;
    private String A3_E,A3_M,A3_W;
    //module  控制层部件
    private ATcode atcode;
    private Forward forward;
    private  main_control main_control;
    private produce_stall produce_stall;
    private Stall_judge stall_judge;
    //temp
    private int Tnew_E,Tnew_M,Tnew_E_D,Tnew_M_D,Tnew_E_E,Tnew_M_E,Tnew_M_M;
    private int Tuse_rs,Tuse_rt,Tuse_rs_D,Tuse_rt_D;
    //控制层流水线寄存器
    private Register pipeline_D = new Register();
    private Register pipeline_M = new Register();
    private Register pipeline_W = new Register();

    private HashMap Reg_data=new HashMap();
    //控制层输出
    //output
    private HashMap control_list = new HashMap();
    control_floor(){
        //初始化模块

        //主控制器
        main_control = new main_control();
        //AT编码器
        atcode = new ATcode();
        //转发控制器
        forward = new Forward();
        //暂停判断器
        stall_judge = new Stall_judge();
        //暂停产生器
        produce_stall = new produce_stall();

    }

    private void init(HashMap datapath_info){
        instruction_control_floor = new instruction(datapath_info.get("instruction").toString());

        A1_D=datapath_info.get("A1_D").toString();
        A2_D=datapath_info.get("A2_D").toString();

        A1_E=datapath_info.get("A1_E").toString();
        A2_E=datapath_info.get("A2_E").toString();

        A3_E=datapath_info.get("A3_E").toString();
        A2_M=datapath_info.get("A2_M").toString();
        A3_W=datapath_info.get("A3_W").toString();
        A3_M=datapath_info.get("A3_M").toString();

        RegWrite_E=Integer.parseInt(datapath_info.get("RegWrite_E").toString());
        RegWrite_M=Integer.parseInt(datapath_info.get("RegWrite_M").toString());
        RegWrite_W=Integer.parseInt(datapath_info.get("RegWrite_W").toString());

    }
    public void run(HashMap datapath_info){
        init(datapath_info);

        //主控制器
        main_control.run(
                instruction_control_floor.get_opcode(),
                instruction_control_floor.get_function()
        );
        HashMap main_control_result=main_control.get_control_list();
        control_list.putAll(main_control_result);

        //AT编码器
        atcode.run(
                instruction_control_floor.get_opcode(),
                instruction_control_floor.get_function()
        );
        HashMap atcode_list=atcode.get_T_list();
        control_list.putAll(atcode_list);

        //转发控制
        forward.run(
                datapath_info,
                Integer.parseInt(atcode.get_T_list().get("T_new_E").toString()),
                Integer.parseInt(atcode.get_T_list().get("T_new_M").toString())
        );

        //暂停控制
        stall_judge.run(
                instruction_control_floor,
                A3_E,
                A3_M,
                RegWrite_E,
                RegWrite_M,
                datapath_info
        );

    }
    private HashMap get_control_list(){
        return control_list;
    }
}
