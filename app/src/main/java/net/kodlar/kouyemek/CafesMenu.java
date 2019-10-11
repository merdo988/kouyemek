package net.kodlar.kouyemek;

import com.android.volley.toolbox.StringRequest;

public class CafesMenu {
    private int ProductID;
    private String ProductName;
    private String ProductInfo;
    private String ProductPrice;
    private String  ProductImageResourceUrl;

    public CafesMenu(){

    }
    public CafesMenu(String ProductName ,String ProductInfo,String ProductPrice, String ProductImageResourceUrl){
        this.ProductInfo = ProductInfo;
        this.ProductName = ProductName;
        this.ProductPrice = ProductPrice;
        this.ProductImageResourceUrl = ProductImageResourceUrl;
    }
    public int getProductID(){
        return this.ProductID;
    }
    public void setProductID(int ProductID){
        this.ProductID = ProductID;
    }
    public String getProductInfo(){
        return this.ProductInfo;
    }
    public void setProductInfo(String ProductInfo){
        this.ProductInfo = ProductInfo;
    }
    public void setProductName(String ProductName){
        this.ProductName = ProductName;
    }
    public String getProductName(){
        return this.ProductName;
    }
    public void setProductPrice(String ProductPrice){
        this.ProductPrice = ProductPrice;
    }
    public String getProductPrice(){
        return this.ProductPrice;
    }
    public void setProductImageResourceUrl(String ProductImageResourceUrl){
        this.ProductImageResourceUrl = ProductImageResourceUrl;
    }
    public String getProductImageResourceUrl() {
        return ProductImageResourceUrl;
    }
}
