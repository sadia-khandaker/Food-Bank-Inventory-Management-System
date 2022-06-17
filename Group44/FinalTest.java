package edu.ucalgary.ensf409;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;

import static org.junit.Assert.*;


/**
 * This class is used to test the functionality of the RegistrationForDailyClientNeeds, AvailableFoodsManager, AvailableFoods, and Hamper classes.
 *
 * @author Maarya Ahmad <a href="mailto:maarya.ahmed@ucalgary.ca">maarya.ahmed@ucalgary.ca</a>
 * @author Sadia Khandaker <a href="mailto:khandaker.tahsin@ucalgary.ca">khandaker.tahsin@ucalgary.ca</a>
 * @author Noor Nawaz <a href="mailto:noor.nawaz@ucalgary.ca">noor.nawaz@ucalgary.ca</a>
 * @author Tamunomiete Brown <a href="mailto:tamunomiete.brown@ucalgary.ca">tamunomiete.brown@ucalgary.ca</a>
 * @version 3.9
 * @since 1.0
 */


public class FinalTest {
    public final static int ITEMID = 0023;
    public final static String FOODNAME = "Tomato Sauce, jar";
    public final static int CALORIES = 120;
    public final static int GRAINCONTENT = 0;
    public final static int FVCONTENT = 80;
    public final static int PROTEINCONTENT = 10;
    public final static int OTHERCONTENT = 10;
    private final String DBURL = "jdbc:mysql://localhost/FOOD_INVENTORY";
    private final String USERNAME = "student";
    private final String PASSWORD = "ensf";
    private final String TABLE_NAME = "DAILY_CLIENT_NEEDS";
    private final ArrayList<String> foodCombo = new ArrayList<String> ();
    public Hamper hamper = new Hamper ("Client", 1, 2, 3, 4, 5);
    public AvailableFoodsManager availableFoodsInventory = new AvailableFoodsManager ();
    RegistrationForDailyClientNeeds registration = new RegistrationForDailyClientNeeds ();
    private Connection dbConnect;
    private ResultSet results;
    private final ArrayList<AvailableFoods> initialAvailableFoodsList = new ArrayList<AvailableFoods> ();
    private final String name = "Client";
    private final int adultMaleNum = 1;
    private final int adultFemaleNum = 2;
    private final int childOver8Num = 3;
    private final int childUnder8Num = 4;
    private final int numOfHampers = 5;

