import Rechner.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class UsageTest {

    public Rechner getMaschine() throws IOException {
        return new RechnerImpl();
    }

    public int ergebnis(Rechner r){
        int erg = r.getSpeicher()[r.getCurrentPosition()];
        return erg;
    }

    @Test
    public void goodAddieren() throws RechnerException, IOException {
        Rechner r = getMaschine();
        int erg = r.addieren(11);
        Assert.assertEquals(erg, 11);
    }

    @Test
    public void goodSubstrahieren() throws IOException, RechnerException {
        Rechner r = getMaschine();
        int erg = r.substrahieren(9);
        Assert.assertEquals(erg, -9);
    }

    @Test
    public void goodAddierenSubstrahieren() throws RechnerException, IOException {
        Rechner r = getMaschine();
        int erg = r.addieren(110);
        erg = r.substrahieren(90);
        Assert.assertEquals(erg, 20);
    }

    @Test
    public void speicherPositionAndValue() throws RechnerException, IOException {
        Rechner r = getMaschine();
        int erg = r.addieren(110);
        erg = r.substrahieren(90);
        Assert.assertEquals(r.getCurrentPosition(), 2);
        Assert.assertEquals(this.ergebnis(r), 20);
    }

    @Test
    public void vorherigenWert() throws IOException, RechnerException {
        Rechner r = getMaschine();
        int erg = r.addieren(110);
        erg = r.substrahieren(90);
        erg = r.vorherigenWert();
        Assert.assertEquals(this.ergebnis(r), 110);
    }

    @Test
    public void vorherigenWert2() throws IOException, RechnerException {
        Rechner r = getMaschine();
        int erg = r.addieren(110);
        erg = r.substrahieren(90);
        erg = r.substrahieren(20);
        erg = r.substrahieren(100);
        erg = r.addieren(90);

        erg = r.vorherigenWert();
        erg = r.vorherigenWert();
        erg = r.vorherigenWert();
        Assert.assertEquals(this.ergebnis(r), 20);
        Assert.assertEquals(r.getCurrentPosition(), 2);
    }

    @Test
    public void zurueckSetzen() throws RechnerException, IOException {
        Rechner r = getMaschine();
        int erg = r.addieren(110);
        erg = r.substrahieren(90);
        erg = r.vorherigenWert();
        r.zurueckSetzen();
        for(int i=0; i<20; i++) {
            Assert.assertEquals(r.getSpeicher()[i], 0);
        }
        Assert.assertEquals(r.getCurrentPosition(), 0);
    }

    @Test
    public void zweiRechnernTest() throws RechnerException, IOException {
        Rechner r = getMaschine();
        int erg = r.addieren(100);
        erg = r.substrahieren(20);
        erg = r.addieren(20);
        erg = r.addieren(20);
        erg = r.addieren(20); //140
        erg = r.vorherigenWert(); //120
        erg = r.vorherigenWert(); //100
        erg = r.substrahieren(20); //80

        Rechner r2 = getMaschine();
        r2.restore();
        Assert.assertEquals(ergebnis(r2), 80);
        Assert.assertEquals(r2.getCurrentPosition(), 4);
    }
}