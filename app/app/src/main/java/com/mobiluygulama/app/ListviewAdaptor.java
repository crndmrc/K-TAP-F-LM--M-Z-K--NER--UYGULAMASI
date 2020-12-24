package com.mobiluygulama.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListviewAdaptor extends BaseAdapter implements Filterable {
    private LayoutInflater mInflater;
    private ArrayList<OfferListview> offerArrayList;
    private ArrayList<OfferListview> offerArrayListFiltre;
    private Context context;

    public ListviewAdaptor(Activity activity, ArrayList<OfferListview>offerArrayList){
        this.mInflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.offerArrayList=offerArrayList;
        this.offerArrayListFiltre=offerArrayList;
        this.context=activity;

    }
    @Override
    public int getCount() {
        return offerArrayListFiltre.size();
    }

    @Override
    public Object getItem(int position) {
        return offerArrayListFiltre.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View lineView;
        convertView = mInflater.inflate(R.layout.listviewsekmesi,null);
        TextView txt1=(TextView)convertView.findViewById(R.id.txtbaslik);
        TextView txt2=(TextView)convertView.findViewById(R.id.txtoffertipi2);
        TextView txt3=(TextView)convertView.findViewById(R.id.txttur2);
        TextView txt4=(TextView)convertView.findViewById(R.id.txtFiyat2);
        TextView txt5=(TextView)convertView.findViewById(R.id.txtfilm2);
        TextView txt6=(TextView)convertView.findViewById(R.id.txtkitap2);
        TextView txt7=(TextView)convertView.findViewById(R.id.knot);
        ImageView imageView=(ImageView) convertView.findViewById(R.id.imageview1);
        OfferListview offerlListview = offerArrayListFiltre.get(position);
        txt1.setText(offerlListview.getBaslik());
        txt2.setText(offerlListview.getOffer_tipitxt());
        txt3.setText(offerlListview.getTurtxt());
        txt4.setText(offerlListview.getFiyati());
        txt5.setText(offerlListview.getFilmtxt());
        txt6.setText(offerlListview.getKitaptxt());
        txt7.setText(offerlListview.getK_not());
        Bitmap bitmap = BitmapFactory.decodeByteArray(offerlListview.getResimId(),0,offerlListview.getResimId().length);
        imageView.setImageBitmap(bitmap);
        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter=new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults=new FilterResults();
                if(constraint==null || constraint.length()==0){
                    filterResults.count=offerArrayList.size();
                    filterResults.values=offerArrayList;
                }
                else {
                    String searchstr=constraint.toString();
                    ArrayList<OfferListview> resultData=new ArrayList<>();
                    for(OfferListview offerlListview:offerArrayList){
                        if(offerlListview.getBaslik().contains(searchstr) ||
                                offerlListview.getOffer_tipitxt().contains(searchstr) ||
                                offerlListview.getTurtxt().contains(searchstr) ||
                                offerlListview.getFiyati().contains(searchstr)||
                                offerlListview.getFilmtxt().contains(searchstr) ||
                                offerlListview.getKitaptxt().contains(searchstr) ||
                                offerlListview.getK_not().contains(searchstr))
                        {
                            resultData.add(offerlListview);
                        }

                        filterResults.count=resultData.size();
                        filterResults.values=resultData;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                offerArrayListFiltre= (ArrayList<OfferListview>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
}
