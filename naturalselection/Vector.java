package naturalselection;

import java.lang.Math;

public final class Vector{
  private short x;
  private short y;
  private static float atLocationDistance = 0.5f;

  public Vector(short x, short y){
    this.x = x;
    this.y = y;
  }
  public short getX(){
    return x;
  }
  public short getY(){
    return y;
  }
  public void setX(short x){
    this.x = x;
  }
  public void setY(short y){
    this.y = y;
  }
  public void moveBy(Vector otherVec){
    this.x += otherVec.getX();
    this.y += otherVec.getY();
  }
  public void moveTo(short x, short y){
    this.x = x;
    this.y = y;
  }
  @Override
  public String toString(){
    return "("+this.x+", "+this.y+")";
  }
  public double getHypotenuse(Vector otherVec){
    double diffx = (double)this.x - (double)otherVec.x;
    double diffy = (double)this.y - (double)otherVec.y;
    return Math.sqrt((diffx*diffx)+(diffy*diffy));
  }
  public Vector getVelocity(Vector otherVec, float speed){
    double diffx = (double)otherVec.x - (double)this.x;
    double diffy = (double)otherVec.y - (double)this.x;
    double hypotenuse = getHypotenuse(otherVec);
    double lx = diffx/hypotenuse;
    double ly = diffy/hypotenuse;
    int nx = (int)(lx*speed);
    int ny = (int)(ly*speed);
    return new Vector((short)nx, (short)ny);
  }
  public boolean atLocation(Vector otherVec){
    if(this.getHypotenuse(otherVec) <= atLocationDistance){
      return true;
    } else{
      return false;
    }
  }
  public Vector copy(){
    return new Vector(x, y);
  }
}
