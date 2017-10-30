package com.example.administrator.day18rikao;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String GSON_URL = "https://api.tianapi.com/wxnew/?key=8d6e3228d25298f13af4fc40ce6c9679&num=10 ";
    private ListView lv;
    private Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        person = new Person(this);
        lv = (ListView) findViewById(R.id.lv);
        if (isNetworkAvailbale(MainActivity.this)){
            Toast.makeText(getApplicationContext(),"没网",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(),"有网",Toast.LENGTH_SHORT).show();
            new AsyncTask<String, Integer, String>() {
                @Override
                protected String doInBackground(String... params) {
                    String s = params[0];
                    String getss = new HttpURLL().getss(s);
                    return getss;
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    Bean bean = new Gson().fromJson(s, Bean.class);
                    List<News> newslist = bean.getNewslist();
                    for (int i = 0 ; i < newslist.size() ; i++){
                        String title = newslist.get(i).getTitle();
                        String picUrl = newslist.get(i).getPicUrl();
                        person.add(title,picUrl);
                    }
                    List<News> file = person.file();
                    MyAdapter adapter = new MyAdapter(file,MainActivity.this);
                    lv.setAdapter(adapter);
                }
            }.execute(GSON_URL);
        }
    }
    public boolean isNetworkAvailbale(Activity activity){
        Context context = activity.getApplicationContext();
        ConnectivityManager connection = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){

            Network[] info = connection.getAllNetworks();
            NetworkInfo network;
            for (Network mNework:info) {
                network = connection.getNetworkInfo(mNework);
                if (network.getState()==NetworkInfo.State.CONNECTED){
                    return true;
                }
            }
        }else{
            return false;
        }
        return false;
    }
}
