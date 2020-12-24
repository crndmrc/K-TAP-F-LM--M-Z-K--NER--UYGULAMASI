package com.mobiluygulama.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class offers extends AppCompatActivity {

    private Toolbar actionbarOffer;
    private ListView listView;
    private TextView textView;
    private Button btnofferEkle;
    private int kullaniciid;

    ArrayList<OfferListview> offers = new ArrayList<OfferListview>();
    Helper1 Helper1;
    ListviewAdaptor adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        btnofferEkle=findViewById(R.id.btnOfferEkle);
        listView=findViewById(R.id.listview);

        Helper1=new Helper1(this);
        this.offers = Helper1.offerListesi();
        adapter=new ListviewAdaptor(this,offers);

        if(listView!=null){
            listView.setAdapter(adapter);
            registerForContextMenu(listView);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(offers.this,offer_bilgileri.class);
                intent.putExtra("offerID",offers.get(position).getOfferid());
                startActivity(intent);
            }
        });

        actionbarOffer=(Toolbar)findViewById(R.id.actionbar_offer);
        setSupportActionBar(actionbarOffer);
        getSupportActionBar().setTitle("Offers");

    }

    public void offerEkle(View view){
        Intent intent=new Intent(offers.this,offer_ozellikleri.class);
        startActivity(intent);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.contex_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        final AdapterView.AdapterContextMenuInfo menuInfo= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.sil:
                AlertDialog.Builder alert= Helper2.alertBuilder(offers.this);
                alert.setMessage("Offer kaydı silinsin mi?");
                alert.setPositiveButton("EVET", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int position;
                        position=(int)menuInfo.id;
                        Helper1.offerSil(offers.get(position).getOfferid());
                        offers.remove(position);
                        adapter.notifyDataSetChanged();

                    }
                });

                alert.setNegativeButton("HAYIR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.show();

                return true;

            case R.id.duzenle:
                int position;
                position=(int)menuInfo.id;
                Intent intent=new Intent(offers.this,offer_bilgileri.class);
                intent.putExtra("offerID",offers.get(position).getOfferid());
                startActivity(intent);
                return true;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.profil_menu,menu);

        MenuItem item=menu.findItem(R.id.action_ara);
        SearchView searchView=(SearchView)item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_profil:
                Intent profilIntent = new Intent(offers.this,profil.class);
                startActivity(profilIntent);
                return true;
            case R.id.action_ara:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}