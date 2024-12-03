import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2
{
    public static void main(String[] args)
    {
        ArrayList<Integer> numberList = new ArrayList<>();
        int totalSum = 0;
        int totalSum2 = 0;

        try {
            File myObj = new File("./day_2/src/input.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                String[] numbers = line.split("\\s+");

                // Clear the list for each new line
                numberList.clear();

                // Make an arrayList from the numbers
                for (String numStr : numbers) {
                    try {
                        int num = Integer.parseInt(numStr);
                        numberList.add(num);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format: " + numStr);
                    }
                }

                // Part 1: Check if the list is safe without removal
                if ((isIncreasing(numberList) || isDecreasing(numberList)) &&
                        biggestDifference(numberList) <= 3)
                {
                    totalSum++;
                }

                // Part 2: Check if the list is safe with Problem Dampener
                if (isSafeWithProblemDampener(numberList))
                {
                    totalSum2++;
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println("Total safe reports (Part 1): " + totalSum);
        System.out.println("Total safe reports (Part 2): " + totalSum2);
    }

    public static boolean isIncreasing(ArrayList<Integer> arrayList)
    {
        if (arrayList.size() == 0)
        {
            return false;
        }
        int previous = arrayList.get(0);
        for (int i = 1; i < arrayList.size(); i++)
        {
            int current = arrayList.get(i);
            if (current <= previous)
            {
                return false;
            }
            previous = current;
        }
        return true;
    }

    public static boolean isDecreasing(ArrayList<Integer> arrayList)
    {
        if (arrayList.size() == 0)
        {
            return false;
        }
        int previous = arrayList.get(0);
        for (int i = 1; i < arrayList.size(); i++)
        {
            int current = arrayList.get(i);
            if (current >= previous)
            {
                return false;
            }
            previous = current;
        }
        return true;
    }

    public static int biggestDifference(ArrayList<Integer> arrayList)
    {
        if (arrayList.size() == 0)
        {
            return 0;
        }
        int biggestDiff = 0;
        int previous = arrayList.get(0);
        for (int i = 1; i < arrayList.size(); i++)
        {
            int current = arrayList.get(i);
            int diff = Math.abs(current - previous);
            if (diff > biggestDiff)
            {
                biggestDiff = diff;
            }
            previous = current;
        }
        return biggestDiff;
    }

    public static boolean isSafeWithProblemDampener(ArrayList<Integer> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            ArrayList<Integer> modifiedList = new ArrayList<>(arrayList);
            modifiedList.remove(i);

            if (isIncreasing(modifiedList) || isDecreasing(modifiedList)) {
                if (biggestDifference(modifiedList) <= 3) {
                    return true;
                }
            }
        }
        // Check if the original list was already safe
        return (isIncreasing(arrayList) || isDecreasing(arrayList)) && biggestDifference(arrayList) <= 3;
    }
}