import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numCases = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numCases; i++) {
            solve(scanner);
        }
        scanner.close();
    }

    public static void solve(Scanner scanner) {
        int n = Integer.parseInt(scanner.nextLine());
        String[] alphabetSymbols = scanner.nextLine().split(" ");

        String finalStatesLine = scanner.nextLine();
        Set<Integer> finalStates = new HashSet<>();
        if (!finalStatesLine.trim().isEmpty()) {
            Arrays.stream(finalStatesLine.split(" "))
                    .map(Integer::parseInt)
                    .forEach(finalStates::add);
        }

        int[][] transitions = new int[n][alphabetSymbols.length];
        for (int j = 0; j < n; j++) {
            String[] row = scanner.nextLine().split(" ");
            for (int k = 0; k < alphabetSymbols.length; k++) {
                transitions[j][k] = Integer.parseInt(row[k + 1]);
            }
        }
        // --- FIN DE LA CORRECCIÓN ---

        boolean[][] marked = new boolean[n][n];

        for (int p = 0; p < n; p++) {
            for (int q = p + 1; q < n; q++) {
                if (finalStates.contains(p) != finalStates.contains(q)) {
                    marked[p][q] = true;
                }
            }
        }

        boolean changed;
        do {
            changed = false;
            for (int p = 0; p < n; p++) {
                for (int q = p + 1; q < n; q++) {
                    if (!marked[p][q]) {
                        for (int k = 0; k < alphabetSymbols.length; k++) {
                            int p_next = transitions[p][k];
                            int q_next = transitions[q][k];
                            int s1 = Math.min(p_next, q_next);
                            int s2 = Math.max(p_next, q_next);

                            if (s1 != s2 && marked[s1][s2]) {
                                marked[p][q] = true;
                                changed = true;
                                break;
                            }
                        }
                    }
                }
            }
        } while (changed);

        List<String> equivalentPairs = new ArrayList<>();
        for (int p = 0; p < n; p++) {
            for (int q = p + 1; q < n; q++) {
                if (!marked[p][q]) {
                    equivalentPairs.add("(" + p + ", " + q + ")");
                }
            }
        }

        System.out.println(String.join(" ", equivalentPairs));
    }
}