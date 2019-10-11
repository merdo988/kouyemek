package net.kodlar.kouyemek;

public class Cafes {
    private String cafeID;
    private String cafeName;
    private String LogoResourceUrl;

    public Cafes(){

    }
    public Cafes(String cafeID,String cafeName , String LogoResID){
        this.cafeID =cafeID;
        this.cafeName = cafeName;
        this.LogoResourceUrl = LogoResID;
    }
    public String getCafeID(){
        return this.cafeID;
    }
    public void setCafeID(String cafeID){
        this.cafeID = cafeID;
    }
    public void setCafeName(String cafeName){
        this.cafeName = cafeName;
    }
    public String getCafeName(){
        return this.cafeName;
    }
    public void setLogoResourceUrl(String LogoResourceUrl){
        this.LogoResourceUrl = LogoResourceUrl;
    }
    public String getLogoResourceUrl() {
        return LogoResourceUrl;
    }
}
