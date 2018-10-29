package in.co.socioedu.asap_app;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseArray;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class saras_menu extends AppCompatActivity {


    private TextView Option;
    private Button addItem;
    private ListView listView;
    private saras_list_adapter listAdapter;
    ArrayList<ListItem> products = new ArrayList<>();
    Button btnPlaceOrder;
    ArrayList<ListItem> productOrders = new ArrayList<>();
    private FirebaseDatabase database;
    private DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saras_menu);

        Option = (TextView) findViewById(R.id.option);
        addItem=(Button)findViewById(R.id.additem);
        Bundle bundle = getIntent().getExtras();
        String option = bundle.getString("X");
        Option.setText(option);
        Toast.makeText(saras_menu.this, option, Toast.LENGTH_SHORT).show();

        database = FirebaseDatabase.getInstance();

        //for displaying menu
        if (option.equals("chaat")) {
            ref = database.getReference("menu").child("SARAS").child(option);
            ref.keepSynced(true);

        } else {
            ref = database.getReference("menu").child("SARAS").child("snacks").child(option);
            ref.keepSynced(true);

        }

        listView = (ListView) findViewById(R.id.customListView);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                saras_menu_chaat x = dataSnapshot.getValue(saras_menu_chaat.class);
                String n = x.getName();
                String p = x.getPrice();

                products.add(new ListItem(n, p, "0"));
                listAdapter = new saras_list_adapter(saras_menu.this, products);
                listView.setAdapter(listAdapter);

            }


            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnPlaceOrder = (Button) findViewById(R.id.btnPlaceOrder);//THIS IS FOR PROCEED TO CART
        btnPlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(saras_menu.this, ASAP8.class);
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
            Intent intent = new Intent(saras_menu.this, ASAP8.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
