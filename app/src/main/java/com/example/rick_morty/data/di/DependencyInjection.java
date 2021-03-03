package com.example.rick_morty.data.di;

import android.content.Context;

import androidx.room.Room;

import com.example.rick_morty.data.api.CharacterService;
import com.example.rick_morty.data.db.CharacterDatabase;
import com.example.rick_morty.data.repository.CharacterDataRepository;
import com.example.rick_morty.data.repository.CharacterRepository;
import com.example.rick_morty.data.repository.CharacterLocalDataSource;
import com.example.rick_morty.data.repository.CharacterRemoteDataSource;
import com.example.rick_morty.presentation.viewModel.ViewModelFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import com.google.gson.Gson;

/**
 * Dependency injection for retrofit and to initialize repository
 */
public class DependencyInjection {

    private static Context applicationContext;
    private static Retrofit retrofit;
    private static Gson gson;
    private static CharacterDatabase characterDatabase;
    private static CharacterRepository characterRepository;
    private static CharacterService characterService;
    private static ViewModelFactory viewModelFactory;

    //Création d'une instance pour utiliser l'interface Retrofit
    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://rickandmortyapi.com/api/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static CharacterRepository getCharacterRepository() {
        if (characterRepository == null) {
            characterRepository = new CharacterDataRepository(
                    new CharacterRemoteDataSource(getCharacterService()),
                    new CharacterLocalDataSource(getCharacterDatabase())
            );
        }
        return characterRepository;
    }

    public static CharacterService getCharacterService() {
        if (characterService == null) {
            characterService = getRetrofit().create(CharacterService.class);
        }
        return characterService;
    }

    public static void setContext(Context context) {
        applicationContext = context;
    }

    public static ViewModelFactory getViewModelFactory() {
        if (viewModelFactory == null) {
            viewModelFactory = new ViewModelFactory(getCharacterRepository());
        }
        return viewModelFactory;
    }

    public static CharacterDatabase getCharacterDatabase() {
        if (characterDatabase == null) {
            characterDatabase = Room.databaseBuilder(applicationContext,
                    CharacterDatabase.class, "character-database").build();
        }
        return characterDatabase;
    }
}
