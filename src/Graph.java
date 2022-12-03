public interface Graph {
    ListNew<Integer> DFS();
    ListNew<Integer> BFS();
    ListNew<ListNew<Integer>> SpanningTree();
    String toStringSpanningTree();
    ListNew<Edge> toEdgeList();
    IncidenceGraph toIncidenceGraph();
    ListGraph toListGraph();
    MatrixGraph toMatrixGraph();
}
