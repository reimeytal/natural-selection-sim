import naturalselection.creature.Creature;

class Main{
  public static void main(String[] args){
    Creature c1 = new Creature(1, 1);
    System.out.println(c1.toString());
    Creature c2 = c1.reproduce(5, 5);
    System.out.println(c2.toString());
  }
}
