package edu.ucalgary.ensf409;

/**
 * AvailableFoods class creates an object that stores the available foods
 *
 * @author Maarya Ahmed <a href="mailto:maarya.ahmed@ucalgary.ca">maarya.ahmed@ucalgary.ca</a>
 * @author Noor Nawaz <a href="mailto:noor.nawaz@ucalgary.ca">noor.nawaz@ucalgary.ca</a>
 * @author Tamunomiete Brown <a href="mailto:tamunomiete.brown@ucalgary.ca">tamunomiete.brown@ucalgary.ca</a>
 * @author Sadia Khandaker <a href="mailto:khandaker.tahsin@ucalgary.ca">khandaker.tahsin@ucalgary.ca</a>
 * @version 1.6
 * @since 1.0
 */
public class AvailableFoods {
    private final int ITEMID; // item id
    private final String FOODNAME; // food name
    private final int GRAINCONTENT; // grain content
    private final int FVCONTENT; // fruit and veggies content
    private final int PROTEINCONTENT; // protein content
    private final int OTHERCONTENT; // other content
    private final int CALORIES; // calories

    /**
     * Constructor for AvailableFoods class. It takes in the item id, food name, grain content, fruit and veggies content, protein content, other content, and calories, and stores them in the object.
     *
     * @param itemID         - item id
     * @param foodName       - food name
     * @param grainContent   - grain content
     * @param fvContent      - fruit and veggies content
     * @param proteinContent - protein content
     * @param otherContent   - other content
     * @param calories       - calories
     */
    public AvailableFoods(int itemID, String foodName, int grainContent, int fvContent, int proteinContent, int otherContent, int calories) {
        this.ITEMID = itemID;
        this.FOODNAME = foodName;
        this.GRAINCONTENT = grainContent;
        this.FVCONTENT = fvContent;
        this.PROTEINCONTENT = proteinContent;
        this.OTHERCONTENT = otherContent;
        this.CALORIES = calories;
    }


    /**
     * Getter for item id
     *
     * @return - item id
     */
    public int getItemID() {
        return ITEMID;
    }

    /**
     * Getter for food name
     *
     * @return - food name
     */
    public String getFoodName() {
        return FOODNAME;
    }

    /**
     * Getter for grain content value
     *
     * @return - grain content value
     */
    public int getGrainContent() {
        return GRAINCONTENT;
    }

    /**
     * Getter for fruit and veggies content value
     *
     * @return - fruit and veggies content value
     */
    public int getFVContent() {
        return FVCONTENT;
    }

    /**
     * Getter for protein content value
     *
     * @return - protein content value
     */
    public int getProteinContent() {
        return PROTEINCONTENT;
    }

    /**
     * Getter for other content value
     *
     * @return - other content value
     */
    public int getOtherContent() {
        return OTHERCONTENT;
    }

    /**
     * Getter for calories
     *
     * @return - calories
     */
    public int getCalories() {
        return CALORIES;
    }

}


