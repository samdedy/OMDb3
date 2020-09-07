package id.sam.omdb3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import id.sam.omdb3.adapter.AdapterListSimple;
import id.sam.omdb3.model.TitleMovie;
import id.sam.omdb3.service.APIClient;
import id.sam.omdb3.service.APIInterfacesRest;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText txtCari;
    RecyclerView rvMovie;
    Button btnCari;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCari = findViewById(R.id.txtCari);
        rvMovie = findViewById(R.id.rvMovie);
        btnCari = findViewById(R.id.btnCari);
        callMovie("naruto");

    }

    APIInterfacesRest apiInterface; //deklarasi variable
    ProgressDialog progressDialog; //deklarasi dialog
    public void callMovie(String title){

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);//inisialisasi api
        progressDialog = new ProgressDialog(MainActivity.this); //inisialisasi progres dialog
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<TitleMovie> call3 = apiInterface.getTitleMovie(title,"33e0eb02");//pemanggilan funsion api
        call3.enqueue(new Callback<TitleMovie>() {
            @Override
            public void onResponse(Call<TitleMovie> call, Response<TitleMovie> response) {
                progressDialog.dismiss();
                TitleMovie titleMovie = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (titleMovie !=null) {

                    //     txtKota.setText(dataWeather.getName());
                    //     txtTemperature.setText(new DecimalFormat("##.##").format(dataWeather.getMain().getTemp()-273.15));
                    List<TitleMovie> titleMovies = new ArrayList<>(); // merubah data objeck ke list(araray)
                    titleMovies.add(titleMovie);

                    AdapterListSimple adapter = new AdapterListSimple(MainActivity.this, titleMovies);


                    rvMovie.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    rvMovie.setItemAnimator(new DefaultItemAnimator());
                    rvMovie.setAdapter(adapter);
                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(MainActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<TitleMovie> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }
}