import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;

public class MST {

    /**
     * Using disjoint set(s), run Kruskal's algorithm on the given graph and
     * return the MST. Return null if no MST exists for the graph.
     *
     * @param g The graph to be processed. Will never be null.
     * @return The MST of the graph; null if no valid MST exists.
     */
    public static Collection<Edge> kruskals(Graph g) {
        ArrayList<Edge> mst = new ArrayList<>();
        DisjointSets<Vertex> dJSets = new DisjointSets<Vertex>(g.getVertices());
        PriorityQueue<Edge> edges = new PriorityQueue<Edge>();
        for (Edge edge : g.getEdgeList()) {
            edges.add(edge);
        }
        while (!edges.isEmpty() && mst.size() < g.getVertices().size() - 1) {
            Edge e = edges.poll();
            Vertex u = e.getU();
            Vertex v = e.getV();
            if (!dJSets.sameSet(u, v)) {
                dJSets.merge(u, v);
                mst.add(e);
            }
            if (g.getVertices().size() - 1 == mst.size()) {
                return mst;
            }
        }
        return null;
    }

    /**
     * Run Prim's algorithm on the given graph and return the minimum spanning
     * tree. If no MST exists, return null.
     *
     * @param g The graph to be processed. Will never be null.
     * @param start The ID of the start node. Will always exist in the graph.
     * @return the MST of the graph; null if no valid MST exists.
     */
    public static Collection<Edge> prims(Graph g, int start) {
        ArrayList<Edge> mst = new ArrayList<>();
        PriorityQueue<Edge> edges = new PriorityQueue<>();
        Vertex startVertex = new Vertex(start);
        HashSet<Vertex> visited = new HashSet();
        Map<Vertex, Integer> adjancencies = g.getAdjacencies(startVertex);
        for (Vertex vertex : adjancencies.keySet()) {
            edges.add(new Edge(startVertex, vertex, adjancencies.get(vertex)));
        }
        visited.add(startVertex);
        while (!edges.isEmpty() && mst.size() < g.getVertices().size() - 1) {
            Edge removedEdge = edges.poll();
            startVertex = removedEdge.getV();
            if (!visited.contains(startVertex)) {
                visited.add(startVertex);
                mst.add(removedEdge);
                adjancencies = g.getAdjacencies(startVertex);
                for (Vertex vertex : adjancencies.keySet()) {
                    edges.add(new Edge(startVertex, vertex,
                              adjancencies.get(vertex)));
                }
            }

            if (g.getVertices().size() - 1 == mst.size()) {
                return mst;
            }
        }
        return null;
    }
}
