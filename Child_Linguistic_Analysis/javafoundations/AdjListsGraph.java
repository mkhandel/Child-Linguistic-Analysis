/********************************************************************
 * AdjListsGraph.java @version 2018.11.08
 * Implementation of the Graph.java interface using Lists of
 * Adjacent nodes
 * KNOWN FEATURES/BUGS: 
 * It handles unweighted graphs only, but it can be extended.
 * It does not handle operations involving non-existing vertices
 * 
 ********************************************************************/
package javafoundations;
import java.util.*;
import java.io.*;

public class AdjListsGraph<T> implements Graph<T>{
    private final int NOT_FOUND = -1;

    private Vector<LinkedList<T>> arcs;   // adjacency matrices of arcs
    private Vector<T> vertices;   // values of vertices

    /******************************************************************
     * Constructor. Creates an empty graph.
     ******************************************************************/
    public AdjListsGraph() {
        this.arcs = new Vector<LinkedList<T>>();
        this.vertices = new Vector<T>();
    }

    /*****************************************************************
     * Creates and returns a new graph using the data found in the input file.
     * If the file does not exist, a message is printed. 
     *****************************************************************/
    public static AdjListsGraph<String> AdjListsGraphFromFile(String tgf_file_name) {
        AdjListsGraph<String> g = new AdjListsGraph<String>();
        try{ // to read from the tgf file
            Scanner scanner = new Scanner(new File(tgf_file_name));
            //read vertices
            while (!scanner.next().equals("#")){
                String token = "";
                token = scanner.next();
                g.addVertex(token); 
            }
            //read arcs
            while (scanner.hasNext()){
                int from = scanner.nextInt();
                int to = scanner.nextInt();
                g.addArc(from-1, to-1);
            }
            scanner.close();
        } catch (IOException ex) {
            System.out.println(" ***(T)ERROR*** The file was not found: " + ex);
        }
        return g;
    }

    /******************************************************************
     * Returns true if the graph is empty and false otherwise. 
     ******************************************************************/
    public boolean isEmpty() {
        return vertices.size() == 0;
    }

    /******************************************************************
     * Returns the number of vertices in the graph.
     ******************************************************************/
    public int getNumVertices() { 
        return vertices.size(); 
    }

    /******************************************************************
     * Returns the number of arcs in the graph by counting them.
     ******************************************************************/
    public int getNumArcs() {
        int totalArcs = 0;
        for (int i = 0; i < vertices.size(); i++){ //for each vertex
            //add the number of its connections
            totalArcs = totalArcs + arcs.get(i).size(); 
        }
        return totalArcs; 
    }

