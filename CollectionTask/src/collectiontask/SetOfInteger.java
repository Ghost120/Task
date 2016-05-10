package collectiontask;


import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Kasyanenko Konstantin
 */
public class SetOfInteger {

    /**
     * 
     * @param set1- множнство 1
     * @param set2- множество2
     * @return set
     */
    public static Set<Integer> isIntersect(Set set1, Set set2) {   
        return (Set<Integer>) set1.stream().filter(set2::contains).collect(Collectors.toSet());
    }
    /**
     * 
     * @param set1
     * @param set2
     * @return 
     */
    public static Set<Integer> concatSet(Set set1, Set set2){
        return (Set) Stream.concat(set1.stream(), set2.stream()).collect(Collectors.toSet());
    }
}
