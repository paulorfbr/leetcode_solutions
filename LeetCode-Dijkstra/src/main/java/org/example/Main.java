package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().networkDelayTime(new int[][]{{2,1,1},{2,3,1},{3,4,1}},4,2));
        System.out.println(new Main().networkDelayTime(new int[][]{{1,2,1}},2,1));
        System.out.println(new Main().networkDelayTime(new int[][]{{1,2,1}},2,2));
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        Graph graph = new Graph();
        graph.vertices = new HashSet<>(n);
        for (int i=1;i<=n;i++) {
            graph.vertices.add(i);
        }
        for (int i=0;i<times.length;i++) {
            int startVertex = times[i][0];
            int endVertex = times[i][1];
            long distance = times[i][2];
            Edge e = new Edge(startVertex,endVertex,distance);
            graph.edges.add(e);
        }
        DijkstraResult result = dijkstra(graph, k);
        int maxDistance = (int) Arrays.stream(result.distancesFromStartVertex).max().getAsLong();
        if (maxDistance==Integer.MAX_VALUE){
            return -1;
        }
        return maxDistance;
    }

    class Edge{
        private Integer startVertex;
        private Integer endVertex;

        private Long distance;

        public Edge(Integer startVertex, Integer endVertex, Long distance) {
            this.startVertex = startVertex;
            this.endVertex = endVertex;
            this.distance = distance;
        }
    }
    class Graph{
        private Set<Integer> vertices = new HashSet<>();
        private List<Edge> edges = new ArrayList<>();

        public List<Edge> getNeigbours(Integer vertexIndex){
            List<Edge> adjacentEdges = new ArrayList<>();
            for (Edge e: edges){
                if (e.startVertex.equals(vertexIndex)){
                    adjacentEdges.add(e);
                }
            }
            return adjacentEdges;
        }
    }

    class QueueElement {
        private int vertexIndex;
        private long distance;

        public QueueElement(int vertexIndex, long distance) {
            this.vertexIndex = vertexIndex;
            this.distance = distance;
        }
    }

    class DijkstraResult {
        int[] predecessors;
        long[] distancesFromStartVertex;

        public DijkstraResult(int[] predecessors, long[] distancesFromStartVertex) {
            this.predecessors = predecessors;
            this.distancesFromStartVertex = distancesFromStartVertex;
        }
    }

    public DijkstraResult dijkstra(Graph g, Integer startVertex) {
        long[] dist = new long[g.vertices.size()+1];
        int[] pred = new int[g.vertices.size()+1];
        //create vertex priority queue Q
        PriorityQueue<QueueElement> queue = new PriorityQueue<>(new Comparator<QueueElement>() {
            @Override
            public int compare(QueueElement o1, QueueElement o2) {
                return ((Long)o1.distance).compareTo(((Long)o2.distance));
            }
        });

        dist[startVertex] = 0;                          // Initialization
        QueueElement source = new QueueElement(startVertex, 0);
        queue.add(source);        // associated priority equals dist

        for (Integer v : g.vertices) {
            if (!v.equals(startVertex)) {
                pred[v] = -1; // undefined - Predecessor of v
                dist[v] = Integer.MAX_VALUE;                // Unknown distance from source to v
                queue.add(new QueueElement(v, Integer.MAX_VALUE));
            }
        }

        while (!queue.isEmpty()) {
            QueueElement u = queue.poll();                   // Remove and return best vertex
            for (Edge e : g.getNeigbours(u.vertexIndex)) {             // Go through all v neighbors of u
                if (dist[e.endVertex] > dist[u.vertexIndex] + e.distance) {
                    //refresh minimum
                    pred[e.endVertex] = u.vertexIndex;
                    dist[e.endVertex] = dist[u.vertexIndex] + e.distance;
                    queue.removeIf(n -> n.vertexIndex == e.endVertex); //remove old distance
                    queue.offer(new QueueElement(e.endVertex, dist[e.endVertex]));
                }
            }
        }
        DijkstraResult dijkstraResult = new DijkstraResult(pred, dist);
        return dijkstraResult;
    }
}