package control_floor_java;

import cpu_datapath_java.instruction;

import java.util.HashMap;

public class Stall_judge {
    private boolean stall;
    private boolean stall_rs,stall_rt;
    private boolean stall_rs0_e1,stall_rs0_e2,stall_rs0_m1,stall_rs1_e2;
    private boolean stall_rt0_e1,stall_rt0_e2,stall_rt0_m1,stall_rt1_e2;
    private int Tnew_E,Tnew_M,Tuse_rs,Tuse_rt;
    private String A1,A2;
    public void run(
            instruction instruction_control_D,
            String A3_E,
            String A3_M,
            int W_E,
            int W_M,
            HashMap T_list
    ){
        //init
        A1=instruction_control_D.get_rs();
        A2=instruction_control_D.get_rt();

        Tnew_E = Integer.parseInt(T_list.get("T_new_E").toString());
        Tnew_M = Integer.parseInt(T_list.get("T_new_E").toString());
        Tuse_rs = Integer.parseInt(T_list.get("T_use_rs").toString());
        Tuse_rt = Integer.parseInt(T_list.get("T_use_rt").toString());

        stall_rs0_e1=(Tuse_rs==0)&&(Tnew_E==1)&&(A1.equals(A3_E))&&W_E==1;
        stall_rs0_e2=(Tuse_rs==0)&&(Tnew_E==2)&&(A1.equals(A3_E))&&W_E==1;
        stall_rs0_m1=(Tuse_rs==0)&&(Tnew_M==1)&&(A1.equals(A3_M))&&W_M==1;
        stall_rs1_e2=(Tuse_rs==1)&&(Tnew_E==2)&&(A1.equals(A3_E))&&W_E==1;

        stall_rt0_e1=(Tuse_rt==0)&&(Tnew_E==1)&&(A2.equals(A3_E))&&W_E==1;
        stall_rt0_e2=(Tuse_rt==0)&&(Tnew_E==2)&&(A2.equals(A3_E))&&W_E==1;
        stall_rt0_m1=(Tuse_rt==0)&&(Tnew_M==1)&&(A2.equals(A3_M))&&W_M==1;
        stall_rt1_e2=(Tuse_rt==1)&&(Tnew_E==2)&&(A2.equals(A3_E))&&W_E==1;

        stall_rs=stall_rs0_e1 ||
                 stall_rs0_e2 ||
                 stall_rs0_m1 ||
                 stall_rs1_e2;

        stall_rt=stall_rt0_e1 ||
                 stall_rt0_e2 ||
                 stall_rt0_m1 ||
                 stall_rt1_e2;

        stall=stall_rs||stall_rt;
    }
    public boolean get_stall(){
        return stall;
    }

}
