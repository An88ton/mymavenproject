
import java.util.ArrayList;

public class Cluster implements Clusterable {

    ArrayList<Server> servers;
    int amountServers;

    public Cluster() {
        servers = new ArrayList<>();
        amountServers = (int) ((Math.random()* (21-2))+2);
        createServers(amountServers);
    }

    public void createServers(int amount){
        // System.out.println("servers: " + amount);
        for(int i = 0; i < amount; i++){
            //   System.out.println("Server " + i);
            servers.add(new Server(i));

        }
    }


    @Override
    public void sendMessage(){
        //int choosen = 0;
        int choosen = (int) (Math.random() * amountServers);
        // System.out.println("Broken server: " + choosen);
        int amountNode = servers.get(choosen).getAmountNode();

        for(int i =(int)(Math.random() * amountNode); i < amountNode; i++){
            servers.get(choosen).getNodes().get(i).setFailed();
            // System.out.println(" Node: " + i + " broken ");
        }

        for(int i = ++choosen; i < servers.size(); i++){
            amountNode = servers.get(i).getAmountNode();
            for(int j = 0; j < amountNode; j++){
                servers.get(i).getNodes().get(j).setFailed();
                //System.out.println("Server " + i + " Node: " + j +" broken: " + servers.get(i).getNodes().get(j).isFailed());
            }
        }
    }

    public void sendMessage(int choosen){
        //int choosen = 0;
        // System.out.println("Broken server: " + choosen);
        int amountNode = servers.get(choosen).getAmountNode();

        for(int i =(int)(Math.random() * amountNode); i < amountNode; i++){
            servers.get(choosen).getNodes().get(i).setFailed();
            // System.out.println(" Node: " + i + " broken ");
        }

        for(int i = ++choosen; i < servers.size(); i++){
            amountNode = servers.get(i).getAmountNode();
            for(int j = 0; j < amountNode; j++){
                servers.get(i).getNodes().get(j).setFailed();
                //System.out.println("Server " + i + " Node: " + j +" broken: " + servers.get(i).getNodes().get(j).isFailed());
            }
        }
    }

    @Override
    public boolean isFailed(int numServer, int numNode){
        return servers.get(numServer).getNodes().get(numNode).isFailed();
    }


}