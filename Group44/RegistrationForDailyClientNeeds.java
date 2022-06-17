package edu.ucalgary.ensf409;
import java.sql.*;

/**
 * This class is used to register the dailyclientneeds in the database
 * 
 * @author Maarya Ahmad <a href="mailto:maarya.ahmed@ucalgary.ca">maarya.ahmed@ucalgary.ca</a>
 * @author Sadia Khandaker <a href="mailto:khandaker.tahsin@ucalgary.ca">khandaker.tahsin@ucalgary.ca</a>
 * @author Noor Nawaz <a href="mailto:noor.nawaz@ucalgary.ca">noor.nawaz@ucalgary.ca</a>
 * @author Tamunomiete Brown <a href="mailto:tamunomiete.brown@ucalgary.ca">tamunomiete.brown@ucalgary.ca</a>
 * @version 1.7
 * @since 1.0
 */

 //registering the dailyclientneeds into the database
public class RegistrationForDailyClientNeeds 
{
    /**
     * setting the url, username, password and table name to a final value, hard coded it into the fil
     */
    private final String DBURL = "jdbc:mysql://localhost/FOOD_INVENTORY";
    private final String USERNAME = "student";
    private final String PASSWORD = "ensf";
    private final String TABLE_NAME = "DAILY_CLIENT_NEEDS";
    private Connection dbConnect;
    private ResultSet results;

    /**
     * initializing driver and connections when the RegistrationForDailyClientNeeds() is called
     */
    public RegistrationForDailyClientNeeds()
    {
        initializeDriver();
        initializeConnection();
    }
    
    //sample main to check if the file is working properly and establishing a connection with the database
    public static void main(String[] args) {
        RegistrationForDailyClientNeeds registration = new RegistrationForDailyClientNeeds(); //("jdbc:mysql://localhost/FOOD_INVENTORY", "student", "ensf409");
        // registration.initializeDriver ();
        // registration.initializeConnection ();
        System.out.println ("printing data for male whole grains: ");
        System.out.println (registration.selectMaleWholeGrains ());
        System.out.println ("Printing data from female whole grains: ");
        System.out.println (registration.selectFemaleWholeGrains ());
        System.out.println ("Printing data from over 8 whole grains: ");
        System.out.println (registration.selectOver8WholeGrains ());
        System.out.println ("Printing data from under 8 whole grains: ");
        System.out.println (registration.selectUnder8WholeGrains ());
        System.out.println ("Printing data from male fruit veggies: ");
        System.out.println (registration.selectMaleFruitVeggies ());
        System.out.println ("Printing data from female fruit veggies: ");
        System.out.println (registration.selectFemaleFruitVeggies ());
        System.out.println ("Printing data from over 8 fruit veggies: ");
        System.out.println (registration.selectOver8FruitVeggies ());
        System.out.println ("Printing data from under 8 fruit veggies: ");
        System.out.println (registration.selectUnder8FruitVeggies ());

    }

