public class Ponto implements Comparable<Ponto>{
    private int x;
    private int y;

    Ponto(){
        setX(0);
        setY(0);
    }

    Ponto(int x, int y){
        setX(x);
        setY(y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String toString(){
        return("(" + x + ", " + y + ")");
    }

    public int compareTo(Ponto p2){
        if (this.x < p2.getX()) {
            return -1;
        }
        if (this.x > p2.getX()) {
            return 1;
        }
        return 0;
    }
}
