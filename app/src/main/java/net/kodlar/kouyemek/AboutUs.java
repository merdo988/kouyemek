package net.kodlar.kouyemek;

import android.widget.ImageView;

public class AboutUs {
    private int socialIcon;
    private String socialInfo;

    public AboutUs(int socialIcon , String socialInfo) {
        this.socialIcon = socialIcon;
        this.socialInfo = socialInfo;
    }

    public int getSocialIcon() {
        return socialIcon;
    }
    public void setSocialIcon(int socialIcon) {
        this.socialIcon = socialIcon;
    }

    public String getSocialInfo() {
        return socialInfo;
    }

    public void setSocialInfo(String socialInfo) {
        this.socialInfo = socialInfo;
    }
}