    /******************************************************************
     * Returns true iff a connection exists from v1 to v2.
     ******************************************************************/
    public boolean isArc (T vertex1, T vertex2){ 
        try {
            //find index of origin vertex in the vector of vertices
            int index = vertices.indexOf(vertex1);
            //find the list of verteces connected to the origin vertex
            LinkedList<T> l = arcs.get(index);
            //check whether the destination vertex is amng them
            return (l.indexOf(vertex2) != -1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(vertex1 + " vertex does not belong in the graph");
            return false;
        }
    }

    /******************************************************************
     *Returns true iff an arc exist from vertex1 to vertex2, AND an arc 
     *exists from vertex2 to vertex1.
     *@param vertex1    The origin vertex for the edge
     *@param vertex2    The destination vertex for the edge
     *@return           true if an edge between vertex1 and vertex2 exists, 
     *                  false otherwise.
     ******************************************************************/
    public boolean isEdge (T vertex1, T vertex2) {
        return isArc(vertex1, vertex2) && isArc(vertex2, vertex1); 
    }

    /******************************************************************
     * Adds the input vertex to the graph, if it does not belong to it already.
     * @param The vertex to be added to the graph
     ******************************************************************/
    public void addVertex (T vertex) {
        if (vertices.indexOf(vertex) == -1) { //the vertex is not already there
            // add it to the vertices vector
            vertices.add(vertex); 

            //also add an empty, for now, list to hold its cnnections
            arcs.add(new LinkedList<T>()); 
        }
    }

    /******************************************************************
     * Removes the input vertex from the graph. Notice that if the vertex 
     * does not exist in the graph, the graph is not altered by this method.
     * Uses equals() on T for testing equality.
     * @param vertex    The vertex to be removed from the graph
     ******************************************************************/
    public void removeVertex (T vertex) {
        //find the index of the vertex in the vector of vertices
        int index = vertices.indexOf(vertex);
        //remove the vertex. 
        this.removeVertex(index);
    }

    /******************************************************************
     * Helper method. Removes the vertex at the given index from the graph.   
     * Note that this may affect the index values of other vertices.
     ******************************************************************/
    private void removeVertex (int index) {
        T vertexToBeRemoved = vertices.get(index);
        //remove vertex from vertices vector
        vertices.remove(index); 

        //remove its list of adjacent vertices from the vector
        arcs.remove(index); 

        //remove the vertex from all the other adjacent lists, wherever it was found
        for (int i = 0; i < arcs.size(); i++) { // for each linked list in the arcs vector
            arcs.get(i).remove(vertexToBeRemoved);  // Use LinkedList's .remove(Object o) method.
        }
    }

    /******************************************************************
     * Adds an edge between two vertices in the graph.
     * If one or both vertices do not exist, the graph is not altered.
     * @param vertex1   one of the end points of the edge
     * @param vertex2   the other end point of the edge
     ******************************************************************/
    public void addEdge (T vertex1, T vertex2) {
        //if one of the two vertices does not exist, the isArc() will not alter the graph
        this.addArc (vertex1, vertex2);
        addArc (vertex2, vertex1);
    }

    /******************************************************************
     * Adds an arc with origin v1 and destination v2.
     * If one of the vertices 9or both) does not exist, the graph is not altered.
     * @param source        The point of origin for the arc to be added
     * @param destination   The end point for the arc to be added
     ******************************************************************/
    public void addArc (T source, T destination){
        int sourceIndex = vertices.indexOf(source);
        int destinationIndex = vertices.indexOf(destination);

        //if source and destination exist, add the arc, else do nothing 
        if ((sourceIndex != -1) && (destinationIndex != -1)){
            LinkedList<T> l = arcs.get(sourceIndex);
            l.add(destination);
        }
    }

    /******************************************************************
     * Helper. Adds an arc between the two input vertices.
     * PRECONDITION: the arc does not exist already
     ******************************************************************/
    protected void addArc (int index1, int index2) {
        //if (indexIsValid(index1) && indexIsValid(index2))
        //vertices.get(index1).add(v2);

        //get the list of vertices of the origin
        LinkedList<T> l = arcs.get(index1);
        //find the destination vertex, based on its index in the vertices vector
        T destination = vertices.elementAt(index2);
        //add the destination to the adjacent list of the origin
        l.add(destination);
    }

    /******************************************************************
     * Removes the edge between the two input vertices.
     * If one or both vertices do not exist, the graph is not altered.
     * @param vertex1       one end point of the edge to be removed
     * @param vertex2       the other end point of the edge to be removed
     ******************************************************************/
    public void removeEdge (T vertex1, T vertex2) {
        removeArc (vertex1, vertex2);
        removeArc (vertex2, vertex1);
    }

    /******************************************************************
     * Removes the arc from vertex v1 to vertex v2,
     * if the vertices exist, else does not change the graph. 
     * @param vertex1       the origin of the edge to be removed
     * @param vertex2       the destination of the edge to be removed
     ******************************************************************/
    public void removeArc (T vertex1, T vertex2) {
        int index1 = vertices.indexOf(vertex1);
        int index2 = vertices.indexOf(vertex2);
        removeArc (index1, index2);
    }

    /******************************************************************
     * Helper. Removes an arc from index v1 to index v2.
     ******************************************************************/
    private void removeArc (int index1, int index2) {
        //if (indexIsValid(index1) && indexIsValid(index2))
        T destination = vertices.get(index2);
        LinkedList<T> connections = arcs.get(index1);
        connections.remove(destination);
    }

    /******************************************************************
    Returns a string representation of the graph. 
    @return     A string represention of the graph, which includes 
    its vertices and adjacent vertices from each one of them.
     ******************************************************************/
    public String toString() {
        if (vertices.size() == 0) return "Graph is empty";

        String result = "Vertices: \n";
        result = result + vertices;

        result = result + "\n\nEdges: \n";
        for (int i=0; i< vertices.size(); i++)
            result = result + "from " + vertices.get(i) + ": "  + arcs.get(i) + "\n";

        return result;
    }

    /******************************************************************
     * Saves the current graph into a .tgf file.
     * If the file does not exist, it is created. If it exists, it is overwitten. 
     * If it cannot save the file, a message is printed. 
     * @param fName     The name of the file to write to 
     *****************************************************************/
    public void saveTGF(String fName) {
        try {
            PrintWriter writer = new PrintWriter(new File(fName));

            //write vertices by iterating through vector "vertices"
            for (int i = 0; i < vertices.size(); i++) {
                writer.print((i+1) + " " + vertices.get(i));
                writer.println("");
            }
            writer.print("#"); 
            writer.println("");

            //write arcs by iterating through arcs vector
            for (int i = 0; i < arcs.size(); i++){ //for each linked list in arcs
                for (T vertex :arcs.get(i)) {
                    int index2 = vertices.indexOf(vertex);
                    writer.print((i+1) + " " + (index2+1));
                    writer.println("");
                }
            }
            writer.close();
        } catch (IOException ex) {
            System.out.println("***ERROR***" +  fName + " could not be written: " + ex);
        }
    }

    /********************************************************************
     * DO NOT CHANGE ANY OF THE CODE ABOVE.
     * WRITE YOUR CODE FOR EXAM 3 BELOW
     * @avery kim
     * Some of my programs work but not all of them achieve what I was trying to do.
     * Clone method successfully creates a shallow copy of the graph. My method for implementing clone was to go iterate through 
     * the vertices of the graph and add them to the new clone graph then have a nested for loop that iterates through each arc 
     * and adds it as well. 
     * 
     * hashPathDFS does not work. I started by looking at the algorithm in the book and trying to implement
     * it for a graph implemented with a LinkedList. I first check if the start vertex is in the graph. 
     * If it is not I return an empty vector. Next I add all the vertices to a hash table and set the values to false.
     * Then I push start vertex into the stack and mark it as visited.
     * I then wanted to continue to visit vertices while there were vertices in the stack so I used a while loop. 
     * There is a for loop to iterate through the linked list of arcs for current vertex. I have a conditional if the this vertex is 
     * the end vertex. If not then I have a conditional to test that the vertex hasn’t been visited. Then pop vertex out of the stack 
     * so another one can be tested.
     * 
     * Topological sort works except the linked lists in the arcs vector are returned in reverse order. 
     * I used a helper method of this. I also could not get graph cycle exception to function properly.
     * I used a cloned graph so the original graph would not be destroyed. I then have a for loop to go through the vector of vertices.
     * A conditional then tests that there are no successors to a given vertex. If there is none, then it is added to the linked list
     * and arcs from it are removed. 

     ********************************************************************/   
    /**
     * clone method creates a shallow copy of a graph. The vertices should reference to the same objects 
     * in the orignal graph.
     * 
     * @return - returns a new shallow copy AdjListsGraph<T>
     */
    public AdjListsGraph<T> clone(){
        AdjListsGraph<T> clonedGraph = new AdjListsGraph<T>();
        
        //iterate through the vertices vector
        for(int i = 0; i < this.getNumVertices(); i++){
            clonedGraph.addVertex(this.vertices.get(i));
            //System.out.println("this is the current vertex " + this.vertices.get(i));
            
            //iterate through arcs vector
            for(int n = 0; n < (this.arcs.get(i)).size(); n++){
                //System.out.println("This is n " + n);
                //LinkedList<T> arcsClone = this.arcs.get(i);
                //System.out.println("this is arcs Linked List at " + i+ " : " + this.arcs.get(i));
                clonedGraph.addEdge(this.vertices.get(i), this.arcs.get(i).get(n));
                //System.out.println("this is arcsClone " + arcsClone.get(n));
                //System.out.println("this is clonedGraph at i ["+i+"] and n ["+n+"] "+ clonedGraph);
            }
        }

        return clonedGraph;
    }

    //attempted recursive DFS method
    // public Vector<T> recursiveDFS(T node){
        // Vector<T> searchedVert = new Vector <T>();
        // Hashtable marked = new Hashtable(this.getNumVertices()*2);
        // //fill hashtable with vertices and set all values to false

        // marked.put(node, true);         //mark node as visited
        // searchedVert.add(node);
        // // go through each element in LinkedList to find connected vertices

        // for(int i = 0; i < this.arcs.get(this.arcs.indexOf(node)).size(); i++){
            // System.out.println("this is current linkelist element: "+ 
                // this.arcs.get(this.arcs.indexOf(node)).get(i));

            // //see if node has already been visited
            // if (marked.contains(i)==false){
                // recursiveDFS(this.arcs.get(i));
            // }
            // System.out.println("This is searchVert: " + searchedVert);
        // }
        // return searchedVert;
    //}
    /**
     * hashPathDFS(T startV, T endV) should search through a graph, using depth first methodology, to find a path from a start index to
     * an end vertex (provided by the user).
     * 
     * @param T startV, T endV - a start vertex of the graph as well as an end vertex that is being serached for. Assumed they are in 
     *                          the graph.
     * @return Vector<T> - returns the path found from start vertex to end vertex
     */
    public Vector<T> hashPathDFS(T startV, T endV){
        T currentVertex;
        LinkedStack<T> traversalStack = new LinkedStack<T>();
        Vector<T> searchList = new Vector<T>();
        Iterator<T> iter = searchList.iterator();
        Hashtable<T, Boolean> marked = new Hashtable<T, Boolean>();
        
        
        boolean found;
        //if start vertex is not in vertices vector return empty searchList
        if(!this.vertices.contains(startV)){
            return searchList;
        }
        
        //fill hashtable with vertices that have false values
        for(int vertexInd = 0; vertexInd < this.getNumVertices(); vertexInd++){
            //System.out.println("This is hashtable counter: " + vertexInd);
            marked.put(this.vertices.get(vertexInd), false);
        }
        
        traversalStack.push(startV);     //add start vertex to stack to be searched
        searchList.add(startV);                                 // add it to searchList Vector
        marked.replace(startV, true);                           //mark as visited in hashtable
        
        //stack is not empty ie there are neighbors still needing searching
        while(!traversalStack.isEmpty()){
            currentVertex = traversalStack.peek();
            found = false;
            //System.out.println("This is traversalStack: " + traversalStack);
            //System.out.println("This is currentVertex: " + currentVertex);
            
            //for as many vertices as there are and while found is false
            for(int vertexInd = 0; vertexInd < this.arcs.size() && !found; vertexInd++){
                
                //return list if reached endV
                if(currentVertex == endV){
                    searchList.add(currentVertex);
                    return searchList;
                }
                //if the vertex has not been visited yet
                if (this.arcs.get(vertexInd).contains(currentVertex) && !marked.get(currentVertex)){
                    //T currentArc = this.arcs.get(vertexInd).get(vertexInd);
                    
                    traversalStack.push(this.arcs.get(this.arcs.indexOf(currentVertex)).get(vertexInd));
                    searchList.add(currentVertex);//(this.arcs.get(currentVertex).get(vertexInd));
                    marked.replace(currentVertex,true);
                    found = true;
                    
                    //System.out.println("This is searchList: " + searchList);
                }
            }
            if(!found && !traversalStack.isEmpty()){
                traversalStack.pop();
            }
        }
        return searchList;
    }
    
    // public LinkedStack<T> DFShelper(T startNode)
    // {
        // LinkedStack<T> stk = new LinkedStack<T>();
        // int startInd = this.vertices.indexOf(startNode);
        // stk.push(this.vertices.get(startInd));
        // ListIterator<T> iter = this.arcs.get(startInd).listIterator(0);
        
        // Hashtable marked = new Hashtable(this.getNumVertices()*2);
        // for(int i = 0; i < this.getNumVertices(); i++){
            // //System.out.println("this is instantiating the hashtable " + i);
            // marked.put(this.vertices.get(i), false);
        // }
        
           // //mark as visited in hashtable
        // while(!stk.isEmpty()){
            // System.out.println("this is while stk is not empty");
            // startNode = stk.peek();
            // stk.pop();
        // }
        
        // //mark node as visited
        // if(marked.get(startNode).equals(false)){
            // System.out.println("this node: "+ startNode + " has not been visited");
            // marked.replace(this.vertices.get(startInd), true); 
        // }
        
        // //seraches for next node
        // while(iter.hasNext()){
            // T nearNode = iter.next();
            // if(marked.get(nearNode).equals(false)){
                // System.out.println("node " + nearNode + " is pushed into stk");
                // stk.push(nearNode);
            // }
        // }
        // return stk;
    // }

    /**
     * Retrieve from a graph the vertices adjacent to vertex v.
     * Assumes that the vertex is in the graph.
     */
    public LinkedList<T> getSuccessors(T vertex) {
        int vertexInd = this.vertices.indexOf(vertex);
        return this.arcs.get(vertexInd);
    }

    /**
     * topologicalSort method goes through a graph and provides a LinkedList of all the nodes sorted according to
     * a topologicalSort algorithm.
     * 
     * @return LinkedList<T> - returns a LinkedList of nodes sorted topologically 
     */
    public LinkedList<T> topologicalSort() //throws GraphCycleException
    {
        T startVertex = null;
        LinkedList<T> topSorted = new LinkedList<T>();
        int numVertex = this.getNumVertices();
        AdjListsGraph<T> clonedGraph = this.clone();
        
        //find start vertex w/ no successors 
        while(clonedGraph.getNumVertices() != 0){
            for(int i = 0; i < clonedGraph.getNumVertices(); i++){
                //System.out.println("this is the big forloop i" +i);
                //System.out.println("this is vertices at index "+i +this.vertices.get(i));
                //System.out.println("getSuccessors test: " + this.getSuccessors(this.vertices.get(i)));

                if((clonedGraph.getSuccessors(clonedGraph.vertices.get(i)).size()) == 0 ){
                    startVertex = clonedGraph.vertices.get(i);
                    //System.out.println("this is startVertex " + startVertex);
                    LinkedList<T> arcList = clonedGraph.arcs.get(i);
                    //System.out.println("this is arcsList " + arcList);
                    for(int arcIt = 0; arcIt < arcList.size(); arcIt++){
                        clonedGraph.removeArc(clonedGraph.vertices.get(i),clonedGraph.arcs.get(i).get(arcIt));
                        //System.out.println("this is arcIt " + arcIt);
                    }
                    topSorted.addFirst(startVertex);
                    //System.out.println("this is topSorted at i "+i+": \n" + topSorted);
                    clonedGraph.removeVertex(startVertex);
                } 

            }
        }
        //save vertex in variable
        //iterate through linked list at vertex index in arcs vector
        //remove arcs one by one 
        //put vertex in beginning of linked list
        //remove vertex from graph
        return topSorted;
    }
}