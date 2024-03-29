// https://www.hackerrank.com/challenges/crossword-puzzle/forum : from mdjabirov

bool crosswordPuzzle(vector<string>& crossword, vector<string>& words)
{
    auto try_place = [&](auto i, auto j, auto r)
    {
        auto word = words.back();
        if ((r?j:i)+word.length() > 10) return false;
        
        for (size_t k = 0; k < word.length(); ++k)
        {
            if (crossword[r?i:i+k][r?j+k:j] != '-' 
             && crossword[r?i:i+k][r?j+k:j] != word[k])
                return false;
        }                
        
        auto crossword_copy = crossword;
        
        for (size_t k = 0; k < word.length(); ++k)
        {
            crossword[r?i:i+k][r?j+k:j] = word[k];
        }
        
        words.pop_back();
        bool success = crosswordPuzzle(crossword, words);
        words.push_back(word);
        
        if (!success)
        {
            crossword = crossword_copy;
        }
        
        return success;
    };
    
    if (words.size() == 0) return true;
    
    for (size_t i = 0; i < 10; ++i)
    {
        for (size_t j = 0; j < 10; ++j)
        {
            if (try_place(i, j, 1/*by row*/)) return true;
            if (try_place(i, j, 0/*by col*/)) return true;
        }
    }
    
    return false;
};

vector<string> crosswordPuzzle(vector<string> crossword, string words)
{
    vector<string> word_vec;

    for (size_t i = 0, j = 0; i < words.length(); ++i)
    {
        if (words[i] == ';')
        {
            word_vec.push_back(words.substr(j, i-j));
            j = i+1;
        }
        else if (i == words.length()-1)
        {
            word_vec.push_back(words.substr(j, i-j+1));
        }
    }
    
    crosswordPuzzle(crossword, word_vec);
    
    return crossword;
}

