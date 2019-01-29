package net.mycompany.pixabay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.mycompany.pixabay.Network.ApiService;
import net.mycompany.pixabay.Utils.AppConstants;
import net.mycompany.pixabay.adapter.PhotoAdapter;
import net.mycompany.pixabay.obj.Hits;
import net.mycompany.pixabay.obj.PhotoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static net.mycompany.pixabay.R.id.rv_photo_ma;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    EditText et;
    Button btn;
    PhotoAdapter pad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et=findViewById(R.id.et_search_ma);
        rv=findViewById(R.id.rv_photo_ma);
        btn=findViewById(R.id.btn_search_ma);

        rv.setLayoutManager(new LinearLayoutManager(this));
        pad=new PhotoAdapter(this);

        rv.setAdapter(pad);

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(AppConstants.Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final ApiService api=retrofit.create(ApiService.class);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str=et.getText().toString();
               if (et.getText()==null){
                   Toast.makeText(MainActivity.this, "should not be empty", Toast.LENGTH_SHORT).show();
               }else {
                   api.getPhoto(AppConstants.KEY,str).enqueue(new Callback<PhotoResponse>() {
                       @Override
                       public void onResponse(Call<PhotoResponse> call, Response<PhotoResponse> response) {
                           if (response.isSuccessful()) {
                               PhotoResponse pr = response.body();
                               List<Hits> hits = pr.getHits();
                               pad.setHits(hits);
                           } else {
                               Toast.makeText(MainActivity.this, "Fail=>", Toast.LENGTH_SHORT).show();
                           }
                       }

                       @Override
                       public void onFailure(Call<PhotoResponse> call, Throwable t) {
                           Toast.makeText(MainActivity.this, "Error=>" + t, Toast.LENGTH_SHORT).show();
                           Log.d("Error123", t.getMessage());
                       }
                   });
               }

            }
        });
    }
}
