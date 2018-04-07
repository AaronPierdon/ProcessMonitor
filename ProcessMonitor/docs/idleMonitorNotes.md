# Idle Monitor Notes


create a program that encapsulates an updated list of windows processes and sets a variable for each process called isActive. As the module monitors the 
windows processes, the list of processes in the program is updated to represent the idle/active state of each process. Other programs should be able to query:

list of processes
list of active processes
list of idle processes
time each processes has been active without interruption since the program started monitoring


## AI

maybe AI can determine if a process is idle by analyzing the peak cpu usage and the current cpu usage. If the current cpu usage is only 10% or less than the peak cpu usage, than maybe that means the program/process utilization is so minimal, it must be idle.

1. get peak
2. get frequence of change 
3. get average change
4. get lowest value in the series of analyses

set the idle threshold to the lowest value and take into account the peak and average change.