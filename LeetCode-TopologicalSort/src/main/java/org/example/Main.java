package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        findOrder(4, new int[][]{{1,0},{2,0},{3,1},{3,2}});
        findOrder(1, new int[][]{});
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> topologicSort = topologicalSort(numCourses, prerequisites);
        System.out.println(topologicSort);
        return topologicSort.stream().mapToInt(Integer::intValue).toArray();
    }

    private static List<Integer> topologicalSort(int numCourses, int[][] prerequisites){
        //L ← Empty list that will contain the sorted elements
        List<Integer> topologicSort = new ArrayList<>();
        int[] inDegree = new int[numCourses];

        for (int l=0;l<prerequisites.length;l++){
            int nodeTo = prerequisites[l][0]; //this discipline depends on;
            inDegree[nodeTo]++;
        }

        //S ← Set of all nodes with no incoming edge
        Queue<Integer> noIncomingNodes = new ArrayDeque<>();
        for (int i=0;i<numCourses;i++){
            if (inDegree[i]==0){
                noIncomingNodes.add(i); //no pre-req for this node
            }
        }

        while (!noIncomingNodes.isEmpty()){
            Integer n = noIncomingNodes.poll();
            topologicSort.add(n);

            //remove all prerequisites from n to others
            for (int l=0;l<prerequisites.length;l++){
                int nodeTo = prerequisites[l][0]; //this discipline m depends on;
                int nodeFrom = prerequisites[l][1]; //discipline is pre-requisite for
                if (nodeFrom==n) {
                    //for each node m with an edge e from n to m do
                    //remove edge e from the graph
                    inDegree[nodeTo]--;
                    if (inDegree[nodeTo]==0){
                        //if m has no other incoming edges then insert m into S
                        noIncomingNodes.offer(nodeTo);
                    }
                }
            }
        }

        boolean stillHasEdges = false;
        for (int i=0;i<numCourses;i++){
            if (inDegree[i]>0){
                stillHasEdges=true;
                break;
            }
        }
        if (stillHasEdges) { //graph has edges then
            return new ArrayList<>(); //there is cycle in graph, can not finish
        }
        else {
            return topologicSort;
        }
    }
}