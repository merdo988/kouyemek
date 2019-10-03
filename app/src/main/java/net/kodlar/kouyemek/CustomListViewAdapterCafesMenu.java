package net.kodlar.kouyemek;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomListViewAdapterCafesMenu extends ArrayAdapter<CafesMenu> {

    private final LayoutInflater inflater;
    private final Context context;
    private final ArrayList<CafesMenu> CafesMenu;

    public CustomListViewAdapterCafesMenu(@NonNull Context context, int resource, ArrayList<CafesMenu> CafesMenu) {
        super(context, resource, CafesMenu);
        this.context = context;
        this.CafesMenu = CafesMenu;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_view_item_cafes_menu, null);
        }
        CafesMenu c = getItem(position);
        if (c != null) {
            TextView product_header = (TextView)v.findViewById(R.id.product_header);
            TextView product_info = (TextView)v.findViewById(R.id.product_info);
            TextView product_price = (TextView)v.findViewById(R.id.product_price);

            product_header.setText(c.getProductName());
            product_info.setText(c.getProductInfo());
            product_price.setText(c.getProductPrice());
        }
        return v;
    }
}
