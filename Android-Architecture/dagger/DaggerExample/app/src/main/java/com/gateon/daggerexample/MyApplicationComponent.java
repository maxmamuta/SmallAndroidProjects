package com.gateon.daggerexample;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

/**
 * Created by Maxim on 8/10/2017.
 */

@Component(modules = { AndroidInjectionModule.class, MyApplicationModule.class})
public interface MyApplicationComponent extends AndroidInjector<MyApplication> {
}
