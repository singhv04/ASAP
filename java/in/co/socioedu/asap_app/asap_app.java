package in.co.socioedu.asap_app;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class asap_app extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
