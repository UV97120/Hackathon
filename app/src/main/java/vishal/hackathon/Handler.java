package vishal.hackathon;

/**
 * Created by Vishal on 3/19/2017.
 */



        import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

public class Handler extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Add your initialization code here
        Parse.initialize(this, "myAppID","");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }

}
