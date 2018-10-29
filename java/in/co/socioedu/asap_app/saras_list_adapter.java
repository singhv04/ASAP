package in.co.socioedu.asap_app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class saras_list_adapter extends BaseAdapter {
    private FirebaseDatabase database;
    private DatabaseReference ref;
    private FirebaseUser user;
    private String userdetails;
    private String con;
    private String regno;
    private String n,p;
    int q;

    public ArrayList<ListItem> listProducts;
    private Context context;

    public saras_list_adapter(Context context,ArrayList<ListItem> listProducts) {
        this.context = context;
        this.listProducts = listProducts;
    }

    @Override
    public int getCount() {
        return listProducts.size();
    }

    @Override
    public ListItem getItem(int position) {
        return listProducts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView
            , ViewGroup parent) {
        View row;
        final saras_list_view_holder listViewHolder;
        if(convertView == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.menu_item_row,parent,false);
            listViewHolder = new saras_list_view_holder();
            listViewHolder.name = row.findViewById(R.id.item);
            listViewHolder.price = row.findViewById(R.id.price);
            listViewHolder.quantity = row.findViewById(R.id.quantity);
            listViewHolder.add = row.findViewById(R.id.add);
            listViewHolder.minus = row.findViewById(R.id.sub);
            listViewHolder.addItem=row.findViewById(R.id.additem);
            row.setTag(listViewHolder);
        }
        else
        {
            row=convertView;
            listViewHolder= (saras_list_view_holder) row.getTag();
        }
        ListItem products = getItem(position);

        listViewHolder.name.setText(products.getName());
        listViewHolder.quantity.setText(products.getQuantity() );
        listViewHolder.price.setText("Rs."+products.getPrice());

        listViewHolder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                updateQuantity(position,listViewHolder.quantity,1);
            }
        });
        //listViewHolder.edTextQuantity.setText("0");
        listViewHolder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateQuantity(position,listViewHolder.quantity,-1);

            }
        });

        listViewHolder.addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                itemadded(position,listViewHolder.quantity);

            }
        });


        return row;
    }

    private void updateQuantity(int position, TextView Quantity, int value) {

        ListItem products = getItem(position);
        if(value > 0)
        {
            products.CartQuantity = products.CartQuantity + 1;
        }
        else
        {
            if(products.CartQuantity > 0)
            {
                products.CartQuantity = products.CartQuantity - 1;
            }

        }
        Quantity.setText(products.CartQuantity+"");
    }

    private void itemadded(int position,  TextView Quantity) {
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("USERS");
        user = FirebaseAuth.getInstance().getCurrentUser();
        userdetails = user.getPhoneNumber();

         ListItem products = getItem(position);
         n = products.getName();
         p = products.getPrice();
         q = products.CartQuantity;


        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                con = dataSnapshot.child("contact").getValue().toString();

                if (userdetails.equalsIgnoreCase("+91" + con)) {

                    //GETTING REGESTRATION NO.
                    regno = dataSnapshot.child("regno").getValue().toString();

                    //PRODUCTS ORDERED ADDED
                    //Creating an alert dialog to confirm the deletion
                    if (q>0) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Item Added");

                        //if the response is positive in the alert
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //removing the item
                                //reloading the list
                                notifyDataSetChanged();

                            }
                        });

                        //if response is negative nothing is being done

                        //creating and displaying the alert dialog
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    ref.child(regno).child("cart").push().setValue(n + "@" + p + "#" + q);

                }}

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
        products.CartQuantity=0;
    }
}
