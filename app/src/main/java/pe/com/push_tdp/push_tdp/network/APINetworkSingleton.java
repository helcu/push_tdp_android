package pe.com.push_tdp.push_tdp.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by kenkina on 7/11/2017.
 */

public class APINetworkSingleton {
    private static APINetworkSingleton mInstance;
    private RequestQueue mRequestQueue;

    private static Context mCtx;

    private APINetworkSingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized APINetworkSingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new APINetworkSingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
