package server;

import data.RestaurantDatabase;
import data.Food;

import java.io.*;
import java.util.List;


public class FileOperations {
    private String inputName;
    private String input2Name;
    private String OutputName;

    public FileOperations() {
        inputName = "restaurant.txt";
        input2Name = "menu.txt";
    }

    public void readFromFile(data.RestaurantDatabase db) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputName));
        while (true) {
            String line = br.readLine();
            if (line == null) break;

            String[] array = line.split(",", -1);
            //data.Restaurant r = new data.Restaurant();
            if (array.length >= 6 && array.length <= 8) {
                int id = Integer.parseInt(array[0]);
                double score = Double.parseDouble(array[2]);

                if (array.length == 6) {
                    data.Restaurant r = new data.Restaurant(id, array[1], score, array[3], array[4], array[5]);
                    int c = db.addRestaurantToList(r);
                } else if (array.length == 7) {
                    data.Restaurant r = new data.Restaurant(id, array[1], score, array[3], array[4], array[5], array[6]);
                    int c = db.addRestaurantToList(r);
                } else {
                    data.Restaurant r = new data.Restaurant(id, array[1], score, array[3], array[4], array[5], array[6], array[7]);
                    int c = db.addRestaurantToList(r);
                }
                //int check = db.addRestaurantToList(r);
            }
        }
    }

    public void readMenu(data.RestaurantDatabase db) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(input2Name));
        while (true) {
            String line = br.readLine();
            if (line == null) break;

            String[] array = line.split(",", -1);
            if (array.length == 4) {
                int id = Integer.parseInt(array[0]);
                double price = Double.parseDouble(array[3]);
                Food f = new Food(id, array[1], array[2], price);
                db.addFoodToList(f);
            }
        }
    }
}