    /**
     * initializing the driver or throws the exception if driver is not found
     */
    public void initializeDriver() {
        try {
            Class.forName ("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println ("Driver not found! Check output console");
            e.printStackTrace ();
        }
    }
    /**
     * initializing the connection and throwing exception if failed
     */
    public void initializeConnection() {
        try {
            dbConnect = DriverManager.getConnection (DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println ("Connection Failed! Check output console");
            e.printStackTrace ();
        }
    }
    /**
     * disconnecting the connection 
     */
    public void disconnectConnection() {
        try {
            dbConnect.close ();
        } catch (SQLException e) {
            System.out.println ("Disconnection Failed! Check output console");
        }
    }
    /**
     * returning the value of wholegrains that a male needs from the database and returning that value, if not, it throws an exception
     */
    public int selectMaleWholeGrains() {
        int maleWholeGrains = 0;
        String query = "SELECT WholeGrains FROM " + TABLE_NAME + " WHERE ClientID = 1";
        try {
            Statement stmt = dbConnect.createStatement ();
            results = stmt.executeQuery (query);
            while (results.next ()) {
                maleWholeGrains = results.getInt ("WholeGrains");
            }
        } catch (SQLException e) {
            System.out.println ("Select Failed! Check output console");
        }
        return maleWholeGrains;

    }
    /**
     * returning the value of wholegrains that a female needs from the database and returning that value, if not, it throws an exception
     */
    public int selectFemaleWholeGrains() {
        int femaleWholeGrains = 0;
        String query = "SELECT WholeGrains FROM " + TABLE_NAME + " WHERE ClientID = 2";
        try {
            Statement stmt = dbConnect.createStatement ();
            results = stmt.executeQuery (query);
            while (results.next ()) {
                femaleWholeGrains = results.getInt ("WholeGrains");
            }
        } catch (SQLException e) {
            System.out.println ("Select Failed! Check output console");
        }
        return femaleWholeGrains;

    }
    /**
     * returning the value of wholegrains that a child over 8 needs from the database and returning that value, if not, it throws an exception
     */
    public int selectOver8WholeGrains() {
        int over8WholeGrains = 0;
        String query = "SELECT WholeGrains FROM " + TABLE_NAME + " WHERE ClientID = 3";
        try {
            Statement stmt = dbConnect.createStatement ();
            results = stmt.executeQuery (query);
            while (results.next ()) {
                over8WholeGrains = results.getInt ("WholeGrains");
            }
        } catch (SQLException e) {
            System.out.println ("Select Failed! Check output console");
        }
        return over8WholeGrains;
    }
    /**
     * returning the value of wholegrains that a child under 8 needs from the database and returning that value, if not, it throws an exception
     */
    public int selectUnder8WholeGrains() {
        int under8WholeGrains = 0;
        String query = "SELECT WholeGrains FROM " + TABLE_NAME + " WHERE ClientID = 4";
        try {
            Statement stmt = dbConnect.createStatement ();
            results = stmt.executeQuery (query);
            while (results.next ()) {
                under8WholeGrains = results.getInt ("WholeGrains");
            }
        } catch (SQLException e) {
            System.out.println ("Select Failed! Check output console");
        }
        return under8WholeGrains;
    }
    /**
     * returning the value of fruit/veggies that a male needs from the database and returning that value, if not, it throws an exception
     */
    public int selectMaleFruitVeggies() {
        int maleFruitVeggies = 0;
        String query = "SELECT FruitVeggies FROM " + TABLE_NAME + " WHERE ClientID = 1";
        try {
            Statement stmt = dbConnect.createStatement ();
            results = stmt.executeQuery (query);
            while (results.next ()) {
                maleFruitVeggies = results.getInt ("FruitVeggies");
            }
        } catch (SQLException e) {
            System.out.println ("Select Failed! Check output console");
        }
        return maleFruitVeggies;
    }
    /**
     * returning the value of fruit/veggies that a female needs from the database and returning that value, if not, it throws an exception
     */
    public int selectFemaleFruitVeggies() {
        int femaleFruitVeggies = 0;
        String query = "SELECT FruitVeggies FROM " + TABLE_NAME + " WHERE ClientID = 2";
        try {
            Statement stmt = dbConnect.createStatement ();
            results = stmt.executeQuery (query);
            while (results.next ()) {
                femaleFruitVeggies = results.getInt ("FruitVeggies");
            }
        } catch (SQLException e) {
            System.out.println ("Select Failed! Check output console");
        }
        return femaleFruitVeggies;
    }
    /**
     * returning the value of fruit/veggies that a child over 8 needs from the database and returning that value, if not, it throws an exception
     */
    public int selectOver8FruitVeggies() {
        int over8FruitVeggies = 0;
        String query = "SELECT FruitVeggies FROM " + TABLE_NAME + " WHERE ClientID = 3";
        try {
            Statement stmt = dbConnect.createStatement ();
            results = stmt.executeQuery (query);
            while (results.next ()) {
                over8FruitVeggies = results.getInt ("FruitVeggies");
            }
        } catch (SQLException e) {
            System.out.println ("Select Failed! Check output console");
        }
        return over8FruitVeggies;
    }
    /**
     * returning the value of fruit/veggies that a child under 8 needs from the database and returning that value, if not, it throws an exception
     */
    public int selectUnder8FruitVeggies() {
        int under8FruitVeggies = 0;
        String query = "SELECT FruitVeggies FROM " + TABLE_NAME + " WHERE ClientID = 4";
        try {
            Statement stmt = dbConnect.createStatement ();
            results = stmt.executeQuery (query);
            while (results.next ()) {
                under8FruitVeggies = results.getInt ("FruitVeggies");
            }
        } catch (SQLException e) {
            System.out.println ("Select Failed! Check output console");
        }
        return under8FruitVeggies;
    }
    /**
     * returning the value of protein that a male needs from the database and returning that value, if not, it throws an exception
     */
    public int selectMaleProtein() {
        int maleProtein = 0;
        String query = "SELECT Protein FROM " + TABLE_NAME + " WHERE ClientID = 1";
        try {
            Statement stmt = dbConnect.createStatement ();
            results = stmt.executeQuery (query);
            while (results.next ()) {
                maleProtein = results.getInt ("Protein");
            }
        } catch (SQLException e) {
            System.out.println ("Select Failed! Check output console");
        }
        return maleProtein;
    }
    /**
     * returning the value of protein that a female needs from the database and returning that value, if not, it throws an exception
     */
    public int selectFemaleProtein() {
        int femaleProtein = 0;
        String query = "SELECT Protein FROM " + TABLE_NAME + " WHERE ClientID = 2";
        try {
            Statement stmt = dbConnect.createStatement ();
            results = stmt.executeQuery (query);
            while (results.next ()) {
                femaleProtein = results.getInt ("Protein");
            }
        } catch (SQLException e) {
            System.out.println ("Select Failed! Check output console");
        }
        return femaleProtein;
    }
    /**
     * returning the value of protein that a child over 8 needs from the database and returning that value, if not, it throws an exception
     */
    public int selectOver8Protein() {
        int over8Protein = 0;
        String query = "SELECT Protein FROM " + TABLE_NAME + " WHERE ClientID = 3";
        try {
            Statement stmt = dbConnect.createStatement ();
            results = stmt.executeQuery (query);
            while (results.next ()) {
                over8Protein = results.getInt ("Protein");
            }
        } catch (SQLException e) {
            System.out.println ("Select Failed! Check output console");
        }
        return over8Protein;
    }
    /**
     * returning the value of protein that a child under 8 needs from the database and returning that value, if not, it throws an exception
     */
    public int selectUnder8Protein() {
        int under8Protein = 0;
        String query = "SELECT Protein FROM " + TABLE_NAME + " WHERE ClientID = 4";
        try {
            Statement stmt = dbConnect.createStatement ();
            results = stmt.executeQuery (query);
            while (results.next ()) {
                under8Protein = results.getInt ("Protein");
            }
        } catch (SQLException e) {
            System.out.println ("Select Failed! Check output console");
        }
        return under8Protein;
    }
    /**
     * returning the value of other that a male needs from the database and returning that value, if not, it throws an exception
     */
    public int selectMaleOther() {
        int maleOther = 0;
        String query = "SELECT Other FROM " + TABLE_NAME + " WHERE ClientID = 1";
        try {
            Statement stmt = dbConnect.createStatement ();
            results = stmt.executeQuery (query);
            while (results.next ()) {
                maleOther = results.getInt ("Other");
            }
        } catch (SQLException e) {
            System.out.println ("Select Failed! Check output console");
        }
        return maleOther;
    }
    /**
     * returning the value of other that a female needs from the database and returning that value, if not, it throws an exception
     */
    public int selectFemaleOther() {
        int femaleOther = 0;
        String query = "SELECT Other FROM " + TABLE_NAME + " WHERE ClientID = 2";
        try {
            Statement stmt = dbConnect.createStatement ();
            results = stmt.executeQuery (query);
            while (results.next ()) {
                femaleOther = results.getInt ("Other");
            }
        } catch (SQLException e) {
            System.out.println ("Select Failed! Check output console");
        }
        return femaleOther;
    }
    /**
     * returning the value of other that a child over 8 needs from the database and returning that value, if not, it throws an exception
     */
    public int selectOver8Other() {
        int over8Other = 0;
        String query = "SELECT Other FROM " + TABLE_NAME + " WHERE ClientID = 3";
        try {
            Statement stmt = dbConnect.createStatement ();
            results = stmt.executeQuery (query);
            while (results.next ()) {
                over8Other = results.getInt ("Other");
            }
        } catch (SQLException e) {
            System.out.println ("Select Failed! Check output console");
        }
        return over8Other;
    }
    /**
     * returning the value of other that a child under 8 needs from the database and returning that value, if not, it throws an exception
     */
    public int selectUnder8Other() {
        int under8Other = 0;
        String query = "SELECT Other FROM " + TABLE_NAME + " WHERE ClientID = 4";
        try {
            Statement stmt = dbConnect.createStatement ();
            results = stmt.executeQuery (query);
            while (results.next ()) {
                under8Other = results.getInt ("Other");
            }
        } catch (SQLException e) {
            System.out.println ("Select Failed! Check output console");
        }
        return under8Other;
    }
    /**
     * returning the value of calories that a male needs from the database and returning that value, if not, it throws an exception
     */
    public int selectMaleCalories() {
        int maleCalories = 0;
        String query = "SELECT Calories FROM " + TABLE_NAME + " WHERE ClientID = 1";
        try {
            Statement stmt = dbConnect.createStatement ();
            results = stmt.executeQuery (query);
            while (results.next ()) {
                maleCalories = results.getInt ("Calories");
            }
        } catch (SQLException e) {
            System.out.println ("Select Failed! Check output console");
        }
        return maleCalories;
    }
    /**
     * returning the value of calories that a female needs from the database and returning that value, if not, it throws an exception
     */
    public int selectFemaleCalories() {
        int femaleCalories = 0;
        String query = "SELECT Calories FROM " + TABLE_NAME + " WHERE ClientID = 2";
        try {
            Statement stmt = dbConnect.createStatement ();
            results = stmt.executeQuery (query);
            while (results.next ()) {
                femaleCalories = results.getInt ("Calories");
            }
        } catch (SQLException e) {
            System.out.println ("Select Failed! Check output console");
        }
        return femaleCalories;
    }
    /**
     * returning the value of calories that a child over 8 needs from the database and returning that value, if not, it throws an exception
     */
    public int selectOver8Calories() {
        int over8Calories = 0;
        String query = "SELECT Calories FROM " + TABLE_NAME + " WHERE ClientID = 3";
        try {
            Statement stmt = dbConnect.createStatement ();
            results = stmt.executeQuery (query);
            while (results.next ()) {
                over8Calories = results.getInt ("Calories");
            }
        } catch (SQLException e) {
            System.out.println ("Select Failed! Check output console");
            e.printStackTrace ();
        }
        return over8Calories;
    }
    /**
     * returning the value of calories that a child under 8 needs from the database and returning that value, if not, it throws an exception
     */
    public int selectUnder8Calories() {
        int under8Calories = 0;
        String query = "SELECT Calories FROM " + TABLE_NAME + " WHERE ClientID = 4";
        try {
            Statement stmt = dbConnect.createStatement ();
            results = stmt.executeQuery (query);
            while (results.next ()) {
                under8Calories = results.getInt ("Calories");
            }
        } catch (SQLException e) {
            System.out.println ("Select Failed! Check output console");
            e.printStackTrace ();
        }
        return under8Calories;
    }

/********************************** GETTERS FOR ALL THE VARIABLE NAMES ********************** */
    public String getDBURL() {
        return DBURL;
    }

    public String getUsername() {
        return USERNAME;
    }

    public String getPassword() {
        return PASSWORD;
    }

    public String getTableName() {
        return TABLE_NAME;
    }

    public Connection getDbConnect() {
        return dbConnect;
    }

    public ResultSet getResults() {
        return results;
    }
}



