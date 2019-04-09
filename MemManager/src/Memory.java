//Chris Callen
import java.util.Random;
import java.util.Scanner;

public class Memory//This will define what each memory block should hold
{
    private int PID;//Process ID
    private int processSize;//Size of the memory block
    private int hole = -1;//Set at -1 to use as a flag to show that the block is empty
    
    public Memory()
    {
        //No parameter constructor
    }
    
    public Memory(int processSize, int PID)//Constructor that accepst the process size and PID
    {
        this.processSize = processSize;
        this.PID = PID;
    }
    
    public void input()//Prompts the user to input a PID and Process size then assigns it to memory
    {
        Scanner in = new Scanner(System.in);
        
        System.out.println("Enter the process ID");;
        this.PID = in.nextInt();
        
        System.out.println("Enter the process size");
        this.processSize = in.nextInt(); 
        
    }
    
    public void inputRand()//Prompts the user to input a PID, and then Process size is randomly generated. Then assigns it to memory
    {
        Scanner in = new Scanner(System.in);
        
        System.out.println("Enter the process ID");
        this.PID = in.nextInt();
        
        Random rand = new Random();
        int pSize = rand.nextInt(500) + 100;//Creates a random process size between 100 and 600
        this.processSize = pSize; 
    }
    
    public int getProcessSize()//returns the process size when called
    {
        return this.processSize;
    }
    
    public int getPID()//returns the PID when called
    {
        return this.PID;
    }
    
    public int getHole()//returns the hole size when called
    {
        return this.hole;
    }
    
    public void setHole(int hole)//sets the hole to desired size
    {
        this.hole = hole;
    }
    
    public void setProcessSize(int processSize)//sets the process to desired size
    {
        this.processSize = processSize;
    }
    
    public void setPID(int PID)//sets the PID
    {
        this.PID = PID;
    }
}
