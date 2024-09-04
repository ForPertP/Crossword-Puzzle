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

    static bool SolveCrossword(List<string> crossword, List<string> wordList)
    {
        if (wordList.Count == 0) return true;

        string word = wordList[wordList.Count - 1];
        int len = word.Length;

        for (int i = 0; i < 10; ++i)
        {
            for (int j = 0; j < 10; ++j)
            {
                if (j + len <= 10)
                {
                    List<string> backup = crossword.Select(s => new string(s.ToCharArray())).ToList();

                    bool canPlace = true;

                    for (int k = 0; k < len; ++k)
                    {
                        if (crossword[i][j + k] != '-' && crossword[i][j + k] != word[k])
                        {
                            canPlace = false;
                            break;
                        }
                    }

                    if (canPlace)
                    {
                        for (int k = 0; k < len; ++k)
                        {
                            //char[] line = crossword[i].ToCharArray();
                            //line[j + k] = word[k];
                            //crossword[i] = new string(line);

                            StringBuilder sb = new StringBuilder(crossword[i]);
                            sb[j + k] = word[k];
                            crossword[i] = sb.ToString();
                        }

                        wordList.RemoveAt(wordList.Count - 1);
                        if (SolveCrossword(crossword, wordList)) return true;

                        wordList.Add(word);

                        for (int l = 0; l < crossword.Count; l++)
                        {
                            crossword[l] = backup[l];
                        }
                    }
                }

            }
        }

        return false;
    }


    public static List<string> crosswordPuzzle(List<string> crossword, string words)
    {
        List<string> wordList = new List<string>(words.Split(';'));
        SolveCrossword(crossword, wordList);
        return crossword;
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
