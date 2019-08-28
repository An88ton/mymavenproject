import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

public class Tests {
    Cluster cluster;

    @Test
    public void checkServersCreates(){
        cluster = new Cluster();
        assertNotNull(cluster.servers);
    }

    @Test
    public void checkNodesCreates(){
        cluster = new Cluster();
        for(Server i: cluster.servers)
            assertNotNull(i.getNodes());
    }
    @Test
    public void isNodesNotBroken(){
        cluster = new Cluster();
        int numberOfServer = cluster.servers.size()-1;
        boolean result = cluster.servers.get(numberOfServer).getNodes().get(cluster.servers.get(numberOfServer).getNodes().size()-1).isFailed();

        assertTrue(!result);
    }

    @Test
    public void checkIsSendMessageFailsNodes(){
        cluster = new Cluster();
        int numberOfServer = cluster.servers.size()-1;
        cluster.sendMessage();
        boolean result = cluster.servers.get(numberOfServer).getNodes().get(cluster.servers.get(numberOfServer).getNodes().size()-1).isFailed();

        assertTrue(result);
    }

    @Test
    // isFailed must return False
    public void checkIsFailedBeforeSendMessage(){
        cluster = new Cluster();
        assertTrue(!cluster.isFailed(cluster.amountServers-1,
                cluster.servers.get(cluster.amountServers-1).getAmountNode()-1));
    }

    @Test
    // isFailed must return True
    public void checkIsFailedAfterSendMessage(){
        cluster = new Cluster();
        cluster.sendMessage();
        assertTrue(cluster.isFailed(cluster.amountServers-1,
                cluster.servers.get(cluster.amountServers-1).getAmountNode()-1));
    }
    @Test
    public void CheckCorrectnessOfSearch(){
        cluster = new Cluster();
        cluster.sendMessage(0);
        FailSearchEngine failSearchEngine = new FailSearchEngine(cluster);
        assertEquals(failSearchEngine.search(), 0);

    }


}
