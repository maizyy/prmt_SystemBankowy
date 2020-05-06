package prmt_SystemBankowy;

public class LackOfFoundsException extends Exception{

    private static final long serialVersionUID = 1L;

    public LackOfFoundsException() {
        System.out.println("Brak wystarczających środków na koncie na przeprowadzenie takie operacji.");
    }
    public LackOfFoundsException(String errorMessage){
        super(errorMessage);
    }
    public LackOfFoundsException(String errorMessage, Throwable err){
        super(errorMessage, err);
    }
}