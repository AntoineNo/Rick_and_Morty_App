package com.example.rick_morty.presentation.characterDisplay.mapper;

import com.example.rick_morty.data.entity.CharacterEntity;
import com.example.rick_morty.presentation.characterDisplay.viewItem.CharacterViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper for CharacterEntity to CharacterViewItem
 */
public class CharacterEntityViewItemMapper {

    public CharacterViewItem map(CharacterEntity characterEntity){
        CharacterViewItem characterViewItem = new CharacterViewItem();
        characterViewItem.setImage(characterEntity.getImage());
        characterViewItem.setName(characterEntity.getName());
        return characterViewItem;
    }

    public List<CharacterViewItem> map(List<CharacterEntity> characterEntityList){
        List<CharacterViewItem> characterViewItems = new ArrayList<>();
        if(characterEntityList != null) {
            for (CharacterEntity c : characterEntityList) {
                characterViewItems.add(map(c));
            }
        }
        return characterViewItems;
    }
}
