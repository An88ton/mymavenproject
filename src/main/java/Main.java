public class Main {

    public static void main(String[] args) {
        Cluster cluster = new Cluster();
        cluster.sendMessage();
        FailSearchEngine failSearchEngine = new FailSearchEngine(cluster);
        failSearchEngine.search();
    }
}
