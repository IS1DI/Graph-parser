public class ListGraph implements Graph {

    private ListNew<ListNew<Node>>  allEdges;
    private ListNew<String> names;

    class Node{
        private int value;
        private int to;
        Node(int val, int to){
            this.value = val;
            this.to = to;
        }

        @Override
        public String toString() {
            return String.valueOf(value) + " ( " + to + " )";
        }
    }
    public ListNew<ListNew<Integer>> SpanningTree(){
        return toIncidenceGraph().SpanningTree();
    }
    public String toStringSpanningTree(){
        return toIncidenceGraph().toStringSpanningTree();
    }
    public ListNew<Edge> toEdgeList(){
        ListNew<Edge> edges = new ArrayNew<>();
        for(int i = 0; i < allEdges.getSize(); i++){
            for(int j = 0; j < allEdges.get(i).getSize(); j ++){
                edges.add(new Edge(names.get(i), names.get(allEdges.get(i).get(j).to),allEdges.get(i).get(j).value ));
            }
        }
        return edges;
    }
    public ListGraph toListGraph(){
        return this;
    }
    public IncidenceGraph toIncidenceGraph(){
        return new IncidenceGraph(toEdgeList(),names);
    }
    public MatrixGraph toMatrixGraph(){
        return new MatrixGraph(toEdgeList(),names);

    }
    ListGraph(ListNew<Edge> values,String[] names){
        this.names = new ArrayNew<String>();
        allEdges = new ArrayNew<>();
        for(int i = 0; i < names.length;i++){
            this.names.add(names[i]);
            this.allEdges.add( new LinkedListNew<Node>());

        }
        for(int i = 0; i < values.getSize(); i++){
            Edge edge = values.get(i);
            allEdges.get(this.names.find(edge.name)).add(new Node(edge.weight,this.names.find(edge.to)));
        }
    }

    ListGraph(ListNew<Edge> values,ListNew<String> names){
        this.names = new ArrayNew<String>();
        allEdges = new ArrayNew<>();
        for(int i = 0; i < names.getSize();i++){
            this.names.add(names.get(i));
            this.allEdges.add( new LinkedListNew<Node>());

        }
        for(int i = 0; i < values.getSize(); i++){
            Edge edge = values.get(i);
            allEdges.get(this.names.find(edge.name)).add(new Node(edge.weight,this.names.find(edge.to)));
        }
    }
    enum Colors {
        WHITE('w'),
        GREY('g'),
        BLACK('b');
        char c;
        Colors(char c){
            this.c = c;
        }

    }

    @Override // DFS
    public ListNew<Integer> DFS() {

        ArrayNew<Colors> colors = new ArrayNew<>();
        for(int i = 0; i< names.getSize(); i++){
            colors.add(Colors.WHITE);
        }
        ListNew<Integer> stack = new LinkedListNew<>();
        for(int i = 0;  i < names.getSize(); i++){
            if (colors.get(i) == Colors.WHITE){
                DFSRecursive(colors,i,stack);
            }
        }
        return stack;
    }



    private void DFSRecursive(ArrayNew<Colors> color, int u, ListNew<Integer> stack){
        color.set(u,Colors.GREY);
        for(int i = 0; i < allEdges.get(u).getSize(); i++){
            if(color.get(allEdges.get(u).get(i).to) == Colors.WHITE){
                DFSRecursive(color,allEdges.get(u).get(i).to, stack);
            }
        }
        stack.add( u );
        color.set(u,Colors.BLACK);
    }

    @Override
    public ListNew<Integer> BFS() {
        ListNew<Integer> queue = new LinkedListNew();
        queue.add(0);
        ListNew<Integer> stack = new LinkedListNew<>();
        while (!queue.isEmpty()){
            int indx = queue.getFirst();
            for(int i = 0; i < allEdges.get(indx).getSize(); i ++){
                if(queue.find(allEdges.get(indx).get(i).to) == -1){
                    queue.add(allEdges.get(indx).get(i).to);
                }
            }
            stack.add(queue.getFirst());
            queue.removeFirst();
        }
        return stack;

    }


}