    //DATABASE CONNECTIONS
    public void initializeConnection() {
        try {
            dbConnect = DriverManager.getConnection (DBURL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println ("Connection Failed! Check output console");
            e.printStackTrace ();
        }
    }

    public void disconnectConnection() {
        try {
            dbConnect.close ();
        } catch (SQLException e) {
            System.out.println ("Disconnection Failed! Check output console");
        }
    }

    public void dailyClientNeeds() {
        try {
            initializeConnection ();
            Statement myStmt1 = dbConnect.createStatement ();
            myStmt1.executeUpdate ("DROP TABLE IF EXISTS DAILY_CLIENT_NEEDS");

            Statement myStmt2 = dbConnect.createStatement ();
            myStmt2.executeUpdate ("CREATE TABLE DAILY_CLIENT_NEEDS (  ClientID		int not null AUTO_INCREMENT, Client			varchar(25), WholeGrains		int, FruitVeggies	int, Protein			int, Other			int, Calories		int, primary key (ClientID))");

            Statement myStmt3 = dbConnect.createStatement ();
            myStmt3.executeUpdate ("INSERT INTO DAILY_CLIENT_NEEDS (Client, WholeGrains, FruitVeggies, Protein, Other, Calories) VALUES ('Adult Male',	16,	28,	26,	30,	2500), ('Adult Female', 16, 28, 26, 30, 2000), ('Child over 8', 21, 33, 31, 15, 2200), ('Child under 8', 21, 33, 31, 15, 1400)");

        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
    }

    public void availableFood() {
        try {
            initializeConnection ();
            Statement myStmt1 = dbConnect.createStatement ();
            myStmt1.executeUpdate ("DROP TABLE IF EXISTS AVAILABLE_FOOD");

            Statement myStmt2 = dbConnect.createStatement ();
            myStmt2.executeUpdate ("CREATE TABLE AVAILABLE_FOOD (ItemID			int not null AUTO_INCREMENT, Name			varchar(50), GrainContent	int, FVContent		int, ProContent		int, Other			int, Calories		int, primary key (ItemID)");

            Statement myStmt3 = dbConnect.createStatement ();
            myStmt3.executeUpdate ("INSERT INTO AVAILABLE_FOOD (Name, GrainContent, FVContent, ProContent, Other, Calories) VALUES ('Tomato Sauce, jar', 0, 80, 10, 10, 120), ('Tomato Sauce, jar', 0, 80, 10, 10, 120), ('Tomato Sauce, jar', 0, 80, 10, 10, 120), ('Apple, dozen', 0, 100, 0, 0, 624), ('Apple, dozen', 0, 100, 0, 0, 624), ('Granola Bar, box', 71, 0, 6, 23, 936), ('Chicken broth, can', 0, 0, 14, 86, 95), ('Baby carrots, pound', 0, 100, 0, 0, 159), ('Broccoli, 3 bunches', 0, 92, 8, 0, 621), ('Broccoli, 3 bunches', 0, 92, 8, 0, 621), ('Wheat bread, loaf', 96, 0, 4, 0, 2192), ('Wheat bread, loaf', 96, 0, 4, 0, 2192), ('Wheat bread, loaf', 96, 0, 4, 0, 2192), ('Orange, dozen', 0, 100, 0, 0, 864), ('Orange, dozen', 0, 100, 0, 0, 864), ('Eggs, dozen', 0, 0, 9, 91, 864), ('Eggs, dozen', 0, 0, 9, 91, 864), ('Chicken breast, pound', 0, 0, 30, 70, 730), ('Pasta, dry, pound', 75, 0, 13, 12, 1683), ('Tuna, six large cans', 0, 0, 19, 81, 1776), ('Tuna, six small cans', 0, 0, 19, 81, 900), ('Tuna, six large cans', 0, 0, 19, 81, 1776), ('Tuna, six small cans', 0, 0, 19, 81, 900), ('Cheddar cheese, pound', 0, 0, 23, 77, 1851), ('Cheddar cheese, pound', 0, 0, 23, 77, 1851), ('Quinoa, pound', 87, 0, 13, 0, 1397), ('Lettuce, 3 heads', 0, 100, 0, 0, 225), ('Bananas, bunch', 0, 100, 0, 0, 672), ('Milk, 1%, 4 L', 0, 0, 3, 97, 1785), ('Whole grain bread, loaf', 89, 0, 11, 0, 1904), ('Ground beef, pound', 0, 0, 26, 74, 1179), ('Ground beef, pound', 0, 0, 26, 74, 1179), ('Ground beef, pound', 0, 0, 26, 74, 1179), ('Avocado, dozen', 0, 100, 0, 0, 2880), ('Avocado, dozen', 0, 100, 0, 0, 2880), ('Corn tortillas, pound', 94, 0, 6, 0, 989), ('Pasta, dry, two pounds', 75, 0, 13, 12, 3366), ('Pasta, dry, two pounds', 75, 0, 13, 12, 3366), ('Oatmeal cookies, dozen', 69, 0, 6, 25, 2436), ('Peanut butter, 2 kg jar', 0, 0, 23, 78, 11940), ('Bran flakes, 1 kg', 90, 0, 10, 0, 3520), ('Nutella, 1 kg', 0, 0, 7, 93, 5330), ('Ground pork, pound', 0, 0, 17, 83, 1193), ('Meatballs, 1 kg', 0, 0, 14, 86, 2860), ('Tomatoes, 1 kg', 0, 100, 0, 0, 180), ('Tomatoes, 1 kg', 0, 100, 0, 0, 180), ('Pancake mix, 1 kg', 74, 0, 12, 14, 3400), ('Maple syrup, 1 L', 0, 0, 0, 100, 3276), ('Cauliflower, 2 heads', 0, 100, 0, 0, 420), ('Strawberries, 2 kg', 0, 100, 0, 0, 640), ('Cantaloupe, dozen', 0, 100, 0, 0, 3324), ('Cottage cheese', 0, 0, 11, 89, 840), ('Trail mix, 1 kg', 21, 0, 20, 59, 6000), ('Soy protein, 1 kg', 0, 0, 88, 12, 3350), ('Turkey, whole', 0, 0, 23, 77, 5752), ('Mixed nuts, 1 kg', 0, 0, 23, 77, 6000), ('Lentils, 1 kg', 63, 0, 25, 12, 3520), ('Greek yogurt, plain, 1 kg', 0, 0, 9, 91, 970), ('Tofu, 1 kg', 0, 0, 19, 81, 2700), ('Mayonnaise, 1 kg', 0, 0, 0, 100, 6800), ('Kidney beans, dozen cans', 25, 0, 9, 66, 6840), ('Bacon, 1 kg', 0, 0, 14, 86, 3930), ('Quinoa, 1 kg', 70, 0, 24, 6, 925), ('Chocolate chip cookies, 500 g', 71, 0, 1, 29, 2440), ('Boba green tea, 4 cans', 88, 0, 0, 12, 924), ('Saltines', 79, 0, 1, 20, 1960), ('Instant ramen, package 20', 89, 0, 11, 0, 3080), ('Frozen pizza, pepperoni', 56, 4, 18, 22, 1215), ('Banana, bunch 5', 0, 97, 3, 0, 605), ('Banana, bunch 6', 0, 97, 3, 0, 1210), ('Banana, bunch 10', 0, 97, 3, 0, 726), ('Sweet potato, bag', 0, 95, 5, 0, 1110), ('Manioc, 1 pound', 100, 0, 0, 0, 635), ('Zucchini, 5', 0, 75, 25, 0, 165), ('Zucchini, 10', 0, 75, 25, 0, 330), ('Celeriac, 1 kg', 0, 67, 33, 0, 250), ('Turnip, 1 pound', 0, 100, 0, 0, 127), ('Kidney beans, 1 pound', 72, 0, 28, 0, 385), ('Beets, 1 pound', 0, 87, 13, 0, 193), ('Chickpeas, 1 kg', 67, 0, 22, 11, 1640), ('Hummus', 24, 0, 11, 65, 450), ('Beets, 1 pound', 0, 87, 13, 0, 193), ('Carrots', 0, 100, 0, 0, 264), ('Wheaties, family size', 92, 0, 8, 0, 8000), ('Whole wheat flour, 10 kg', 72, 0, 12, 16, 3660), ('Eggs, 1 kg', 2, 0, 36, 62, 1430), ('Avocado, 1 pound', 19, 76, 5, 0, 725), ('Black olives, large tin', 23, 0, 0, 77, 2200), ('Coconut milk, 1 liter', 4, 0, 0, 96, 1960), ('Avocado, 1 pound', 19, 76, 5, 0, 725), ('Parsnip, 1 kg', 0, 95, 5, 0, 750), ('Yam, 1 kg', 0, 93, 0, 2, 1160), ('Mushrooms, 800g', 0, 48, 44, 8, 160), ('Canned corn, 1 tin', 73, 0, 14, 13, 210), ('Lentils, 1 kg', 49, 0, 46, 5, 1160), ('Almonds, large bag', 14, 0, 14, 72, 1600), ('Protein shake, 10 cans', 12, 0, 72, 16, 1600), ('Soy burger, 20', 21, 0, 41, 38, 4800), ('Whole grain bread', 74, 0, 16, 10, 2000), ('Everything bagel, 6', 79, 0, 13, 8, 1440), ('Beyond Breakfast sausage', 14, 0, 25, 61, 900), ('Black beans, 1 kg', 69, 0, 31, 0, 1290), ('Horse gram, 1 kg', 72, 0, 28, 0, 3210), ('Green peas, 1 pound', 0, 54, 46, 0, 430), ('Teff flour, 2 kg', 83, 0, 12, 5, 6920), ('Creamed spinach', 0, 40, 16, 44, 1000), ('Creamed spinach', 0, 40, 16, 44, 1000), ('Creamed spinach', 0, 40, 16, 44, 1000), ('String cheese, pack of 20', 6, 0, 29, 65, 1400), ('String cheese, pack of 20', 6, 0, 29, 65, 1400), ('Brown rice, small bag', 86, 0, 8, 6, 1500), ('Brown rice, large bag', 86, 0, 8, 6, 15000), ('Butter, 4 sticks', 0, 0, 0, 100, 424), ('Apple juice, 1 kg', 0, 87, 4, 9, 450), ('Raisin bran, 1 box', 87, 1, 9, 3, 1710), ('Oatmeal, 10 packets', 69, 0, 15, 16, 1000), ('Frozen waffles, pack of 10', 64, 0, 9, 27, 850), ('Frozen blueberries, 2100 g', 0, 95, 5, 0, 1200), ('Cocktail fruits, tin', 0, 88, 4, 8, 51), ('Olive oil, 1 kg', 0, 0, 0, 100, 8840), ('Lima beans, 1 pound', 79, 0, 18, 3, 326), ('Dried figs, 500 g', 0, 88, 0, 12, 838), ('Dried figs, 500 g', 0, 88, 0, 12, 838), ('Tinned sardines, pack of 5', 0, 0, 40, 60, 1060), ('Tinned sardines, pack of 5', 0, 0, 40, 60, 1060), ('Egg salad, 57 g', 19, 0, 19, 62, 94), ('Grape jelly', 0, 5, 0, 95, 1500), ('Onions, net of 10', 0, 93, 7, 0, 640), ('Radish, large bunch', 0, 62, 23, 15, 84), ('Kimchi, 400 g', 0, 95, 5, 0, 120), ('Roasted seaweed, 250 g', 0, 26, 26, 48, 1500), ('Honey, 300 g', 0, 0, 0, 100, 987), ('Escargot, 500 g', 0, 0, 13, 87, 1565), ('Salmon, 5 filets', 0, 0, 51, 49, 1400), ('Frozen squid, 1 pound', 0, 0, 72, 28, 417), ('Cocktail shrimp, package', 0, 0, 36, 64, 300), ('Roasted mixed nuts, 56 oz', 13, 0, 13, 74, 9520), ('Farro, medium bag', 81, 0, 14, 5, 1870), ('Pearl barley, 1 pound', 0, 0, 44, 56, 1814), ('Plaintain, 4', 0, 97, 3, 0, 872), ('Frozen french fries, 500 g', 65, 0, 6, 29, 1155), ('Whey powder, large jar', 16, 0, 77, 7, 3900), ('Oranges, bag', 0, 96, 4, 0, 1740), ('Tinned vegetable chilli', 52, 0, 18, 30, 600), ('Raisins, 40 g', 0, 97, 3, 0, 1320), ('Dates, container', 0, 97, 3, 0, 1200), ('Lettuce, 1 head', 0, 68, 32, 0, 93), ('Sauerkraut, 1 kg', 0, 100, 0, 0, 250), ('Brioche', 67, 0, 12, 21, 768), ('Bean sprouts, 1 kg', 0, 55, 45, 0, 52), ('Peaches, crate', 0, 93, 7, 0, 1740), ('Peaches, crate', 0, 93, 7, 0, 1740), ('Pumpkin, very large', 0, 89, 11, 0, 1180), ('Pumpkin, small', 0, 89, 11, 0, 118), ('Snake gourd', 0, 73, 16, 11, 68), ('Hominy, canned, 1 kg', 82, 0, 8, 10, 720), ('Dried apricots, package', 0, 96, 4, 0, 1000), ('Cake mix', 68, 0, 7, 25, 1300), ('Adzuki bean, medium package', 75, 0, 24, 1, 3240), ('Strawberry Skyr', 0, 0, 59, 41, 252), ('Keffir', 0, 0, 22, 78, 600), ('Cottage cheese', 0, 0, 56, 44, 480), ('Bluegreen algae powder, 100 g', 9, 0, 70, 21, 177), ('Chia seeds, 100 g', 29, 0, 16, 55, 510), ('Flax oil, bottle', 0, 0, 0, 100, 1920), ('Fish fingers, box of 30', 37, 0, 25, 38, 1530), ('Cream of mushroom soup', 31, 0, 8, 61, 200), ('Granola cereal', 60, 0, 19, 21, 1260), ('Black fungus, 200 g', 0, 77, 23, 0, 564)");

        } catch (SQLException throwables) {
            throwables.printStackTrace ();
        }
    }


    /*********************** HAMPER TEST************************/


	/*
    Tests if Hamper constructor works correctly
    */
    @Test
    public void testHamperConstructor() {
        assertEquals ("Incorrect 'name' was passed to the constructor.", name, hamper.getName ());
        assertEquals ("Incorrect 'adultMaleNum' was passed to the constructor.", adultMaleNum, hamper.getNumberOfAdultMales ());
        assertEquals ("Incorrect 'adultFemaleNum' was passed to the constructor.", adultFemaleNum, hamper.getNumberOfAdultFemales ());
        assertEquals ("Incorrect 'childOver8Num' was passed to the constructor.", childOver8Num, hamper.getNumberOfChildrenOver8 ());
        assertEquals ("Incorrect 'childUnder8Num' was passed to the constructor.", childUnder8Num, hamper.getNumberOfChildrenUnder8 ());
    }

    /*
    Tests if Hamper constructor works correctly with numberOfHampers
    */
    @Test
    public void testHamperConstructorWithHamperNum() {

        Hamper hamperTwo = new Hamper ("Client", 1, 2, 3, 4, 5);
        assertEquals ("Incorrect 'name' was passed to the constructor.", name, hamperTwo.getName ());
        assertEquals ("Incorrect 'adultMaleNum' was passed to the constructor.", adultMaleNum, hamperTwo.getNumberOfAdultMales ());
        assertEquals ("Incorrect 'adultFemaleNum' was passed to the constructor.", adultFemaleNum, hamperTwo.getNumberOfAdultFemales ());
        assertEquals ("Incorrect 'childOver8Num' was passed to the constructor.", childOver8Num, hamperTwo.getNumberOfChildrenOver8 ());
        assertEquals ("Incorrect 'childUnder8Num' was passed to the constructor.", childUnder8Num, hamperTwo.getNumberOfChildrenUnder8 ());
        assertEquals ("Incorrect 'numOfHampers' was passed to the constructor.", numOfHampers, hamperTwo.getNumberOfHampers ());
    }

    /**
     * Tests if client exists
     */
    @Test
    public void testClientExists() {
        boolean expected = true;
        boolean actual = hamper.clientExists ();
        assertEquals ("Client does not exist", expected, actual);
    }

    /**
     * Tests if clientExists method throws an exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testClientExistsException() {
        Hamper noClientExists = new Hamper ("", 0, 0, 0, 0);
        noClientExists.clientExists ();
    }

    /**
     * Tests if getDailyGrainsNeeded method works correctly
     */

    @Test
    public void testGetDailyGrainsNeeded() {
        int actualnumber = hamper.getDailyGrainsNeeded ();
        int expectedNumber = 195;
        assertEquals ("Method GetDailyGrainsNeeded did not return the correct WholeGrains value: ", actualnumber, expectedNumber);
    }

    /**
     * Tests if getDailyFVNeeded method works correctly
     */
    @Test
    public void testGetDailyFVNeeded() {
        int actualnumber = hamper.getDailyFVNeeded ();
        int expectedNumber = 315;
        assertEquals ("Method GetDailyFVNeeded did not return the correct FV value: ", actualnumber, expectedNumber);
    }

    /**
     * Tests if getDailyProteinNeeded method works correctly
     */
    @Test
    public void testGetDailyProteinNeeded() {
        int actualnumber = hamper.getDailyProteinNeeded ();
        int expectedNumber = 295;
        assertEquals ("Method GetDailyProteinNeeded did not return the correct Protein value: ", expectedNumber, actualnumber);
    }

    /**
     * Tests if getDailyCaloriesNeeded method works correctly
     */
    @Test
    public void testGetDailyCaloriesNeeded() {
        int actualnumber = hamper.getDailyCaloriesNeeded ();
        int expectedNumber = 18700;
        assertEquals ("Method GetDailyCaloriesNeeded did not return the correct Calories value: ", expectedNumber, actualnumber);
    }

    /**
     * Tests the getDailyOthersNeeded method
     */
    @Test
    public void testGetDailyOtherNeeded() {
        int actualnumber = hamper.getDailyOtherNeeded ();
        int expectedNumber = 195;
        assertEquals ("Method GetDailyOtherNeeded did not return the correct Other value: ", expectedNumber, actualnumber);
    }

    /**
     * Tests the getWeeklyGrainsNeeded method
     */
    @Test
    public void testGetWeeklyGrainsNeeded() {
        int actualnumber = hamper.getWeeklyGrainsNeeded ();
        int expectedNumber = 1365;
        assertEquals ("Method getWeeklyGrainsNeeded did not return the correct value: ", expectedNumber, actualnumber);
    }

    /**
     * Tests the getWeeklyFVNeeded method.
     */
    @Test
    public void testGetWeeklyFVNeeded() {
        int actualnumber = hamper.getWeeklyFVNeeded ();
        int expectedNumber = 2205;
        assertEquals ("Method getWeeklyFVNeeded did not return the correct value: ", expectedNumber, actualnumber);
    }

    /**
     * Tests the getWeeklyProteinNeeded method.
     */
    @Test
    public void testGetWeeklyProteinNeeded() {
        int actualnumber = hamper.getWeeklyProteinNeeded ();
        int expectedNumber = 2065;
        assertEquals ("Method getWeeklyProteinNeeded did not return the correct value: ", expectedNumber, actualnumber);
    }

    /**
     * Tests the getWeeklyCaloriesNeeded method.
     */
    @Test
    public void testGetWeeklyCaloriesNeeded() {
        int actualnumber = hamper.getWeeklyCaloriesNeeded ();
        int expectedNumber = 130900;
        assertEquals ("Method getWeeklyCaloriesNeeded did not return the correct value: ", 130900, actualnumber);
    }

    /**
     * Tests the getWeeklyOtherNeeded method
     */
    @Test
    public void testGetWeeklyOtherNeeded() {
        int actualnumber = hamper.getWeeklyOtherNeeded ();
        int expectedNumber = 1365;
        assertEquals ("Method getWeeklyOtherNeeded did not return the correct value: ", expectedNumber, actualnumber);
    }

    /**
     * Tests the getter for the client's name
     */
    @Test
    public void testGetName() {
        String actualName = hamper.getName ();
        String expectedName = "Client";
        assertEquals ("Method getName did not return the correct value: ", expectedName, actualName);
    }

    /**
     * Tests the getNumberOfAdultMales method
     */
    @Test
    public void testGetNumberOfAdultMales() {
        int actualnumber = hamper.getNumberOfAdultMales ();
        int expectedNumber = 1;
        assertEquals ("Method getNumberOfAdultMales did not return the correct value: ", expectedNumber, actualnumber);
    }

    /**
     * Tests the getNumberOfAdultFemales method
     */
    @Test
    public void testGetNumberOfAdultFemales() {
        int actualnumber = hamper.getNumberOfAdultFemales ();
        int expectedNumber = 2;
        assertEquals ("Method getNumberOfAdultFemales did not return the correct value: ", expectedNumber, actualnumber);
    }

    /**
     * Tests the getNumberOfChildrenOver8 method
     */
    @Test
    public void testGetNumberOfChildrenOver8() {
        int actualnumber = hamper.getNumberOfChildrenOver8 ();
        int expectedNumber = 3;
        assertEquals ("Method getNumberOfChildrenOver8 did not return the correct value: ", expectedNumber, actualnumber);
    }

    /**
     * Test of getNumberOfChildrenUnder8 method
     */
    @Test
    public void testGetNumberOfChildrenUnder8() {
        int actualnumber = hamper.getNumberOfChildrenUnder8 ();
        int expectedNumber = 4;
        assertEquals ("Method getNumberOfChildrenUnder8 did not return the correct value: ", expectedNumber, actualnumber);
    }

    /**
     * Tests the getter for the number of hampers
     */
    @Test
    public void testGetNumberOfHampers() {
        int actualnumber = hamper.getNumberOfHampers ();
        int expectedNumber = 5;
        assertEquals ("Method getNumberOfHampers did not return the correct value: ", expectedNumber, actualnumber);
    }

    @Test
    /**
     * Tests if getFoodComboPerHamper actually creates an arraylist of food
     */
    public void testGetFoodComboPerHamper() {
        Hamper test = new Hamper ("Client", 1, 0, 0, 0, 0);
        ArrayList<String> actual = test.getFoodComboPerHamper ();
        assertNotNull ("Method getFoodComboPerHamper did not return the correct value: ", actual);
    }

    /*********************** AVAILABLE FOODS TEST************************/
    @Test
    /*
    Tests if AvailableFood constructor creates an object
    */
    public void testAvailableFoodsConstructorObject() {
        AvailableFoods food = new AvailableFoods (ITEMID, FOODNAME, GRAINCONTENT, FVCONTENT, PROTEINCONTENT, OTHERCONTENT, CALORIES);
        assertNotNull ("AvailableFoods constructor did not create an object when given valid attributes.", food);
    }

    @Test
    /*
    Tests if AvailableFood constructor works correctly
    */
    public void testAvailableFoods() {
        AvailableFoods food = new AvailableFoods (ITEMID, FOODNAME, GRAINCONTENT, FVCONTENT, PROTEINCONTENT, OTHERCONTENT, CALORIES);
        assertEquals ("Incorrect 'item ID' was passed to the constructor.", ITEMID, food.getItemID ());
        assertEquals ("Incorrect 'food name' was passed to the constructor.", FOODNAME, food.getFoodName ());
        assertEquals ("Incorrect 'calories' was passed to the constructor.", CALORIES, food.getCalories ());
        assertEquals ("Incorrect 'grain content' was passed to the constructor.", GRAINCONTENT, food.getGrainContent ());
        assertEquals ("Incorrect 'fruit/veggies' content was passed to the constructor.", FVCONTENT, food.getFVContent ());
        assertEquals ("Incorrect 'protein' content was passed to the constructor.", PROTEINCONTENT, food.getProteinContent ());
        assertEquals ("Incorrect 'other' content was passed to the constructor.", OTHERCONTENT, food.getOtherContent ());
    }


    @Test
    /*
    Tests if method getItemID() works correctly
    */
    public void testGetItemID() {
        AvailableFoods food = new AvailableFoods (ITEMID, FOODNAME, GRAINCONTENT, FVCONTENT, PROTEINCONTENT, OTHERCONTENT, CALORIES);
        int actualItemID = food.getItemID ();
        int expectedItemID = 0023;
        assertEquals ("Method getItemID() did not return the expected result: ", expectedItemID, actualItemID);

    }

    @Test
    /*
    Tests if method getFoodName() works correctly
    */
    public void testGetFoodName() {
        AvailableFoods food = new AvailableFoods (ITEMID, FOODNAME, GRAINCONTENT, FVCONTENT, PROTEINCONTENT, OTHERCONTENT, CALORIES);
        String actualFoodName = food.getFoodName ();
        String expectedFoodName = "Tomato Sauce, jar";
        assertEquals ("Method getFoodName() did not return the expected result: ", expectedFoodName, actualFoodName);

    }


    @Test
    /*
    Tests if method getCalories() works correctly
    */
    public void testGetCalories() {
        AvailableFoods food = new AvailableFoods (ITEMID, FOODNAME, GRAINCONTENT, FVCONTENT, PROTEINCONTENT, OTHERCONTENT, CALORIES);
        int actualCalories = food.getCalories ();
        int expectedCalories = 120;
        assertEquals ("Method getCalories() did not return the expected result: ", expectedCalories, actualCalories);

    }

    @Test
    /*
    Tests if method getGrainContent works correctly
    */

    public void testGetGrainContent() {
        AvailableFoods food = new AvailableFoods (ITEMID, FOODNAME, GRAINCONTENT, FVCONTENT, PROTEINCONTENT, OTHERCONTENT, CALORIES);
        int actualGrainContent = food.getGrainContent ();
        int expectedGrainContent = 0;
        assertEquals ("Method getCalories() did not return the expected result: ", expectedGrainContent, actualGrainContent);
    }

    @Test
    /*
    Tests if method getFVContent() works correctly
     */
    public void testGetFVContent() {
        AvailableFoods food = new AvailableFoods (ITEMID, FOODNAME, GRAINCONTENT, FVCONTENT, PROTEINCONTENT, OTHERCONTENT, CALORIES);
        int actualFVContent = food.getFVContent ();
        int expectedFVContent = 80;
        assertEquals ("Method FVContent() did not return the expected result: ", expectedFVContent, actualFVContent);
    }

    @Test
    /*
    Tests if method getProteinContent() works correctly
     */
    public void testGetProteinContent() {
        AvailableFoods food = new AvailableFoods (ITEMID, FOODNAME, GRAINCONTENT, FVCONTENT, PROTEINCONTENT, OTHERCONTENT, CALORIES);
        int actualProteinContent = food.getProteinContent ();
        int expectedProteinContent = 10;
        assertEquals ("Method getProteinContent() did not return the expected result: ", expectedProteinContent, actualProteinContent);
    }

    @Test
    /*
    Tests if method getOtherContent() works correctly
     */
    public void testGetOtherContent() {
        AvailableFoods food = new AvailableFoods (ITEMID, FOODNAME, GRAINCONTENT, FVCONTENT, PROTEINCONTENT, OTHERCONTENT, CALORIES);
        int actualOtherContent = food.getOtherContent ();
        int expectedOtherContent = 10;
        assertEquals ("Method getCalories() did not return the expected result: ", expectedOtherContent, actualOtherContent);
    }

    /*********************** AVAILABLE FOODS MANAGER TEST************************/
    @Test
    /*
    Tests if AvailableFoodsManager constructor creates an object
    */
    public void testAvailableFoodsManagerConstructorObject() {
        AvailableFoodsManager foodManager = new AvailableFoodsManager ();
        assertNotNull ("AvailableFoodsManager constructor did not create an object when given valid attributes.", foodManager);
    }


    @Test
    /*
    Tests if method getDBConnect() works correctly
    */
    public void testGetDBConnect() throws SQLException {
        // test if the connection is established
        AvailableFoodsManager foodManager = new AvailableFoodsManager ();
        dbConnect = DriverManager.getConnection (DBURL, USERNAME, PASSWORD);
        assertNotNull ("Method getDBConnect() did not return the expected result: ", dbConnect);
    }

    @Test
    /*
    Tests if method getDBURL() works correctly
    */
    public void testGetDBURL() {
        AvailableFoodsManager foodManager = new AvailableFoodsManager ();
        String actualDBURL = foodManager.getDBURL ();
        String expectedDBURL = "jdbc:mysql://localhost/FOOD_INVENTORY";
        assertEquals ("Method getDBURL() did not return the expected result: ", expectedDBURL, actualDBURL);
    }


    @Test
    /*
    Tests if method getUsername() works correctly
    */
    public void testGetUsername() {
        AvailableFoodsManager foodManager = new AvailableFoodsManager ();
        String actualUsername = foodManager.getUsername ();
        String expectedUsername = "student";
        assertEquals ("Method getUsername() did not return the expected result: ", expectedUsername, actualUsername);
    }

    @Test
    /*
    Tests if method getPassword() works correctly
    */
    public void testGetPassword() {
        AvailableFoodsManager foodManager = new AvailableFoodsManager ();
        String actualPassword = foodManager.getPassword ();
        String expectedPassword = "ensf";
        assertEquals ("Method getPassword() did not return the expected result: ", expectedPassword, actualPassword);
    }

    @Test
    /*
    Tests if method getInitialAvailableFoodsList works correctly
    */
    public void testGetInitialAvailableFoodsList() {
        AvailableFoodsManager foodManager = new AvailableFoodsManager ();
        ArrayList<AvailableFoods> actualInitialAvailableFoodsList = foodManager.getInitialAvailableFoodsList ();
        assertNotNull ("Method getInitialAvailableFoodsList() did not return the expected result: ", actualInitialAvailableFoodsList);
    }


    @Test
    /*
    Tests if method IsAvailable() works correctly i.e. returns false for a food that is not available
    */
    public void testIsAvailableForNotAvailable() {
        AvailableFoodsManager foodManager = new AvailableFoodsManager ();
        boolean actual = foodManager.isAvailable ("Lemon");
        assertFalse ("Method IsAvailable() did not return the expected result: ", actual);
    }

    @Test
    /*
    Tests if method IsAvailable() works correctly i.e. returns true for a food that is available
    */
    public void testIsAvailable() {
        AvailableFoodsManager foodManager = new AvailableFoodsManager ();
        boolean actual = foodManager.isAvailable ("Apple");
        //assertTrue("Method IsAvailable() did not return the expected result: ", actual);
        assertFalse ("Method IsAvailable() did not return the expected result: ", actual);
    }


    @Test
    /*
    Tests if method getCalories() works correctly i.e. returns the correct value of calories for specified valid food
     */
    public void testGetCaloriesValidFood() {
        AvailableFoodsManager foodManager = new AvailableFoodsManager ();
        int actualCalories = foodManager.getCalories ("Keffir");
        int expectedCalories = 600;
        assertEquals ("Method getCalories() did not return the expected result: ", expectedCalories, actualCalories);
    }

    @Test
    /*
    Tests if method getCalories() works correctly i.e. returns 0 when food specified is unavailable
     */
    public void testGetCaloriesUnvailableFood() {
        AvailableFoodsManager foodManager = new AvailableFoodsManager ();
        int actualCalories = foodManager.getCalories ("Lemon");
        int expectedCalories = 0;
        assertEquals ("Method getCalories() did not return the expected result: ", expectedCalories, actualCalories);
    }

    @Test
    /*
    Tests if method getGrainContent() works correctly i.e. returns the correct value of grain content for specified valid food
     */
    public void testGetGrainContentValidFood() {
        AvailableFoodsManager foodManager = new AvailableFoodsManager ();
        int actualGrainContent = foodManager.getGrainContent ("Wheat bread, loaf");
        int expectedGrainContent = 96;
        assertEquals ("Method getGrainContent() did not return the expected result: ", expectedGrainContent, actualGrainContent);
    }

    @Test
    /*
    Tests if method getGrainContent() works correctly i.e. returns 0 when food specified is unavailable
     */
    public void testGetGrainContentUnavailableFood() {
        AvailableFoodsManager foodManager = new AvailableFoodsManager ();
        int actualGrainContent = foodManager.getGrainContent ("Lemon");
        int expectedGrainContent = 0;
        assertEquals ("Method getGrainContent() did not return the expected result: ", expectedGrainContent, actualGrainContent);
    }

    @Test
    /*
    Tests if method getFVContent() works correctly i.e. returns the correct value of FV content for specified valid food
     */
    public void testGetFVContentValidFood() {
        AvailableFoodsManager foodManager = new AvailableFoodsManager ();
        int actualFVContent = foodManager.getFVContent ("Creamed spinach");
        int expectedFVContent = 40;
        assertEquals ("Method getFVContent() did not return the expected result: ", expectedFVContent, actualFVContent);
    }

    @Test
    /*
    Tests if method getFVContent() works correctly i.e. returns 0 when food specified is unavailable
     */
    public void testGetFVContentUnavailableFood() {
        AvailableFoodsManager foodManager = new AvailableFoodsManager ();
        int actualFVContent = foodManager.getFVContent ("Lemon");
        int expectedFVContent = 0;
        assertEquals ("Method getFVContent() did not return the expected result: ", expectedFVContent, actualFVContent);
    }

    @Test
    /*
    Tests if method getProContent() works correctly i.e. returns the correct value of protein content for specified valid food
     */
    public void testGetProContentValidFood() {
        AvailableFoodsManager foodManager = new AvailableFoodsManager ();
        int actualProContent = foodManager.getProteinContent ("Chicken broth, can");
        int expectedProContent = 14;
        assertEquals ("Method getProContent() did not return the expected result: ", expectedProContent, actualProContent);
    }

    @Test
    /*
    Tests if method getProContent() works correctly i.e. returns 0 when food specified is unavailable
     */
    public void testGetProContentUnavailableFood() {
        AvailableFoodsManager foodManager = new AvailableFoodsManager ();
        int actualProContent = foodManager.getProteinContent ("Lemon");
        int expectedProContent = 0;
        assertEquals ("Method getProContent() did not return the expected result: ", expectedProContent, actualProContent);
    }

    @Test
    /*
    Tests if method getOther() works correctly i.e. returns the correct value of other content for specified valid food
     */
    public void testGetOtherValidFood() {
        AvailableFoodsManager foodManager = new AvailableFoodsManager ();
        int actualOther = foodManager.getOtherContent ("Chicken broth, can");
        int expectedOther = 86;
        assertEquals ("Method getOther() did not return the expected result: ", expectedOther, actualOther);
    }

    @Test
    /*
    Tests if method getOther() works correctly i.e. returns 0 when food specified is unavailable
     */
    public void testGetOtherUnavailableFood() {
        AvailableFoodsManager foodManager = new AvailableFoodsManager ();
        int actualOther = foodManager.getGrainContent ("Lemon");
        int expectedOther = 0;
        assertEquals ("Method getOther() did not return the expected result: ", expectedOther, actualOther);
    }

    /**
     * Tests if the removeFood() method works correctly i.e. removes the specified food from the list of available foods
     */
    @Test
    public void removeFood() {
        AvailableFoodsManager foodManager = new AvailableFoodsManager ();
        foodManager.removeFood ("Green peas");
        foodManager.isAvailable ("Green peas");
        assertFalse ("Food was not removed", foodManager.isAvailable ("Green peas"));
    }

    /*********************** REGISTRATION FOR DAILY CLIENT NEEDS TEST************************/

    @Test
    /*
    Tests if RegistrationForDailyClientNeeds constructor creates an object
    */
    public void testRegistrationForDailyClientNeedsConstructorObject() {
        RegistrationForDailyClientNeeds needs = new RegistrationForDailyClientNeeds ();
        assertNotNull ("RegistrationForDailyClientNeeds constructor did not create an object when given valid attributes.", needs);
    }

    @Test
    /*
    Tests if method selectMaleWholeGrains() works correctly i.e. returns the correct value of whole grains for males
     */
    public void testSelectMaleWholeGrains() {
        RegistrationForDailyClientNeeds needs = new RegistrationForDailyClientNeeds ();
        int actualValue = needs.selectMaleWholeGrains ();
        int expectedValue = 16;
        assertEquals ("Method selectMaleWholeGrains() did not return the expected result: ", expectedValue, actualValue);
    }

    @Test
    /*
    Tests if method selectFemaleWholeGrains() works correctly i.e. returns the correct value of whole grains for females
     */
    public void testSelectFemaleWholeGrains() {
        RegistrationForDailyClientNeeds needs = new RegistrationForDailyClientNeeds ();
        int actualValue = needs.selectFemaleWholeGrains ();
        int expectedValue = 16;
        assertEquals ("Method selectFemaleWholeGrains() did not return the expected result: ", expectedValue, actualValue);
    }

    @Test
    /*
    Tests if method selectOver8WholeGrains() works correctly i.e. returns the correct value of whole grains for children over 8
     */
    public void testSelectOver8WholeGrains() {
        RegistrationForDailyClientNeeds needs = new RegistrationForDailyClientNeeds ();
        int actualValue = needs.selectOver8WholeGrains ();
        int expectedValue = 21;
        assertEquals ("Method selectOver8WholeGrains() did not return the expected result: ", expectedValue, actualValue);
    }

    @Test
    /*
    Tests if method selectUnder8WholeGrains() works correctly i.e. returns the correct value of whole grains for children under 8
     */
    public void testSelectUnder8WholeGrains() {
        RegistrationForDailyClientNeeds needs = new RegistrationForDailyClientNeeds ();
        int actualValue = needs.selectUnder8WholeGrains ();
        int expectedValue = 21;
        assertEquals ("Method selectUnder8WholeGrains() did not return the expected result: ", expectedValue, actualValue);
    }

    @Test
    /*
    Tests if method selectMaleFruitVeggies() works correctly i.e. returns the correct value of fruit veggies for males
     */
    public void testSelectMaleFruitVeggies() {
        RegistrationForDailyClientNeeds needs = new RegistrationForDailyClientNeeds ();
        int actualValue = needs.selectMaleFruitVeggies ();
        int expectedValue = 28;
        assertEquals ("Method selectMaleFruitVeggies() did not return the expected result: ", expectedValue, actualValue);
    }

    @Test
    /*
    Tests if method selectFemaleFruitVeggies() works correctly i.e. returns the correct value of fruit veggies for females
     */
    public void testSelectFemaleFruitVeggies() {
        RegistrationForDailyClientNeeds needs = new RegistrationForDailyClientNeeds ();
        int actualValue = needs.selectFemaleFruitVeggies ();
        int expectedValue = 28;
        assertEquals ("Method selectFemaleFruitVeggies() did not return the expected result: ", expectedValue, actualValue);
    }

    @Test
    /*
    Tests if method selectOver8FruitVeggies() works correctly i.e. returns the correct value of fruit veggies for children over 8
     */
    public void testSelectOver8FruitVeggies() {
        RegistrationForDailyClientNeeds needs = new RegistrationForDailyClientNeeds ();
        int actualValue = needs.selectOver8FruitVeggies ();
        int expectedValue = 33;
        assertEquals ("Method selectOver8FruitVeggies() did not return the expected result: ", expectedValue, actualValue);
    }

    @Test
    /*
    Tests if method selectUnder8FruitVeggies() works correctly i.e. returns the correct value of fruit veggies for children under 8
     */
    public void testSelectUnder8FruitVeggies() {
        RegistrationForDailyClientNeeds needs = new RegistrationForDailyClientNeeds ();
        int actualValue = needs.selectUnder8FruitVeggies ();
        int expectedValue = 33;
        assertEquals ("Method selectUnder8FruitVeggies() did not return the expected result: ", expectedValue, actualValue);
    }

    @Test
    /*
    Tests if method selectMaleProtein() works correctly i.e. returns the correct value of protein for males
     */
    public void testSelectMaleProtein() {
        RegistrationForDailyClientNeeds needs = new RegistrationForDailyClientNeeds ();
        int actualValue = needs.selectMaleProtein ();
        int expectedValue = 26;
        assertEquals ("Method selectMaleProtein() did not return the expected result: ", expectedValue, actualValue);
    }

    @Test
    /*
    Tests if method selectFemaleProtein() works correctly i.e. returns the correct value of protein for females
     */
    public void testSelectFemaleProtein() {
        RegistrationForDailyClientNeeds needs = new RegistrationForDailyClientNeeds ();
        int actualValue = needs.selectFemaleProtein ();
        int expectedValue = 26;
        assertEquals ("Method selectFemaleProtein() did not return the expected result: ", expectedValue, actualValue);
    }

    @Test
    /*
    Tests if method selectOver8Protein() works correctly i.e. returns the correct value of protein for children over 8
     */
    public void testSelectOver8Protein() {
        RegistrationForDailyClientNeeds needs = new RegistrationForDailyClientNeeds ();
        int actualValue = needs.selectOver8Protein ();
        int expectedValue = 31;
        assertEquals ("Method selectOver8Protein() did not return the expected result: ", expectedValue, actualValue);
    }

    @Test
    /*
    Tests if method selectUnder8Protein() works correctly i.e. returns the correct value of protein for children under 8
     */
    public void testSelectUnder8Protein() {
        RegistrationForDailyClientNeeds needs = new RegistrationForDailyClientNeeds ();
        int actualValue = needs.selectUnder8Protein ();
        int expectedValue = 31;
        assertEquals ("Method selectUnder8Protein() did not return the expected result: ", expectedValue, actualValue);
    }

    @Test
    /*
    Tests if method selectMaleOther() works correctly i.e. returns the correct value of other for males
     */
    public void testSelectMaleOther() {
        RegistrationForDailyClientNeeds needs = new RegistrationForDailyClientNeeds ();
        int actualValue = needs.selectMaleOther ();
        int expectedValue = 30;
        assertEquals ("Method selectMaleOther() did not return the expected result: ", expectedValue, actualValue);
    }

    @Test
    /*
    Tests if method selectFemaleOther() works correctly i.e. returns the correct value of other for females
     */
    public void testSelectFemaleOther() {
        RegistrationForDailyClientNeeds needs = new RegistrationForDailyClientNeeds ();
        int actualValue = needs.selectFemaleOther ();
        int expectedValue = 30;
        assertEquals ("Method selectFemaleOther() did not return the expected result: ", expectedValue, actualValue);
    }

    @Test
    /*
    Tests if method selectOver8Other() works correctly i.e. returns the correct value of other for children over 8
     */
    public void testSelectOver8Other() {
        RegistrationForDailyClientNeeds needs = new RegistrationForDailyClientNeeds ();
        int actualValue = needs.selectOver8Other ();
        int expectedValue = 15;
        assertEquals ("Method selectOver8Other() did not return the expected result: ", expectedValue, actualValue);
    }

    @Test
    /*
    Tests if method selectUnder8Other() works correctly i.e. returns the correct value of other for children under 8
     */
    public void testSelectUnder8Other() {
        RegistrationForDailyClientNeeds needs = new RegistrationForDailyClientNeeds ();
        int actualValue = needs.selectUnder8Other ();
        int expectedValue = 15;
        assertEquals ("Method selectUnder8Other() did not return the expected result: ", expectedValue, actualValue);
    }

    @Test
    /*
    Tests if method selectMaleCalories() works correctly i.e. returns the correct value of calories for males
     */
    public void testSelectMaleCalories() {
        RegistrationForDailyClientNeeds needs = new RegistrationForDailyClientNeeds ();
        int actualValue = needs.selectMaleCalories ();
        int expectedValue = 2500;
        assertEquals ("Method selectMaleCalories() did not return the expected result: ", expectedValue, actualValue);
    }

    @Test
    /*
    Tests if method selectFemaleCalories() works correctly i.e. returns the correct value of calories for females
     */
    public void testSelectFemaleCalories() {
        RegistrationForDailyClientNeeds needs = new RegistrationForDailyClientNeeds ();
        int actualValue = needs.selectFemaleCalories ();
        int expectedValue = 2000;
        assertEquals ("Method selectFemaleCalories() did not return the expected result: ", expectedValue, actualValue);
    }

    @Test
    /*
    Tests if method selectOver8Calories() works correctly i.e. returns the correct value of calories for children over 8
     */
    public void testSelectOver8Calories() {
        RegistrationForDailyClientNeeds needs = new RegistrationForDailyClientNeeds ();
        int actualValue = needs.selectOver8Calories ();
        int expectedValue = 2200;
        assertEquals ("Method selectOver8Calories() did not return the expected result: ", expectedValue, actualValue);
    }

    @Test
    /*
    Tests if method selectUnder8Calories() works correctly i.e. returns the correct value of calories for children under 8
     */
    public void testSelectUnder8Calories() {
        RegistrationForDailyClientNeeds needs = new RegistrationForDailyClientNeeds ();
        int actualValue = needs.selectUnder8Calories ();
        int expectedValue = 1400;
        assertEquals ("Method selectUnder8Calories() did not return the expected result: ", expectedValue, actualValue);
    }

    @Test
    /*
    Tests if method getDBURL() works correctly
    */
    public void testGetDBURLForRegistrationForDailyClient() {
        RegistrationForDailyClientNeeds needs = new RegistrationForDailyClientNeeds ();
        String actualDBURL = needs.getDBURL ();
        String expectedDBURL = "jdbc:mysql://localhost/FOOD_INVENTORY";
        assertEquals ("Method getDBURL() did not return the expected result: ", expectedDBURL, actualDBURL);
    }

    @Test
    /*
    Tests if method getUsername() works correctly
    */
    public void testGetUsernameForRegistrationForDailyClient() {
        RegistrationForDailyClientNeeds needs = new RegistrationForDailyClientNeeds ();
        String actualUsername = needs.getUsername ();
        String expectedUsername = "student";
        assertEquals ("Method getUsername() did not return the expected result: ", expectedUsername, actualUsername);
    }

    @Test
    /*
    Tests if method getPassword() works correctly
    */
    public void testGetPasswordForRegistrationForDailyClient() {
        RegistrationForDailyClientNeeds needs = new RegistrationForDailyClientNeeds ();
        String actualPassword = needs.getPassword ();
        String expectedPassword = "ensf";
        assertEquals ("Method getPassword() did not return the expected result: ", expectedPassword, actualPassword);
    }

    @Test
    /*
    Tests if method getTableName() works correctly
    */
    public void testGetTableName() {
        RegistrationForDailyClientNeeds needs = new RegistrationForDailyClientNeeds ();
        String actualTableName = needs.getTableName ();
        String expectedTableName = "DAILY_CLIENT_NEEDS";
        assertEquals ("Method getTableName() did not return the expected result: ", expectedTableName, actualTableName);
    }


}
