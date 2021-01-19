package Rechner;

public class RechnerException extends Exception {
    public RechnerException(){ super(); }
    public RechnerException(String message){ super(message); }
    public RechnerException(String message, Throwable t){ super(message, t); }
}
