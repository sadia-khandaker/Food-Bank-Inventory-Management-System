

package edu.ucalgary.ensf409;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Hamper class. This class creates a hamper object that uses the number of males, females, and children and their needs.
 * This class will create a combination of foods that will fulfill the household's nutritional needs.
 *
 * @author Noor Nawaz <a href="mailto:noor.nawaz@ucalgary.ca">noor.nawaz@ucalgary.ca</a>
 * @author Sadia Khandaker <a href="mailto:khandaker.tahsin@ucalgary.ca">khandaker.tahsin@ucalgary.ca</a>
 * @author Maarya Ahmad <a href="mailto:maarya.ahmed@ucalgary.ca">maarya.ahmed@ucalgary.ca</a>
 * @author Tamunomiete Brown <a href="mailto:tamunomiete.brown@ucalgary.ca">tamunomiete.brown@ucalgary.ca</a>
 */


public class Hamper {
    private int numberOfAdultMales; // number of adult males
    private int numberOfAdultFemales;  // number of adult females
    private int numberOfChildrenOver8; // number of children over 8
    private int numberOfChildrenUnder8; // number of children under 8
    private int numberOfHampers; //how many hampers in order
    private AvailableFoodsManager availableFoodsInventory; //available foods
    private RegistrationForDailyClientNeeds registration; //number and type of clients
    private ArrayList<String> food = new ArrayList<String> (); //arraylist of food in hamper
    private String date; // date of order
    private String name; // name of client
    private int grainsNeeded; //total grain content needed for hamper
    private int fvNeeded; //total fv content needed for hamper
    private int proteinNeeded; //total protein content needed for hamper
    private int caloriesNeeded; //total calorie content needed for hamper
    private int otherNeeded; //total other content needed for hamper
    private int currentGrainContentInHamper; //used to calculate how many more grain we need in hamper
    private int currentFVContentInHamper; //used to calculate how many more fv we need in hamper
    private int currentProteinContentInHamper; //used to calculate how many more protein we need in hamper
    private int currentCaloriesInHamper; //used to calculate how many more calories we need in hamper
    private int currentOtherContentInHamper; //used to calculate how many more other we need in hamper

    /**
     * Constructs a hamper for a household with the specified number of adults and children.
     *
     * @param numberOfAdultMales     the number of adult males in a household
     * @param numberOfAdultFemales   the number of adult females in a household
     * @param numberOfChildrenOver8  the number of children over the age of 8
     * @param numberOfChildrenUnder8 the number of children under the age of 8
     */
    public Hamper(String name, int numberOfAdultMales, int numberOfAdultFemales, int numberOfChildrenOver8, int numberOfChildrenUnder8) {
        this.name = name;
        LocalDate today = LocalDate.now ();
        this.date = today.format (DateTimeFormatter.ofPattern ("MM/dd/yyyy"));
        this.numberOfAdultMales = numberOfAdultMales;
        this.numberOfAdultFemales = numberOfAdultFemales;
        this.numberOfChildrenOver8 = numberOfChildrenOver8;
        this.numberOfChildrenUnder8 = numberOfChildrenUnder8;
        this.numberOfHampers = 1;
        this.availableFoodsInventory = new AvailableFoodsManager ();
        this.registration = new RegistrationForDailyClientNeeds ();
    }

    /**
     * Constructs a hamper for a household with the specified number of adults and children.
     *
     * @param name
     * @param numberOfAdultMales
     * @param numberOfAdultFemales
     * @param numberOfChildrenOver8
     * @param numberOfChildrenUnder8
     * @param numberOfHampers
     */
    public Hamper(String name, int numberOfAdultMales, int numberOfAdultFemales, int numberOfChildrenOver8, int numberOfChildrenUnder8, int numberOfHampers) {
        this.name = name;
        LocalDate today = LocalDate.now ();
        this.date = today.format (DateTimeFormatter.ofPattern ("MM/dd/yyyy"));
        this.numberOfAdultMales = numberOfAdultMales;
        this.numberOfAdultFemales = numberOfAdultFemales;
        this.numberOfChildrenOver8 = numberOfChildrenOver8;
        this.numberOfChildrenUnder8 = numberOfChildrenUnder8;
        this.numberOfHampers = numberOfHampers;
        this.availableFoodsInventory = new AvailableFoodsManager ();
        this.registration = new RegistrationForDailyClientNeeds ();
    }


