package com.example.rick_morty.data.repository;

import com.example.rick_morty.data.api.model.Character;
import com.example.rick_morty.data.entity.CharacterEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Repository to choose API call or local database call
 */
public class CharacterDataRepository implements CharacterRepository {

    private CharacterRemoteDataSource characterRemoteDataSource;
    private CharacterLocalDataSource characterLocalDataSource;
    private CharacterToCharacterEntity characterToCharacterEntity;

    public CharacterDataRepository(CharacterRemoteDataSource characterRemoteDataSource, CharacterLocalDataSource characterLocalDataSource){
        this.characterRemoteDataSource = characterRemoteDataSource;
        this.characterLocalDataSource = characterLocalDataSource;
        this.characterToCharacterEntity = new CharacterToCharacterEntity();
    }

    @Override
    public Single<List<Character>> getAllCharacters() {
        return this.characterRemoteDataSource.getAllCharacters();
    }

    @Override
    public Single<Character> getCharacter(int id) {
        return this.characterRemoteDataSource.getCharacter(id);
    }

    @Override
    public Flowable<List<CharacterEntity>> getCharacters() {
        return this.characterLocalDataSource.getCharacters();
    }

    @Override
    public Flowable<List<CharacterEntity>> getACharacter(int id) {
        return this.characterLocalDataSource.getACharacter(id);
    }

    @Override
    public Completable addCharacter(int id) {
        return characterRemoteDataSource.getCharacter(id)
                .map(new Function<Character, CharacterEntity>() {
                    @Override
                    public CharacterEntity apply(@NonNull Character character) throws Exception {
                        return characterToCharacterEntity.map(character);
                    }
                }).flatMapCompletable(new Function<CharacterEntity, CompletableSource>() {
                    @Override
                    public CompletableSource apply(@NonNull CharacterEntity characterEntity) throws Exception {
                        return characterLocalDataSource.addCharacter(characterEntity);
                    }
                });
    }

    @Override
    public Completable removeCharacter(int id) {
        return characterLocalDataSource.removeCharacter(id);
    }

}
