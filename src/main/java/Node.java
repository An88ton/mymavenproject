public class Node {

    private int number;
    private boolean isFailed = false;

    public Node(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public boolean isFailed() {
        return isFailed;
    }

    public void setFailed() {
        isFailed = true;
    }
}
