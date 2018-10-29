package in.co.socioedu.asap_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Arrays;

public class PhoneNumberAuthentication extends AppCompatActivity {
    private static final int RC_SIGN_IN = 123;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number_authentication);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            // already signed in

            startActivity(new Intent(PhoneNumberAuthentication.this, ASAP5.class));
            finish();
        }
        else {
            // not signed in
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(
                                    Arrays.asList(
                                            new AuthUI.IdpConfig.Builder(AuthUI.PHONE_VERIFICATION_PROVIDER).build()
                                    ))
                            .build(),
                    RC_SIGN_IN);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);
            // Successfully signed in
            if (resultCode == ResultCodes.OK) {

                startActivity(new Intent(PhoneNumberAuthentication.this, CHECKING_USER.class));
                return;
            }

            else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    Log.e("Login", "Login canceled by User");
                    Toast.makeText(
                            PhoneNumberAuthentication.this,
                            "Login canceled by User!",
                            Toast.LENGTH_SHORT
                    ).show();
                    Intent a = new Intent(PhoneNumberAuthentication.this, ASAP4.class);
                    startActivity(a);
                    return;
                }

            }
            if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                Log.e("Login","No Internet Connection");
                Toast.makeText(
                        PhoneNumberAuthentication.this,
                        "No Internet Connection!",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }
            if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                Log.e("Login","Unknown Error");
                Toast.makeText(
                        PhoneNumberAuthentication.this,
                        "Unknown Error!",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }
        }
        Log.e("Login","Unknown sign in response");
        Toast.makeText(
                PhoneNumberAuthentication.this,
                "Unknown sign in response!",
                Toast.LENGTH_SHORT
        ).show();
    }



}

