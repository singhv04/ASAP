package in.co.socioedu.asap_app;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class ASAP extends AppCompatActivity {

    private ImageView customer_img;
    private Button customer_button;
    private  ImageView bussiness_img;
    private Button bussiness_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            // already signed in
                startActivity(new Intent(ASAP.this, ASAP5.class));
                finish();
            }

        else {
            setContentView(R.layout.activity_asap);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            customer_button = (Button) findViewById(R.id.button4);
            customer_img = (ImageView) findViewById(R.id.imageView5);
            bussiness_button = (Button) findViewById(R.id.button5);
            bussiness_img = (ImageView) findViewById(R.id.imageView6);

            customer_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(ASAP.this, ASAP3.class);
                    startActivity(intent1);
                }

            });


            bussiness_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(ASAP.this, ASAP2.class);
                    startActivity(intent1);

                }

            });

        }

    }
    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }
}
