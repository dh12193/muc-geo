package muc.geocaching;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv = (TextView)findViewById(R.id.w_username);
        	//tv.setText( getUsername() );
        	
//        if(isOnline()){
//        	//
//        }
        
        AccountManager am = AccountManager.get(this);
        Bundle options = new Bundle();
        AccountManager manager = AccountManager.get(this); 
        Account[] accounts1 = manager.getAccountsByType("com.google");

        am.getAuthToken(
            accounts1[0], 		    // Account retrieved using getAccountsByType()
            "HMAC-SHA1",		            // Auth scope
            options,                        // Authenticator-specific options
            this,                           // Your activity
            new OnTokenAcquired(),          // Callback called when a token is successfully acquired
            new Handler());    // Callback called if an error occurs
        /*URL url = new URL("http://www.opencaching.com/api/oauth/requestToken");
        URLConnection conn = (HttpURLConnection) url.openConnection();
        conn.addRequestProperty("oauth_consumer_key", "a3eZtUsKbV9buaRU");
        conn.addRequestProperty("oauth_signature_method", "HMAC-SHA1");
        conn.addRequestProperty("oauth_timestamp", "2012-12-13 01:57:26Z");
        conn.addRequestProperty("oauth_nonce", String.valueOf(Calendar.getInstance()) );
        conn.addRequestProperty("oauth_callback" , "url");*/
        	/*try {
        		OAuthHelper a = new OAuthHelper();
        		String uri = a.getRequestToken();
        		tv.setText("bbbbb");
        		//startActivity(new Intent("android.intent.action.VIEW", Uri.parse(uri)));
        	} catch (Exception e) {
        		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("MyException Occured");
                dialog.setMessage(e.getMessage() + " ");
                dialog.setNeutralButton("Ok", null);
                dialog.create().show();
        	}*/
    }
    private class OnTokenAcquired implements AccountManagerCallback<Bundle> {
        
        public void run(AccountManagerFuture<Bundle> result) {
            // Get the result of the operation from the AccountManagerFuture.
            Bundle bundle = null;
			try {
				bundle = result.getResult();
			} catch (OperationCanceledException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (AuthenticatorException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
            // The token is a named value in the bundle. The name of the value
            // is stored in the constant AccountManager.KEY_AUTHTOKEN.
            String token = bundle.getString(AccountManager.KEY_AUTHTOKEN);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    
    // Return Google Account username setup in the cellphone
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

    // Verify if cellphone is connected to the internet
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
    
    	/*
    	 * OAuthConsumer consumer = new CommonsHttpOAuthConsumer(accessToken[0], accessToken[1]);

			HttpGet request = new HttpGet(url);
			
			      // sign the request
			      consumer.sign(request);
			
			      // send the request
			      HttpClient httpClient = new DefaultHttpClient();
			      HttpResponse response = httpClient.execute(request);

    	 * */

	private String[] getVerifier() {
		// extract the token if it exists
		Uri uri = this.getIntent().getData();
		if (uri == null) {
			return null;
		}
	
		String token = uri.getQueryParameter("oauth_token");
		String verifier = uri.getQueryParameter("oauth_verifier");
		return new String[] { token, verifier };
	}

    
    /// **************************** show image example */
    /*LinearLayout mLinearLayout;

     
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
/*
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.signature.SignatureMethod;

public class Main {

    public static void main(String[] args) throws Exception {

        OAuthConsumer consumer = new DefaultOAuthConsumer(
                "iIlNngv1KdV6XzNYkoLA",
                "exQ94pBpLXFcyttvLoxU2nrktThrlsj580zjYzmoM",
                SignatureMethod.HMAC_SHA1);

        OAuthProvider provider = new DefaultOAuthProvider(consumer,
                "http://twitter.com/oauth/request_token",
                "http://twitter.com/oauth/access_token",
                "http://twitter.com/oauth/authorize");

        System.out.println("Fetching request token from Twitter...");

        // we do not support callbacks, thus pass OOB
        String authUrl = provider.retrieveRequestToken(OAuth.OUT_OF_BAND);

        System.out.println("Request token: " + consumer.getToken());
        System.out.println("Token secret: " + consumer.getTokenSecret());

        System.out.println("Now visit:\n" + authUrl
                + "\n... and grant this app authorization");
        System.out.println("Enter the PIN code and hit ENTER when you're done:");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String pin = br.readLine();

        System.out.println("Fetching access token from Twitter...");

        provider.retrieveAccessToken(pin);

        System.out.println("Access token: " + consumer.getToken());
        System.out.println("Token secret: " + consumer.getTokenSecret());

        URL url = new URL("http://twitter.com/statuses/mentions.xml");
        HttpURLConnection request = (HttpURLConnection) url.openConnection();

        consumer.sign(request);

        System.out.println("Sending request to Twitter...");
        request.connect();

        System.out.println("Response: " + request.getResponseCode() + " "
                + request.getResponseMessage());
    }
}
*/