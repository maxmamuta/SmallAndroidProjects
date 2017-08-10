package com.gateon.daggerexample;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Maxim on 8/10/2017.
 */

@Module
public abstract class MyApplicationModule {
    @ContributesAndroidInjector
    abstract MainActivity contributeActivityInjector();
}
