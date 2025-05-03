import java.util.*;

public class TreeDiameter {
    static class Tree {
        int n; // מספר הצמתים
        List<List<Integer>> adj;

        Tree(int n) {
            this.n = n;
            adj = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }
        }

        void addEdge(int u, int v) {
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        void printTree() {
            System.out.println("העץ (רשימות שכנות):");
            for (int i = 0; i < n; i++) {
                System.out.print(i + ": ");
                for (int neighbor : adj.get(i)) {
                    System.out.print(neighbor + " ");
                }
                System.out.println();
            }
        }

        // מחזירה את הצומת הרחוק ביותר ואת המרחק אליו
        private int[] bfs(int start) {
            int[] dist = new int[n];
            Arrays.fill(dist, -1);
            Queue<Integer> q = new LinkedList<>();
            q.add(start);
            dist[start] = 0;

            int farthestNode = start;
            while (!q.isEmpty()) {
                int u = q.poll();
                for (int v : adj.get(u)) {
                    if (dist[v] == -1) {
                        dist[v] = dist[u] + 1;
                        q.add(v);
                        if (dist[v] > dist[farthestNode]) {
                            farthestNode = v;
                        }
                    }
                }
            }
            return new int[]{farthestNode, dist[farthestNode]};
        }

        int findDiameter() {
            int[] firstBFS = bfs(0);          // שלב 1: מציאת קצה ראשון של הקוטר
            int[] secondBFS = bfs(firstBFS[0]); // שלב 2: מציאת הקצה השני ומרחקו
            return secondBFS[1];              // מחזיר את הקוטר
        }
    }

}
