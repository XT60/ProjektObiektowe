import java.util.Objects;

public class Vector2d {
    public int y;
    public int x;

    public Vector2d(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Vector2d(Vector2d vec){
        this.x = vec.getX();
        this.y = vec.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(y, x);
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    @Override
    public String toString() {
        return String.format("(%s,%s)", this.x, this.y);
    }

    boolean precedes(Vector2d other){
        return this.x <= other.getX() && this.y <= other.getY();
        }

    boolean follows(Vector2d other){
        return this.x >= other.getX() && this.y >= other.getY();
    }

    Vector2d upperRight(Vector2d other) {
        return new Vector2d(Math.max(this.x, other.getX()), Math.max(this.y, other.getY()));
    }

    Vector2d lowerLeft(Vector2d other) {
        return new Vector2d(Math.min(this.x, other.getX()), Math.min(this.y, other.getY()));
    }

    Vector2d add(Vector2d other){
        return new Vector2d(this.x + other.getX(), this.y + other.getY());
    }

    Vector2d subtract(Vector2d other){
        return new Vector2d(this.x - other.getX(), this.y - other.getY());
    }

    public boolean equals(Object other){
        if (this == other){
            return true;
        }
        if (!(other instanceof Vector2d)){
            return false;
        }
        Vector2d that = (Vector2d) other;
        if (this.x != that.getX()){
            return false;
        }
        if (this.y != that.getY()){
            return false;
        }
        return true;
    }

    Vector2d opposite(){
        return new Vector2d(this.y, this.x);
    }

}
