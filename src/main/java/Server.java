import java.util.ArrayList;


public class Server {



    private ArrayList <Node> nodes;

    private int amountNode;
    private int number;

    public Server(int number) {
        this.number = number;
        amountNode = (int) ((Math.random()* (6-1))+1);
        nodes = new ArrayList<>();
        createNodes(amountNode);


    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public void createNodes(int amount){
        //System.out.println("    amount nodes: " + amount);
        for(int i = 0; i < amount; i++){
            nodes.add(new Node(i));
            //  System.out.println("    Node "+ i);
        }
    }

    public int getAmountNode() {
        return amountNode;
    }

}
