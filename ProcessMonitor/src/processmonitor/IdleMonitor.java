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


public class IdleMonitor extends TimerTask{

    
                
            String process = null;
            ArrayList<String> processes = new ArrayList<>();

            double oldValue = 0;
            double newValue = 0;
            double idleThreshold = .2;
            boolean oldValueSet = false;
            boolean isActive;
            Runtime runtime = Runtime.getRuntime();
            String cmds = "powershell.exe \"get-process netbeans64 | Format-Table CPU\"";
            ArrayList<String> lines = new ArrayList<>();

            
    public void startMonitoring(){
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(this, 0, 2000);


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
    public boolean isActive(){

        
        
    if((newValue - oldValue) >  idleThreshold)
            return true;
        else 
            return false;
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

    @Override
    public void run() {
        if(!oldValueSet){
            oldValue = getCPUTime();
            newValue = oldValue;
            oldValueSet = true;
        } else {
            newValue = getCPUTime();

            

            if(isActive()){
                isActive = true;
                oldValue = newValue;
            } else{
                isActive = false;
            }
        }
        
        try{
            runtime.exec("cls");
        
        }catch(Exception err){}
        
        System.out.println("Process Active = " + isActive);
        
    }
        
                       
                            
                            
}

