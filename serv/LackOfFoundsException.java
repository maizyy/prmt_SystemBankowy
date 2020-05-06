package prmt_SystemBankowy;

public class LackOfFoundsException extends Exception{
    public LackOfFoundsException(){
        System.out.println("Brak wystarczających środków na koncie na przeprowadzenie takie operacji.");
    }
    public LackOfFoundsException(String errorMessege, Throwable err){
        super(errorMessege, err);
    }
}