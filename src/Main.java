
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        //******* שאלה 2 **********
        // יוצרים עץ לדוגמה עם 7 צמתים
        TreeDiameter.Tree tree = new TreeDiameter.Tree(7);
        tree.addEdge(0, 1);
        tree.addEdge(0, 2);
        tree.addEdge(1, 3);
        tree.addEdge(1, 4);
        tree.addEdge(2, 5);
        tree.addEdge(5, 6);

        System.out.println("שאלה 2");
        tree.printTree(); // הדפסת העץ

        int diameter = tree.findDiameter(); // חישוב הקוטר
        System.out.println("קוטר העץ הוא: " + diameter);

        //******* שאלה 3 **********
        System.out.println("\nשאלה 3");
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B"));
        graph.put("B", Arrays.asList("C"));
        graph.put("C", Arrays.asList("A", "D"));
        graph.put("D", Arrays.asList("B"));
        testGraph(graph, "שאלה 3 - גרף ראשון(קשיר)");

        // שאלה 3 - גרף לא קשיר היטב
        Map<String, List<String>> graph2 = new HashMap<>();
        graph2.put("A", Arrays.asList("B"));
        graph2.put("B", Arrays.asList("C"));
        graph2.put("C", Arrays.asList());
        testGraph(graph2, "שאלה 3 - גרף שני (לא קשיר)");

    }

    public static void testGraph(Map<String, List<String>> graph, String label) {
        System.out.println("\n" + label);
        System.out.println("הגרף:");
        StronglyConnectedGraph.printGraph(graph);

        System.out.println("\nהאם הגרף קשיר היטב?");
        boolean result = StronglyConnectedGraph.isStronglyConnected(graph);
        System.out.println(result ? "כן, הגרף קשיר היטב." : "לא, הגרף אינו קשיר היטב.");
    }
}