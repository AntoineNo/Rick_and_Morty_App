package com.example.rick_morty.presentation.characterDisplay.viewItem;

/**
 * Class to specify info on details screen
 */
public class CharacterDetailsViewItem {

    private String image;
    private String name;
    private String species;
    private String status;
    private String gender;

    public CharacterDetailsViewItem() {
        this.image = "";
        this.name = "";
        this.species = "";
        this.status = "";
        this.gender = "";
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return " " + name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return " " + species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getStatus() {
        return " " + status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() { return " " + gender; }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
