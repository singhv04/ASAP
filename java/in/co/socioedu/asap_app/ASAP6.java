package in.co.socioedu.asap_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ASAP6 extends AppCompatActivity {

    private Button chaat;
    private Button pasta;
    private Button snacks;
    private Button maggi;
    private Button sprouts;
    private Button roll;

    private Button saras_chaach;
    private Button saras_lassi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asap6);
        chaat=(Button)findViewById(R.id.chaat);
        pasta=(Button)findViewById(R.id.pasta);
        roll=(Button)findViewById(R.id.roll);
        snacks=(Button)findViewById(R.id.snacks);
        sprouts=(Button)findViewById(R.id.sprouts);
        maggi=(Button)findViewById(R.id.maggi);

        saras_chaach=(Button)findViewById(R.id.saras_chaach);
        saras_lassi=(Button)findViewById(R.id.saras_lassi);

        chaat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ASAP6.this, saras_menu.class);
                Bundle bundle= new Bundle();
                bundle.putString("X","chaat");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        pasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ASAP6.this, saras_menu.class);
                Bundle bundle= new Bundle();
                bundle.putString("X","pasta");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        snacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ASAP6.this, saras_menu.class);
                Bundle bundle= new Bundle();
                bundle.putString("X","Snacks");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ASAP6.this, saras_menu.class);
                Bundle bundle= new Bundle();
                bundle.putString("X","Roll");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        maggi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ASAP6.this, saras_menu.class);
                Bundle bundle= new Bundle();
                bundle.putString("X","Maggi");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        sprouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ASAP6.this, saras_menu.class);
                Bundle bundle= new Bundle();
                bundle.putString("X","Sprouts");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_onlycart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_about) {
            Intent intent = new Intent(ASAP6.this, ASAP8.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
