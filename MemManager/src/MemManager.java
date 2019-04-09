//Colaboration of David Siopa and Chris Callen
import java.util.Scanner;


public class MemManager 
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        int choice = 0;//used to make a selectionn for the memory manager to be used
        int selection = 0;//used for the second do while to add, remove, compact and show the memoryr block
        
        do
        {   //First Menu Choice for the user to pick a Memory Manager
            System.out.println("\nChose your Memory Manger:\n"
                                + "Enter 1: First Fit\n"
                                + "Enter 2: Best Fit\n"
                                + "Enter 3: Quit The Program");
            choice = in.nextInt();
            
            if(choice == 1)//First Fit
            {
                FirstFit ff = new FirstFit();
                ff.init();
                do
                {
                    System.out.println("\nEnter 1: Add a Process to Memory\n"
                                        + "Enter 2: Remove a Process from Memory\n"
                                        + "Enter 3: Compact Memory\n"
                                        + "Enter 4: Print Out Memory\n"
                                        + "Enter 5: Exit First Fit Simulator");
                    selection = in.nextInt();
                    
                    if(selection == 1)//Add a Process into Memory
                    {
                        ff.FirstFitInsert();
                    }
                    else if(selection == 2)//Remove a Process from Memory
                    {
                        ff.remove();
                    }
                    else if(selection == 3)//Compact Memory
                    {
                        ff.compact();
                    }
                    else if(selection == 4)//Print Memory
                    {
                        ff.print();//testing!!!!
                    }
                     else if(selection == 5)//Quit the Program
                    {
                        //Do Nothing
                    }
                    else
                    {
                        System.out.println("Invalid Input! Try Again.\n");
                    }
                }while(selection != 5);
            }
            else if(choice == 2)//Best Fit
            {
                BestFit bf = new BestFit();
                bf.init();
                do
                {
                    System.out.println("\nEnter 1: Add a Process to Memory\n"
                                        + "Enter 2: Remove a Process from Memory\n"
                                        + "Enter 3: Compact Memory\n"
                                        + "Enter 4: Print Out Memory\n"
                                        + "Enter 5: Exit First Fit Simulator");
                    selection = in.nextInt();
                    
                    if(selection == 1)//Add a Process into Memory
                    {
                        bf.BestFitInsert();
                    }
                    else if(selection == 2)//Remove a Process from Memory
                    {
                        bf.remove();
                    }
                    else if(selection == 3)//Compact Memory
                    {
                        bf.compact();
                    }
                    else if(selection == 4)//Print Memory
                    {
                        bf.print();
                    }
                    else if(selection == 5)//Exit Best Fit Simulator
                    {
                        //Do Nothing
                    }
                    else
                    {
                        System.out.println("Invalid Input! Try Again.\n");
                    }
                }while(selection != 5);
            }
            else if(choice == 3)//Quit The Program
            {
                //Do Nothing
            }
            else//If an invalid choice is picked
            {
                System.out.println("Invalid Input! Try Again.\n");
            }
            
        }while(choice != 3);
    }
    
}
