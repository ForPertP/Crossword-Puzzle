import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'crosswordPuzzle' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. STRING_ARRAY crossword
     *  2. STRING words
     */

    public static boolean canPlaceHorizontally(List<String> crossword, String word, int row, int col) {
        if (col + word.length() > 10) {
            return false;
        }

        for (int i = 0; i < word.length(); i++) {
            if (crossword.get(row).charAt(col + i) != '-' && crossword.get(row).charAt(col + i) != word.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    public static boolean[] placeHorizontally(List<String> crossword, String word, int row, int col) {
        boolean[] placed = new boolean[word.length()];
        StringBuilder rowBuilder = new StringBuilder(crossword.get(row));

        for (int i = 0; i < word.length(); i++) {
            if (rowBuilder.charAt(col + i) == '-') {
                rowBuilder.setCharAt(col + i, word.charAt(i));
                placed[i] = true;
            }
        }

        crossword.set(row, rowBuilder.toString());
        return placed;
    }

    public static void removeHorizontally(List<String> crossword, String word, int row, int col, boolean[] placed) {
        StringBuilder rowBuilder = new StringBuilder(crossword.get(row));

        for (int i = 0; i < word.length(); i++) {
            if (placed[i]) {
                rowBuilder.setCharAt(col + i, '-');
            }
        }

        crossword.set(row, rowBuilder.toString());
    }

    public static boolean canPlaceVertically(List<String> crossword, String word, int row, int col) {
        if (row + word.length() > 10) {
            return false;
        }

        for (int i = 0; i < word.length(); i++) {
            if (crossword.get(row + i).charAt(col) != '-' && crossword.get(row + i).charAt(col) != word.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    public static boolean[] placeVertically(List<String> crossword, String word, int row, int col) {
        boolean[] placed = new boolean[word.length()];

        for (int i = 0; i < word.length(); i++) {
            StringBuilder rowBuilder = new StringBuilder(crossword.get(row + i));
            if (rowBuilder.charAt(col) == '-') {
                rowBuilder.setCharAt(col, word.charAt(i));
                placed[i] = true;
            }
            crossword.set(row + i, rowBuilder.toString());
        }

        return placed;
    }

    public static void removeVertically(List<String> crossword, String word, int row, int col, boolean[] placed) {
        for (int i = 0; i < word.length(); i++) {
            StringBuilder rowBuilder = new StringBuilder(crossword.get(row + i));
            if (placed[i]) {
                rowBuilder.setCharAt(col, '-');
            }
            crossword.set(row + i, rowBuilder.toString());
        }
    }

    public static boolean solveCrossword(List<String> crossword, List<String> words, int index) {
        if (index == words.size()) {
            return true;
        }

        String word = words.get(index);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (canPlaceHorizontally(crossword, word, i, j)) {
                    boolean[] placed = placeHorizontally(crossword, word, i, j);
                    if (solveCrossword(crossword, words, index + 1)) {
                        return true;
                    }
                    removeHorizontally(crossword, word, i, j, placed);
                }

                if (canPlaceVertically(crossword, word, i, j)) {
                    boolean[] placed = placeVertically(crossword, word, i, j);
                    if (solveCrossword(crossword, words, index + 1)) {
                        return true;
                    }
                    removeVertically(crossword, word, i, j, placed);
                }
            }
        }

        return false;
    }

    public static List<String> crosswordPuzzle(List<String> crossword, String words) {
        List<String> wordList = Arrays.asList(words.split(";"));

        solveCrossword(crossword, wordList, 0);
        return crossword;
    }
}


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        List<String> crossword = IntStream.range(0, 10).mapToObj(i -> {
            try {
                return bufferedReader.readLine();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .collect(toList());

        String words = bufferedReader.readLine();

        List<String> result = Result.crosswordPuzzle(crossword, words);

        bufferedWriter.write(
            result.stream()
                .collect(joining("\n"))
            + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}
