/*
 * Developers: Aaron Pierdon
 * Date: Apr 4, 2018
 * Description :
 * 
 */
package processmonitor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *
 * @author Aaron
 */
public class Processes {


            
    public static void main(String[] args) {
        IdleMonitor idleMontior = new IdleMonitor();
        idleMontior.startMonitoring();
    }
    
}
