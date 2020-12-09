public class QSPalindrome{
    
    // creates empty queues to work with
    Queue_DL queue = new Queue_DL(500);
    Stack_DL stack = new Stack_DL(500);

    // useful strings to use later
    private int n;
    private String[] string;
    private String orig;
    
    public QSPalindrome(String test){
        // want to work with half of the string to compare halves for palindrome
        n = test.length() / 2;
        string = new String[test.length()];
        for(int i = 0; i < test.length(); i++){
            string[i] = String.valueOf(test.charAt(i));
        }
        orig = test;
    }

    public String isPalindrome(){
        String comp1 = ""; String comp2 = "";
        // push out n amount of characters from stack and compare to dequeued n amount of characters from queue
        for(int i = 0; i < string.length; i++){
            String character = string[i];
            queue.enqueue(character);
            stack.push(character);
        }
        // create strings to compare
        for(int i = 0; i < n; i++){
            comp1 += queue.dequeue();
            comp2 += stack.pop();
        }
        // compare strings and return result

        if(comp1.equals(comp2) == true){
            return orig + " is a palindrome.";
        }
        else{
            return orig + " is not a palindrome.";
        }
    }

    public static void main(String[] args) {
        QSPalindrome palindromeCheck = new QSPalindrome("racecar");
        System.out.println(palindromeCheck.isPalindrome());
    }
}

/**
 * racecar is a palindrome.
 */