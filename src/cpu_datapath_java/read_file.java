package cpu_datapath_java;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class read_file {
    private List<String> context = new ArrayList<String>();
    public List<String> read_text_txt(){
        try {
            String encoding="utf-8";
            File file=new File("C:\\Users\\ym500\\IdeaProjects\\AAedion\\src\\cpu_datapath_java\\test.txt");
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;//缓存字符串
                while(( lineTxt= bufferedReader.readLine()) != null){
                    context.add(lineTxt);
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return context;
    }
    public static void main(String[] args) {
        read_file read = new read_file();
        List<String> a =read.read_text_txt();
        System.out.println(a);
        System.out.println(a.get(0));
    }

}
