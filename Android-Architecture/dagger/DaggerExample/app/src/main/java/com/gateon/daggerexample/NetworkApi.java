package com.gateon.daggerexample;

import javax.inject.Inject;

/**
 * Created by Maxim on 8/10/2017.
 */

public class NetworkApi {

    @Inject
    public NetworkApi() {

    }

    public boolean validateUser(String username, String password) {
        return !(username == null || username.length() == 0);
    }
}
