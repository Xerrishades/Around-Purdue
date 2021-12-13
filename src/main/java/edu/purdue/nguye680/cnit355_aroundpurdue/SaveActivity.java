package edu.purdue.nguye680.cnit355_aroundpurdue;

import static android.view.Gravity.CENTER;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class SaveActivity extends AppCompatActivity {
    static ArrayList<Location> savedLocations = new ArrayList<>();
    ArrayList<String> names = new ArrayList<>();
    int place;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

        TableLayout tl = (TableLayout) findViewById(R.id.tableLayout1);

        if(savedLocations.size() % 2 != 0) {
            savedLocations.add(Location.placeHolder);
        }

        for( int i = 0; i < savedLocations.size(); i = i + 2)
        {
            int j = i + 1;
            TableRow tr = new TableRow(this);
            TextView textview2 = new TextView(this);
            TextView textview3 = new TextView(this);
            RelativeLayout ll = new RelativeLayout(this);
            RelativeLayout ll2 = new RelativeLayout(this);
            ImageView iv = new ImageView(this);
            ImageView iv2 = new ImageView(this);

            int paddingDp = 5;
            float density = getResources().getDisplayMetrics().density;
            int paddingPixel = (int)(paddingDp * density);

            tr.setGravity(CENTER);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(paddingPixel * 39, paddingPixel * 7, 1);
            LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(paddingPixel * 39, paddingPixel * 50);

            ll.setLayoutParams(lp);
            ll2.setLayoutParams(lp);

            lp.setMargins(paddingPixel, paddingPixel, paddingPixel, 0);
            lp2.setMargins(paddingPixel, 0, paddingPixel, 0);
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

            textview2.setText(savedLocations.get(i).name);
            textview2.setTextColor(getResources().getColor(R.color.black));
            textview2.setBackgroundResource(R.color.purdue);
            textview2.setGravity(CENTER);

            textview3.setText(savedLocations.get(j).name);
            textview3.setTextColor(getResources().getColor(R.color.black));
            textview3.setBackgroundResource(R.color.purdue);
            textview3.setGravity(CENTER);

            iv.setImageResource(savedLocations.get(i).image);
            iv2.setImageResource(savedLocations.get(j).image);


            textview2.setLayoutParams(lp);
            textview3.setLayoutParams(lp);
            iv.setLayoutParams(lp2);
            iv2.setLayoutParams(lp2);
            ll.addView(textview2);
            ll.addView(iv);
            ll2.addView(textview3);
            ll2.addView(iv2);

            tr.addView(ll, new TableRow.LayoutParams(0));
            tr.addView(ll2, new TableRow.LayoutParams(0));
            tl.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));

            int I = i;
            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    place = I;
                    Intent intent = new Intent(SaveActivity.this, GuideActivity.class);
                    startActivity(intent);
                }
            });
            iv2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    place = j;
                    Intent intent = new Intent(SaveActivity.this, GuideActivity.class);
                    startActivity(intent);
                }
            });


        }

    }
    String mySdPath = Environment.getExternalStorageDirectory().getAbsolutePath();

    public void onClickExport(View view) {


        try {
            File myFile = new File(mySdPath + "/savedLocation.txt");
            OutputStreamWriter myOutWriter = new OutputStreamWriter(new FileOutputStream(myFile));
            for (Location location : savedLocations) {
                myOutWriter.append(location.toString());
            }
            myOutWriter.close();
            Toast.makeText(getBaseContext(),"Location is successfully exported", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}
