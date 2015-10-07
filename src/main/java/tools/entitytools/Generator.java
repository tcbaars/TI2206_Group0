package tools.entitytools;

import java.util.Random;

/**
 * The Generator class is a utility for generating random values.
 */
public class Generator {

    private static Random generator = new Random(System.currentTimeMillis());

    /**
     * Returns a pseudo-random uniformly distributed integer value between zero (inclusive) and the specified boundary (inclusive).
     * The specified boundary limits the possible range of results to [0, boundary] or [boundary, 0].
     * @param boundary the boundary.
     * @return randomly generated integer between the specified range.
     */
    public static int generateInteger(int boundary){
        if (boundary > 0) {
            /*
             *  If the boundary is positive return a random integer adjusted to include the boundary.
             *  Because generator.nextInt(boundary) has range [0,boundary[ by default.
             */
            return generator.nextInt(boundary + 1);
        } else if (boundary < 0) {
            /*
             * If the boundary is negative return the random integer from [0, |boundary|] multiplied by -1,
             * to give [-boundary, 0].
             */
            int tempBoundary = (-1) * boundary;
            return (-1) * generateInteger(tempBoundary);
        } else {
            // If boundary == 0 then [0,0]
            return 0;
        }
    }

    /**
     * Returns a pseudo-random uniformly distributed integer value between the specified boundaries (inclusive).
     * The specified boundaries limit the possible range of results to [boundary1,boundary2] or [boundary2, boundary1.]
     * @param boundary1 a boundary.
     * @param boundary2 another boundary.
     * @return randomly generated integer between the specified range.
     */
    public static int generateInteger(int boundary1, int boundary2){
        int upperBoundary;
        int lowerBoundary;

        if (boundary1 == boundary2) {
            // If boundary1 == boundary2 then [boundary1,boundary1]
            return boundary1;
        }

        if (boundary1 >= 0) {
            if (boundary1 > boundary2){
                // If boundary1 > boundary2 then [boundary2, boundary1]
                upperBoundary = boundary1;
                lowerBoundary = boundary2;
            } else {
                // Else [boundary1, boundary2]
                upperBoundary = boundary2;
                lowerBoundary = boundary1;
            }
        } else {
            if (boundary2 > 0) {
                //If boundary2 > boundary1 then [boundary1, boundary2]
                upperBoundary = boundary2;
                lowerBoundary = boundary1;
            } else {
                if (boundary1 < boundary2) {
                    //If |boundary1| > |boundary2| then [boundary2, boundary1]
                    upperBoundary = boundary1;
                    lowerBoundary = boundary2;
                } else {
                  //Else |boundary2| > |boundar1| then [boundary1, boundary2]
                    upperBoundary = boundary2;
                    lowerBoundary = boundary1;
                }
            }
        }

        // Adjust boundary to start at 0
        int boundary = upperBoundary - lowerBoundary;
        // Get random integer within [0, boundary] or [boundary, 0]
        int randomInt = generateInteger(boundary);
        // Offset result to start at lower boundary again
        int result = randomInt + lowerBoundary;
        return result;
    }

    /**
     * Returns <code>true</code> or <code>false</code> randomly.
     * With approximately 50% chance of being <code>true</code>.
     * @return <code>true</code> or <code>false</code> randomly.
     */
    public static Boolean generateBoolean(){
        return generateBoolean(0.5);
    }

    /**
     * Returns <code>true</code> or <code>false</code> randomly.
     * With the specified probability of being <code>true</code>.
     * Where the probability is a number between 0 and 1 (inclusive).
     * @param probability the probability of being <code>true</code>.
     * @return <code>true</code> or <code>false</code> randomly.
     */
    public static Boolean generateBoolean(double probability){
        if (probability > 0) {
            if (probability < 1) {
                // Get it as a percentage out of 100
                double tempProbability = probability * 100;
                // Get a random number out of 100
                int randomInt = generateInteger(100);

                if (randomInt < tempProbability) {
                    // If the number out of 100 falls in the percentage
                    return true;
                }
            } else {
                // If probability >= 1
                return true;
            }
        }
        return false;
    }
}
