

public class IncidenceGraph implements Graph {
    ListNew<String> names;
    ListNew<Integer> weights;
    ListNew<ListNew<Integer>> matrix;

    public ListNew<ListNew<Integer>> SpanningTree(){
        sort();
        ListNew<String> allNames = new ArrayNew<String>();
        ListNew<ListNew<Integer>> allEdges = new ArrayNew<>();
        int count = 0;
        for(int i = 0; i < weights.getSize(); i++){
            int indexOfParent = findParentIndex(i);
            int indexOfChild = findChildIndex(i);

            if(allNames.find(names.get(indexOfParent)) == -1||allNames.find(names.get(indexOfChild))==-1){
                allNames.add(names.get(indexOfChild));
                allNames.add(names.get(indexOfParent));
                ListNew temp = new ArrayNew(3);
                temp.set(0,indexOfParent);
                temp.set(1,indexOfChild);
                temp.set(2,weights.get(i));
                allEdges.add(temp);
            }
        }

        return allEdges;

    }

    @Override
    public ListNew<Edge> toEdgeList() {
        ListNew<Edge> edges = new ArrayNew<>();
        for(int i = 0; i < weights.getSize(); i++){
            edges.add(new Edge(names.get(findParentIndex(i)), names.get(findChildIndex(i)), weights.get(i)));
        }
        return edges;
    }

    @Override
    public IncidenceGraph toIncidenceGraph() {
        return this;
    }

    @Override
    public ListGraph toListGraph() {

        return new ListGraph(toEdgeList(),names);
    }

    @Override
    public MatrixGraph toMatrixGraph() {
        return new MatrixGraph(toEdgeList(),names);
    }

    public String toStringSpanningTree(){
        ListNew<ListNew<Integer>> tree = SpanningTree();
        int count = 0;
        String out = "";
        for(int i = 0; i < tree.getSize(); i++){
            count+= tree.get(i).get(2);
            out += names.get(tree.get(i).get(0)) + " " + names.get(tree.get(i).get(1)) + "\n";
        }
        return out + count + "\n";
    }
    private int findParentIndex(int indexOfWeight){
        if(indexOfWeight>weights.getSize()){
            return -2;
        }
        for(int i = 0; i < names.getSize(); i++){
            if(matrix.get(i).get(indexOfWeight) == 1){
                return i;
            }
        }
        return -1;
    }

    public void sort(){
        for(int i = 0; i < weights.getSize(); i++){
            for(int j = 1; j< weights.getSize();j++){
                if(weights.get(j-1)>weights.get(j)){
                    swap(j-1,j);
                }
            }
        }
    }
    public void swap(int index, int index2){
        for (int i = 0; i < names.getSize(); i++){
            int temp = matrix.get(i).get(index);
            matrix.get(i).set(index,matrix.get(i).get(index2));
            matrix.get(i).set(index2,temp);
        }
        int temp = weights.get(index);
        weights.set(index, weights.get(index2));
        weights.set(index2,temp);
    }
    IncidenceGraph(ListNew<Edge> allEdges, ListNew<String> names){
        this.names = new ArrayNew<>(names.getSize());
        this.weights = new ArrayNew<>(allEdges.getSize());
        matrix = new ArrayNew<>(names.getSize());
        for(int i = 0; i < names.getSize();i ++){
            this.names.set(i,names.get(i));
            matrix.set(i,new ArrayNew<>(allEdges.getSize(),0));
        }
        for(int i = 0; !allEdges.isEmpty(); i++){
            Edge edge = allEdges.getFirst();
            allEdges.removeFirst();
            int indxFrom = names.find(edge.name);
            int indxTo = names.find(edge.to);
            weights.set(i,edge.weight);
            matrix.get(indxFrom).set(i,1);
            matrix.get(indxTo).set(i,-1);

        }
    }

    IncidenceGraph(ListNew<Edge> allEdges, String[] names){
        this.names = new ArrayNew<>(names.length);
        this.weights = new ArrayNew<>(allEdges.getSize());
        matrix = new ArrayNew<>(names.length);
        for(int i = 0; i < names.length;i ++){
            this.names.set(i,names[i]);
            matrix.set(i,new ArrayNew<>(allEdges.getSize(),0));
        }
        for(int i = 0; !allEdges.isEmpty(); i++){
            Edge edge = allEdges.getFirst();
            allEdges.removeFirst();
            int indxFrom = this.names.find(edge.name);
            int indxTo = this.names.find(edge.to);
            weights.set(i,edge.weight);
            matrix.get(indxFrom).set(i,1);
            matrix.get(indxTo).set(i,-1);
        }
    }

    @Override
    public ListNew<Integer> DFS() {
        ListNew<Colors> colors = new ArrayNew<>(names.getSize());
        for(int i = 0; i < names.getSize(); i++){
            colors.set(i,Colors.WHITE);
        }
        ListNew<Integer> stack = new LinkedListNew<>();
        for(int i = 0; i < names.getSize(); i++){
            if(colors.get(i) == Colors.WHITE){
                DFSRecursive(colors, i, stack);
            }
        }
        return stack;
    }

    private void DFSRecursive(ListNew<Colors> colors, int u, ListNew<Integer> stack){
        colors.set(u,Colors.GREY);
        for(int i = 0; i < weights.getSize(); i++){
            if(matrix.get(u).get(i) == 1&&colors.get(findChildIndex(i))==Colors.WHITE){
                DFSRecursive(colors, findChildIndex(i), stack);
            }
        }
        stack.add(u);
        colors.set(u, Colors.BLACK);
    }

    private int findChildIndex(int weightIndex){
        for(int i = 0; i < names.getSize(); i++){
            if(matrix.get(i).get(weightIndex) == -1){
                return i;
            }
        }
        return -1;
    }



    @Override
    public ListNew<Integer> BFS() {
        ListNew<Integer> queue = new LinkedListNew<>();
        queue.add(0);
        ListNew<Integer> stack = new LinkedListNew<>();
        while(!queue.isEmpty()){
            int indx = queue.getFirst();
            for(int i = 0; i < weights.getSize(); i++){
                if(matrix.get(indx).get(i) == 1){
                    int child = findChildIndex(i);
                    if(queue.find(child) == -1){
                        queue.add(child);
                    }
                }
            }
             stack.add(queue.getFirst());
            queue.removeFirst();
        }
        return stack;
    }

    @Override
    public String toString() {
        return BFS().toString();
    }
    public String toStringDFS(){
        return DFS().toString();
    }
}
