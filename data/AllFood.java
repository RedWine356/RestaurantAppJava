package data;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AllFood implements Serializable{
    List<Food> FL;

    public AllFood(){FL = new ArrayList<>(); }

    public AllFood(List<Food> FL) {
        this.FL = FL;
    }

    public List<Food> getFL() {
        return FL;
    }

    public void setFL(List<Food> FL) {
        this.FL = FL;
    }

    public void AddFood(Food f)
    {
        this.FL.add(f);
    }
    public void clr()
    {
        this.FL.clear();
    }
}
