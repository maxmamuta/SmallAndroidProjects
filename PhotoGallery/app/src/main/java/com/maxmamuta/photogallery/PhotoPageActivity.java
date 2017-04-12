package com.maxmamuta.photogallery;

import android.support.v4.app.Fragment;

/**
 * Created by Maxim on 4/11/2017.
 */

public class PhotoPageActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment() {
        return new PhotoPageFragment();
    }
}
