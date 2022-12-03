

public class ParserGraphs {

    static ListGraph listParser(String path){
        EdgesAndNames edgesAndNames = parser(path);
        return new ListGraph(edgesAndNames.edges, edgesAndNames.names);
    }
    static MatrixGraph matrixParser(String path){
        EdgesAndNames edgesAndNames = parser(path);
        return new MatrixGraph(edgesAndNames.edges, edgesAndNames.names);
    }
    static IncidenceGraph incidenceParser(String path){
        EdgesAndNames edgesAndNames = parser(path);
        return new IncidenceGraph(edgesAndNames.edges, edgesAndNames.names);
    }
    private static EdgesAndNames parser(String path) {
        java.io.File file = new java.io.File(path);
        try(
        java.util.Scanner scanner = new java.util.Scanner(file)
        ) {
            String input = scanner.nextLine();
            String[] names = input.trim().split("\\s+");
            ListNew<Edge> edges = new LinkedListNew<>();
            for (int i = 0; i < names.length; i++) {
                for (int j = 0; j < names.length; j++) {
                    int num = scanner.nextInt();
                    if (num > 0) {
                        Edge current = new Edge(names[i], names[j], num);
                        if (edges.find(current) == -1) {
                            edges.add(current);
                        }
                    } else if (num < 0) {
                        Edge current = new Edge(names[j], names[i], -1 * num);
                        if (edges.find(current) == -1) {
                            edges.add(current);
                        }
                    }
                }
            }

            return new EdgesAndNames(edges,names);
        } catch (Exception e){
            System.out.print("Error");
            return null;
        }
    }
    private static class EdgesAndNames{
        String[] names;
        ListNew<Edge> edges;
        EdgesAndNames(ListNew<Edge> edges ,String[] names){
            this.edges = edges;
            this.names = names;
        }
    }
}
