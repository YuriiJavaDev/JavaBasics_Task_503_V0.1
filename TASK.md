### Imagine you're the strict director of a digital zoo. You have a list of animals, but you've decided that only those with names that are long enough (at least 4 characters) deserve a place in your prestigious collection. Short names, in your opinion, don't sound impressive enough.

#### - Take a list of animal names: "dog," "cow," "horse," and "sheep." Your task is to clean this list by removing all animals that aren't long enough.

#### - Use the removeIf method to do this and create a special "filter" using an anonymous class that implements Predicate<String>. This filter should check each name and exclude those with fewer than 4 characters.

#### - After "cleaning," display the final, cleaned list. It's expected that only [horse, sheep] will remain in your exhibit.

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class ZooManagerApp {
    public static void main(String[] args) {
        // Initial list of animals
        List<String> animals = new ArrayList<>(Arrays.asList("dog", "cow", "horse", "sheep"));

        // Remove all strings shorter than 4 characters from the list,
        // passing an anonymous class implementing Predicate<String> to removeIf.
        
        // Print the cleared list
        System.out.println(animals); // Expected: [horse, sheep]
    }
}
```
