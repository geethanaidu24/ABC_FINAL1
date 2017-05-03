package com.example.admin.abc;

import com.android.volley.ApplicationController;

/**
 * Created by Admin on 5/2/2017.
 */

class MyApplication {
    private static ApplicationController instance;

    public static ApplicationController getInstance() {
        return instance;
    }
}
