/**
 * Project: I'm a Big Kid Now (Avery Kim, Carmen Chan, Mehek Khandelwal)
 * filename: Reader.java
 * description: Takes a CSV datafile and converts it into a Vector<Row>.
 * date: 01/09/19
 * @author Angelina Li, 
 * @modified: Avery Kim
 */

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Vector;
import fastcsv.*;

public class Reader {
    //initialize instance variables
    //contains a Row for each word datapoint in the dataset
    public final Vector<Row> data;

    /**
     * Constructor for Reader class. Given a path to where child linguistic data is being
     * stored on your local machine, as well as a number of rows desired, will
     * initialize a Vector<Row> containing a datapoint for each word in the dataset.
     *
     * @param filepath - A filepath representing where the csv data file 
     *                   containing child linguistic data is kept. This can be either a
     *                   relative or absolute filepath - e.g. "linguistics.csv" or
     *                   "Users/angie/Desktop/finalProject/linguistics.csv".
     * @param numRows -  The number of rows desired. Note that if you specify
     *                   more rows of data than exist in the dataset, you will
     *                   get the entire dataset.
     */
    public Reader(String filepath, int numRows) throws IOException {
        this.data = new Vector<Row>(); 

        File file = new File(filepath);
        CsvReader reader = new CsvReader();
        reader.setContainsHeader(true);

        try (CsvParser parser = reader.parse(file, StandardCharsets.UTF_8)) {
            CsvRow dataRow;
            int currentNumberRows = 0;
            while ( ((dataRow = parser.nextRow()) != null) && 
                    currentNumberRows < numRows ) {
                Row row = new Row(
                    getIntField(dataRow, "num_item_id"),
                    dataRow.getField("definition"),
                    dataRow.getField("type"),
                    dataRow.getField("category"),
                    getDoubleField(dataRow, "month.16.pct"),
                    getDoubleField(dataRow, "month.17.pct"),
                    getDoubleField(dataRow, "month.18.pct"),
                    getDoubleField(dataRow, "month.19.pct"),
                    getDoubleField(dataRow, "month.20.pct"),
                    getDoubleField(dataRow, "month.21.pct"),
                    getDoubleField(dataRow, "month.22.pct"),
                    getDoubleField(dataRow, "month.23.pct"),
                    getDoubleField(dataRow, "month.24.pct"),
                    getDoubleField(dataRow, "month.25.pct"),
                    getDoubleField(dataRow, "month.26.pct"),
                    getDoubleField(dataRow, "month.27.pct"),
                    getDoubleField(dataRow, "month.28.pct"),
                    getDoubleField(dataRow, "month.29.pct"),
                    getDoubleField(dataRow, "month.30.pct")
                );
                data.add(row);
                currentNumberRows++;
            }
        }
    }

    private static int getIntField(CsvRow row, String field) {
        return Integer.parseInt(row.getField(field));
    }

    private static double getDoubleField(CsvRow row, String field) {
        return Double.parseDouble(row.getField(field));
    }

    public final Vector<Row> getDataset() {
        return this.data;
    }
}