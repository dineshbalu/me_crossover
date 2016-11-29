package android.db.me_crossover;

import android.db.me_crossover.helper.DBHelper;
import android.db.me_crossover.model.User;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper helper = new DBHelper(this);

        User user = helper.getUser("admin@conference.com","123456");

        if( user != null )
            Log.v("MainActivity","User = " + user.getName());
    }
}
