package com.example.rick_morty.data.repository;

import com.example.rick_morty.data.db.CharacterDatabase;
import com.example.rick_morty.data.entity.CharacterEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * Repository for local database calls
 */
public class CharacterLocalDataSource {

    private CharacterDatabase characterDatabase;

    public CharacterLocalDataSource(CharacterDatabase characterDatabase){
        this.characterDatabase = characterDatabase;
    }

    public Flowable<List<CharacterEntity>> getCharacters(){
        return characterDatabase.characterDao().getCharacters();
    }

    public Flowable<List<CharacterEntity>> getACharacter(int id){
        return characterDatabase.characterDao().getACharacter(id);
    }

    public Completable addCharacter(CharacterEntity entity){
        return characterDatabase.characterDao().addCharacter(entity);
    }

    public Completable removeCharacter(int id){
        return characterDatabase.characterDao().deleteCharacter(id);
    }

}
