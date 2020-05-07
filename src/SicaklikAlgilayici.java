import java.util.Random;

public class SicaklikAlgilayici implements ISicaklikAlgilayici{
    private static SicaklikAlgilayici instance ;
    private float sicaklik;
    Random rand = new Random();
    private SicaklikAlgilayici(){
         sicaklik=rand.nextInt(80)+20;
    }
    public String SicaklikOku(){
        return Float.toString(sicaklik);
    }
    public static SicaklikAlgilayici getInstance(){
        if(instance==null)
            instance=new SicaklikAlgilayici();
        return instance;
    }
    public int SogumaIcınGerekliZamanHesaplama(String istenensicaklik) {
        int gecenzaman= (int) (sicaklik-Float.parseFloat(istenensicaklik))*5;
        return gecenzaman;
    }
    public void SicaklikDegisimi(String istenensicaklik,long gecenzaman,long planlananzaman) {
            sicaklik -= (float)gecenzaman/5000;
        if (sicaklik <= Float.parseFloat(istenensicaklik)) {
            sicaklik = Float.parseFloat(istenensicaklik);
        }
    }
}
