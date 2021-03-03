package com.example.rick_morty.data.repository;

import com.example.rick_morty.data.api.model.Character;
import com.example.rick_morty.data.entity.CharacterEntity;

/**
 * Mapper Character to CharacterEntity for local database
 */
public class CharacterToCharacterEntity {

    public CharacterEntity map(Character character){
        CharacterEntity characterEntity = new CharacterEntity();

        characterEntity.setId(character.getId());
        characterEntity.setImage(character.getImage());
        characterEntity.setName(character.getName());
        characterEntity.setSpecies(character.getSpecies());
        characterEntity.setStatus(character.getStatus());
        characterEntity.setGender(character.getGender());

        return characterEntity;
    }
}
