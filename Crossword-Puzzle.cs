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
