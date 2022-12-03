public class MatrixGraph implements Graph{
    private ListNew<ListNew<Integer>> matrix;
    private ListNew<String> names;
    MatrixGraph(ListNew<Edge> allEdges, ListNew<String> names){
        this.names = new ArrayNew<String>(names.getSize());
        matrix = new ArrayNew<>(names.getSize());
        for(int i = 0; i < names.getSize(); i++){
            this.names.set(i,names.get(i));
            matrix.set(i,new ArrayNew<>(names.getSize(),0));

        }
        while(!allEdges.isEmpty()){
            Edge edge = allEdges.getFirst();
            allEdges.removeFirst();
            int indxFrom = names.find(edge.name);
            int indxTo = names.find(edge.to);
            matrix.get(indxFrom).set(indxTo,edge.weight);
            matrix.get(indxTo).set(indxFrom,-1 * edge.weight);
        }

    }
    MatrixGraph(ListNew<Edge> allEdges, String[] names){
        this.names = new ArrayNew<String>(names.length);
        matrix = new ArrayNew<>(names.length);
        for(int i = 0; i < names.length; i++){
            this.names.set(i,names[i]);
            matrix.set(i,new ArrayNew<>(names.length,0));

        }
        while(!allEdges.isEmpty()){
            Edge edge = allEdges.getFirst();
            allEdges.removeFirst();
            int indxFrom = this.names.find(edge.name);
            int indxTo = this.names.find(edge.to);
            matrix.get(indxFrom).set(indxTo,edge.weight);
            matrix.get(indxTo).set(indxFrom,-1 * edge.weight);
        }

    }


    @Override
    public ListNew<Integer> DFS() {
        ListNew<Colors> colors = new ArrayNew<>();
        for(int i = 0; i < names.getSize(); i++){
            colors.add(Colors.WHITE);
        }
        ListNew<Integer> stack = new LinkedListNew<>();
        for(int i = 0; i < names.getSize(); i++){
            if(colors.get(i) == Colors.WHITE) {
                DFSRecursive(colors, i,stack);
            }
        }
        return stack;
    }
    private void DFSRecursive(ListNew<Colors> color, int u, ListNew<Integer> stack){
        color.set(u,Colors.GREY);
        for(int i = u + 1; i < matrix.get(u).getSize(); i++){
            if(matrix.get(u).get(i) != 0){
                if(color.get(i) == Colors.WHITE){
                    DFSRecursive(color, i, stack);
                }
            }
        }
        stack.add(u);
        color.set(u,Colors.BLACK);
    }

    @Override
    public ListNew<Integer> BFS() {
        ListNew<Integer> queue = new LinkedListNew<>();
        queue.add(0);
        ListNew<Integer> stack = new LinkedListNew<>();
        while(!queue.isEmpty()){
            int indx = queue.getFirst();
            for(int i = indx + 1; i < matrix.get(indx).getSize(); i++){
                if(matrix.get(indx).get(i)>0&&queue.find(i) == -1){
                    queue.add(i);
                }
            }
            stack.add(queue.getFirst());
            queue.removeFirst();
        }
        return stack;
    }

    @Override
    public ListNew<ListNew<Integer>> SpanningTree() {
        return toIncidenceGraph().SpanningTree();
    }

    @Override
    public String toStringSpanningTree() {
        return toIncidenceGraph().toStringSpanningTree();
    }

    @Override
    public ListNew<Edge> toEdgeList() {
        ListNew<Edge> edges = new ArrayNew<>();
        for(int i = 0; i < matrix.getSize(); i++){
            for(int j = i + 1; j < matrix.get(i).getSize(); j++ ){
                if(matrix.get(i).get(j) > 0){
                    edges.add(new Edge(names.get(i),names.get(j), matrix.get(i).get(j)));
                }
            }
        }
        return edges;
    }

    @Override
    public IncidenceGraph toIncidenceGraph() {
        return new IncidenceGraph(toEdgeList(),names);
    }

    @Override
    public ListGraph toListGraph() {
        return new ListGraph(toEdgeList(),names);
    }

    @Override
    public MatrixGraph toMatrixGraph() {
        return this;
    }

}
