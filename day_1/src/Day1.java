import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Day1
{
    public static void main(String[] args)
    {
        int totalSum = 0;
        int smallestLeft = 0;
        int smallestRight = 0;

        int similarityScore = 0;
        int currentNumber = 0;
        int howManySimilar = 0;
        try {
            ArrayList<Integer> leftList = processFile("left");
            ArrayList<Integer> rightList = processFile("right");

            while (leftList.size() != 0) {
                smallestLeft = smallestNumberInList(leftList);
                smallestRight = smallestNumberInList(rightList);

                totalSum += Math.abs(smallestLeft - smallestRight);

                leftList.remove(leftList.indexOf(smallestLeft));
                rightList.remove(rightList.indexOf(smallestRight));
            }
            System.out.println("Total sum: " + totalSum);

            // initialize arrayLists again
            leftList = processFile("left");
            rightList = processFile("right");

            for (int i = 0; i < leftList.size(); i++) {
                currentNumber = leftList.get(i);
                for (int j = 0; j < rightList.size(); j++) {
                    if (currentNumber == rightList.get(j))
                    {
                        howManySimilar++;
                    }
                }
                similarityScore += howManySimilar * currentNumber;
                howManySimilar = 0;
            }
            System.out.println("Similarity score: " + similarityScore);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static ArrayList<Integer> processFile(String side) throws IOException {
        ArrayList<Integer> leftList = new ArrayList<Integer>();
        ArrayList<Integer> rightList = new ArrayList<Integer>();

        try (BufferedReader br = new BufferedReader(new FileReader("day_1/src/input.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 2) {
                    leftList.add(Integer.parseInt(parts[0]));
                    rightList.add(Integer.parseInt(parts[1]));
                }
            }
        }

        if (side.equalsIgnoreCase("left")) {
            return leftList;
        } else if (side.equalsIgnoreCase("right")) {
            return rightList;
        } else {
            throw new IllegalArgumentException("Invalid argument: side must be 'left' or 'right'.");
        }
    }

    private static int smallestNumberInList(ArrayList<Integer> list) {
        int smallest = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < smallest) {
                smallest = list.get(i);
            }
        }
        return smallest;
    }
}
