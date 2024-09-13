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

    static boolean solveCrossword(List<String> crossword, List<String> wordList) {
        if (wordList.size() == 0) return true;
    }


    public static List<String> crosswordPuzzle(List<String> crossword, String words) {
        List<String> wordList = new ArrayList<>(Arrays.asList(words.split(";")));
        solveCrossword(crossword, wordList);
        return crossword;
    }
}

