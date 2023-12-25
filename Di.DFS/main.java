import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        String filename = System.console().readLine();

        Scanner scanner = new Scanner(System.in);
        int hopCount = scanner.nextInt();

        scanner.nextLine();
        String startCity = scanner.nextLine();

        Digraph dg = reDigraph(filename);
        dg.hop(startCity, hopCount);

    }

    public static Digraph reDigraph(String filename) {

        Digraph dg = new Digraph(0);

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] route = line.trim().split(",");
                String sourceCity = route[0];
                String destCity = route[1];

                if (!dg.containsCity(sourceCity)) {
                    dg.addCity(sourceCity);
                }
                if (!dg.containsCity(destCity)) {
                    dg.addCity(destCity);
                }

                dg.addRoute(sourceCity, destCity);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return dg;
    }

}