    /**
     * Checks if there are valid number of clients to register for the hamper.
     *
     * @return true if there are valid number of clients to register for the hamper, false otherwise
     * @throws IllegalArgumentException if the number of adults, children, or hampers is invalid
     */
    public boolean clientExists() throws IllegalArgumentException {
        if (numberOfAdultMales > 0 || numberOfAdultFemales > 0 || numberOfChildrenOver8 > 0 || numberOfChildrenUnder8 > 0) {
            return true;
        }
        if (numberOfAdultMales == 0 && numberOfAdultFemales == 0 && numberOfChildrenOver8 == 0 && numberOfChildrenUnder8 == 0) {
            throw new IllegalArgumentException ("Request cannot be fulfilled. Please register at least one client.");
        }
        return false;
    }

    //getters
    //takes (grain needs per type of client) and multiplies it by (the number of each type of client) to get the total
    public int getDailyGrainsNeeded() {
        return (numberOfAdultMales * registration.selectMaleWholeGrains ()) + (numberOfAdultFemales * registration.selectFemaleWholeGrains ()) + (numberOfChildrenOver8 * registration.selectOver8WholeGrains ()) + (numberOfChildrenUnder8 * registration.selectUnder8WholeGrains ());
    }

    //takes (FV needs per type of client) and multiplies it by (the number of each type of client) to get the total
    public int getDailyFVNeeded() {
        return (numberOfAdultMales * registration.selectMaleFruitVeggies ()) + (numberOfAdultFemales * registration.selectFemaleFruitVeggies ()) + (numberOfChildrenOver8 * registration.selectOver8FruitVeggies ()) + (numberOfChildrenUnder8 * registration.selectUnder8FruitVeggies ());
    }

    //takes (protein needs per type of client) and multiplies it by (the number of each type of client) to get the total
    public int getDailyProteinNeeded() {
        return (numberOfAdultMales * registration.selectMaleProtein ()) + (numberOfAdultFemales * registration.selectFemaleProtein ()) + (numberOfChildrenOver8 * registration.selectOver8Protein ()) + (numberOfChildrenUnder8 * registration.selectUnder8Protein ());
    }

    //takes (calorie needs per type of client) and multiplies it by (the number of each type of client) to get the total
    public int getDailyCaloriesNeeded() {
        return (numberOfAdultMales * registration.selectMaleCalories ()) + (numberOfAdultFemales * registration.selectFemaleCalories ()) + (numberOfChildrenOver8 * registration.selectOver8Calories ()) + (numberOfChildrenUnder8 * registration.selectUnder8Calories ());
    }

    //takes (other needs per type of client) and multiplies it by (the number of each type of client) to get the total
    public int getDailyOtherNeeded() {
        return (numberOfAdultMales * registration.selectMaleOther ()) + (numberOfAdultFemales * registration.selectFemaleOther ()) + (numberOfChildrenOver8 * registration.selectOver8Other ()) + (numberOfChildrenUnder8 * registration.selectUnder8Other ());
    }

    /**
     * Gets weekly grains needed for the household.
     *
     * @return - weekly grains needed for the household
     */
    public int getWeeklyGrainsNeeded() {
        return getDailyGrainsNeeded () * 7;
    }

    /**
     * Gets weekly FV needed for the household.
     *
     * @return - weekly FV needed for the household
     */
    public int getWeeklyFVNeeded() {
        return getDailyFVNeeded () * 7;
    }

    /**
     * Gets weekly protein needed for the household.
     *
     * @return - weekly protein needed for the household
     */
    public int getWeeklyProteinNeeded() {
        return getDailyProteinNeeded () * 7;
    }

    /**
     * Gets weekly calories needed for the household.
     *
     * @return - weekly calories needed for the household
     */
    public int getWeeklyCaloriesNeeded() {
        return getDailyCaloriesNeeded () * 7;
    }

    /**
     * Gets weekly other needed for the household.
     *
     * @return - weekly other needed for the household
     */
    public int getWeeklyOtherNeeded() {
        return getDailyOtherNeeded () * 7;
    }

    /**
     * Gets the name of the client.
     *
     * @return - the name of the client
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the date of the request.
     *
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets the number of adult males
     *
     * @return - number of adult males
     */
    public int getNumberOfAdultMales() {
        return numberOfAdultMales;
    }

    /**
     * Gets the number of adult females
     *
     * @return - number of adult females
     */
    public int getNumberOfAdultFemales() {
        return numberOfAdultFemales;
    }

