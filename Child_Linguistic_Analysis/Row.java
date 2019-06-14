/**
 * filename: Row.java
 * description: Initializes Row class
 * date: 01/09/19
 * @author Angelina Li
 *
 * NOTE: Do NOT modify this class.
 */

import java.util.HashMap;

public class Row {
    
    private final String definition, type, category;
    private final int num_item_id;
    private final HashMap<String, Double> data;

    /**
     * Constructor for Row class. 
     * Each Row represents data on the city-year level.
     */
    public Row(
        int num_item_id,
        String definition,
        String type,
        String category,
        double month16,
        double month17,
        double month18,
        double month19,
        double month20,
        double month21,
        double month22,
        double month23,
        double month24,
        double month25,
        double month26,
        double month27,
        double month28,
        double month29,
        double month30
    
    ) {
        this.num_item_id = num_item_id;
        this.definition = definition;
        this.type = type;
        this.category = category;

        this.data = new HashMap<String, Double>();
        this.data.put("month.16.pct", month16);
        this.data.put("month.17.pct", month17);
        this.data.put("month.18.pct", month18);
        this.data.put("month.19.pct", month19);
        this.data.put("month.20.pct", month20);
        this.data.put("month.21.pct", month21);
        this.data.put("month.22.pct", month22);
        this.data.put("month.23.pct", month23);
        this.data.put("month.24.pct", month24);
        this.data.put("month.25.pct", month25);
        this.data.put("month.26.pct", month26);
        this.data.put("month.27.pct", month27);
        this.data.put("month.28.pct", month28);
        this.data.put("month.29.pct", month29);
        this.data.put("month.30.pct", month30);
    }

    /**
     * Getter method for name variable.
     * @return name data for this Row instance.
     */
    public final int getNumItem() {
        return this.num_item_id;
    }

    /**
     * Getter method for state variable.
     * @return state data for this Row instance.
     */
    public final String getDefinition() {
        return this.definition;
    }

    /**
     * Getter method for year variable.
     * @return year data for this Row instance.
     */
    public final String getType() {
        return this.type;
    }

    /**
     * Getter method for year variable.
     * @return year data for this Row instance.
     */
    public final String getCategory() {
        return this.category;
    }
    
    /**
     * Getter method for all numerical values associated with this row.
     * @return numerical value associated with this row.
     */
    public final double getDataValue(String variableName) {
        if( this.data.containsKey(variableName) ) {
            return this.data.get(variableName);
        }
        throw new IllegalArgumentException(
            "Row objects do not contain the variable '" + variableName + "'!");
    }

    /**
     * Returns a String representing all the data in this Row instance.
     @return a String representation of this Row.
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("definition=" + this.definition + ", ");
        sb.append("item number id="); sb.append(String.valueOf(this.num_item_id));
        sb.append("type=" + this.type + ", ");
        sb.append("category=" + this.category + ", ");
        sb.append("}");
        return sb.toString();
    }
}