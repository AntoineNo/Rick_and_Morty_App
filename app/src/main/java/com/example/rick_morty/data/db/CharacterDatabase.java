package com.example.rick_morty.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.rick_morty.data.entity.CharacterEntity;

/**
 * Abstract class to specify entity for local database, here CharacterEntity
 */
@Database(entities = {CharacterEntity.class}, version = 1)
public abstract class CharacterDatabase extends RoomDatabase {
    public abstract CharacterDao characterDao();
}
