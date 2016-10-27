import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;

/**
 * Created by mjsobrep on 9/4/2014.
 */
public class QueStackOTests {

    private QueueInterface<Integer> queue;
    private StackInterface<Integer> stack;
    private int numIteration;
    private double errorRange;

    @Before
    public void setUp() {
        stack = new Stack<Integer>();
        queue = new Queue<Integer>();
        numIteration = 10000;   // the amount of loops each test will run
        errorRange = 5; // this is the percentage (obviously *100%) by which the average for the time values to run numIteration(.15 thru .25) can differ from numIteration(.75 thru .85)
    }

    @Test
    public void oPush() {
        long[] times = new long[numIteration];
        for (int i = 0; i < times.length; i++) {
            long start = System.nanoTime();
            stack.push(new Integer(8));
            long finish = System.nanoTime();
            times[i] = (finish - start);
        }
        long frontAvg = averageFront(times);
        long backAvg = averageBack(times);

        if(!((backAvg<(frontAvg*errorRange))&(frontAvg<(backAvg*errorRange))))
            fail("something is off with your push\nAverage time for low n:"+frontAvg+"\nAverage time for high n:"+backAvg+"\n");
    }

    @Test
    public void oPop(){
        long[] times = new long[numIteration];
        for (int i = 0; i < times.length; i++) {
            long start = System.nanoTime();
            stack.pop();
            long finish = System.nanoTime();
            times[i] = (finish - start);
        }
        long frontAvg = averageFront(times);
        long backAvg = averageBack(times);

        if(!((backAvg<(frontAvg*errorRange))&(frontAvg<(backAvg*errorRange))))
            fail("something is off with your pop\nAverage time for low n:"+frontAvg+"\nAverage time for high n:"+backAvg+"\n");
    }

    @Test
    public void oEnqueue(){
        long[] times = new long[numIteration];
        for (int i = 0; i < times.length; i++) {
            long start = System.nanoTime();
            queue.enqueue(new Integer(8));
            long finish = System.nanoTime();
            times[i] = (finish - start);
        }
        long frontAvg = averageFront(times);
        long backAvg = averageBack(times);

        if(!((backAvg<(frontAvg*errorRange))&(frontAvg<(backAvg*errorRange))))
            fail("something is off with your enqueue\nAverage time for low n:"+frontAvg+"\nAverage time for high n:"+backAvg+"\n");
    }

    @Test
    public void oDequeue(){
        long[] times = new long[numIteration];
        for (int i = 0; i < times.length; i++) {
            long start = System.nanoTime();
            queue.dequeue();
            long finish = System.nanoTime();
            times[i] = (finish - start);
        }
        long frontAvg = averageFront(times);
        long backAvg = averageBack(times);

        if(!((backAvg<(frontAvg*errorRange))&(frontAvg<(backAvg*errorRange))))
            fail("something is off with your dequeue\nAverage time for low n:"+frontAvg+"\nAverage time for high n:"+backAvg+"\n");
    }

    private long averageFront(long[] in) {
        long average = 0;
        int x = 0;
        for (int i = (int) (in.length * .15); i < in.length * .25; i++) {
            average += in[i];
            x++;
        }
        return average / x;
    }

    private long averageBack(long[] in) {
        long average = 0;
        int x = 0;
        for (int i = (int) (in.length * .85); i > (in.length * .75); i--) {
            average += in[i];
            x++;
        }
        return average / x;
    }
}
