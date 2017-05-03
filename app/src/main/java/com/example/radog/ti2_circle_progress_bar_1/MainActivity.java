package com.example.radog.ti2_circle_progress_bar_1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.DonutProgress;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.donut_progress)
    DonutProgress dpProgress;
    private int cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        cont = 0;
    }

    @OnClick(R.id.btnDown)
    public void btnDown() {
        cont += 5;
        new Downloader(cont).execute();

    }

    class Downloader extends AsyncTask<Void, Integer, Integer> {

        private int cont;

        public Downloader(int cont) {
            this.cont = cont;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dpProgress.setMax(100);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            dpProgress.setProgress(values[0]);
        }

        @Override
        protected Integer doInBackground(Void... params) {
            publishProgress(cont);
            /*for (int i = 0; i <= 100; i++) {
                publishProgress(i);

                //simulate
                try{
                    Thread.sleep(200);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }*/
            return null;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            if (cont == 100)
                Toast.makeText(MainActivity.this, "Download finished!", Toast.LENGTH_SHORT).show();
        }
    }
}
