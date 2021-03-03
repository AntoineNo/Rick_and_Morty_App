package com.example.rick_morty.data.repository;

import com.example.rick_morty.data.api.CharacterService;
import com.example.rick_morty.data.api.model.Character;

import java.util.List;

import io.reactivex.Single;

/**
 * repository for API calls
 */
public class CharacterRemoteDataSource {

    private CharacterService characterService;

    public CharacterRemoteDataSource(CharacterService characterService){
        this.characterService = characterService;
    }

    public Single<List<Character>> getAllCharacters(){
        return this.characterService.getAllCharacters();
    }

    public Single<Character> getCharacter(int id){
        return this.characterService.getCharacter(id);
    }

}
