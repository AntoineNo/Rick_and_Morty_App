package com.example.rick_morty.data.repository;

import com.example.rick_morty.data.api.model.Character;
import com.example.rick_morty.data.entity.CharacterEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Interface for character repository
 */
public interface CharacterRepository {

    Single<List<Character>> getAllCharacters();

    Single<Character> getCharacter(int id);

    Flowable<List<CharacterEntity>> getCharacters();

    Flowable<List<CharacterEntity>> getACharacter(int id);

    Completable addCharacter(int id);

    Completable removeCharacter(int id);
}
