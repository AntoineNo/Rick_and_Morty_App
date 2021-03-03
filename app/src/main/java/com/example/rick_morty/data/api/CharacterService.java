package com.example.rick_morty.data.api;

import com.example.rick_morty.data.api.model.Character;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * List of route API call
 */
public interface CharacterService {

    @GET("character/1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61")
    Single<List<Character>> getAllCharacters();

    @GET("character/{id}")
    Single<Character> getCharacter(@Path("id") int id);


}
