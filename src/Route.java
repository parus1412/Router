public class Route implements Cloneable {
    private Network network;
    private IPv4Address gateway;
    private String interfaceName;
    private int metric;
    
    public Route(Network network, IPv4Address gateway, String interfaceName, int metric) throws MyNetworkException {
        this.network = network;
        this.gateway = gateway;
        this.interfaceName = interfaceName;
        this.metric = metric;
    }
    public Route(Network network, Object gateway, String interfaceName, int metric) throws MyNetworkException {
        this.network = network;
        this.gateway = new IPv4Address((String)gateway);
        this.interfaceName = interfaceName;
        this.metric = metric;
    }

    public IPv4Address getGateway() {
        return this.gateway;
    }
    public String getInterfaceName() {
        return this.interfaceName;
    }
    public int getMetric() {
        return this.metric;
    }
    public Network getNetwork() {
        return this.network;
    }
    public String toString() {
        if ( this.gateway != null ) {
            return "net:" + " " + this.network.toString() + ", " +
                   "gateway:" + " " + this.gateway.toString() + ", " +
                   "interface:" + " " + this.getInterfaceName() + ", " +
                   "metric:" + " " + this.getMetric();
        }
        return "net:" + " " + this.network.toString() + ", " +
               "interface:" + " " + this.getInterfaceName() + ", " +
               "metric:" + " " + this.getMetric();    }
}
