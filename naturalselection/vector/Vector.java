package naturalselection.vector;

import java.lang.Math;

public final class Vector{
  private short x;
  private short y;
  private float atLocationDistance;

  public Vector(short x, short y){
    this.x = x;
    this.y = y;
    atLocationDistance = 0.5f;
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
    double diffx = (double)this.x - (double)otherVec.getX();
    double diffy = (double)this.y - (double)otherVec.getY();
    return Math.sqrt((diffx*diffx)+(diffy*diffy));
  }
  public Vector getVelocity(Vector otherVec, float speed){
    double diffx = (double)this.x - (double)otherVec.getX();
    double diffy = (double)this.y - (double)otherVec.getY();
    double hypotenuse = this.getHypotenuse(otherVec);
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
