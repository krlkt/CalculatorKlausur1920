package Rechner;

import java.io.IOException;

public interface Rechner {
    /**
     *
     * @param a Integer value a
     * @return sum of integer a
     */
    int addieren(int a) throws RechnerException, IOException;

    /**
     *
     * @param a Integer value a
     * @return result of integer a minus
     */
    int substrahieren(int a) throws IOException, RechnerException;

    /**
     * will return a value of 0 every time the method is called
     * @return 0
     */
    int zurueckSetzen() throws IOException;

    /**
     * show the current value after operation
     * @return int currentValue
     */
    int ergebnisZeigen();

    /**
     * return the int value of before
     * @return
     */
    int vorherigenWert() throws RechnerException, IOException;

    int[] getSpeicher();

    int getCurrentPosition();

    void restore() throws IOException;
}
