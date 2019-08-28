

public interface Clusterable {

    public void sendMessage();

    public boolean isFailed(int numServer, int numNode);
}
