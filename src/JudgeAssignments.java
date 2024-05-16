import java.io.*;
import java.util.*;

//YASAMIN VALISHARIATPANAHI 2022510013
//KASRA SOLTANI 2022510011
/*
 * RUN TIME ANALYSIS:
 *
 * public static List<String> problemTypesInput(String fileName): O(n)
 * public static int assignProblems(int totalJudges, int changeCost, LinkedList<String> problemTypes): O(n*m)
 * n is number of problem types and m is number of total judges which in the worst case it will be O(n^2)
 */
public class JudgeAssignments {
    // Attributes
    static final String TXTFile = "input3.txt";
    static int totalJudges;
    static int changeCost;
    static LinkedList<String> problemTypes = new LinkedList<>();
    static List<Queue<String>> judgeQueues = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("<< Welcome To The Court >>\n");

        // Getting necessary data from the user
        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the number of judges: ");
        totalJudges = scanner.nextInt();
        System.out.print("Please enter the cost of problem type changing: ");
        changeCost = scanner.nextInt();

        // Read the TXT file and assigned the data to the LinkedList
        problemTypes.addAll(problemTypesInput(TXTFile));
        System.out.println("\nThe input file is read.");

        // Print the problem types
        System.out.println("\nThe problem types are listed:");
        Iterator<String> iterator = problemTypes.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
            if (iterator.hasNext()) {
                System.out.print(", ");
            }
        }
        System.out.println("\n");

        // Greedy Algorithm
        int totalCost = assignProblems(totalJudges, changeCost, problemTypes);

        // Total cost of these problem types
        System.out.println("\nTotal cost: " + totalCost);
    }

    // O(n)
    public static List<String> problemTypesInput(String fileName) throws FileNotFoundException {
        List<String> problemTypes = new ArrayList<>();
        File file = new File(fileName);
        Scanner scanner = new Scanner(file);

        // Skip the header
        if (scanner.hasNextLine()) scanner.nextLine(); // Skip the first line

        while (scanner.hasNextLine()) {
            String type = scanner.nextLine().trim();
            if (!type.isEmpty()) problemTypes.add(type);
        }

        scanner.close();
        return problemTypes;
    }

    // Greedy Algorithm : O(n*m) depends on number of problems and number of judges in worst case O(n^2)
    public static int assignProblems(int totalJudges, int changeCost, List<String> problemTypes) {
        int totalCost = 0;
        String[] lastSeenType = new String[totalJudges];

        for (int i = 0; i < totalJudges; i++) {
            judgeQueues.add(new LinkedList<>());
        }

        for (int i = 0; i < problemTypes.size(); i++) {
            String problemType = problemTypes.get(i);
            boolean assigned = false;
            int alternativeJudgeIndex = -1;

            // If there's only one judge, assign problem directly without considering last seen type
            if (totalJudges == 1) {
                judgeQueues.get(0).add(problemType);
                totalCost++;
                continue;
            }

            for (int judgeIndex = 0; judgeIndex < totalJudges; judgeIndex++) {
                String lastType = lastSeenType[judgeIndex];
                Queue<String> currentJudgeQueue = judgeQueues.get(judgeIndex);

                // If the last seen type for this judge is null or matches the current problem type
                if (lastType == null || lastType.equals(problemType) ||
                        (!currentJudgeQueue.isEmpty() && currentJudgeQueue.peek().equals(problemType))) {

                    // If the last seen type is not null, and it matches the current problem type
                    if (lastType != null && lastType.equals(problemType)) {
                        currentJudgeQueue.add(problemType);
                        lastSeenType[judgeIndex] = problemType;
                        assigned = true;
                        break;
                    }

                    // Assign the problem to this judge if the last seen type is null
                    currentJudgeQueue.add(problemType);
                    lastSeenType[judgeIndex] = problemType;
                    totalCost++;
                    assigned = true;
                    break;
                } else if (!lastType.equals(problemType) && alternativeJudgeIndex == -1) {
                    boolean foundSameTypeInFuture = false;
                    for (int j = i + 1; j < Math.min(i + 1 + totalJudges, problemTypes.size()); j++) {
                        if (problemTypes.get(j).equals(lastType)) {
                            foundSameTypeInFuture = true;
                            break;
                        }
                    }
                    if (!foundSameTypeInFuture) {
                        alternativeJudgeIndex = judgeIndex;
                    }
                }
            }

            // If the problem type was not assigned to any judge
            if (!assigned) {
                if (alternativeJudgeIndex != -1) {
                    judgeQueues.get(alternativeJudgeIndex).add(problemType);
                    lastSeenType[alternativeJudgeIndex] = problemType;
                } else {
                    // Fallback case if no suitable judge is found, assign to the first available judge
                    for (int judgeIndex = 0; judgeIndex < totalJudges; judgeIndex++) {
                        if (lastSeenType[judgeIndex] != null && !lastSeenType[judgeIndex].equals(problemType)) {
                            judgeQueues.get(judgeIndex).add(problemType);
                            lastSeenType[judgeIndex] = problemType;
                            break;
                        }
                    }
                }
                totalCost++;
            }
        }

        totalCost *= changeCost;

        // Print final assignments to the judges
        System.out.println("Solution:");
        int num = 1;
        for (int i = 0; i < totalJudges; i++) {
            System.out.println("Judge " + num + " assigned problems: " + judgeQueues.get(i));
            num++;
        }

        return totalCost;
    }
}


