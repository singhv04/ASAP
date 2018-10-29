package in.co.socioedu.asap_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class ASAP3 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asap3);
        Spinner spinner = findViewById(R.id.spinner);
        imageButton=findViewById(R.id.imageButton);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.college_names, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view,  int i, long l) {

            final int j=i;
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ( j== 1) {
                        Intent intent1 = new Intent(ASAP3.this, ASAP4.class);
                        startActivity(intent1);
                    }
                    else {
                        Toast.makeText(
                                ASAP3.this,
                                "Please Select A college!",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }
            });
        }



    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(
                ASAP3.this,
                "Please Select A college!",
                Toast.LENGTH_SHORT
        ).show();

    }

    public void onBackPressed() {
        Intent a = new Intent(ASAP3.this, ASAP.class);
        startActivity(a);
    }

}
