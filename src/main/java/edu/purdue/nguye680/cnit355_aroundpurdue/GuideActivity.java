package edu.purdue.nguye680.cnit355_aroundpurdue;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class GuideActivity extends AppCompatActivity {

    ImageView btnSave;
    Location locationName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        TextView lblMain = (TextView)findViewById(R.id.lblSave);
        lblMain.setText(MainActivity.locations2.get(MainActivity.place).name);

        TextView txtRate = (TextView)findViewById(R.id.txtRate);
        txtRate.setText(MainActivity.locations2.get(MainActivity.place).rate);

        TextView txtFilter = (TextView)findViewById(R.id.txtFilter);
        txtFilter.setText(MainActivity.locations.get(MainActivity.place).filter);

        TextView txtDescription = (TextView)findViewById(R.id.txtDescription);
        txtDescription.setText(MainActivity.locations2.get(MainActivity.place).description);

        TextView txtContact = (TextView)findViewById(R.id.txtContact);
        txtContact.setText(txtContact.getText() + MainActivity.locations2.get(MainActivity.place).contact);

        TextView txtPrice = (TextView)findViewById(R.id.txtPrice);
        txtPrice.setText(txtPrice.getText() + MainActivity.locations2.get(MainActivity.place).price);

        TextView txtLocation = (TextView)findViewById(R.id.txtLocation);
        txtLocation.setText(txtLocation.getText() + MainActivity.locations2.get(MainActivity.place).location);

        TextView txtTime = (TextView)findViewById(R.id.txtTime);
        txtTime.setText(txtTime.getText() + MainActivity.locations2.get(MainActivity.place).time);

        TextView txtRecommendation = (TextView)findViewById(R.id.txtRecommendation);
        txtRecommendation.setText(txtRecommendation.getText() + MainActivity.locations2.get(MainActivity.place).recommendation);


        ImageView img = (ImageView)findViewById(R.id.img);
        img.setImageResource(MainActivity.locations2.get(MainActivity.place).image);

        btnSave = (ImageView) findViewById(R.id.imgUnsave);
        locationName = MainActivity.locations2.get(MainActivity.place);

        if(SaveActivity.savedLocations.contains(locationName)) {
            System.out.println("already Saved");
            btnSave.setImageDrawable(getResources().getDrawable(R.drawable.save));
        }

        ImageView map = (ImageView)findViewById(R.id.imgMap);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuideActivity.this, MapsActivity.class);
                MainActivity.lat = MainActivity.locations2.get(MainActivity.place).lat;
                MainActivity.lon = MainActivity.locations2.get(MainActivity.place).lon;
                startActivity(intent);
            }
        });
    }

    public void clickLink(View view) {

       String website = MainActivity.locations2.get(MainActivity.place).link;
       Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(website));
       startActivity(mIntent);
    }


    public void onClickSave(View view) {
        if(!SaveActivity.savedLocations.contains(locationName)) {
            System.out.println("saving");
            btnSave.setImageDrawable(getResources().getDrawable(R.drawable.save));

            SaveActivity.savedLocations.add(locationName);

        } else {
            System.out.println("unsaving");
            btnSave.setImageDrawable(getResources().getDrawable(R.drawable.unsave));
            SaveActivity.savedLocations.remove(locationName);
        }

        for(int i = 0; i < SaveActivity.savedLocations.size() ; i++){

            System.out.println(SaveActivity.savedLocations.get(i).name);

        }



    }

    public void onClickHome(View view) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}