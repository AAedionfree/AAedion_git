package control_floor_java;

import java.util.HashMap;

public class Forward {
    private HashMap Reg_info= new HashMap();
    private HashMap Forward_info = new HashMap();

    //用常量来定义优先级 值越大 优先级越高

    //转发到D级(分别可以从E,M,W级转发)
    private final int EtoD_PC4=3;
    private final int MtoD_ALU_result=2;
    private final int WtoD_busW=1;
    //转发到E级(分别可以从M,W级转发)
    private final int MtoE_ALU_result=2;
    private final int WtoE_busW=1;
    //转发到M级(可以从W级转发)
    private final int WtoM_busW=1;
    //不转发
    private final int SELF=0;

    //数据
    private String A1_D,A2_D;
    private String A1_E,A2_E;
    private String A2_M;
    private String A3_E,A3_M,A3_W;
    private int RegWrite_E,RegWrite_M,RegWrite_W;

    public void run(HashMap Reg_info,int Tnew_E,int Tnew_M){
        init(Reg_info);

        //D级转发
        int F_RF_RD1=
                (((A1_D.equals(A3_E))&&(Tnew_E==0)&&RegWrite_E==1)&&(!A3_E.equals("00000")))? EtoD_PC4:   //PC4
        (((A1_D.equals(A3_M))&&(Tnew_M==0)&&RegWrite_M==1)&&(!A3_M.equals("00000")))? MtoD_ALU_result:
        (((A1_D.equals(A3_W))&&RegWrite_W==1)&&(!A3_W.equals("00000")))? WtoD_busW: SELF;

        int F_RF_RD2=
                (((A2_D.equals(A3_E))&&(Tnew_E==0)&&RegWrite_E==1)&&(!A3_E.equals("00000")))? EtoD_PC4:   //PC4
          (((A2_D.equals(A3_M))&&(Tnew_M==0)&&RegWrite_M==1)&&(!A3_M.equals("00000")))? MtoD_ALU_result:
          (((A2_D.equals(A3_W))&&RegWrite_W==1)&&(!A3_W.equals("00000")))? WtoD_busW: SELF;

        //E级转发
        int F_busA_E=
                ((A1_E.equals(A3_M))&&(Tnew_M==0)&&RegWrite_M==1&&(!A3_M.equals("00000")))? MtoE_ALU_result:
        ((A1_E.equals(A3_W))&&RegWrite_W==1&&(!A3_W.equals("00000")))? WtoE_busW: SELF;

        int F_busB_E=
                ((A2_E.equals(A3_M))&&(Tnew_M==0)&&RegWrite_M==1&&(!A3_M.equals("00000")))? MtoE_ALU_result:
        ((A2_E.equals(A3_W))&&RegWrite_W==1&&(!A3_W.equals("00000")))? WtoE_busW: SELF;

         //M级转发
        int F_busB_M=((A2_M.equals(A3_W))&&RegWrite_W==1&&(!A3_W.equals("00000")))? WtoM_busW : SELF;

        Forward_info.put("F_RF_RD1",F_RF_RD1);
        Forward_info.put("F_RF_RD2",F_RF_RD2);
        Forward_info.put("F_busA_E",F_busA_E);
        Forward_info.put("F_busB_E",F_busB_E);
        Forward_info.put("F_busB_M",F_busB_M);
    }
    private void init(HashMap Reg_info){
        A1_D=Reg_info.get("A1_D").toString();
        A2_D=Reg_info.get("A2_D").toString();

        A1_E=Reg_info.get("A1_E").toString();
        A2_E=Reg_info.get("A2_E").toString();

        A3_E=Reg_info.get("A3_E").toString();
        A2_M=Reg_info.get("A2_M").toString();
        A3_W=Reg_info.get("A3_W").toString();
        A3_M=Reg_info.get("A3_M").toString();

        RegWrite_E=Integer.parseInt(Reg_info.get("RegWrite_E").toString());
        RegWrite_M=Integer.parseInt(Reg_info.get("RegWrite_M").toString());
        RegWrite_W=Integer.parseInt(Reg_info.get("RegWrite_W").toString());

    }
    public HashMap getForward_info(){
        return Forward_info;
    }
}
