package Networks;
import java.util.*;
import data.Food;
import java.io.Serializable;
public class FoodList implements Serializable{
    List<Food> fl;
    public void setFoodList(List<Food> list)
    {
        this.fl = list;
    }
    public List<Food> getFoodList()
    {
        return fl;
    }
}
