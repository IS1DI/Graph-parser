import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.print("input path to file:\n");
        Graph gr = ParserGraphs.incidenceParser(scan.next());
        System.out.println(gr.BFS());
        System.out.println(gr.DFS());
        System.out.println(gr.toStringSpanningTree());

    }
}
