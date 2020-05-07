import java.sql.*;

public class kullaniciVeritabaniPostgreSQL implements IkullaniciVeritabani {
    private static kullaniciVeritabaniPostgreSQL instance ;
    private kullaniciVeritabaniPostgreSQL(){

    }
        public boolean KullaniciDogrula (String kullaniciadi,String sifre) throws SQLException {
            boolean control = false;
            Connection conn = null;

            try {
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/AkilliCihaz", "postgres", "emirhan");

            } catch (Exception e) {
                System.out.println("Baglantı başarısız");
            }
            String query = "select * from \"public\".\"Kullanici\"";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                if (kullaniciadi.equals(rs.getString("kullaniciadi")) && sifre.equals(rs.getString("sifre"))) {
                    control = true;
                }
            }
            if (control)
                return true;
            else return false;
        }
    public static kullaniciVeritabaniPostgreSQL getInstance(){
        if(instance==null)
            instance=new kullaniciVeritabaniPostgreSQL();
        return instance;
    }
}
