package com.example.rick_morty.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.rick_morty.data.entity.CharacterEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * Interface for local database request
 */
@Dao
public interface CharacterDao {

    @Query("SELECT * FROM characters")
    Flowable<List<CharacterEntity>> getCharacters();

    @Query("SELECT * FROM characters WHERE :id = id")
    Flowable<List<CharacterEntity>> getACharacter(int id);

    @Query("DELETE FROM characters WHERE :id = id")
    Completable deleteCharacter(int id);

    @Insert
    Completable addCharacter(CharacterEntity characterEntity);

}
