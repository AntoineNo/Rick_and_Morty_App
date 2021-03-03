package com.example.rick_morty.presentation.characterDisplay.mapper;

import com.example.rick_morty.data.api.model.Character;
import com.example.rick_morty.presentation.characterDisplay.viewItem.CharacterViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 *  Mapper for Character to CharacterViewItem
 */
public class CharacterViewItemMapper {

    public CharacterViewItem map(Character character){
        CharacterViewItem characterViewItem = new CharacterViewItem();
        characterViewItem.setCharacterId(character.getId());
        characterViewItem.setImage(character.getImage());
        characterViewItem.setName(character.getName());
        return characterViewItem;
    }

    public List<CharacterViewItem> map(List<Character> characterList){
        List<CharacterViewItem> characterViewItems = new ArrayList<>();
        if(characterList != null) {
            for (Character c : characterList) {
                characterViewItems.add(map(c));
            }
        }
        return characterViewItems;
    }
}
