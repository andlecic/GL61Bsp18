import java.util.Locale;

public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> res = new LinkedListDeque(); /*mystring.charAt(0) gets the 0th element of the string*/
        int i = 0;
        for (i = 0; i < word.length(); i++) {
            res.addLast(word.charAt(i));
        }
        return res;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> DequedWord = wordToDeque(word.toLowerCase());
        return isPalindromeHelper(DequedWord);
    }

    public boolean isPalindromeHelper(Deque<Character> DequedWord) {
        if (DequedWord.size() <= 1) {
            return true;
        } else if (DequedWord.removeLast() != DequedWord.removeFirst()) {
            return false;
        } else {
            return isPalindromeHelper(DequedWord);
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> DequedWord = wordToDeque(word.toLowerCase());
        return isPalindromeHelper(DequedWord, cc);
    }

    public boolean isPalindromeHelper(Deque<Character> DequedWord, CharacterComparator cc) {
        if (DequedWord.size() <= 1) {
            return true;
        } else if (!cc.equalChars(DequedWord.removeLast(),DequedWord.removeFirst())) {
            return false;
        } else {
            return isPalindromeHelper(DequedWord, cc);
        }
    }
}
