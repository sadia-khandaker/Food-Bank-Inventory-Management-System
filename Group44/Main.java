package edu.ucalgary.ensf409;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Main class for the program that gets user input and creates a new hamper based on the user input, and their needs.
 * It is a GUI application that allows the user to input the number of males, number of females, number of children over 8,
 * and number of children under 8.
 *
 * @author Maarya Ahmad <a href="mailto:maarya.ahmed@ucalgary.ca">maarya.ahmed@ucalgary.ca</a>
 * @author Sadia Khandaker <a href="mailto:khandaker.tahsin@ucalgary.ca">khandaker.tahsin@ucalgary.ca</a>
 * @author Noor Nawaz <a href="mailto:noor.nawaz@ucalgary.ca">noor.nawaz@ucalgary.ca</a>
 * @author Tamunomiete Brown <a href="mailto:tamunomiete.brown@ucalgary.ca">tamunomiete.brown@ucalgary.ca</a>
 * @version 1.1
 * @since 1.0
 */

 //this class acts as our GUI and interacts with the user and asks for all the inputs as well as throws
 //error messages when food runs out or we have surplus amount of people in a hamper
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        UIManager.put("OptionPane.background", new java.awt.Color(173, 223, 255));
        UIManager.put("Panel.background", new java.awt.Color(173, 223, 255));
        PrintStream writer = new PrintStream ("output.txt"); //creates a new file called output.txt
        writer.println ("Example Food Bank"); //prints the name of the food bank to the file
        writer.println ("Hamper Order Form" + "\n"); //prints the name of the form to the file
        String name = JOptionPane.showInputDialog ("Please enter your name."); //asks the user for their name
        writer.println ("Name: " + name); //prints the name to the file
        writer.println ("Date " + LocalDate.now ().format (DateTimeFormatter.ofPattern ("MM/dd/yyyy")) + "\n"); //prints the date to the file
        int numberOfHampers = Integer.parseInt (JOptionPane.showInputDialog ("How many hampers would you like?")); //asks the user for the number of hampers they would like
        writer.println ("Hamper Quantity:" + numberOfHampers + "\n"); //prints the number of hampers to the file

        for (int i = 0; i < numberOfHampers; i++) {
            int numberOfMales = Integer.parseInt (JOptionPane.showInputDialog ("Please enter the number of males in your household.")); 
            // asks user for the number of males
            int numberOfFemales = Integer.parseInt (JOptionPane.showInputDialog ("Please enter the number of females in your household.")); 
            // ask user for the number of females
            int numberOfChildrenOver8 = Integer.parseInt (JOptionPane.showInputDialog ("Please enter the number of children over 8 in your household.")); 
            // ask user for the number of children over 8
            int numberOfChildrenUnder8 = Integer.parseInt (JOptionPane.showInputDialog ("Please enter the number of children under 8 in your household.")); 
            // ask user for the number of children under 8

            //if males AND females AND child over 8 AND child under 8 are all 0 in a hamper so the user doesnt have any humans
            //in a hamper, it will give an error message and terminate the program
            if((numberOfMales+numberOfFemales+numberOfChildrenOver8+numberOfChildrenUnder8) == 0)
            {
                JOptionPane.showMessageDialog (null, "You must have atleast one person in your hamper.");
                System.exit(-1);
            }

            //send the number of males, females, and children to the hamper class and checks if a hamper order is possible or not
            Hamper hamper = new Hamper (name, numberOfMales, numberOfFemales, numberOfChildrenOver8, numberOfChildrenUnder8); // creates a new hamper based on the user input
            //hamper is possible so it goes ahead and prints the food
            if(hamper.isOrderPossible ()){
                writer.println ("\nHamper " + (i + 1) + " Items:"); //print to output file
                System.out.println ("\nHamper " + (i + 1) + " Items:"); //prints to terminal
                hamper.getFoodComboPerHamper (); //gets the foodCombo form hamper class

                //iterates through the foodCombo method and prints to terminal and writes to output file
                for (String item : hamper.getFoodComboPerHamper ()) {
                    writer.println (item); // prints the food combo to the file
                    System.out.println (item); //print to terminal
                }
            }
            //hamper order is not possible so it displays a message and terminates the program
            else if(!hamper.isOrderPossible ()){
                hamper.calculateDeficit ();
                JOptionPane.showMessageDialog (null, "Sorry, we do not have enough food to fulfill your order.");
                System.exit(-1);

            }

        }

    }
}
