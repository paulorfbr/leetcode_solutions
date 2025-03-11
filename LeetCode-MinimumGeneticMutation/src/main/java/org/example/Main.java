package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        //System.out.println(new Main().minMutation("AACCGGTT", "AACCGGTA", new String[] {"AACCGGTA"}));
        //System.out.println(new Main().minMutation("AACCGGTT", "AAACGGTA", new String[] {"AACCGGTA","AACCGCTA","AAACGGTA"}));
        //System.out.println(new Main().minMutation("AACCTTGG","AATTCCGG",new String[]{"AATTCCGG","AACCTGGG","AACCCCGG","AACCTACC"}));
        System.out.println(new Main().minMutation("AAAAAAAA","CCCCCCCC",new String[]{"AAAAAAAA","AAAAAAAC","AAAAAACC","AAAAACCC","AAAACCCC","AACACCCC","ACCACCCC","ACCCCCCC","CCCCCCCA","CCCCCCCC"}));

    }

    public int minMutation(String startGene, String endGene, String[] bank) {
        if (startGene.equals(endGene)){
            return 0;
        }
        List<String> bankGene = Arrays.asList(bank);
        if (!bankGene.contains(endGene)){
            return -1;
        }
        //is possible to reach from start to end 1 change?
        int distance = minDistance(startGene, endGene);
        if (distance<=1){
            return distance;
        }

        List<String> allGenes = new ArrayList<>();
        allGenes.add(startGene);
        allGenes.addAll(Arrays.asList(bank).stream().filter(g-> !g.equals(endGene)).collect(Collectors.toList()));
        allGenes.add(endGene);

        Graph graph = new Graph();
        graph.vertices = new HashSet<>(allGenes.size());
        for (int i=0;i<allGenes.size();i++) {
            graph.vertices.add(i);
        }

        int[][] distances = new int[allGenes.size()][allGenes.size()];
        for (int i=0;i<allGenes.size();i++) {
            for (int j = 0; j < allGenes.size(); j++) {
                if (i != j) {
                    int dist = minDistance(allGenes.get(i), allGenes.get(j));
                    if (dist == 1) {
                        distances[i][j] = dist;
                        Edge e = new Edge(i,j,dist);
                        graph.edges.add(e);
                    }
                    else {
                        distances[i][j] = Integer.MAX_VALUE;
                    }
                } else {
                    distances[i][j] = Integer.MAX_VALUE;
                }
            }
        }

        List<String> transformations = new ArrayList<>();
        int start=0;
        int end = allGenes.size()-1;
        for (int j=0;j<allGenes.size();j++){
            if (distances[start][j]==1){
                transformations.add("start:"+startGene+">"+allGenes.get(j));
            }
            if (distances[j][end]==1){
                transformations.add(allGenes.get(j)+">end:"+endGene);
            }
        }
        //intermediate
        for (int i=1;i<allGenes.size()-1;i++){
            for (int j=1;j<allGenes.size()-1;j++){
                if (distances[i][j]==1) {
                    transformations.add("i1:" + allGenes.get(i) + ">i2:" + allGenes.get(j));
                }
            }
        }
        System.out.println(transformations);

        DijkstraResult result = dijkstra(graph, 0);
        int maxDistance = (int)result.distancesFromStartVertex[end];
        if (maxDistance==Integer.MAX_VALUE){
            return -1;
        }
        return maxDistance;
    }

    public int minDistance(String word1, String word2) {
        return distance(word1.toCharArray(), word2.toCharArray());
    }

    private int distance(char[] s, char[] t) {
        int diffCount = 0;
        for (int i=0;i<s.length;i++){
            if (s[i]!=t[i]){
                diffCount++;
            }
        }
        return diffCount;
    }

    class Edge{
        private Integer startVertex;
        private Integer endVertex;

        private Integer distance;

        public Edge(Integer startVertex, Integer endVertex, Integer distance) {
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