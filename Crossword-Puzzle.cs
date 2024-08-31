using System.CodeDom.Compiler;
using System.Collections.Generic;
using System.Collections;
using System.ComponentModel;
using System.Diagnostics.CodeAnalysis;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Runtime.Serialization;
using System.Text.RegularExpressions;
using System.Text;
using System;

class Result
{

    /*
     * Complete the 'crosswordPuzzle' function below.
     *
     * The function is expected to return a STRING_ARRAY.
     * The function accepts following parameters:
     *  1. STRING_ARRAY crossword
     *  2. STRING words
     */

    public static List<string> crosswordPuzzle(List<string> crossword, string words)
    {
        string[] wordList = words.Split(';');
        char[,] board = new char[10, 10];

        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                board[i, j] = crossword[i][j];
            }
        }

        SolveCrossword(board, wordList, 0);

        List<string> result = new List<string>();
        for (int i = 0; i < 10; i++)
        {
            char[] row = new char[10];
            for (int j = 0; j < 10; j++)
            {
                row[j] = board[i, j];
            }
            result.Add(new string(row));
        }

        return result;
    }


    private static bool SolveCrossword(char[,] board, string[] words, int index)
    {
        if (index == words.Length)
        {
            return true;
        }

        string word = words[index];

        for (int i = 0; i < 10; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                if (CanPlaceHorizontally(board, word, i, j))
                {
                    bool[] placed = PlaceHorizontally(board, word, i, j);
                    if (SolveCrossword(board, words, index + 1))
                    {
                        return true;
                    }
                    RemoveHorizontally(board, word, i, j, placed);
                }

                if (CanPlaceVertically(board, word, i, j))
                {
                    bool[] placed = PlaceVertically(board, word, i, j);
                    if (SolveCrossword(board, words, index + 1))
                    {
                        return true;
                    }
                    RemoveVertically(board, word, i, j, placed);
                }
            }
        }

        return false;
    }


private static bool CanPlaceHorizontally(char[,] board, string word, int row, int col)
    {
        if (col + word.Length > 10)
        {
            return false;
        }

        for (int i = 0; i < word.Length; i++)
        {
            if (board[row, col + i] != '-' && board[row, col + i] != word[i])
            {
                return false;
            }
        }

        return true;
    }

    
    private static bool[] PlaceHorizontally(char[,] board, string word, int row, int col)
    {
        bool[] placed = new bool[word.Length];

        for (int i = 0; i < word.Length; i++)
        {
            if (board[row, col + i] == '-')
            {
                board[row, col + i] = word[i];
                placed[i] = true;
            }
        }

        return placed;
    }

    private static void RemoveHorizontally(char[,] board, string word, int row, int col, bool[] placed)
    {
        for (int i = 0; i < word.Length; i++)
        {
            if (placed[i])
            {
                board[row, col + i] = '-';
            }
        }
    }


    private static bool CanPlaceVertically(char[,] board, string word, int row, int col)
    {
        if (row + word.Length > 10)
        {
            return false;
        }

        for (int i = 0; i < word.Length; i++)
        {
            if (board[row + i, col] != '-' && board[row + i, col] != word[i])
            {
                return false;
            }
        }

        return true;
    }

    private static bool[] PlaceVertically(char[,] board, string word, int row, int col)
    {
        bool[] placed = new bool[word.Length];

        for (int i = 0; i < word.Length; i++)
        {
            if (board[row + i, col] == '-')
            {
                board[row + i, col] = word[i];
                placed[i] = true;
            }
        }

        return placed;
    }

    private static void RemoveVertically(char[,] board, string word, int row, int col, bool[] placed)
    {
        for (int i = 0; i < word.Length; i++)
        {
            if (placed[i])
            {
                board[row + i, col] = '-';
            }
        }
    }
    
    
}

class Solution
{
    public static void Main(string[] args)
    {
        TextWriter textWriter = new StreamWriter(@System.Environment.GetEnvironmentVariable("OUTPUT_PATH"), true);

        List<string> crossword = new List<string>();

        for (int i = 0; i < 10; i++)
        {
            string crosswordItem = Console.ReadLine();
            crossword.Add(crosswordItem);
        }

        string words = Console.ReadLine();

        List<string> result = Result.crosswordPuzzle(crossword, words);

        textWriter.WriteLine(String.Join("\n", result));

        textWriter.Flush();
        textWriter.Close();
    }
}
