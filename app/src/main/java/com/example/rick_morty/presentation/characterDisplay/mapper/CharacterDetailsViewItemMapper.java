package com.example.rick_morty.presentation.characterDisplay.mapper;

import com.example.rick_morty.data.api.model.Character;
import com.example.rick_morty.presentation.characterDisplay.viewItem.CharacterDetailsViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper for Character to CharacterDetailsViewItem
 */
public class CharacterDetailsViewItemMapper {

    public CharacterDetailsViewItem map(Character character){
        CharacterDetailsViewItem characterDetailsViewItem = new CharacterDetailsViewItem();
        characterDetailsViewItem.setImage(character.getImage());
        characterDetailsViewItem.setName(character.getName());
        characterDetailsViewItem.setSpecies(character.getSpecies());
        characterDetailsViewItem.setStatus(character.getStatus());
        characterDetailsViewItem.setGender(character.getGender());

        return characterDetailsViewItem;
    }
}
