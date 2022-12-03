import java.util.Objects;

public class Edge{
    int weight;
    String to;
    String name;
    Edge(String to, int weight){
        this.to = to;
        this.weight = weight;
    }
    Edge(String name, String to, int weight){
        this.name = name;
        this.weight = weight;
        this.to = to;
    }




    @Override
    public String toString() {
        return """
                from: %s
                to: %s
                weight: %s
                """.formatted(name,to,weight);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge edge)) return false;
        return weight == edge.weight && to.equals(edge.to) && name.equals(edge.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, to, name);
    }
}
