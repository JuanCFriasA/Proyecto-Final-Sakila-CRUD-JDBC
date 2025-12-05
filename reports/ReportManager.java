
package reports;

import java.io.FileWriter;
import java.util.*;
import models.*;

public class ReportManager {

    public <T> void exportCSV(List<T> list, String path){
        try{
            FileWriter fw = new FileWriter(path);
            for(var item: list){
                fw.write(item.toString() + "\n");
            }
            fw.close();
        }catch(Exception e){e.printStackTrace();}
    }

    public <T> void exportJSON(List<T> list, String path){
        try{
            FileWriter fw = new FileWriter(path);
            fw.write("[\n");
            for(int i=0;i<list.size();i++){
                fw.write("  \"" + list.get(i).toString() + "\"");
                if(i < list.size()-1) fw.write(",");
                fw.write("\n");
            }
            fw.write("]");
            fw.close();
        }catch(Exception e){e.printStackTrace();}
    }
}
        
