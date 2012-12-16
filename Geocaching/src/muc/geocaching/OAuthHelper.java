package muc.geocaching;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;

/*
 * developer key:  a3eZtUsKbV9buaRU to access the OpenCaching.com API.  
 *  For example: 
 * http://test.opencaching.com/api/geocache?center=38.642,-94.754&limit=5&Authorization=a3eZtUsKbV9buaRU.
 * 
 * http://test.opencaching.com
 * */
public class OAuthHelper {
    //OAuth connection
    private static OAuthConsumer mConsumer;
    private static OAuthProvider mProvider;

    private static String mCallbackUrl;

    public void OAuthHelper(String consumerKey, String consumerSecret,
    		String scope, String callbackUrl)
    throws UnsupportedEncodingException {
    	mConsumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
    	mProvider = new CommonsHttpOAuthProvider(
    			"https://www.google.com/accounts/OAuthGetRequestToken?scope="
    			//"http://www.opencaching.com/api/oauth/requestToken"
    			+ URLEncoder.encode(scope, "utf-8"),
    			"https://www.google.com/accounts/OAuthGetAccessToken",
    			"https://www.google.com/accounts/OAuthAuthorizeToken?hd=default");
    	mProvider.setOAuth10a(true);
    	mCallbackUrl = (callbackUrl == null ? OAuth.OUT_OF_BAND : callbackUrl);
    }
    
    public static String getRequestToken()
    		throws OAuthMessageSignerException, OAuthNotAuthorizedException,
    		OAuthExpectationFailedException, OAuthCommunicationException {
    			String authUrl = mProvider.retrieveRequestToken(mConsumer, mCallbackUrl);
    			return authUrl;
    		}
    
    public static String[] getAccessToken(String verifier)
    		throws OAuthMessageSignerException, OAuthNotAuthorizedException,
    		OAuthExpectationFailedException, OAuthCommunicationException {
    			mProvider.retrieveAccessToken(mConsumer, verifier);
    			return new String[] {
    					mConsumer.getToken(), mConsumer.getTokenSecret()
    			};
    		}

}
