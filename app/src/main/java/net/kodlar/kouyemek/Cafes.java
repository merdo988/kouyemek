package net.kodlar.kouyemek;

public class Cafes {
    private int cafeID;
    private String cafeName;
    private String LogoResourceUrl;

    public Cafes(){

    }
    public Cafes(String cafeName , String LogoResID){
        this.cafeName = cafeName;
        this.LogoResourceUrl = LogoResID;
    }
    public int getCafeID(){
        return this.cafeID;
    }
    public void setCafeID(int cafeID){
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
