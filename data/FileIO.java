package data;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;


public class FileIO {
    public static String restaurantDB;
    public static String foodDB;
    //private static String OUTPUT_FILE_NAME;
    List<String> finalCategory;


    FileIO() {

        finalCategory = new ArrayList<>();

    }

    List<Restaurant> loadRestaurantInfo(String res) throws Exception {
        List<Restaurant> ResList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(res))) {
            while (true) {
                String line = br.readLine();
                if (line == null) break;

                String[] array = line.split(",", -1);
                if (array.length >= 6 && array.length <= 8) {
                    int id = Integer.parseInt(array[0]);
                    double score = Double.parseDouble(array[2]);

                    if (array.length == 6) {
                        Restaurant r = new Restaurant(id, array[1], score, array[3], array[4], array[5]);
                        ResList.add(r);
                    } else if (array.length == 7) {
                        Restaurant r = new Restaurant(id, array[1], score, array[3], array[4], array[5], array[6]);
                        ResList.add(r);
                    } else {
                        Restaurant r = new Restaurant(id, array[1], score, array[3], array[4], array[5], array[6], array[7]);
                        ResList.add(r);
                    }
                }
            }
        }
        return ResList;
    }

    List<Food> loadFoodList(String food) throws Exception {
        List<Food> foods = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(food))) {
            while (true) {
                String line = br.readLine();
                if (line == null) break;

                String[] array = line.split(",", -1);
                if (array.length == 4) {
                    int id = Integer.parseInt(array[0]);
                    double price = Double.parseDouble(array[3]);
                    Food f = new Food(id, array[1], array[2], price);
                    foods.add(f);
                }
            }
            br.close();
        }
        return foods;
    }

}