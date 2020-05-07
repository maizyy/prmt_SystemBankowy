public class LittleBullException extends Exception{

    private static final long serialVersionUID = 1L;
    
    public LittleBullException(){
        System.out.println("oj nie -1 byczku -1");
    }

    public LittleBullException(String errormessage, Throwable e){
        super(errormessage, e);
    }

    public LittleBullException(Throwable e){
        super(e);
    }
}   