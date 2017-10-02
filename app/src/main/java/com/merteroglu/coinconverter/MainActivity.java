package com.merteroglu.coinconverter;

import android.app.ProgressDialog;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.merteroglu.coinconverter.Model.Coin;
import com.merteroglu.coinconverter.Remote.CoinService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    CoinService mService;

    RadioButton coin2coin,money2coin,coin2money;
    MaterialSpinner fromSpinner,toSpinner;
    RadioGroup radioGroup;

    Button btnConvert;

    ImageView coinImage;
    TextView toTextView;

    String[] money = {"USD","EUR","GBP"};
    String[] coin = {"BTC","ETH","ETC","XRP","LTC","XMR","DASH","MAID","AUR","XEM"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mService = Common.getCoinService();

        fromSpinner = (MaterialSpinner) findViewById(R.id.fromSpinner);
        toSpinner = (MaterialSpinner) findViewById(R.id.toSpinner);

        toTextView = (TextView) findViewById(R.id.toTextView);

        btnConvert = (Button) findViewById(R.id.btnConvert);

        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateValue();
            }
        });

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(i == R.id.coin2coin){
                    setCoin2CoinSource();
                }else if(i == R.id.money2coin){
                    setMoney2CoinSource();
                }else if(i == R.id.coin2money){
                    setCoin2MoneySource();
                }
            }
        });

        coin2coin = (RadioButton) findViewById(R.id.coin2coin);
        money2coin = (RadioButton) findViewById(R.id.money2coin);
        coin2money = (RadioButton) findViewById(R.id.coin2money);

        coinImage = (ImageView) findViewById(R.id.coinImage);

        coin2coin.setSelected(true);

        loadCoinList();


    }

    private void loadCoinList() {
        if(coin2money.isSelected()){
            setCoin2MoneySource();
        }else if(coin2coin.isSelected()){
            setCoin2CoinSource();
        }else if(money2coin.isSelected()){
            setMoney2CoinSource();
        }

    }

    private void setCoin2MoneySource() {
        fromSpinner.setItems(coin);
        toSpinner.setItems(money);
    }

    private void setMoney2CoinSource() {
        fromSpinner.setItems(money);
        toSpinner.setItems(coin);
    }

    private void setCoin2CoinSource(){
        fromSpinner.setItems(coin);
        toSpinner.setItems(coin);
    }


    private void calculateValue(){
        final ProgressDialog mDialog = new ProgressDialog(MainActivity.this);
        mDialog.setMessage("Please waiting...");
        mDialog.show();

        final String coinName = toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString();
        String fromCoin = fromSpinner.getItems().get(fromSpinner.getSelectedIndex()).toString();

        mService.calculateValue(fromCoin,coinName).enqueue(new Callback<Coin>() {
            @Override
            public void onResponse(Call<Coin> call, Response<Coin> response) {
                mDialog.dismiss();
                if(coinName.equals("BTC"))
                    showData(response.body().getBTC());
                if(coinName.equals("ETC"))
                    showData(response.body().getETC());
                if(coinName.equals("ETH"))
                    showData(response.body().getETH());
                if(coinName.equals("AUR"))
                    showData(response.body().getAUR());
                if(coinName.equals("DASH"))
                    showData(response.body().getDASH());
                if(coinName.equals("MAID"))
                    showData(response.body().getMAID());
                if(coinName.equals("LTC"))
                    showData(response.body().getLTC());
                if(coinName.equals("XMR"))
                    showData(response.body().getXMR());
                if(coinName.equals("XEM"))
                    showData(response.body().getXEM());
                if(coinName.equals("XRP"))
                    showData(response.body().getXRP());
                if(coinName.equals("USD"))
                    showData(response.body().getUSD());
                if(coinName.equals("EUR"))
                    showData(response.body().getEUR());
                if(coinName.equals("GBP"))
                    showData(response.body().getGBP());
            }

            @Override
            public void onFailure(Call<Coin> call, Throwable t) {
                Log.e("ERROR",t.getMessage());
                mDialog.dismiss();
            }
        });

    }

    private void showData(String value) {
            if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("BTC")){
                Picasso.with(MainActivity.this).load(Common.BTC_IMAGE).into(coinImage);
                toTextView.setText(value);
            }else  if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("ETC")){
                Picasso.with(MainActivity.this).load(Common.ETC_IMAGE).into(coinImage);
                toTextView.setText(value);
            }else  if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("ETH")){
                Picasso.with(MainActivity.this).load(Common.ETH_IMAGE).into(coinImage);
                toTextView.setText(value);
            }else  if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("XRP")){
                Picasso.with(MainActivity.this).load(Common.XRP_IMAGE).into(coinImage);
                toTextView.setText(value);
            }else  if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("LTC")){
                Picasso.with(MainActivity.this).load(Common.LTC_IMAGE).into(coinImage);
                toTextView.setText(value);
            }else  if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("AUR")){
                Picasso.with(MainActivity.this).load(Common.AUR_IMAGE).into(coinImage);
                toTextView.setText(value);
            }else  if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("DASH")){
                Picasso.with(MainActivity.this).load(Common.DASH_IMAGE).into(coinImage);
                toTextView.setText(value);
            }else  if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("MAID")){
                Picasso.with(MainActivity.this).load(Common.MAID_IMAGE).into(coinImage);
                toTextView.setText(value);
            }else  if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("XMR")){
                Picasso.with(MainActivity.this).load(Common.XMR_IMAGE).into(coinImage);
                toTextView.setText(value);
            }else  if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("XEM")){
                Picasso.with(MainActivity.this).load(Common.XEM_IMAGE).into(coinImage);
                toTextView.setText(value);
            }else  if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("USD")){
                toTextView.setText("$ " + value);
            }else  if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("GBP")){
                toTextView.setText("£ " + value);
            }else  if(toSpinner.getItems().get(toSpinner.getSelectedIndex()).toString().equals("EUR")){
                toTextView.setText("€ " + value);
            }

    }


}
