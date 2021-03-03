package com.example.rick_morty.presentation;

import android.app.Application;

import com.example.rick_morty.data.di.DependencyInjection;

public class RickAndMortyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DependencyInjection.setContext(this);
    }
}
