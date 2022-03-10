package test.java;

import java.util.*;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

class PriorityQueueTest{
    PriorityQueue<Integer> testQueue;
    @BeforeEach
    void setup(){
        testQueue = new PriorityQueue<>();
    }
    static Stream<Arguments> streamProvider(){
        return Stream.of(
                Arguments.of(new int[]{3, 1, 2}, new int[]{1, 2, 3}),
                Arguments.of(new int[]{0, 3, 5, Integer.MIN_VALUE}, new int[]{Integer.MIN_VALUE, 0, 3, 5}),
                Arguments.of(new int[]{Integer.MAX_VALUE, 1, 2}, new int[]{1, 2, Integer.MAX_VALUE}),
                Arguments.of(new int[]{0, -7, 2}, new int[]{-7, 0, 2}),
                Arguments.of(new int[]{0, 1, 1, 2, -3, 2, -3}, new int[]{-3, -3, 0, 1, 1, 2, 2})
        );
    }
    @ParameterizedTest(name="#{index} - Test argument = {0}, {1}")
    @MethodSource("streamProvider")
    public void PriorityQueue_RunTest(int[] in_array, int[] correct_array){
        for (int element : in_array) {
            testQueue.add(element);
        }
        List<Integer> actual_array = new ArrayList<Integer>();
        for(Integer thing = testQueue.poll(); thing != null; thing = testQueue.poll()) {
            actual_array.add(thing);
        }
        assertEquals(Arrays.toString(correct_array), actual_array.toString());
    }
    @Test
    public void IllegalArgumentException_InitialCapacityNotGreaterThanOne(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()->
                {
                    testQueue = new PriorityQueue(0);
                });
    }
    @Test
    public void NullPointerException_InitialWithCollectionsHaveNull(){
        Exception exception = assertThrows(NullPointerException.class, ()->
        {
            testQueue = new PriorityQueue(Collections.singleton(null));
        });
    }
    @Test
    public void NoSuchElementException_GetNextElementOfNull(){
        testQueue.addAll(Arrays.asList(new Integer[] {1, 2, 3}));
        Exception exception = assertThrows(NoSuchElementException.class, ()->
        {
            testQueue.iterator().next();
        });
    }
}