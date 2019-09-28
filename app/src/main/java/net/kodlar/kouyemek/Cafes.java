package net.kodlar.kouyemek;

public class Cafes {
    private int cafeID;
    private String cafeName;
    private int LogoResID;

    public Cafes(){

    }
    public Cafes(String cafeName , int LogoResID){
        this.cafeName = cafeName;
        this.LogoResID = LogoResID;
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
    public void setLogoResID(int LogoResID){
        this.LogoResID = LogoResID;
    }
    public int getLogoResourceID() {
        return LogoResID;
    }
}
