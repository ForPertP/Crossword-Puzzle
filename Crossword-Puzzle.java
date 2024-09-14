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

        String word = wordList.get(wordList.size() - 1);
        int len = word.length();

        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 10; ++j) {
                if (j + len <= 10) {
                    List<String> backup = new ArrayList<>(crossword);

                    boolean canPlace = true;
                    for (int k = 0; k < len; ++k) {
                        if (crossword.get(i).charAt(j + k) != '-' && crossword.get(i).charAt(j + k) != word.charAt(k)) {
                            canPlace = false;
                            break;
                        }
                    }

                    if (canPlace) {
                        StringBuilder sb = new StringBuilder(crossword.get(i));
                        for (int k = 0; k < len; ++k) {
                            sb.setCharAt(j + k, word.charAt(k));
                        }
                        crossword.set(i, sb.toString());

                        wordList.remove(wordList.size() - 1);
                        if (solveCrossword(crossword, wordList)) return true;
                        wordList.add(word);

                        crossword.clear();
                        crossword.addAll(backup);
                    }
                }


                if (i + len <= 10) {
                    List<String> backup = new ArrayList<>(crossword);

                    boolean canPlace = true;
                    for (int k = 0; k < len; ++k) {
                        if (crossword.get(i + k).charAt(j) != '-' && crossword.get(i + k).charAt(j) != word.charAt(k)) {
                            canPlace = false;
                            break;
                        }
                    }

                    if (canPlace) {
                        for (int k = 0; k < len; ++k) {
                            StringBuilder sb = new StringBuilder(crossword.get(i + k));
                            sb.setCharAt(j, word.charAt(k));
                            crossword.set(i + k, sb.toString());
                        }

                        wordList.remove(wordList.size() - 1);
                        if (solveCrossword(crossword, wordList)) return true;
                        wordList.add(word);

                        crossword.clear();
                        crossword.addAll(backup);
                    }
                }
            }
        }
        return false;
    }


    public static List<String> crosswordPuzzle(List<String> crossword, String words) {
        List<String> wordList = new ArrayList<>(Arrays.asList(words.split(";")));
        solveCrossword(crossword, wordList);
        return crossword;
    }
}

