package edu.ucalgary.ensf409;

import java.sql.*;
import java.util.ArrayList;

/**
 * This class is used to manage the available foods in the database.
 *
 * @author Sadia Khandaker <a href="mailto:khandaker.tahsin@ucalgary.ca">khandaker.tahsin@ucalgary.ca</a>
 * @author Noor Nawaz <a href="mailto:noor.nawaz@ucalgary.ca">noor.nawaz@ucalgary.ca</a>
 * @author Maarya Ahmad <a href="mailto:maarya.ahmed@ucalgary.ca">maarya.ahmed@ucalgary.ca</a>
 * @author Tamunomiete Brown <a href="mailto:tamunomiete.brown@ucalgary.ca">tamunomiete.brown@ucalgary.ca</a>
 * @version 1.3
 * @since 1.0
 */
public class AvailableFoodsManager {
    private final String DBURL = "jdbc:mysql://localhost/FOOD_INVENTORY";
    private final String USERNAME = "student";
    private final String PASSWORD = "ensf";
    private final String TABLE_NAME = "AVAILABLE_FOOD";
    private Connection dbConnect;
    private ArrayList<AvailableFoods> initialAvailableFoodsList = new ArrayList<AvailableFoods> ();

    public static void main(String[] args) {
        AvailableFoodsManager availableFoodsManager = new AvailableFoodsManager ();
        String foodName = "Wheat bread, loaf";
        System.out.println (availableFoodsManager.getCalories (foodName));
        System.out.println (availableFoodsManager.getProteinContent (foodName));
        System.out.println (availableFoodsManager.getGrainContent (foodName));
        System.out.println (availableFoodsManager.getFVContent (foodName));

    }

    /**
     * Constructor for the AvailableFoodsManager class, it connects to the database and imports the available foods.
     */
    public AvailableFoodsManager() {
        connectToDatabase ();
        importDatabase ();
    }

    /*
    main method for testing
     */
    // public static void main(String[] args) {
    //     AvailableFoodsManager availableFoodsManager = new AvailableFoodsManager ();
    //     for (AvailableFoods availableFoods : availableFoodsManager.getCurrentAvailableFoods ()) {
    //         System.out.println (availableFoods.getFoodName () + " " + availableFoods.getCalories () + " " + availableFoods.getGrainContent () + " " + availableFoods.getFVContent () + " " + availableFoods.getProteinContent () + " " + availableFoods.getOtherContent ());
    //     }
    //     System.out.println ("*******************************************************************************");
    //     availableFoodsManager.removeFood ("Tomato Sauce, jar");
    //     System.out.println ("****************************Tomato Sauce, jar removed*************************");
    //     for (AvailableFoods availableFoods : availableFoodsManager.getCurrentAvailableFoods ()) {
    //         System.out.println (availableFoods.getFoodName () + " " + availableFoods.getCalories () + " " + availableFoods.getGrainContent () + " " + availableFoods.getFVContent () + " " + availableFoods.getProteinContent () + " " + availableFoods.getOtherContent ());
    //     }
    // }

