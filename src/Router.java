import java.util.List;
import java.util.ArrayList;

public class Router implements Cloneable {
    private List<Route> routes = new ArrayList<Route>();
    
    public Router(Iterable<Route> routes) {
        for (Route route : routes) {
            this.routes.add(route);
        }
    }
    
    public void addRoute(Route route) {
    	System.out.println("new route added");
        routes.add(route);
    }

	public Route get(int i) {
		return routes.get(i);
		
	}
    
     public Route getRouteForAddress(IPv4Address address) {
    	 Route bestRoute = this.routes.get(0);
 	 
   	    	 for (Route route : routes) {
             if ( route.getNetwork().contains(address) && (route.getNetwork().getMask() > bestRoute.getNetwork().getMask()) ){
            	 bestRoute = route;
              }
         }
    	 return bestRoute;
     }
     public Iterable<Route> getRoutes() {
         return routes;
     }
     public void removeRoute(Route route) {
         this.routes.remove(route);
     }
}
