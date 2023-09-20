package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.Serializable;
public class Restaurant implements Serializable{
    int restaurantId;
    String restaurantName;
    double restaurantScore;
    String restaurantPrice;
    String restaurantZip;
    List<String> categoryList;
    private List<Food> foods;
    
    public Restaurant(int id, String name, double score, String price, String zip, String...categories)
    {
        this.restaurantId = id;
        this.restaurantName = name;
        this.restaurantPrice = price;
        this.restaurantScore = score;
        this.restaurantZip = zip;
        if(categories.length > 3 || categories.length == 0)
        {
            throw new IllegalArgumentException("A restaurant can have at least 1 and at most 3 categories");
        }
        else
        {
            this.categoryList = new ArrayList<>();
            this.categoryList.addAll(Arrays.asList(categories));
        }
        foods = new ArrayList<>();
    }

    public List<Food> getFoods()
    {
        return foods;
    }
    public void setFoods(List<Food> foods)
    {
        this.foods = foods;
    }
    public void addFood(Food f)
    {
        foods.add(f);
    }
    void setRestaurantId(int id)
    {
        this.restaurantId = id;
    }
    void setRestaurantName(String name)
    {
        this.restaurantName = name;
    }
    void setRestaurantScore(double score)
    {
        this.restaurantScore = score;
    }
    void setRestaurantPrice(String price)
    {
        this.restaurantPrice = price;
    }
    void setRestaurantZip(String zip)
    {
        this.restaurantZip = zip;
    }
    void addRestaurantCategory(String cat)
    {
        if(categoryList.size() < 3)
        {
            categoryList.add(cat);
        }
    }
    public int getRestaurantId()
    {
        return this.restaurantId;
    }
    public String getRestaurantName()
    {
        return this.restaurantName;
    }
    double getRestaurantScore()
    {
        return this.restaurantScore;
    }
    String getRestaurantPrice()
    {
        return this.restaurantPrice;
    }
    String getRestaurantZip()
    {
        return this.restaurantZip;
    }
    int getRestaurantCategoryNum()
    {
        return this.categoryList.size();
    }
    List<String> getRestaurantCategories()
    {
        return this.categoryList;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Restaurant ID: ").append(restaurantId).append("\n");
        sb.append("Name: ").append(restaurantName).append("\n");
        sb.append("Score: ").append(restaurantScore).append("\n");
        sb.append("Price: ").append(restaurantPrice).append("\n");
        sb.append("Zip Code: ").append(restaurantZip).append("\n");
        sb.append("Categories: ").append(categoryList).append("\n");
        return sb.toString();
    }
}