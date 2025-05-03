
import java.util.*;

    public class StronglyConnectedGraph {

        // פונקציית DFS שמחזירה את קבוצת הצמתים שניתן להגיע אליהם מהצומת ההתחלתי
        public static Set<String> dfs(Map<String, List<String>> graph, String start) {
            Set<String> visited = new HashSet<>();
            dfsVisit(graph, start, visited);
            return visited;
        }

        private static void dfsVisit(Map<String, List<String>> graph, String node, Set<String> visited) {
            visited.add(node);
            for (String neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    dfsVisit(graph, neighbor, visited);
                }
            }
        }

        // פונקציה ליצירת גרף הפוך
        public static Map<String, List<String>> reverseGraph(Map<String, List<String>> graph) {
            Map<String, List<String>> reversed = new HashMap<>();
            for (String u : graph.keySet()) {
                for (String v : graph.get(u)) {
                    reversed.putIfAbsent(v, new ArrayList<>());
                    reversed.get(v).add(u);
                }
            }
            // חשוב להוסיף גם צמתים ללא שכנים בגרף המקורי
            for (String u : graph.keySet()) {
                reversed.putIfAbsent(u, reversed.getOrDefault(u, new ArrayList<>()));
            }
            return reversed;
        }

        // פונקציה שבודקת אם הגרף קשיר היטב
        public static boolean isStronglyConnected(Map<String, List<String>> graph) {
            String start = graph.keySet().iterator().next();

            // בדיקה בגרף המקורי
            Set<String> visited = dfs(graph, start);
            if (visited.size() != graph.size()) return false;

            // בדיקה בגרף ההפוך
            Map<String, List<String>> reversedGraph = reverseGraph(graph);
            Set<String> visitedReversed = dfs(reversedGraph, start);
            return visitedReversed.size() == graph.size();
        }

        // הדפסת הגרף
        public static void printGraph(Map<String, List<String>> graph) {
            for (String node : graph.keySet()) {
                System.out.print(node + " → ");
                System.out.println(graph.get(node));
            }
        }

    }

