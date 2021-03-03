package com.example.rick_morty.presentation.characterDisplay.mapper;

import com.example.rick_morty.data.entity.CharacterEntity;
import com.example.rick_morty.presentation.characterDisplay.viewItem.CharacterDetailsViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper for CharacterEntity to CharacterDetailsViewItem
 */
public class CharacterEntityDetailsViewItemMapper {

    public CharacterDetailsViewItem map(CharacterEntity characterEntity){
        CharacterDetailsViewItem characterDetailsViewItem = new CharacterDetailsViewItem();
        characterDetailsViewItem.setImage(characterEntity.getImage());
        characterDetailsViewItem.setName(characterEntity.getName());
        characterDetailsViewItem.setSpecies(characterEntity.getSpecies());
        characterDetailsViewItem.setStatus(characterEntity.getStatus());
        characterDetailsViewItem.setGender(characterEntity.getGender());

        return characterDetailsViewItem;
    }

    public List<CharacterDetailsViewItem> map(List<CharacterEntity> characterEntityList){
        List<CharacterDetailsViewItem> characterDetailsViewItems = new ArrayList<>();
        if(characterEntityList != null) {
            for (CharacterEntity c : characterEntityList) {
                characterDetailsViewItems.add(map(c));
            }
        }
        return characterDetailsViewItems;
    }
}
