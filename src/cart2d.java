
public class cart2d {
  protected double x;
  protected double y;
  private static double precision = 0.000001;

  //CONSTRUCTORS
  //no-argument constructor
  public cart2d() {
    x = 0.0;
    y = 0.0;
  }
  //precision constructor
  public static void setPrecision(double val) {
    precision = val;
  }
  //modifiers aka setters with one argument
  public void setx(double val) {
    x = val;
  }
  public void sety(double val) {
    y = val;
  }
  //constructor with two arguments
  public cart2d(double xval, double yval) {
    x = xval;
    y = yval;
  }
  //copy constructor
  public cart2d(cart2d P) {
    x = P.x;
    y = P.y;
  }


  //GETTERS
  public double getx() {
    return x;
  }
  public double gety() {
    return y;
  }


  //toString method
  public String toString() {
    String coord = "";
    return(coord + "(" + x + ", " + y + ")");
  }


  //equals method
  /*
  public boolean equals(cart2d P) {
    if (!(x==0) && !(y==0)) {
      return((Math.abs(x-P.x) / x < precision) && (Math.abs(y-P.y) / y < precision));
    } else {
      return((Math.abs(x-P.x) < precision) && (Math.abs(y-P.y) < precision));
    }
  }
  */
  public boolean equals(Object A) {
    if(this.getClass() != A.getClass()) {
      return(false);
    } else {
        cart2d B = ((cart2d) A);
        if (!(x==0) && !(y==0)) {
          return((Math.abs(x-B.x) / Math.abs(x) < precision) && (Math.abs(y-B.y) / Math.abs(y) < precision));
        } else {
          return((Math.abs(x-B.x) < precision) && (Math.abs(y-B.y) < precision));
        }
    }
  }
  public int compareTo(Object A) {
    if(this.getClass() != A.getClass()) {
      return(-999);
    } else if (this.distance() < ((cart2d) A).distance()) {
      return(-1);
    } else if (this.distance() > ((cart2d) A).distance()) {
      return(1);
    } else {
      return(0);
    }
  }

  //DISTANCE
  //distance from this to Q
  public double distance(cart2d Q) {
    return(Math.sqrt((x-Q.x)*(x-Q.x) + (y-Q.y)*(y-Q.y)));
  }
  //distance from this to origin
  public double distance() {
    return(distance(new cart2d()));
  }
  //distance from P to Q
  public static double distance(cart2d P, cart2d Q) {
    return(Math.sqrt((P.x-Q.x)*(P.x-Q.x)+(P.y-Q.y)*(P.y-Q.y)));
  }


  //MIDPOINT
  //midpoint of P and Q
  public static cart2d mid(cart2d P, cart2d Q) {
    cart2d foo = new cart2d(0.5 * (P.x + Q.x), 0.5 * (P.y + Q.y));
    return(foo);
  }
  //midpoint of this and P
  public cart2d mid(cart2d P) {
    cart2d foo = new cart2d(0.5 * (P.x + x), 0.5 * (P.y + y));
    return(foo);
  }


  //ANGLE
  //cart2d.angle(P,Q,R) gives <PQR
  public static double angle(cart2d P, cart2d Q, cart2d R) {
    double a = P.distance(Q);
    double b = Q.distance(R);
    double c = P.distance(R);
    return(180 / Math.PI * Math.acos((-c*c + a*a + b*b) / (2*a*b)));
  }
  //F.angle() gives the angle <PFR
  public double angle(cart2d P, cart2d R) {
    double a = P.distance(this);
    double b = this.distance(R);
    double c = P.distance(R);
    return(180 / Math.PI * Math.acos((-c*c + a*a + b*b) / (2*a*b)));
  }
  //gives the angle formed by the lines from the origin to P and R
  public static double originAngle(cart2d P, cart2d R) {
    cart2d origin = new cart2d();
    double a = P.distance(origin);
    double b = origin.distance(R);
    double c = P.distance(R);
    return(180 / Math.PI * Math.acos((-c*c + a*a + b*b) / (2*a*b)));
  }
  //F.originAngle(P) gives the angle formed by the lines from the origin to F and P
  public double originAngle(cart2d P) {
    cart2d origin = new cart2d();
    double a = P.distance(origin);
    double b = origin.distance(this);
    double c = P.distance(this);
    return(180 / Math.PI * Math.acos((-c*c + a*a + b*b) / (2*a*b)));
  }


  //ALTITUDE
  //length of altitude from P to QR
  public static double alt(cart2d P, cart2d Q, cart2d R) {
    return(P.area(Q,R) * 2 / Q.distance(R));
  }
  public double alt(cart2d Q, cart2d R) {
    return(this.area(Q,R) * 2 / Q.distance(R));
  }


  //PROJECTION
  //projection of P onto QR
  public static cart2d proj(cart2d P, cart2d Q, cart2d R) {
    double foo = Math.sqrt(Q.distance(P) * Q.distance(P) - P.alt(Q,R) * P.alt(Q,R));
    double slope = (R.y - Q.y) / (R.x - Q.x);
    cart2d S = new cart2d(Q.x + (R.x - Q.x) * foo / Q.distance(R), Q.y + (R.y - Q.y) * foo / Q.distance(R));
    return(S);
  }
  //projection of this onto QR
  public cart2d proj(cart2d Q, cart2d R) {
    double foo = Math.sqrt(Q.distance(this) * Q.distance(this) - this.alt(Q,R) * this.alt(Q,R));
    double slope = (R.y - Q.y) / (R.x - Q.x);
    cart2d S = new cart2d(Q.x + (R.x - Q.x) * foo / Q.distance(R), Q.y + (R.y - Q.y) * foo / Q.distance(R));
    return(S);
  }


