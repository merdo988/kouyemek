package net.kodlar.kouyemek;


public class Sponsorlar {
    private String LogoResourceUrl;
    private String socialText;

    public Sponsorlar(String LogoResourceUrl , String socialText) {
        this.LogoResourceUrl = LogoResourceUrl;
        this.socialText = socialText;
    }

    public String getSocialIcon() {
        return LogoResourceUrl;
    }
    public void setSocialIcon(int socialIcon) {
        this.LogoResourceUrl = LogoResourceUrl;
    }

    public String getSocialInfo() {
        return socialText;
    }

    public void setSocialInfo(String socialInfo) {
        this.socialText = socialInfo;
    }
}