    /**
     * Get the number of children over 8
     *
     * @return - number of children over 8
     */
    public int getNumberOfChildrenOver8() {
        return numberOfChildrenOver8;
    }

    /**
     * Gets the number of children under 8
     *
     * @return - number of children under 8
     */
    public int getNumberOfChildrenUnder8() {
        return numberOfChildrenUnder8;
    }

    /**
     * Gets the number of hampers
     *
     * @return - number of hampers
     */
    public int getNumberOfHampers() {
        return numberOfHampers;
    }

    /**
     * Creates a combination of foods to put in the hamper that will satisfy the household's needs.
     *
     * @return - a combination of foods to put in the hamper that will satisfy the household's needs
     */
    public ArrayList<String> getFoodComboPerHamper() {
        grainsNeeded = getWeeklyGrainsNeeded ();
        proteinNeeded = getWeeklyProteinNeeded ();
        fvNeeded = getWeeklyFVNeeded ();
        caloriesNeeded = getWeeklyCaloriesNeeded ();
        otherNeeded = getWeeklyOtherNeeded ();

        currentGrainContentInHamper = 0;
        currentProteinContentInHamper = 0;
        currentOtherContentInHamper = 0;
        currentCaloriesInHamper = 0;
        currentFVContentInHamper = 0;

        for (int i = 0; i < availableFoodsInventory.getCurrentAvailableFoodsName ().size (); i++) {
            while ((grainsNeeded >= currentGrainContentInHamper) || (proteinNeeded >= currentProteinContentInHamper) || (fvNeeded >= currentFVContentInHamper) || (caloriesNeeded >= currentCaloriesInHamper) || (otherNeeded >= currentOtherContentInHamper)) {
                String foodName = availableFoodsInventory.getCurrentAvailableFoodsName ().get (i);
                food.add (foodName);
                currentGrainContentInHamper += availableFoodsInventory.getGrainContent (foodName);
                currentProteinContentInHamper += availableFoodsInventory.getProteinContent (foodName);
                currentFVContentInHamper += availableFoodsInventory.getFVContent (foodName);
                currentCaloriesInHamper += availableFoodsInventory.getCalories (foodName);
                currentOtherContentInHamper += availableFoodsInventory.getOtherContent (foodName);
                availableFoodsInventory.removeFood (foodName);
                i++;
            }
        }
        return food;
    }

    /**
     * Checks if the order is possibly by comparing the total number of nutrients in the entire database vs. the weekly nutrients.
     *
     * @return - boolean of whether the order can be completed or not.
     */
    public boolean isOrderPossible(){
        return (getWeeklyGrainsNeeded () <= availableFoodsInventory.totalGrainContentInInventory ()) && (getWeeklyProteinNeeded () <= availableFoodsInventory.totalProteinContentInventory()) && (getWeeklyFVNeeded () <= availableFoodsInventory.totalFVContentInInventory ()) && (getWeeklyCaloriesNeeded () <= availableFoodsInventory.totalCaloriesInInventory()) && (getWeeklyOtherNeeded () <= availableFoodsInventory.totalOtherContentInventory ());
    }

     /**
     * Calculates the deficit in nutrients by subtracting the current nutrients from the weekly nutrients. Then, it outputs a message to the terminal.
     */
    public void calculateDeficit(){
        boolean notPossible = !isOrderPossible ();
        boolean deficit;
        if (notPossible) {
            deficit = true;
            int grainDeficit = getWeeklyGrainsNeeded () - availableFoodsInventory.totalGrainContentInInventory ();
            int proteinDeficit = getWeeklyProteinNeeded () - availableFoodsInventory.totalProteinContentInventory ();
            int fvDeficit = getWeeklyFVNeeded () - availableFoodsInventory.totalFVContentInInventory ();
            int otherDeficit = getWeeklyOtherNeeded () - availableFoodsInventory.totalOtherContentInventory ();
            int caloricDeficit = getWeeklyCaloriesNeeded () - availableFoodsInventory.totalCaloriesInInventory ();
            // print out the deficit
            System.out.println ("Grain deficit: " + grainDeficit);
            System.out.println ("Protein deficit: " + proteinDeficit);
            System.out.println ("FV deficit: " + fvDeficit);
            System.out.println ("Other deficit: " + otherDeficit);
            System.out.println ("Caloric deficit: " + caloricDeficit);
        }
        }

    }
