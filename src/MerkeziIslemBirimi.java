import java.util.ArrayList;
import java.util.List;

public class MerkeziIslemBirimi implements IMerkeziIslemBirimi,IObserver {
    private IEyleyici eyleyici;
    private ISicaklikAlgilayici sicaklikAlgilayici;
    private String sicaklik;
    private String sogutucuDurumu ;
    private int gereklizaman;
    private long startTime ;
    private long endTime ;
    private String istenensicaklik;
    private static MerkeziIslemBirimi instance ;
    private ISubject publisher;
    private MerkeziIslemBirimi (ISubject publisher) {
        eyleyici=Eyleyici.getInstance();
        sicaklikAlgilayici=SicaklikAlgilayici.getInstance();
        sogutucuDurumu = "bekleme";
        sicaklik = "23";
        this.publisher=publisher;
    }
    public String SogutucuDurumu(){
        return sogutucuDurumu;
    }

    public void SogutucuAc(String istenensicaklik){
        this.istenensicaklik=istenensicaklik;
        sogutucuDurumu = eyleyici.SogutucuAc();
        startTime = System.currentTimeMillis();
        gereklizaman=sicaklikAlgilayici.SogumaIcınGerekliZamanHesaplama(this.istenensicaklik);
        publisher.notify("Sogutucunun soğutma işlemini gerçekleştirmesi için gerekli zaman "+ gereklizaman);
    }
    public void SogutucuKapat(){
        sogutucuDurumu = eyleyici.SogutucuKapat();
        endTime = System.currentTimeMillis();
    }
    public String SicaklıkOku(){
        if(sogutucuDurumu.equals("Acık") && !istenensicaklik.equals(sicaklik)) {
            endTime = System.currentTimeMillis();
            long sogumavakti = endTime-startTime;
            sicaklikAlgilayici.SicaklikDegisimi(istenensicaklik,sogumavakti,(long)gereklizaman*1000);
            startTime=endTime;
            sicaklik = sicaklikAlgilayici.SicaklikOku();
        }
        else if(sogutucuDurumu.equals("Acık") && istenensicaklik.equals(sicaklik)){
            sicaklik = sicaklikAlgilayici.SicaklikOku();
        }
        else {
            sicaklik = sicaklikAlgilayici.SicaklikOku();
        }
        return sicaklik;
    }
    public static MerkeziIslemBirimi getInstance(ISubject publisher){
        if(instance==null)
            instance=new MerkeziIslemBirimi(publisher);
        return instance;
    }
    public void update(String mesaj) {
        System.out.println("Kullanıcıya gelen bildirimi->" + mesaj);
    }
}
