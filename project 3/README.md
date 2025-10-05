The purpose of Assignment 3 is to become familiar with the data structures used for implementing a symbol table, such as binary search trees (see related lecture slides: 12–14).

The goal of the assignment is to construct a Word Counter. The main functionality of the counter is that it will be able to 'load' a file containing English text and count how many times each word appears. For example, consider the following text:

Hello, how are you?

Very well, thank you. I study for the exams. How about you?

Fine, thank you. How many exams will you have?

Too many…

The word 'you' appears 5 times, the word 'how' 3 times, the words 'thank', 'many', 'exams' 2 times each, while the words hello, are, very, well, I, study, about, fine, will, have appear once.

The Word Counter should ignore punctuation marks, e.g., , . ? ; ! - :, as well as parentheses, brackets, operators, or any other character that is not a letter of the English alphabet. From the text above, when reading the string 'well,' it should strip the comma, leaving only the word 'well'. If there were a phrase in parentheses, e.g., '(Maria asked me)', it should consider only the words Maria, asked, and me.

Only the single apostrophe is allowed within a word, e.g., 'don't' should be treated as a single word. Additionally, all strings containing numbers should be completely ignored, e.g., '17:25' or '1980’s'.

Finally, the user of the library should be able to define special words (stop words) that will be completely ignored. For example, in related applications, articles such as 'a', 'an', and 'the' are usually ignored.

The program must be case-insensitive. For example, the words 'Hello' and 'hello' are the same
