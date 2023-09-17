package com.example.userlogin;

import static com.example.userlogin.R.id.btnTahmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ToggleButton toggleTopla, toggleCarp, toggleCikar, toggleBol;
    private TextView txtSoru, txtStatus;
    private EditText editTextCevap;
    private ArrayList<String> islem;
    private Button btnSoru, btnTahmin;
    private Random randomIslem, randomSayi;
    private int randomIslemNumber, randomSayiNumber ;
    private String soru, txtTahmin;
    private int s1, s2, sonuc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        islem = new ArrayList<>();
        randomIslem = new Random();
        randomSayi = new Random();

        toggleTopla = (ToggleButton) findViewById(R.id.togglebtntopla);
        toggleCarp = (ToggleButton) findViewById(R.id.togglebtnCarp);
        toggleCikar = (ToggleButton) findViewById(R.id.toggleBtnCikar);
        toggleBol = (ToggleButton) findViewById(R.id.toggleBtnBol);

        txtSoru = (TextView) findViewById(R.id.txtSoru);
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        editTextCevap = (EditText) findViewById(R.id.editTextCevap);

        toggleTopla.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    islem.add("+");
                }
                else {
                    islem.remove("+");
                }

            }
        });

        toggleCarp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    islem.add("*");
                }
                else {
                    islem.remove("*");
                }

            }
        });

        toggleBol.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    islem.add("/");
                }
                else {
                    islem.remove("/");
                }

            }
        });

        toggleCikar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    islem.add("-");
                }
                else {
                    islem.remove("-");
                }

            }
        });
    }

    public void btnClick(View v){
        if(v.getId() == R.id.btnSoru){
            txtSoru.setText(islemveSoruGetir());
            //txtSoru.setText(islemveSoruGetir());
        }
        else if (v.getId() == R.id.btnTahmin){
            tahminKontrol();
        }
    }

    private String islemveSoruGetir(){
        soru = " ";

        if(islem.size()>0) {
            randomIslemNumber = randomIslem.nextInt(islem.size());

            s1 = sayiGetir();
            soru += s1;
            soru += " ";

            soru += islem.get(randomIslemNumber);

            soru += " ";
            s2 = sayiGetir();
            soru += s2;

            switch (islem.get(randomIslemNumber)) {
                case "+":
                    sonuc = s1 + s2;
                    break;
                case "*":
                    sonuc = s1 * s2;
                    break;
                case "-":
                    sonuc = s1 - s2;
                    break;
                case "/":
                    sonuc = s1 / s2;
                    break;

            }
        }
        return soru;
    }
    public int sayiGetir(){
        randomSayiNumber = randomSayi.nextInt(10)+1;
        return randomSayiNumber;
    }

    private void tahminKontrol(){
        txtTahmin = editTextCevap.getText().toString();
        if(!TextUtils.isEmpty(txtTahmin)){
            if(txtTahmin.matches(String.valueOf(sonuc))){
                txtStatus.setText("Tebrikler tahmininiz dogru :)))) ");
                editTextCevap.setText(" ");
                txtSoru.setText(islemveSoruGetir());
            }
            else {
                txtStatus.setText("Yanlis tahminde bulundunuz!!!! ");
            }
        }
        else {
            txtStatus.setText("Girilen tahmin degeri bos gecilemez !!!!!");
        }
    }
}