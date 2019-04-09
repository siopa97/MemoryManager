//David Siopa
//This class is the parent to both First Fit and Best Fit but all the methods for both classes are
//already defined in this class. The classes are there to help differentiate between the two while working in
//the main class
import java.util.Scanner;


public class Algorithim//this class will define the basic methods used by the specific algorithims
{
    private Memory[] mem = new Memory[10];
    private int totalMemory;
    private int maxMemory = 5000;
    private int[] holes = new int[10];
    private int count = 0;
    
    public Algorithim()
    {
        
    }
    
   public void init()//Initializes the memory array so that it can then be filled
    {
        
        for(int i = 0; i < mem.length; i++)
        {
            Memory m = new Memory(0,0);
            mem[i] = m;
        }
    }
    
    
    public void remove()//used to remove a process from memory
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the process ID you would like to remove");
        int id = in.nextInt();
        
        for(int i = 0; i < mem.length; i++)
        {
            if(mem[i].getPID() == id)
            {  
                mem[i].setHole(mem[i].getProcessSize());
                mem[i].setProcessSize(0);
                mem[i].setPID(0);
                count--;
            }
        }
        
        print();
    }
    
    public void compact()//used to compact the memory
    {
        for(int j = 0; j < mem.length; j++)
        {
        for(int i = 0; i < mem.length; i++)
        {
            if(i == 9 && mem[i].getProcessSize() == 0)
            {
                mem[i].setHole(0);
            }
            else if(mem[i].getProcessSize() == 0)
            {
                Memory temp = mem[i];
                mem[i] = mem[i+1];
                mem[i+1] = temp;
            }
            mem[i].setHole(0);
        }
        }
        print();
    }
    
    public void print()//used to print out the memory to the console
    {
        System.out.println("\n**************************Memory**************************\n");
        for(int i = 0; i < mem.length; i++)
        {
            if(mem[i].getHole() == -1)
            {
                System.out.println("Address: " + i 
                                + "  Process ID: " + mem[i].getPID()
                                + "  Process Size: " + mem[i].getProcessSize() 
                                + "    Hole Size: " + 0);
            }
            else
            {
                System.out.println("Address: " + i 
                                + "  Process ID: " + mem[i].getPID()
                                + "  Process Size: " + mem[i].getProcessSize() 
                                + "  Hole Size: " + mem[i].getHole());
            }
        }
    }
    
 
        public void FirstFitInsert()//Insert method for the First Fit  
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter 1: to enter the process size manually "
                        + "\nEnter 2: for a random process size");
        int choice = in.nextInt();
        
        if (choice == 1)//Manually entered process size
        {
            Memory m = new Memory();
            m.input();
            
            if((totalMemory + m.getProcessSize()) <= maxMemory && count < 10)
            {
                inputFF(m);
                count++;
            }
            else
            {
                System.out.println("\nThere is not enough memory for this process!\n");
            }
        } 
        else if (choice == 2)//Randomly entered porocess size
        {
            Memory m = new Memory();
            m.inputRand();//method defined in Memory class to give a random process size
            
            if((totalMemory + m.getProcessSize()) <= maxMemory &&  count < 10)
            {
                inputFF(m);
                count++;
            }
            else
            {
                System.out.println("\nThere is not enough memory for this process!\n");
            }
        }
    }
    
    public void BestFitInsert()//Insert Method for Best Fit
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter 1: to enter the process size manually "
                        + "\nEnter 2: for a random process size");
        
        int choice = in.nextInt();
        
        if (choice == 1)//Manually entered process size
        {
            Memory m = new Memory();
            m.input();
            holeSort();
            
            if((totalMemory + m.getProcessSize()) <= maxMemory && count < 10)
            {
                inputBF(m);
                count++;
            }
            else
            {
                System.out.println("\nThere is not enough memory for this process!\n");
            }
             
        } 
        else if (choice == 2)//Randomly entered porocess size
        {
            Memory m = new Memory();
            m.inputRand();//method defined in Memory class to give a random process size
            holeSort();
            
            if((totalMemory + m.getProcessSize()) <= maxMemory && count < 10)
            {
                inputBF(m);
                count++;
            }
            else
            {
                System.out.println("\nThere is not enough memory for this process!\n");
            }
        }
    }
    
    public void inputFF(Memory m)//input method for First Fit
    {
        for (int i = 0; i < mem.length; i++) 
        {
            if(mem[i].getProcessSize() == 0 && (mem[i].getHole() == -1 || mem[i].getHole() > m.getProcessSize()))
            {     
                if(mem[i].getHole() > 0)
                {
                    int newHole = mem[i].getHole() - m.getProcessSize();
                    
                    totalMemory += m.getProcessSize();
                    mem[i] = m;
                    mem[i].setHole(newHole);
                    
                }
                else
                {
                    totalMemory += m.getProcessSize();
                    mem[i] = m;
                    mem[i].setHole(0);
                }
                        
                print();
                break; 
            }
            else if(mem[i].getProcessSize() > 0 && mem[i].getHole() >= m.getProcessSize())
            {
                for(int j = mem.length-1; j > i+1; j--)
                {
                    mem[j] = mem[j-1];       
                }
                
                int newHole = mem[i].getHole() - m.getProcessSize();
                mem[i].setHole(0);
                totalMemory += m.getProcessSize();
                mem[i+1] = m;
                mem[i+1].setHole(newHole);
                
                print();
                break; 
            }
                     
        }
    }
    
    public void inputBF(Memory m)//input method for Best Fit
    {
        for (int i = 0; i < holes.length; i++) 
        {
            if(holes[i] >= m.getProcessSize() || holes[i] == -1)
            {
                for(int j = 0; j < mem.length; j++)
                {
                    if(mem[j].getHole() == holes[i] && mem[j].getHole() > 0)//finds the best fit hole for the process
                    {
                        if(mem[j].getProcessSize() > 0)//If a process is already in memory but has a hole that can be filled
                        {
                            for(int f = mem.length-1; f > j+1; f--)
                            {
                                mem[f] = mem[f-1];
                            }
                            int newHole = mem[j].getHole() - m.getProcessSize();
                            mem[j].setHole(0);
                            totalMemory += m.getProcessSize();
                            mem[j+1] = m;
                            mem[j+1].setHole(newHole);
                            break;
                            
                        }
                        else
                        {
                            int newHole = mem[j].getHole() - m.getProcessSize();
                            totalMemory += m.getProcessSize();
                            mem[j] = m;
                            mem[j].setHole(newHole);
                            break;
                        }
                    }
                    else if(mem[j].getHole() == -1)//if its a new block of memory and the process doesnt fit in an open hole
                    {
                        totalMemory += m.getProcessSize();
                        mem[j] = m;
                        mem[j].setHole(0);
                        break;
                    } 
                }
                print();
                break;
            }            
        }
    }
    
    public void holeSort()//Sorts an integer array of all the holes in the memory from least to greatest
                          //ignoring any empty blocks of memory(those that are flagged with a -1)
    {
        for(int i = 0; i < mem.length; i++)
        {
            holes[i] = mem[i].getHole();
        }
        
        for(int i = 0; i < holes.length; i++)
        {
            for(int j = 0; j < holes.length; j++)
            {
                if(holes[j] < holes[i])
                {
                    int temp = holes[j];
                    holes[j] = holes[i];
                    holes[i] = temp;
                }
            }
        }
        
        for(int i = 0; i < holes.length; i++)
        {
            for(int j = 0; j < holes.length; j++)
            {
                if(holes[j] > holes[i] && (holes[j] > 0 && holes[i] > 0))
                {
                    int temp = holes[j];
                    holes[j] = holes[i];
                    holes[i] = temp;
                }
            }
        }
        
        for(int i = 0; i < holes.length; i++)
        {
            System.out.println("Holes " + holes[i]);
        }
    }
}