    /**
     * This method tries to connect to the database using the credentials provided.
     */
    public void connectToDatabase() {
        try {
            Class.forName ("com.mysql.cj.jdbc.Driver");
            dbConnect = DriverManager.getConnection (DBURL, USERNAME, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    /**
     * Getter method for the database connection.
     *
     * @return - the database connection.
     */
    public Connection getDBConnect() {
        return dbConnect;
    }

    /**
     * Getter method for the database url.
     *
     * @return - the database url.
     */
    public String getDBURL() {
        return DBURL;
    }

    /**
     * This method adds a new food to the database.
     *
     * @return
     */
    public String getUsername() {
        return USERNAME;
    }

    /**
     * This method imports the available foods from the database.
     *
     * @return
     */
    public String getPassword() {
        return PASSWORD;
    }

    /**
     * Getter method for the available foods list.
     *
     * @return
     */
    public ArrayList<AvailableFoods> getInitialAvailableFoodsList() {
        return initialAvailableFoodsList;
    }

    /**
     * This method disconnects from the database.
     */
    public void disconnectFromDatabase() {
        try {
            dbConnect.close ();
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    /**
     * This method removes a food from the database.
     *
     * @param foodName
     */
    public void removeFood(String foodName) {
        try {
            Statement statement = dbConnect.createStatement ();
            statement.executeUpdate ("DELETE FROM AVAILABLE_FOOD WHERE Name = '" + foodName + "'" + " LIMIT 1");
            initialAvailableFoodsList.remove (foodName);
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    /**
     * This method imports the available foods from the database.
     */
    public void importDatabase() {
        try {
            Statement statement = dbConnect.createStatement ();
            ResultSet resultSet = statement.executeQuery ("SELECT * FROM AVAILABLE_FOOD");
            initialAvailableFoodsList = new ArrayList<> ();
            while (resultSet.next ()) {
                AvailableFoods availableFoods = new AvailableFoods (resultSet.getInt ("ItemID"), resultSet.getString ("Name"), resultSet.getInt ("GrainContent"), resultSet.getInt ("FVContent"), resultSet.getInt ("ProContent"), resultSet.getInt ("Other"), resultSet.getInt ("Calories"));
                initialAvailableFoodsList.add (availableFoods);
            }
        } catch (SQLException e) {
            e.printStackTrace ();
        }
    }

    /**
     * Select all current entries from the database and return them as an ArrayList.
     *
     * @return - the ArrayList of all entries in the database.
     */
    public ArrayList<AvailableFoods> getCurrentAvailableFoods() {
        ArrayList<AvailableFoods> currentAvailableFoods = new ArrayList<> ();
        importDatabase ();
        currentAvailableFoods.addAll (initialAvailableFoodsList);
        return currentAvailableFoods;
    }

    /**
     * Method to check if the food is already in the database.
     *
     * @param foodName - the name of the food to be checked.
     * @return - true if the food is already in the database, false if it is not.
     */
    public boolean isAvailable(String foodName) {
        for (AvailableFoods availableFoods : initialAvailableFoodsList) {
            if (availableFoods.getFoodName ().equals (foodName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets calories of a food from the database.
     *
     * @param foodName - the name of the food.
     * @return - the calories of the food.
     */
    public int getCalories(String foodName) {
        for (AvailableFoods availableFoods : initialAvailableFoodsList) {
            if (availableFoods.getFoodName ().equals (foodName)) {
                return availableFoods.getCalories ();
            }
        }
        return 0;
    }

    /**
     * Gets grain content of a food from the database.
     *
     * @param foodName - the name of the food.
     * @return - the grain content of the food.
     */
    public int getGrainContent(String foodName) {
        for (AvailableFoods availableFoods : initialAvailableFoodsList) {
            if (availableFoods.getFoodName ().equals (foodName)) {
                return availableFoods.getGrainContent ();
            }
        }
        return 0;
    }

    /**
     * Gets fruit veggies content of a food from the database.
     *
     * @param foodName - the name of the food.
     * @return - the fruit veggies content of the food.
     */
    public int getFVContent(String foodName) {
        for (AvailableFoods availableFoods : initialAvailableFoodsList) {
            if (availableFoods.getFoodName ().equals (foodName)) {
                return availableFoods.getFVContent ();
            }
        }
        return 0;
    }

    /**
     * Gets protein content of a food from the database.
     *
     * @param foodName - the name of the food.
     * @return - the protein content of the food.
     */
    public int getProteinContent(String foodName) {
        for (AvailableFoods availableFoods : initialAvailableFoodsList) {
            if (availableFoods.getFoodName ().equals (foodName)) {
                return availableFoods.getProteinContent ();
            }
        }
        return 0;
    }

    /**
     * Gets other content of a food from the database.
     *
     * @param foodName - the name of the food.
     * @return - the other content of the food.
     */
    public int getOtherContent(String foodName) {
        for (AvailableFoods availableFoods : initialAvailableFoodsList) {
            if (availableFoods.getFoodName ().equals (foodName)) {
                return availableFoods.getOtherContent ();
            }
        }
        return 0;
    }

    /**
     * Returns an ArrayList of all the current available foods names.
     *
     * @return - an ArrayList of all the current available foods names.
     */
    public ArrayList<String> getCurrentAvailableFoodsName() {
        ArrayList<String> currentAvailableFoodsName = new ArrayList<> ();
        importDatabase ();
        for (AvailableFoods availableFoods : initialAvailableFoodsList) {
            currentAvailableFoodsName.add (availableFoods.getFoodName ());
        }
        return currentAvailableFoodsName;
    }

    /**
     * Gets Item ID of a food from the database.
     *
     * @param foodName - the name of the food.
     * @return - the Item ID of the food.
     */
    public int getItemID(String foodName) {
        for (AvailableFoods availableFoods : initialAvailableFoodsList) {
            if (availableFoods.getFoodName ().equals (foodName)) {
                return availableFoods.getItemID ();
            }
        }
        return 0;
    }

    /**
     * Returns the total number of calories in the entire database.
     *
     * @return - total number of calories in the entire database.
     */
    public int totalCaloriesInInventory(){
        int totalCalories = 0;
        for (AvailableFoods availableFoods : initialAvailableFoodsList) {
            totalCalories += availableFoods.getCalories ();
        }
        return totalCalories;
    }

    /**
     * Returns the total number of grains in the entire database.
     *
     * @return - total number of grains in the entire database.
     */
    public int totalGrainContentInInventory(){
        int totalGrainContent = 0;
        for (AvailableFoods availableFoods : initialAvailableFoodsList) {
            totalGrainContent += availableFoods.getGrainContent ();
        }
        return totalGrainContent;
    }

     /**
     * Returns the total number of FV in the entire database.
     *
     * @return - total number of FV in the entire database.
     */
    public int totalFVContentInInventory(){
        int totalFVContent = 0;
        for (AvailableFoods availableFoods : initialAvailableFoodsList) {
            totalFVContent += availableFoods.getFVContent ();
        }
        return totalFVContent;
    }

     /**
     * Returns the total number of protein in the entire database.
     *
     * @return - total number of protein in the entire database.
     */
    public int totalProteinContentInventory(){
        int totalProteinContent = 0;
        for (AvailableFoods availableFoods : initialAvailableFoodsList) {
            totalProteinContent += availableFoods.getProteinContent ();
        }
        return totalProteinContent;
    }

     /**
     * Returns the total number of other nutrients in the entire database.
     *
     * @return - total number of other nutrients in the entire database.
     */
    public int totalOtherContentInventory(){
        int totalOtherContent = 0;
        for (AvailableFoods availableFoods : initialAvailableFoodsList) {
            totalOtherContent += availableFoods.getOtherContent ();
        }
        return totalOtherContent;
    }

}

