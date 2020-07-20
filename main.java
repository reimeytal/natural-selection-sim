import naturalselection.vector.Vector;

class Main{
  public static void main(String[] args){
    Vector coord1 = new Vector((short)1, (short)1);
    Vector coord2 = new Vector((short)0, (short)0);
    Vector coord3 = coord1.getVelocity(coord2, 50);
    System.out.println(coord3.toString());
  }
}
