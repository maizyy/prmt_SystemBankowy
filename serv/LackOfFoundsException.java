package prmt_SystemBankowy;

public class LackOfFoundsException extends Exception{
    public LackOfFoundsException(String errorMessege, Throwable err){
        super(errorMessege, err);
    }
}