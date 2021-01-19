package Rechner;

import java.io.*;

public class RechnerImpl implements Rechner{
    int[] speicher = new int[100]; //speicher[0] wird immer 0
    int currentPosition = 0;

    public int[] getSpeicher() {
        return speicher;
    }

    @Override
    public int getCurrentPosition() {
        return currentPosition;
    }

    @Override
    public int addieren(int a) throws RechnerException, IOException {
        int erg = speicher[currentPosition];
        currentPosition++;
        speicher[currentPosition] = erg + a;

        if(speicher[currentPosition] > Integer.MAX_VALUE){
            throw new RechnerException("Integer value is too big");
        }

        this.save(); //always save data after calculating

        return speicher[currentPosition];

    }

    @Override
    public int substrahieren(int a) throws IOException, RechnerException {
        int erg = speicher[currentPosition];
        currentPosition++;
        speicher[currentPosition] = erg - a;

        if(speicher[currentPosition] < Integer.MIN_VALUE){
            throw new RechnerException("Integer value is too small");
        }

        this.save(); //always save data after calculating

        return speicher[currentPosition];
    }

    @Override
    public int zurueckSetzen() throws IOException {
        speicher = new int[100];
        currentPosition = 0;

        this.save(); //always save data after calculating

        return 0;
    }

    @Override
    public int ergebnisZeigen() {
        return speicher[currentPosition];
    }

    @Override
    public int vorherigenWert() throws RechnerException, IOException {
        speicher[currentPosition] = 0;
        if(currentPosition < 1){
            throw new RechnerException("es gibt kein vorherigen Wert");
        }
        currentPosition--;

        this.save(); //always save data after calculating

        return speicher[currentPosition];
    }

    public void save() throws IOException {
        //Create file output stream
        FileOutputStream fos = new FileOutputStream("memory.txt");

        //Create data output stream
        DataOutputStream dos = new DataOutputStream(fos);

        //write attributes
        dos.writeInt(currentPosition);
        for(int i=0; i<=currentPosition; i++){
            dos.writeInt(speicher[i]);
        }
    }

    @Override
    public void restore() throws IOException {
        //Create file input stream
        FileInputStream fis = new FileInputStream("memory.txt");

        //Create data input stream
        DataInputStream dis = new DataInputStream(fis);

        //read and set attributes
        this.currentPosition = dis.readInt();
        for(int i=0; i<=currentPosition; i++){
            speicher[i] = dis.readInt();
        }
    }
}
