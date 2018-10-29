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

public class ASAP2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asap2);


         spinner = (Spinner) findViewById(R.id.spinner);
        imageButton=(ImageButton)findViewById(R.id.imageButton);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.college_names, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        final int j=i;
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( j== 1) {
                    Intent intent1 = new Intent(ASAP2.this, ASAP4.class);
                    startActivity(intent1);
                    finish();
                } else {
                    Toast.makeText(
                            ASAP2.this,
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
                ASAP2.this,
                "Please Select A college!",
                Toast.LENGTH_SHORT
        ).show();

    }

}
