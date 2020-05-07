public class Eyleyici implements IEyleyici{
    private static Eyleyici instance ;
    private Eyleyici(){

    }
    public String SogutucuAc(){
        return "Acık";
    }
    public String SogutucuKapat(){
        return "Kapalı" ;
    }
    public static Eyleyici getInstance(){
        if(instance==null)
            instance=new Eyleyici();
        return instance;
    }
}
