package in.co.socioedu.asap_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CHECKING_USER extends AppCompatActivity {

    //private ProgressBar spinner;
    private TextView checking;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private FirebaseUser user;
    String userdetails;
    String con;
    int check=0;
    int noOfUsers=2;
    String regno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checking__user);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("USERS");
        checking=findViewById(R.id.checking);
        //spinner = (ProgressBar)findViewById(R.id.progressBar1);
        //spinner.setVisibility(View.GONE);

        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            //spinner.setVisibility(View.VISIBLE);
            userdetails = user.getPhoneNumber();
            databaseReference.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    noOfUsers--;


                    con = dataSnapshot.child("contact").getValue().toString();
                    if(userdetails.equalsIgnoreCase("+91"+con)){
                        check=1;
                        regno=dataSnapshot.child("regno").getValue().toString();
                        Toast.makeText(CHECKING_USER.this,"VALID USER",Toast.LENGTH_SHORT).show();
                        //GETTING REGESTRATION NO.
                        Toast.makeText(CHECKING_USER.this,"REGISTRATION NO. :"+regno,Toast.LENGTH_SHORT).show();
                        //going to next activity if valid user
                        Intent intent = new Intent(CHECKING_USER.this,ASAP5.class);
                        startActivity(intent);
                        finish();

                    }
                    else if(noOfUsers==0&&check!=1)
                    {
                        Toast.makeText(CHECKING_USER.this,"USER is not Registered",Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(CHECKING_USER.this,ASAP4.class);

                        startActivity(intent);
                        finish();
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
            /*if(check==0){
                Toast.makeText(CHECKING_USER.this,"USER NOT VALID FOR ORDER",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CHECKING_USER.this,ASAP4.class);
                startActivity(intent);
                finish();*/

        }
        if(check==0){
            if (noOfUsers==0){

            }

        }


    }

}

