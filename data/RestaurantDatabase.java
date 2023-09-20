package data;

import jdk.jfr.Category;

import java.io.*;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class RestaurantDatabase{
    List<Restaurant> ResList;
    List<Food> foods;
    public RestaurantDatabase() throws Exception {

        ResList=new ArrayList<>();

        foods = new ArrayList<>();
    }
    public List<Restaurant> getResList()
    {
        return ResList;
    }
    public void setResList(List<Restaurant> resList)
    {
        this.ResList = resList;
    }
    public void setFoodList(List<Food> flist)
    {
        this.foods = flist;
    }
    public List<Food> getFoods()
    {
        return foods;
    }
    public int addRestaurantToList(Restaurant r)
    {
        ResList.add(r);
        return 1;
    }
    public int addFoodToList(Food f)
    {
        foods.add(f);
        updateResList(f);
        return 1;
    }
    public AllFood getAll(){
        AllFood o = new AllFood(foods);
        return o;
    }
    public AllFood getFoodInRes(String resName, String fName)
    {
        List<Food> f = searchResToFood(resName, fName);
        return new AllFood(f);
    }
    public AllFood getAllByName(String name)
    {
        List<Food> f = searchFoodsByName(name);
        return new AllFood(f);
    }
    public static boolean isStringInSetIgnoreCase(Set<String> stringSet, String targetString) {
        for (String str : stringSet) {
            if (str.equalsIgnoreCase(targetString)) {
                return true;
            }
        }
        return false;
    }
    Set<String> generateSetForCategory(List<Restaurant>r) {
        Set<String> unorderedSet = new HashSet<>();
        for (Restaurant rf : r) {
            for (String s : rf.getRestaurantCategories()) {
                if (!isStringInSetIgnoreCase(unorderedSet, s)) {
                    unorderedSet.add(s);
                }
            }
        }
        return  unorderedSet;
    }
    public Map<String, List<Restaurant>> generateMap(){
        Map<String, List<Restaurant>>categoryToRestaurantsMap = new HashMap<>();
        Set<String> unorderedSet = generateSetForCategory(ResList);

        for (String s:unorderedSet){
            List<Restaurant> res = new ArrayList<>();
            for(Restaurant r : ResList)
            {
                if(r.getRestaurantCategories().contains(s))
                {
                    res.add(r);
                }
            }
            categoryToRestaurantsMap.put(s, res);
        }
        return  categoryToRestaurantsMap;
    }

    public static boolean isIntegerInSetIgnoreCase(Set<Integer> IntSet, int id) {
        for (int i : IntSet) {
            if (i == id) {
                return true;
            }
        }
        return false;
    }


    Set<Integer> generateSetForResID(List<Restaurant> r)
    {
        Set<Integer> unorderedSet = new HashSet<>();
        for(Restaurant res : r)
        {
            int id = res.getRestaurantId();
            if(!isIntegerInSetIgnoreCase(unorderedSet, id))
            {
                unorderedSet.add(id);
            }
        }
        return unorderedSet;
    }


    int ResIdFromName(String resName)
    {
        for(Restaurant r : ResList)
        {
            if(r.getRestaurantName().equalsIgnoreCase(resName))
            {
                return r.getRestaurantId();
            }
        }
        return -1;
    }

    List<Food> getFoodFromResToCat (String catName, String resName)
    {
        catName = catName.toLowerCase();
        int id = ResIdFromName(resName);
        Map<Integer, List<Food>> resToFood = generateMapForRestaurants();
        List<Food> foodInCat = new ArrayList<>();
        List<Food> foodInRes = resToFood.get(id);
        for(Food f : foodInRes)
            {
                if(f.getFoodCategory().equalsIgnoreCase(catName))
                {
                    foodInCat.add(f);
                }
            }
        return foodInCat;
    }
    public AllFood getCatInRes(String rName, String catName)
    {
        List<Food> f = getFoodFromResToCat(catName, rName);
        return new AllFood(f);
    }
    public Map<Integer, List<Food>> generateMapForRestaurants(){
        Map<Integer, List<Food>> restaurantToFoodMap = new HashMap<>();
        Set<Integer> setForResId = generateSetForResID(ResList);
        for(int i : setForResId)
        {
            List<Food> foodInRes = new ArrayList<>();
            for(Food food : foods)
            {
                if(food.getFoodId() == i)
                {
                    foodInRes.add(food);
                }
            }
            restaurantToFoodMap.put(i, foodInRes);
        }
        return restaurantToFoodMap;
    }
    void printByCategory()
    {
        Map <String, List<Restaurant>> m = generateMap();
        for(String category : m.keySet()) {
            List<Restaurant> restaurantsInCat = m.get(category);
            System.out.print(category + ": ");
            for (Restaurant rst : restaurantsInCat) {
                System.out.print(rst.getRestaurantName() + " ");
            }
            System.out.println();
        }
    }

    List<Food> searchResToFood(String resName, String foodName)
    {
        int resId = ResIdFromName(resName);
        Map<Integer, List<Food>> m = generateMapForRestaurants();
        foodName = foodName.toLowerCase();
        List<Food> found = new ArrayList<>();
        for(Integer i : m.keySet())
        {
            if(i == resId)
            {
                List<Food> foodls = m.get(i);
                for(Food f : foodls)
                {
                    if(f.getFoodName().toLowerCase().contains(foodName))
                    {
                        found.add(f);
                    }
                }
            }
        }

        return found;
    }
    void addRestaurant(Restaurant r)
    {
        ResList.add(r);
    }
    void addFood(Food f)
    {
        foods.add(f);
    }
    public List<Restaurant> searchRestaurantsByName(String name) {
        List<Restaurant> matchingRestaurants = new ArrayList<>();
        name = name.toLowerCase();
        for (Restaurant restaurant : ResList) {
            if (restaurant.getRestaurantName().toLowerCase().contains(name)) {
                matchingRestaurants.add(restaurant);
            }
        }
        return matchingRestaurants;
    }
    public List<Restaurant> searchRestaurantsByScore(double lowerRange, double upperRange)
    {
        List<Restaurant> matchingRestaurantsByScore = new ArrayList<>();

        for(Restaurant restaurant : ResList)
        {
            if((lowerRange <= restaurant.getRestaurantScore()) && (upperRange >= restaurant.getRestaurantScore()))
            {
                matchingRestaurantsByScore.add(restaurant);
            }
        }
        return matchingRestaurantsByScore;
    }
    public List<Restaurant> searchRestaurantsByCategory(String category)
    {
        List<Restaurant> matchingRestaurantsByCategory = new ArrayList<>();
        List<String> restaurantCategory;
        category = category.toLowerCase();
        for(Restaurant restaurant : ResList)
        {
            restaurantCategory = restaurant.getRestaurantCategories();
            for(String cat : restaurantCategory)
            {
                if(cat.toLowerCase().contains(category))
                {
                    matchingRestaurantsByCategory.add(restaurant);
                    break;
                }
            }
        }
        return matchingRestaurantsByCategory;
    }
    private void updateResList(Food f)
    {
        for(Restaurant r : ResList)
        {
            if(r.getRestaurantId() == f.getFoodId())
            {
                r.addFood(f);
                return;
            }
        }
    }
    List<Restaurant> searchRestaurantsByPrice(String price)
    {
        List<Restaurant> matchingRestaurantsByPrice = new ArrayList<>();
        for(Restaurant restaurant : ResList)
        {
            if(restaurant.getRestaurantPrice().equals(price))
            {
                matchingRestaurantsByPrice.add(restaurant);
            }
        }
        return matchingRestaurantsByPrice;
    }
    List<Restaurant> searchByZipCodes(String Zipcode)
    {
        List<Restaurant> matchingRestaurantsByZip = new ArrayList<>();
        for(Restaurant restaurant : ResList)
        {
            if(restaurant.getRestaurantZip().equals(Zipcode))
            {
                matchingRestaurantsByZip.add(restaurant);
            }
        }
        return matchingRestaurantsByZip;
    }

    List<Food> searchFoodsByName(String foodName)
    {
        List<Food> matchedFoods = new ArrayList<>();
        foodName = foodName.toLowerCase();
        for(Food f : foods)
        {
            if(f.getFoodName().toLowerCase().contains(foodName))
            {
                matchedFoods.add(f);
            }
        }
        return matchedFoods;
    }
    /*public List<Food> searchFoodInRes(String Resname)
    {
        int resID = ResIdFromName(Resname);
        Map<Integer, List<Food>> m = generateMapForRestaurants();
        List<Food> f = m.get(resID);
        return f;
    }*/

    List<Food> searchFoodByCategory(String category)
    {
        List<Food> matchedFoods = new ArrayList<>();
        category = category.toLowerCase();
        for(Food f : foods)
        {
            if(f.getFoodCategory().toLowerCase().contains(category))
            {
                matchedFoods.add(f);
            }
        }
        return matchedFoods;
    }
    public AllFood getCat(String cat)
    {
        List<Food> f = searchFoodByCategory(cat);
        return new AllFood(f);
    }

    List<Food> searchFoodByPriceRange(double low, double high)
    {
        List<Food> matchingFoodsByPrice = new ArrayList<>();

        for(Food f : foods)
        {
            if((low <= f.getFoodPrice()) && (high >= f.getFoodPrice()))
            {
                matchingFoodsByPrice.add(f);
            }
        }
        return matchingFoodsByPrice;
    }

    public AllFood getPrice(int high, int low)
    {
        List<Food> f = searchFoodByPriceRange(low, high);
        return new AllFood(f);
    }

    List<Food> searchFoodByPriceInRestaurant(String resName, double lower, double upper)
    {
        int resID = ResIdFromName(resName);
        Map<Integer, List<Food>> mapForResToFood = generateMapForRestaurants();
        List<Food> foodInRes = mapForResToFood.get(resID);
        List<Food> foodInRange = new ArrayList<>();
        for(Food food : foodInRes)
        {
            if((lower <= food.getFoodPrice()) && (upper >= food.getFoodPrice()))
            {
                foodInRange.add(food);
            }
        }
        return foodInRange;
    }
    List<Food> searchCostliestFood(String resName)
    {
        int id = ResIdFromName(resName);
        Map<Integer, List<Food>> mapForFood = generateMapForRestaurants();
        List<Food> foodInRes = mapForFood.get(id);
        List<Food> costliestFood = new ArrayList<>();
        double max = 0;
        for(Food f : foodInRes)
        {
            if(max < f.getFoodPrice())
            {
                max = f.getFoodPrice();
            }
        }
        for(Food f : foodInRes)
        {
            if (max == f.getFoodPrice())
            {
                costliestFood.add(f);
            }
        }
        return costliestFood;
    }

    String getResNameFromID(int i)
    {
        String s = "";
        for(Restaurant r : ResList)
        {
            if(r.getRestaurantId() == i)
            {
                s = r.getRestaurantName();
                break;
            }
        }
        return s;
    }
    void printAllFoodInRes()
    {
        Map<Integer, List<Food>> map = generateMapForRestaurants();
        for(Integer i : map.keySet())
        {
            List<Food> food = map.get(i);
            System.out.println(getResNameFromID(i) + ": " + food.size());
        }
    }
    void printList(List<Restaurant> r)
    {
        if(!r.isEmpty()) {
            for (Restaurant re : r) {
                System.out.println("Restaurant ID: " + re.getRestaurantId());
                System.out.println("Restaurant Name: " + re.getRestaurantName());
                System.out.println("Restaurant Price: " + re.getRestaurantPrice());
                System.out.println("Restaurant Score: " + re.getRestaurantScore());
                System.out.println("Restaurant ZipCode: " + re.getRestaurantZip());
                List<String> s = re.getRestaurantCategories();
                System.out.print("Restaurant Categories: ");
                for (String cat : s) {
                    System.out.print(cat + " ");
                }
                System.out.println();
            }
        }
        else
        {
            System.out.println("No such restaurant with this name");
        }
    }
    void printFood(List<Food> f)
    {
        if(!f.isEmpty())
        {
            for(Food food : f)
            {
                System.out.println(food.toString());
            }
        }
    }

    public String createRestaurantInfoString(Restaurant restaurant) {
        StringBuilder sb = new StringBuilder();
        //sb.append("\n");
        sb.append(restaurant.getRestaurantId()).append(",");
        sb.append(restaurant.getRestaurantName()).append(",");
        sb.append(restaurant.getRestaurantScore()).append(",");
        sb.append(restaurant.getRestaurantPrice()).append(",");
        sb.append(restaurant.getRestaurantZip()).append(",");
        List<String> categories = restaurant.getRestaurantCategories();
        for (int i = 0; i < categories.size(); i++) {
            sb.append(categories.get(i));
            if (i < categories.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public void writeRestaurantToFile(String filename, Restaurant restaurant) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            String restaurantInfo = createRestaurantInfoString(restaurant);
            writer.write(restaurantInfo);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public void writeToMenu(String filename, Food food)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            String FoodInfo = food.toString();
            writer.write(FoodInfo);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
    /*public static void main(String[] args) throws Exception {
        RestaurantDatabase r = new RestaurantDatabase();
        r.printByCategory();
        System.out.println();
        r.printFood(r.searchResToFood(3, "bar"));
        System.out.println();
        List<Restaurant> res = r.searchRestaurantsByName("k");
        r.printList(res);
        System.out.println();
        List<Restaurant> rem = r.searchRestaurantsByScore(4.7, 5.0);
        r.printList(rem);
        System.out.println();
        List<Restaurant> rec = r.searchRestaurantsByCategory("fast food");
        r.printList(rec);
        System.out.println();
        List<Restaurant> ret = r.searchRestaurantsByPrice("$");
        r.printList(ret);
        System.out.println();
        r.printList(r.searchByZipCodes("99218"));
        System.out.println();
        r.printFood(r.searchFoodsByName("chocolate"));
        System.out.println();
        r.printFood(r.searchFoodByCategory("owls"));
        System.out.println();
        r.printFood(r.getFoodFromResToCat("family feasts", "IHOP"));
        System.out.println();
        r.printFood(r.searchFoodByPriceRange(10.98, 23));
        System.out.println();
        r.printFood(r.searchFoodByPriceInRestaurant("KFC", 10.99, 23));
        System.out.println();
        r.printFood(r.searchCostliestFood("Starbucks"));
        System.out.println();
        r.printAllFoodInRes();
        System.out.println();
        Food f = new Food (r.ResIdFromName("KFC"), "Beverages", "Beer and Wine", 13.89);
        r.addFood(f);
        r.printAllFoodInRes();
        System.out.println();
        r.printFood(r.searchFoodByPriceInRestaurant("KFC", 10, 15));
        Restaurant restaurant = new Restaurant(5, "BFC", 4.5, "$$", "98928", "Breakfast and Brunch", "Chicken");
        r.addRestaurant(restaurant);
        r.printAllFoodInRes();
        System.out.println();
        r.printByCategory();
    }*/
}

