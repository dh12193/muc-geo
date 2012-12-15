package muc.geocaching;

import java.util.LinkedList;
import java.util.List;

import android.os.Bundle;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.view.Menu;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    //show username example
    public String getUsername(){
        AccountManager manager = AccountManager.get(this); 
        Account[] accounts = manager.getAccountsByType("com.google"); 
        List<String> possibleEmails = new LinkedList<String>();

        for (Account account : accounts) {
          // TODO: Check possibleEmail against an email regex or treat
          // account.name as an email address only for certain account.type values.
          possibleEmails.add(account.name);
        }

        if(!possibleEmails.isEmpty() && possibleEmails.get(0) != null){
            String email = possibleEmails.get(0);
            String[] parts = email.split("@");
            if(parts.length > 0 && parts[0] != null)
                return parts[0];
            else
                return null;
        }else
            return null;
    }
    
    /// **************************** show image example */
    LinearLayout mLinearLayout;

    /*
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Create a LinearLayout in which to add the ImageView
    mLinearLayout = new LinearLayout(this);

    // Instantiate an ImageView and define its properties
    ImageView i = new ImageView(this);
    i.setImageResource(R.drawable.my_image);
    i.setAdjustViewBounds(true); // set the ImageView bounds to match the Drawable's dimensions
    i.setLayoutParams(new Gallery.LayoutParams(LayoutParams.WRAP_CONTENT,
    LayoutParams.WRAP_CONTENT));

    // Add the ImageView to the layout and set the layout as the content view
    mLinearLayout.addView(i);
    setContentView(mLinearLayout);
    }
    */

}
