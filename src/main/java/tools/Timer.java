package tools;

/**
 * The Timer class is a tool for keeping track of the number of updates (or 'ticks') that have occurred.
 * It is structured such that the desired number of ticks should be specified, and the timer should be updated each tick.
 * The timer then can be used to keep track of whether or not the desired number of ticks has been reached or not.
 * Which is useful for implementing delays, where something only updates after a certain number of ticks have passed.
 */
public class Timer {

    private int current;
    private int goal;
    private boolean completed;

    /**
     * Creates timer object with specified desired goal.
     * @param goal the number of ticks needed to pass.
     */
    public Timer(int goal){
        this.goal = goal;
        reset();
    }

    /**
     * Resets the timer.
     */
    public void reset(){
        current = 0;
        completed = false;
    }

    /**
     * Performs a single update or 'tick'.
     * The counter is only increased if the timer has not been completed yet.
     */
    public void tick(){
        if (!completed) {
            current++;
            if (current >= goal) {
                current = 0;
                completed = true;
            }
        }
    }

    /**
     * Returns the current number of ticks recorded.
     * @return current tick number.
     */
    public int getCurrentTick(){
        return current;
    }

    /**
     * Returns the desired number of ticks that is needed to occur,
     * for the timer to be considered complete.
     * @return
     */
    public int getGoal(){
        return goal;
    }

    /**
     * Returns whether or not the desired number of ticks has occurred.
     * @return <code>true</code> if and only if the desired number of ticks has occurred, otherwise <code>false</code>.
     */
    public boolean hasCompleted(){
        return completed;
    }
}
