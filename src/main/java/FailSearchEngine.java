import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class FailSearchEngine {

    private Cluster cluster;

    public FailSearchEngine(Cluster cluster){
        this.cluster = cluster;
    }

    public int search(){
        int firstIndex = 0;
        int lastIndex = cluster.servers.size()-1;

        while(firstIndex <= lastIndex){
            int middleIndex = (firstIndex + lastIndex) / 2;

            if(cluster.isFailed(middleIndex, cluster.servers.get(middleIndex).getAmountNode()-1)){
                try {
                    if (!(cluster.isFailed(middleIndex - 1, cluster.servers.get(middleIndex - 1).getAmountNode() - 1))) {
                        searchNode(middleIndex);
                        return middleIndex;
                    } else {
                        lastIndex = middleIndex - 1;

                    }
                }catch (IndexOutOfBoundsException e){
                    searchNode(0);
                    return 0;
                }
            }else{
                firstIndex = middleIndex + 1;
            }

        }
        return 0;
    }


    public void searchNode(int server) {

            for (int i = 0; i < cluster.servers.get(server).getAmountNode(); i++) {
                if (cluster.isFailed(server, i) == true) {
                    System.out.println("First broken server is " + server + " on Node " + i);
                    infoToJson(server, i);
                    return;
                }
            }
    }
    public void infoToJson(int server, int node){
        Gson gson = new Gson();
        String firstBrokenServer = gson.toJson(cluster.servers.get(server));
        String firstBrokenNode = gson.toJson(cluster.servers.get(server).getNodes().get(node));
        try
        {
            FileWriter writer = new FileWriter("FirstBrokenServerAndNode.json");
            writer.write(firstBrokenServer + '\n' + firstBrokenNode);
        }catch (Exception e){
            e.getCause();
        }

    }
}
