import java.util.ArrayList;
import java.util.List;

public class RouterMain {
    public static void main(String[] args) throws MyNetworkException {
         IPv4Address ip = new IPv4Address ("192.168.0.1");
         //Network net = new Network(ip, 24);
        
        List<Route> routes = new ArrayList<Route>() {{
            add(new Route(new Network(new IPv4Address("0.0.0.0"), 0), "192.168.0.1", "en0", 10));
            add(new Route(new Network(new IPv4Address("192.168.0.0"), 24), null, "en0", 10));
            add(new Route(new Network(new IPv4Address("10.0.0.0"), 8), "10.123.0.1", "en1", 10));
            add(new Route(new Network(new IPv4Address("10.123.0.0"), 20), null, "en1", 10));
        }};
        
        Router router = new Router(routes);
        Route newRoute = new Route(new Network(new IPv4Address("192.168.0.0"), 13), "192.168.0.1", "en2", 10);
        router.addRoute(newRoute);
        
//        Route route = router.getRouteForAddress(new IPv4Address("192.168.0.176"));
//        route.getMetric();                  // 10
//        route.getInterfaceName();           // en0
//        Network net = route.getNetwork();
//        net.toString();                     // 192.168.0.0/24
//        net.getAddress().toString();        // 192.168.0.0
//        
        
        
        Route ggg = router.getRouteForAddress(new IPv4Address("192.168.0.176"));
        
        System.out.println(ggg.getMetric());
        System.out.println(ggg.getInterfaceName());
        
        Network net = ggg.getNetwork();
                            
        
        System.out.println(net.toString());
        System.out.println(net.getAddress().toString());
        
        
//        for (Route route : router.getRoutes()) {
//        	Network net = route.getNetwork();
//        	System.out.println(route.toString());
//        }
//        
//        router.removeRoute(newRoute);
//        
        System.out.println("------------------------------------------------");
        
        Route hhh = router.getRouteForAddress(new IPv4Address("10.0.1.1"));
        
        System.out.println(hhh.getMetric());
        System.out.println(hhh.getInterfaceName());
        
        net = hhh.getNetwork();
        
        System.out.println(net.toString());
        System.out.println(net.getAddress().toString());
//        
//        for (Route route : router.getRoutes()) {
//        	Network net = route.getNetwork();
//        	System.out.println(route.toString());
//        }
//     
        
//        Route newRoute = new Route(new Network(new IPv4Address("192.168.0.0"), 0), "192.168.0.1", "en0", 10);
//        
//        router.addRoute(newRoute);
//        
//        router.get(3);
//        
        
        
        
        
        

//         Route route = new Route(new Network(new IPv4Address("0.0.0.0"), 0), null, "en0", 10);
//         Route route2 = new Route(new Network(new IPv4Address("0.0.0.0"), 0), "192.168.0.1", "en0", 10);
        
//        System.out.println(router.get(3).toString());
//         System.out.println("Route" + " " + route2.toString());
//         Network net = route.getNetwork();
    

//         System.out.println("Route" + " " + route.toString());
//         System.out.println("Metric: " + " " + route.getMetric());
//         System.out.println("Interface Name: " + " " + route.getInterfaceName());
//         System.out.println("Network" + " " + net.toString());
//         System.out.println("Network address" + " " + net.getAddress());
//         System.out.println("Broadcast Address" + " " + route.getBroadcastAddress());
//         System.out.println("First Usable Address" + " " + route.getFirstUsableAddress());
//         System.out.println("Last Usable Address" + " " + route.getLastUsableAddress());
//         System.out.println("total hosts" + " " + route.getTotalHosts());
//         System.out.println(route.contains(new IPv4Address("10.0.23.4")));
//         System.out.println(route.contains(new IPv4Address("192.168.0.25")));
//         System.out.println(route.isPublic());
//    
    }
}