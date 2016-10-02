package com.iaccept.pehechano.common.network;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.iaccept.pehechano.App;


public class VolleySingleton {

    private static VolleySingleton singleton = null;
    private RequestQueue requestQueue;

    private VolleySingleton() {
        requestQueue = Volley.newRequestQueue(App.getAppContext());
    }

    public static VolleySingleton getInstance() {
        if (singleton == null) {
            singleton = new VolleySingleton();
        }
        return singleton;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
}