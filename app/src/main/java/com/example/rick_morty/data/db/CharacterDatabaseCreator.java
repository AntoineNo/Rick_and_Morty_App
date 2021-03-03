package com.example.rick_morty.data.db;

import android.content.Context;

import androidx.room.Room;

/**
 * To create the characters database
 */
public class CharacterDatabaseCreator {
    private static CharacterDatabase CharacterDatabase;

    public static CharacterDatabase database(Context context){

        if(CharacterDatabase == null){
            CharacterDatabase = Room.databaseBuilder(context, CharacterDatabase.class, "CharacterDatabase").build();
        }

        return CharacterDatabase;
    }
}
