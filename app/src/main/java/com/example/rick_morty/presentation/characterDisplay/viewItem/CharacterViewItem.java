package com.example.rick_morty.presentation.characterDisplay.viewItem;

/**
 * Class to specify info for grid or list screen
 */
public class CharacterViewItem {

    private int characterId;
    private String image;
    private String name;

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
