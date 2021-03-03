package com.example.rick_morty.presentation.viewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.rick_morty.data.repository.CharacterRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final CharacterRepository characterRepository;

    public ViewModelFactory(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(CharacterViewModel.class)) {
            return (T) new CharacterViewModel(characterRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
