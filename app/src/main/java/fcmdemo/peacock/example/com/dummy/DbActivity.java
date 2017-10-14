package fcmdemo.peacock.example.com.dummy;

import android.app.Activity;
import android.database.SQLException;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DbActivity extends AppCompatActivity {

    Activity actvity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        actvity=this;

        deleteDatabase("/data/data/fcmdemo.peacock.example.com.dummy/databases/rapdata.db");
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                LiveBaseHelper myDbHelper = new LiveBaseHelper(actvity);
                myDbHelper = new LiveBaseHelper(actvity);

                try {
                    myDbHelper.openDataBase();


                }catch(SQLException sqle){

                    throw sqle;

                }
            }
        },1500);


    }
}
