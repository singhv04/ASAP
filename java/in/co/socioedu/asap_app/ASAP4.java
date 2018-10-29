package in.co.socioedu.asap_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ASAP4 extends AppCompatActivity {

    private EditText editText2;
    private EditText editText3;
    private EditText editText5;
    private EditText editText6;
    private EditText editText4;
    private View view;

    private ImageButton imagebutton3;
    private ImageButton imagebutton5;
    private Button  login_button;
    private Button privacy;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferencex;
    private DatabaseReference contactx;
    private DatabaseReference regx;
    char check='5';


        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asap4);

        editText2=findViewById(R.id.editText2);
        editText3=findViewById(R.id.editText3);

        editText5=findViewById(R.id.editText5);
        editText6=findViewById(R.id.editText6);
        view=findViewById(R.id.view3);
        imagebutton5=findViewById(R.id.imageButton5);
        imagebutton3=findViewById(R.id.imageButton3);
        login_button=findViewById(R.id.button);
        privacy=findViewById(R.id.button2);


        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("USERS");

        databaseReferencex = database.getReference("student_info");
        imagebutton3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    if(!editText2.getText().toString().equals("") && !editText3.getText().toString().equals("") &&!editText5.getText().toString().equals("")&&!editText2.getText().toString().equals("")) {

                        regx=FirebaseDatabase.getInstance().getReference("student_info").child(editText6.getText().toString());
                        regx.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                String con=  dataSnapshot.child("name").getValue().toString();
                                if (editText2.getText().toString().equalsIgnoreCase(con)){
                                    dataAdapter dataadapter = new dataAdapter(editText2.getText().toString(), editText3.getText().toString(), editText6.getText().toString(), editText5.getText().toString());

                                    databaseReference.child(editText6.getText().toString()).setValue(dataadapter);

                                    Toast.makeText(
                                            ASAP4.this,
                                            "Registered Successfully!",
                                            Toast.LENGTH_SHORT
                                    ).show();


                                    Intent intent1 = new Intent(ASAP4.this, PhoneNumberAuthentication.class);
                                    startActivity(intent1);
                                    finish();


                                }else {
                                    Toast.makeText(
                                            ASAP4.this,
                                            "Invalid datails",
                                            Toast.LENGTH_LONG
                                    ).show();

                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                    }


                     else {

                            Toast.makeText(
                                    ASAP4.this,
                                    "Please Fill All Details!",
                                    Toast.LENGTH_LONG
                            ).show();
                     }


            }
            });

            login_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        Intent intent2=new Intent(ASAP4.this,PhoneNumberAuthentication.class);
                        startActivity(intent2);


                    }
            });


            privacy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent3=new Intent(ASAP4.this,privacy_policy.class);
                    startActivity(intent3);


                }
            });

            imagebutton5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent2=new Intent(ASAP4.this,t_c.class);
                    startActivity(intent2);


                }
            });

        }

    public void onBackPressed(){
        Intent a = new Intent(ASAP4.this,ASAP.class);
        startActivity(a);

    }


}
