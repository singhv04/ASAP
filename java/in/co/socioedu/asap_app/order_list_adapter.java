package in.co.socioedu.asap_app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class order_list_adapter extends ArrayAdapter<ListItem>{

    List<ListItem> orderlist;
    Context context;
    int resource;

    public order_list_adapter(Context context, int resource, List<ListItem> orderlist) {
        super(context, resource, orderlist);
        this.context = context;
        this.resource = resource;
        this.orderlist =orderlist;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        //we need to get the view of the xml for our list item
        //And for this we need a layoutinflater
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        //getting the view
        View view = layoutInflater.inflate(resource, null, false);

        //getting the view elements of the list from the view
        final TextView namex = view.findViewById(R.id.item);
        final TextView pricex = view.findViewById(R.id.price);
        final TextView quantityx = view.findViewById(R.id.quantity);
        ImageButton buttonDelete = view.findViewById(R.id.delete);
        Button addx=view.findViewById(R.id.add);
        Button subx=view.findViewById(R.id.sub);


        //getting the hero of the specified position
        ListItem  listItem= orderlist.get(position);

        //adding values to the list item
        namex.setText(listItem.getName());
        pricex.setText(listItem.getPrice());
        quantityx.setText(listItem.getQuantity());

        //adding a click listener to the button to remove item from the list
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //we will call this method to remove the selected value from the list
                //we are passing the position which is to be removed in the method
                removeListItem(position,namex,pricex,quantityx);


            }
        });

        //finally returning the view
        return view;
    }

    //this method will remove the item from the list
    private void removeListItem(final int position, final TextView name, TextView price, final TextView quantity) {
        //Creating an alert dialog to confirm the deletion
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Are you sure you want to delete this?");

        //if the response is positive in the alert
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //removing the item
                orderlist.remove(position);
                //reloading the list
                notifyDataSetChanged();

            }
        });

        //if response is negative nothing is being done
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        //creating and displaying the alert dialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}



