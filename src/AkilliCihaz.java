import java.sql.SQLException;

public class AkilliCihaz {
    private static AkilliCihaz instance ;
    private AkilliCihaz(){

    }
    public void basla() throws SQLException {
       AgArayuzu agArayuzu=AgArayuzu.getInstance();
       agArayuzu.KullaniciKontrol();
       agArayuzu.IstekGonder();
    }
    public static AkilliCihaz getInstance(){
        if(instance==null)
            instance=new AkilliCihaz();
        return instance;
    }
}