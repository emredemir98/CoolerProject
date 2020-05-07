public interface ISicaklikAlgilayici {
    public String SicaklikOku();
    public int SogumaIcınGerekliZamanHesaplama(String istenensicaklik);
    public void SicaklikDegisimi(String istenensicaklik,long gecenzaman,long planlananzaman);
}