  //AREA
  //area of the triangle PQR
  public static double area(cart2d P, cart2d Q, cart2d R) {
    double a = P.distance(Q);
    double b = Q.distance(R);
    double theta = cart2d.angle(P, Q, R)*Math.PI/180;
    return(0.5*a*b*Math.sin(theta));
  }
  //area of the triangle formed by this, P, and Q
  public double area(cart2d P, cart2d Q) {
    double a = P.distance(Q);
    double b = Q.distance(this);
    double theta = Q.angle(P, this)*Math.PI/180;
    return(0.5*a*b*Math.sin(theta));
  }
  //area of the triangle formed by the origin, P, and Q
  public static double originArea(cart2d P, cart2d Q) {
    cart2d origin = new cart2d();
    double a = P.distance(Q);
    double b = Q.distance(origin);
    double theta = cart2d.angle(P, Q, origin)*Math.PI/180;
    return(0.5*a*b*Math.sin(theta));
  }
  //area of the triangle formed by the origin, this, and P
  public double originArea(cart2d P) {
    cart2d origin = new cart2d();
    double a = P.distance(origin);
    double b = origin.distance(this);
    double theta = cart2d.angle(P, origin, this)*Math.PI/180;
    return(0.5*a*b*Math.sin(theta));
  }
  //area of a n-sided polygon with vertices defined in coords in order
  public static double area(int n, cart2d[] coords) {
    double area = 0;
    for (int i = 0; i < n - 1; i++) {
      area += 0.5 * coords[i].x * coords[i + 1].y;
      area -= 0.5 * coords[i].y * coords[i + 1].x;
    }
    area += 0.5 * coords[n-1].x * coords[0].y - 0.5 * coords[0].x * coords[n-1].y;
    return(Math.abs(area));
  }

  //ROTATION
  //rotates point P theta degrees counterclockwise around the origin
  public static cart2d rotateOrigin(cart2d P, double theta) {
    cart2d Q = new cart2d();
    theta = theta * Math.PI / 180;
    Q.x = Math.cos((Math.atan(P.y/P.x) + theta)) * P.distance();
    Q.y = Math.sin((Math.atan(P.y/P.x) + theta)) * P.distance();
    return(Q);
  }
  //rotates this theta degrees counterclockwise around the origin
  public cart2d rotateOrigin(double theta) {
    cart2d Q = new cart2d();
    theta = theta * Math.PI / 180;
    Q.x = Math.cos((Math.atan(y/x) + theta)) * this.distance();
    Q.y = Math.sin((Math.atan(y/x) + theta)) * this.distance();
    return(Q);
  }
  //rotates point Q theta degrees counterclockwise around point P
  public static cart2d rotate(cart2d P, cart2d Q, double theta) {
    cart2d F = new cart2d();
    cart2d dummy = new cart2d(P.x+1, P.y);
    theta = theta * Math.PI / 180;
    F.x = Math.cos((cart2d.angle(Q, P, dummy) + theta)) * P.distance(Q) + P.x;
    F.y = Math.sin((cart2d.angle(Q, P, dummy) + theta)) * P.distance(Q) + P.y;
    return(F);
  }
  //rotates point Q theta degrees counterclockwise around this
  public cart2d rotate(cart2d Q, double theta) {
    cart2d F = new cart2d();
    cart2d dummy = new cart2d(this.x+1, this.y);
    theta = theta * Math.PI / 180;
    F.x = Math.cos((cart2d.angle(Q, this, dummy) + theta)) * this.distance(Q) + x;
    F.y = Math.sin((cart2d.angle(Q, this, dummy) + theta)) * this.distance(Q) + y;
    return(F);
  }


  //PERIMETER
  //perimeter of the triangle formed by P, Q, and R
  public static double perimeter(cart2d P, cart2d Q, cart2d R) {
    return(P.distance(Q) + Q.distance(R) + R.distance(P));
  }
  //perimeter of the triangle formed by this, P, and Q
  public double perimeter(cart2d P, cart2d Q) {
    return(P.distance(Q) + Q.distance(this) + this.distance(P));
  }
  //perimeter of the triangle formed by the origin, P, and Q
  public static double perimeterOrigin(cart2d P, cart2d Q) {
    return(P.distance(Q) + Q.distance() + P.distance());
  }
  //perimeter of the triangle formed by the origin, this, and P
  public double perimeterOrigin(cart2d P) {
    return(P.distance(this) + this.distance() + P.distance());
  }


  //REFLECTION
  //reflects P across the origin
  public static cart2d reflectOrigin(cart2d P) {
    cart2d foo = new cart2d();
    foo.x = -P.x;
    foo.y = -P.y;
    return(foo);
  }
  //reflects this across the origin
  public cart2d reflectOrigin() {
    cart2d foo = new cart2d();
    foo.x = -x;
    foo.y = -y;
    return(foo);
  }
  //reflects point Q across point P
  public static cart2d reflect(cart2d P, cart2d Q) {
    return(P.rotate(Q, 180));
  }
  public cart2d reflect(cart2d Q) {
    return(this.rotate(Q,180));
  }
  //reflects P across the x-axis
  public static cart2d reflectx(cart2d P) {
    cart2d foo = new cart2d();
    foo.y = -P.y;
    return(foo);
  }
  //reflects this across the x-axis
  public cart2d reflectx() {
    cart2d foo = new cart2d();
    foo.y = -this.y;
    return(foo);
  }
  //reflects P across the y-axis
  public static cart2d reflecty(cart2d P) {
    cart2d foo = new cart2d();
    foo.x = -P.x;
    return(foo);
  }
  //reflects this across the y-axis
  public cart2d reflecty() {
    cart2d foo = new cart2d();
    foo.y = -this.y;
    return(foo);
  }
}
