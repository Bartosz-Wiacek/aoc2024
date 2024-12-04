import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3
{
    public static void main(String[] args)
    {
        int resultPartOne = 0;
        int resultPartTwo = 0;
        boolean mulEnabled = true; // Track if mul instructions are enabled for part 2
        try {
            File myObj = new File("./day_3/src/input.txt");
            Scanner myReader = new Scanner(myObj);

            StringBuilder fullInput = new StringBuilder();
            while (myReader.hasNextLine()) {
                fullInput.append(myReader.nextLine());
            }
            String line = fullInput.toString();

            // Combined regex to find mul() instructions, do(), and don't()
            // This regex matches:
            // - mul(X,Y) where X and Y are 1-3 digit numbers
            // - do()
            // - don't()
            Pattern combinedPattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)|do\\(\\)|don't\\(\\)");
            Matcher matcher = combinedPattern.matcher(line);

            // Find and process all instructions in the order they appear
            while (matcher.find()) {
                if (matcher.group(1) != null && matcher.group(2) != null) {
                    // It's a mul(X,Y) instruction
                    try {
                        int firstNum = Integer.parseInt(matcher.group(1));
                        int secondNum = Integer.parseInt(matcher.group(2));
                        int result = firstNum * secondNum;
                        resultPartOne += result;

                        // For Part Two, add to sum only if mulEnabled is true
                        if (mulEnabled) {
                            resultPartTwo += result;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number format in mul instruction.");
                    }
                }
                else if (matcher.group(0).equals("do()")) {
                    mulEnabled = true;
                }
                else if (matcher.group(0).equals("don't()")) {
                    mulEnabled = false;
                }
            }

            System.out.println("Result part one: " + resultPartOne);
            System.out.println("Result part two: " + resultPartTwo);
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}
