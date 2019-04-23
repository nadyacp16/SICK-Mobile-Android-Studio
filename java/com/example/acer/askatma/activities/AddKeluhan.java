package com.example.acer.askatma.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.acer.askatma.Api.ApiKeluhan;
import com.example.acer.askatma.Api.ApiUrl;
import com.example.acer.askatma.R;
import com.example.acer.askatma.models.Value;
import com.example.acer.askatma.storage.SharedPrefManager;

import org.w3c.dom.Text;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddKeluhan extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "" ;
    private Spinner spinnerTujuan;
    private EditText editTextDesc, totalrandomnum;
    private Button btnAddFoto;
    private Button btnSubmitKeluhan;
    ProgressDialog progress;
    private TextView txtrandomnum, txtrandomnum2;
    private String tujuan, isi, foto,iduser, namafoto;
    private SharedPrefManager sharedPrefManager;
    Random rand = new Random();
    private int max = 9;
    private int min = 0;
    int randomNum = rand.nextInt((max - min) + 1) + min;
    int randomNum2 = rand.nextInt((max - min) + 1) + min;

    int totrandnum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_keluhan);
        sharedPrefManager=new SharedPrefManager(this);

        initKeluhan();
        iduser = sharedPrefManager.getSpIdPel();
        btnSubmitKeluhan.setOnClickListener(this);
        txtrandomnum.setText(String.valueOf(randomNum));
        txtrandomnum2.setText(String.valueOf(randomNum2));



        btnAddFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, 100);
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + getPackageName()));
            finish();
            startActivity(intent);
            return;
        }
    }

    public void initKeluhan(){
        spinnerTujuan = (Spinner)findViewById(R.id.addtujuan);
        editTextDesc = (EditText)findViewById(R.id.adddescription);
        btnAddFoto = (Button)findViewById(R.id.btn_foto);
        btnSubmitKeluhan = (Button)findViewById(R.id.btn_submitkeluhan);

        txtrandomnum = (TextView) findViewById(R.id.randomNum1);
        txtrandomnum2 = (TextView) findViewById(R.id.randomNum2);
        totalrandomnum = (EditText) findViewById(R.id.totrandnum);
    }

    public void addKeluhan(){
        progress = new ProgressDialog(AddKeluhan.this);
        progress.setCancelable(false);
        progress.setMessage("Loading ...");
        progress.show();

//        File file = new File(getRealPathFromURI(image));
//        RequestBody requestFile = RequestBody.create(MediaType.parse(getContentResolver().getType(image)), file);
//        RequestBody tujuanBody = RequestBody.create(MediaType.parse("text/plain"), tujuan);
//        RequestBody isiBody = RequestBody.create(MediaType.parse("text/plain"), isi);

//        Gson gson = new GsonBuilder()
//                        .setLenient()
//                        .create();
        try {
            tujuan = spinnerTujuan.getSelectedItem().toString();
            isi = editTextDesc.getText().toString();

            if(tujuan.equals("Sarana Perkuliahan (AC, LCD, dll)")){
                tujuan = "KTG01";
            }
            if(tujuan.equals("Sarana Prasarana Umum")){
                tujuan = "KTG02";
            }
            if(tujuan.equals("Surat Menyurat")){
                tujuan = "KTG03";
            }
            if(tujuan.equals("Jadwal KP/Pendadaran")){
                tujuan = "KTG04";
            }
            if(tujuan.equals("Berkas Ujian")){
                tujuan = "KTG05";
            }

            Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(ApiUrl.GetMyURL())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
            ApiKeluhan api = retrofit.create(ApiKeluhan.class);
            Call<Value> call = api.createComplaint(tujuan, isi, iduser);
            call.enqueue(new Callback<Value>() {
                @Override
                public void onResponse(Call<Value> call, Response<Value> response) {
                    Integer value = response.body().getValue();
                    String message = response.body().getMessage();
                    progress.dismiss();
                    if (value == 1) {
                        Toast.makeText(AddKeluhan.this, message, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(AddKeluhan.this, message, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Value> call, Throwable t) {
                    progress.dismiss();
                    Toast.makeText(AddKeluhan.this, "Connection Failed!", Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == btnSubmitKeluhan) {
            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which){
                        case DialogInterface.BUTTON_POSITIVE:
                            totrandnum = Integer.valueOf(txtrandomnum.getText().toString()) + Integer.valueOf(txtrandomnum2.getText().toString());
                            if(totalrandomnum.getText().toString().equals(String.valueOf(totrandnum))) {
                                addKeluhan();
                                finish();
                            }
                            else {
                                Toast.makeText(AddKeluhan.this, "Failed to created complaint!", Toast.LENGTH_SHORT).show();
                            }
                            break;

                        case DialogInterface.BUTTON_NEGATIVE:
                            break;
                    }
                }
            };

            AlertDialog.Builder builder = new AlertDialog.Builder(AddKeluhan.this);
            builder.setMessage("Submit Complaint?").setPositiveButton("Yes", dialogClickListener)
                    .setNegativeButton("No", dialogClickListener).show();
        }
    }


    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        Uri selectedImage = null;
        if (reqCode == 100 && resultCode == RESULT_OK && data != null) {
            selectedImage = data.getData();
            final Uri finalSelectedImage = selectedImage;
        }
    }

    /*
     * This method is fetching the absolute path of the image file
     * if you want to upload other kind of files like .pdf, .docx
     * you need to make changes on this method only
     * Rest part will be the same
     * */
    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(this, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }
}
