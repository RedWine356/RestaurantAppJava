package data;
import java.io.Serializable;
public class Food implements Serializable{
    public int FoodId;
    public String FoodCategory;
    public String FoodName;
    public Double FoodPrice;

    public Food(int id, String cat, String name, Double price)
    {
        this.FoodId = id;
        this.FoodCategory = cat;
        this.FoodName = name;
        this.FoodPrice = price;
    }

    void setFoodId(int id)
    {
        this.FoodId = id;
    }
    void setCategory(String cat)
    {
        this.FoodCategory = cat;
    }
    void setName(String name)
    {
        this.FoodName = name;
    }
    void setPrice(Double price)
    {
        this.FoodPrice = price;
    }
    public Integer getFoodId()
    {
        return this.FoodId;
    }
    public String getFoodName()
    {
        return this.FoodName;
    }
    public String getFoodCategory()
    {
        return this.FoodCategory;
    }
    public Double getFoodPrice()
    {
        return this.FoodPrice;
    }

    @Override
    public String toString() {
        return FoodId + "," + FoodCategory + "," + FoodName + "," + FoodPrice;
    }
}
