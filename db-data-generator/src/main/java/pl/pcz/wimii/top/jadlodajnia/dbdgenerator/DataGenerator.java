package pl.pcz.wimii.top.jadlodajnia.dbdgenerator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataGenerator {

    private static final String fPath = "E:\\dev\\@top\\jd_m\\android\\Jadlodajnia_mobile\\android_app\\Jadlodajnia\\app\\src\\main\\java\\pl\\pcz\\wimii\\top\\jadlodajnia\\GetStartedActivity.java";

    public static void main(String[] args) {

        File fOld = new File(fPath);


    }

    public void run() {

        String csvFile = "/Users/mkyong/Downloads/GeoIPCountryWhois.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);

                System.out.println("Country [code= " + country[4]
                        + " , name=" + country[5] + "]");

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Done");
    }

}
