package in.co.socioedu.asap_app;

import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ASAP8 extends AppCompatActivity {

    private List<ListItem> orderlist;
    private ListView listView;
    private Button btnPlaceOrder;
    private TextView totalbill;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private ArrayList<ListItem> products_orders;
    private FirebaseUser user;
    private String userdetails;
    private String con;
    private double total_bill=0;
    private String regno;

    private DatabaseReference finalorderlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asap8);

        //initializing the view
        btnPlaceOrder = (Button) findViewById(R.id.btnPlaceOrderx);
        listView = (ListView) findViewById(R.id.customListView2);


        orderlist = new ArrayList<>();

        //database reference
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("USERS");

        //CHECKING USER
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            userdetails = user.getPhoneNumber();
        }


        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                con = dataSnapshot.child("contact").getValue().toString();
                if (userdetails.equalsIgnoreCase("+91" + con)) {

                    //GETTING REGESTRATION NO.
                    regno = dataSnapshot.child("regno").getValue().toString();


                    finalorderlist = databaseReference.child(regno).child("cart");
                    finalorderlist.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                            String x = dataSnapshot.getValue().toString();

                                int l1 = x.indexOf("@");
                                String nx = x.substring(0, l1);
                                //Toast.makeText(ASAP8.this,"ITEM NAME :"+nx,Toast.LENGTH_SHORT).show();

                                int l2 = x.indexOf("#");
                                String px = x.substring(l1 + 1, l2);
                                //Toast.makeText(ASAP8.this,"ITEM PRICE :"+px,Toast.LENGTH_SHORT).show();

                                String qx = x.substring(l2 + 1);
                                //Toast.makeText(ASAP8.this,"ITEM QUANTITY :"+qx,Toast.LENGTH_SHORT).show();

                                orderlist.add(new ListItem(nx, "Rs." +(Double.parseDouble(px)*Double.parseDouble(qx)), qx));
                                total_bill=total_bill+(Double.parseDouble(px)*Double.parseDouble(qx));
                                order_list_adapter adapter = new order_list_adapter(ASAP8.this, R.layout.order_item_row, orderlist);
                                //attaching adapter to the listview
                                listView.setAdapter(adapter);
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





                }

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

    }
}


