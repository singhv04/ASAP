package in.co.socioedu.asap_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

public class ASAP5 extends AppCompatActivity {

    private ImageButton saras_button;
    private ImageButton yo_zing_button;
    private ImageButton tuck_shop_button;
    private ImageButton chatkara_button;
    private ImageButton amul_button;
    private ImageButton all_mart;


    private ImageView mujlogo;
    private TextView title;
    String regno;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asap5);


        saras_button=findViewById(R.id.imageButton2);
        saras_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ASAP5.this, ASAP6.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_about) {
            Intent intent = new Intent(ASAP5.this, ASAP8.class);
            startActivity(intent);
            return true;
        }
        else if(id==R.id.sign_out_menu){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(ASAP5.this, ASAP4.class);
            startActivity(intent);
            finish();
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

}
