import utils.CSVFileReader;
import utils.CSVFileWriter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainCSV {
    public static void main(String[] args){

        String[][] table = new String[10][3];
        table[0][0] = "id";
        table[0][1] = "price";
        table[0][2] = "amount";

        for(int i = 1; i < 10; i++){
            table[i][0] = String.valueOf(i);
        }

        for(int i=1; i < 10; i++){
            for(int j = 1; j < 3; j++){
                table[i][j] = String.valueOf(i+j);
            }
        }

        try(CSVFileWriter csvWriter = new CSVFileWriter(new FileWriter("data.csv"), table)){
            csvWriter.writeData();
        } catch (IOException e) {
            System.out.println(e.getMessage() + "Problems with writing data.");
        }

        try(CSVFileReader csvReader = new CSVFileReader(new FileReader("data.csv"))){
            String[][] data = csvReader.readData();
            for(int i = 0; i < data.length; i++){
                for(int j = 0; j < data[i].length; j++){
                    System.out.print(data[i][j] + " ");
                }
                System.out.println();
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage() + "Unknown file.");
        } catch (IOException e) {
            System.out.println(e.getMessage() + "Problems with writing data.");
        }
    }
}
