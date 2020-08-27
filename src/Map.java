
import java.io.*;
import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;

public class Map{
  private static cart2d[] coordinates;
  private int size;
  //1 for small (250), 2 for med (500), 3 for large (750)
  //one pt every 50 units
  private double yvariation;
  //max/min y values for map points

  
  public cart2d[] getCoords()
  {
    return coordinates;
  }
  public int getSize()
  {
    return size;
  }

  /**
   * generates a flat map
   */
  public Map() {
    coordinates = new cart2d[] {(new cart2d(0,400)), (new cart2d(500,400))};
    size = 2;
    yvariation = 75;
  }
  /**
   * randomly generates a map given size
   * medium (mapsize=2) is used
   */
  public Map(int mapsize) {
    coordinates = new cart2d[5*mapsize+1];
    coordinates[0] = new cart2d(0,400);
    coordinates[coordinates.length-1] = new cart2d(250*mapsize,400);
    size = mapsize;
    yvariation = 75;
    double y = 0;
    for (int i = 1; i < 5*mapsize/2+1; i++) {
        y = Math.random()*yvariation;
        coordinates[i] = new cart2d(50*i,400-y);
      coordinates[coordinates.length-1-i] = new cart2d(250*mapsize-50*i,400-y);
    }
  }
  /**
   * //randomly generates a map given size and yvariation
   */
  public Map(int mapsize, double yvar) {
	coordinates = new cart2d[5*mapsize+1];
	    coordinates[0] = new cart2d(0,400);
	    coordinates[coordinates.length-1] = new cart2d(250*mapsize,400);
	  size = mapsize;
	  yvariation = yvar;
	  double y = 0;
	  for (int i = 1; i < 5*mapsize/2+1; i++) {
	      y = Math.random()*yvariation;
	      coordinates[i] = new cart2d(50*i,400-y);
	    coordinates[coordinates.length-1-i] = new cart2d(250*mapsize-50*i,400-y);
  }
}
  public Map(cart2d[] coords, int mapsize, double yvar) {
    coordinates = coords;
    size = mapsize;
    yvariation = yvar;
  }

  /**
   * returns the slope of a map at a certain x position
   */
  public double identifyslope(double xpos){
    if (xpos%50 == 0) {
        return(0);
    }
    int a = (int)(xpos)/50;
    double slope = (coordinates[a].gety() - coordinates[a+1].gety()) / (coordinates[a].x - coordinates[a+1].x);
    return(slope);
  }
  /**
   * returns the height of the map given an x position
   */
  public double findheight(double xpos) {
	  if (xpos%50==0) return (coordinates[(int) ((xpos)/50)].gety());
      int index = (int)(xpos)/50;
      return(coordinates[index].gety()+identifyslope(xpos)*(xpos-index*50));
  }
  

}

