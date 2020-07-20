import naturalselection.food.Food;
import java.util.Random;

class Main{
  private static int MAXSIZE = 250;
  private static Random numbergenerator;
  public static void main(String[] args){
    numbergenerator = new Random();
    Food food1 = new Food(1+numbergenerator.nextInt(MAXSIZE), 1+numbergenerator.nextInt(MAXSIZE));
    System.out.println(food1.getLocation().toString());
  }
}
