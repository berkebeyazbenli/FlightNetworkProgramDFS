import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Digraph {

    public int V;
    public int E;
    private Bag<Integer>[] adj;
    private List<String> cities;

    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        cities = new ArrayList<>();

        for (int v = 0; v < V; v++)
            adj[v] = new Bag<Integer>();

    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;

    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public void addCity(String cityName) {

        Bag<Integer>[] newAdj = (Bag<Integer>[]) new Bag[V + 1];
        System.arraycopy(adj, 0, newAdj, 0, V);
        newAdj[V] = new Bag<Integer>();
        adj = newAdj;

        List<String> newCities = new ArrayList<>(cities);
        newCities.add(cityName);
        cities = newCities;

        V++;
    }

    public void addRoute(String sourceCity, String destCity) {
        int sourceIndex = cities.indexOf(sourceCity);
        int destIndex = cities.indexOf(destCity);

        if (sourceIndex == -1) {
            addCity(sourceCity);
            sourceIndex = cities.indexOf(sourceCity);
        }

        if (destIndex == -1) {
            addCity(destCity);
            destIndex = cities.indexOf(destCity);
        }

        if (!sourceCity.equals(destCity) && !adj[sourceIndex].contains(destIndex)) {
            addEdge(sourceIndex, destIndex);
        }

    }

    public void hop(String startCity, int hopNumber) {
        int startIndex = cities.indexOf(startCity);

        if (startIndex == -1) {
            System.out.println(" ");
            return;
        }

        String result = possibleCities(startIndex, hopNumber, startCity);
        String[] routes = result.split("\n");

        Arrays.sort(routes);

        System.out.println("Number of total routes: " + routes.length);
        System.out.println(" ");
        System.out.println("Routes are:");

        for (String route : routes) {
            System.out.println(" ");
            System.out.println(route);
        }
    }

    public String possibleCities(int currentCity, int NowHops, String currentRoute) {
        String result = "";

        if (NowHops > 0) {
            for (int neighbor : getAdjList(currentCity)) {
                result += possibleCities(neighbor, NowHops - 1, currentRoute + "-" + getCity(neighbor));
            }
        } else {
            result += currentRoute + "\n";
        }
        return result;
    }

    public Bag<Integer> getAdjList(int v) {
        return adj[v];
    }

    public boolean containsCity(String city) {
        return cities.contains(city);
    }

    public String getCity(int index) {
        return cities.get(index);
    }

}