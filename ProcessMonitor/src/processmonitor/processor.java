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


public class processor extends TimerTask{

    
                
            String process = null;
            ArrayList<String> processes = new ArrayList<>();

            
            double idleThreshold = .4;
            double oldValue = 0;
            double newValue = 0;
            boolean oldValueSet = false;
            boolean isActive;
            Runtime runtime = Runtime.getRuntime();
            String cmds = "powershell.exe \"get-process netbeans64 | Format-Table CPU\"";
            ArrayList<String> lines = new ArrayList<>();
            
            public void startMonitoring(){
              java.util.Timer timer = new java.util.Timer();
              
              timer.scheduleAtFixedRate(this, 0, 2000);
                
            }


    
    
    
    public String getCPUTime(){
        String cpuValue = "";
        for(String line : lines){
            if(line.contains("---")){
                cpuValue = lines.get(lines.indexOf(line) + 1);
            }
        }
        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println(cpuValue);
        System.out.println("");
        System.out.println("");
        System.out.println("");
        return cpuValue;
    }



    @Override
    public void run() {

                        try{
                            Process proc = runtime.exec(cmds);
                            InputStream inputstream = proc.getInputStream();
                            InputStreamReader reader = new InputStreamReader(inputstream);
                            BufferedReader bufferedReader = new BufferedReader(reader);
                            String line;
                            while((line = bufferedReader.readLine()) != null)
                                lines.add(line);

                            }catch(Exception err){

                            }

                            String cpuTime = getCPUTime();

                            System.out.println("Pre: Old: " + oldValue + "| New: " + newValue);
                            // if this is first iteration, set the old time
                            if(!oldValueSet){
                                oldValue = Double.valueOf(cpuTime);
                                oldValueSet = true;
                            } else {
                                System.out.println("Before Casting " + cpuTime);
                                newValue = Double.valueOf(cpuTime);
                                System.out.println("After Casting " + newValue);

                                
                                if(isActive()){
                                    isActive = true;
                                    oldValue = newValue;
                                } else{
                                    isActive = false;
                                }
                            }
                            
                           
                            System.out.println("Post: Old: " + oldValue + "| New: " + newValue);
                            System.out.println("Active Status = " + isActive);
                            try{
                                runtime.exec("powershell.exe \"cls\"");
                            }catch(Exception err){}
                            
                            
    }
}
