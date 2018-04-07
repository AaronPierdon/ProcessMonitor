/*
 * Developers: Aaron Pierdon
 * Date: Apr 6, 2018
 * Description :
 * 
 */

package processmonitor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class testprocessor{

    
                
            String process = null;
            ArrayList<String> processes = new ArrayList<>();

            double oldValue = 0;
            double newValue = 0;
            boolean oldValueSet = false;
            boolean isActive;
            Runtime runtime = Runtime.getRuntime();
            String cmds = "powershell.exe \"get-process netbeans64 | Format-Table CPU\"";
            ArrayList<String> lines = new ArrayList<>();

            
    public void startMonitoring(){
        while(1==1)
          System.out.println(getCPUTime());


    }


    
    
    
    public String isolateCPULine(){
        String cpuValue = "";
        for(String line : lines){
            if(line.contains("---")){
                cpuValue = lines.get(lines.indexOf(line) + 1);
            }
        }

        lines.clear();
        return cpuValue;
    }



   
    public double getCPUTime(){
                 try{
                    Process proc = runtime.exec(cmds);
                    InputStream inputstream = proc.getInputStream();
                    InputStreamReader reader = new InputStreamReader(inputstream);
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    String line;
                    while((line = bufferedReader.readLine()) != null)
                        lines.add(line);

                    }catch(Exception err){
                        err.printStackTrace();
                    }

                    String cpuTime = isolateCPULine();

                    return Double.valueOf(cpuTime);
        
    }
        
                       
                            
                            
}

