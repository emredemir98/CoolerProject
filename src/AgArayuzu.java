import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class AgArayuzu implements IAgArayuzu{
    private MerkeziIslemBirimi islem ;
    private IkullaniciVeritabani hesap;
    private static AgArayuzu instance ;
    private Publisher publisher;
    private AgArayuzu(IkullaniciVeritabani hesap){
        this.hesap=hesap;
        publisher =new Publisher();
        islem = MerkeziIslemBirimi.getInstance(publisher);
        publisher.attach(islem);
    }
    public void KullaniciKontrol() throws SQLException {

        Scanner scan = new Scanner(System.in);
        while(true) {
            System.out.println("---------Kullanici Girisi-------- ");
            System.out.println("Lütfen kullanici adinizi giriniz..");
            String kullaniciadi = scan.next();
            System.out.println("Lütfen sifrenizi giriniz..");
            String sifre = scan.next();
            if(hesap.KullaniciDogrula(kullaniciadi, sifre)){
                System.out.println("Akilli cihaz sistemine giris yapılıyor...");
                break;
            }
            System.out.println("Kullanıcı doğrulanamadı lütfen kullanıcı adınızı ve şifrenizi kontrol edin");
        }
    }
 public String SecenekSun(){
     System.out.println("Sogutucu Durumu : "+islem.SogutucuDurumu());
     System.out.println(">>Hangi işlemi istediğinizi seçiniz");
     System.out.println("1-Sogutucuyu aç");
     System.out.println("2-Sogutucuyu kapat");
     System.out.println("3-Sıcaklık görüntüle");
     System.out.println("4-Cıkış");
     Scanner scan = new Scanner(System.in);
     return scan.next();
 }
 public void IstekGonder(){
     Scanner scan = new Scanner(System.in);
     String secim="0";
     while(!secim.equals("4")) {
          secim = SecenekSun();
         if(secim.equals("1")){
             System.out.println(">>Sogutucu kac derecede çalışsın");
                 String istenensicaklik =scan.next();
                 if(Float.parseFloat(istenensicaklik)>=Float.parseFloat(islem.SicaklıkOku()))
                     System.out.println("Suanki sıcaklık değerinden daha düsük bir değer girmediginiz için sogutucu açılamadı");
             else {
                      islem.SogutucuAc(istenensicaklik);
                 }
         }
         else if(secim.equals("2")){
             islem.SogutucuKapat();

         }
         else if(secim.equals("3")){
             System.out.println("Sıcaklık "+islem.SicaklıkOku()+" derece ");

         }
         else if(secim.equals("4")){
             System.out.println("Akıllı cihazdan cıkılıyor..");
             break;
         }
         else{
             System.out.println("Lutfen sadece ekranda yazan işlemlerden birini seciniz ");
         }
     }
 }
    public static AgArayuzu getInstance(){
        if(instance==null)
            instance=new AgArayuzu(kullaniciVeritabaniPostgreSQL.getInstance());
        return instance;
    }



}
