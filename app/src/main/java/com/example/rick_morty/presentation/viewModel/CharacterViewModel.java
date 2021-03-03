package com.example.rick_morty.presentation.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rick_morty.data.api.model.Character;
import com.example.rick_morty.data.entity.CharacterEntity;
import com.example.rick_morty.data.repository.CharacterRepository;
import com.example.rick_morty.presentation.characterDisplay.viewItem.CharacterDetailsViewItem;
import com.example.rick_morty.presentation.characterDisplay.viewItem.CharacterViewItem;
import com.example.rick_morty.presentation.characterDisplay.mapper.CharacterDetailsViewItemMapper;
import com.example.rick_morty.presentation.characterDisplay.mapper.CharacterEntityDetailsViewItemMapper;
import com.example.rick_morty.presentation.characterDisplay.mapper.CharacterEntityViewItemMapper;
import com.example.rick_morty.presentation.characterDisplay.mapper.CharacterViewItemMapper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

/**
 * Character view model to get element from api or local database
 */
public class CharacterViewModel extends ViewModel {

    private CharacterRepository characterRepository;
    private CompositeDisposable compositeDisposable;

    private CharacterViewItemMapper characterViewItemMapper;
    private CharacterDetailsViewItemMapper characterDetailsViewItemMapper;
    private CharacterEntityViewItemMapper characterEntityViewItemMapper;
    private CharacterEntityDetailsViewItemMapper characterEntityDetailsViewItemMapper;
    private MutableLiveData<List<CharacterViewItem>> characters;
    private MutableLiveData<List<CharacterDetailsViewItem>> character;

    public CharacterViewModel(CharacterRepository characterRepository){
        this.characterRepository = characterRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.characterViewItemMapper = new CharacterViewItemMapper();
        this.characterDetailsViewItemMapper = new CharacterDetailsViewItemMapper();
        this.characterEntityViewItemMapper = new CharacterEntityViewItemMapper();
        this.characterEntityDetailsViewItemMapper = new CharacterEntityDetailsViewItemMapper();
        this.characters = new MutableLiveData<>();
        this.character = new MutableLiveData<>();
    }

    public MutableLiveData<List<CharacterViewItem>> getCharacters(){
        return characters;
    }
    public MutableLiveData<List<CharacterDetailsViewItem>> getCharacter(){
        return character;
    }

    public void getAllCharacters(){
        compositeDisposable.clear();
        compositeDisposable.add(characterRepository.getAllCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Character>>() {

                    @Override
                    public void onSuccess(@NonNull List<Character> characterList) {
                        characters.setValue(characterViewItemMapper.map(characterList));
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                        //Yet, do not do nothing in this app

                        System.out.println(e.toString());
                        getAllCharactersFromBDD();

                    }
                }));
    }

    public void getAllCharactersFromBDD() {
        compositeDisposable.clear();
        compositeDisposable.add(characterRepository.getCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<CharacterEntity>>() {

                    @Override
                    public void onNext(List<CharacterEntity> characterEntities) {
                        characters.setValue(characterEntityViewItemMapper.map(characterEntities));
                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                        //Yet, do not do nothing in this app

                        System.out.println(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public void getCharacterById(int id){
        Log.i("DANS LE VIEW MODEL","Récupération du personnage id = " + id);
        compositeDisposable.clear();
        compositeDisposable.add(characterRepository.getCharacter(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Character>(){

                    @Override
                    public void onSuccess(@NonNull Character character) {
                        Log.i("DANS LE VIEW MODEL","Succès !");
                        try {
                            deleteCharacter(character.getId());
                        } catch (Exception e){}
                        addCharacter(character.getId());
                        List<CharacterDetailsViewItem> characterDetailsViewItemList = new ArrayList<>();
                        characterDetailsViewItemList.add(characterDetailsViewItemMapper.map(character));
                        CharacterViewModel.this.character.setValue(characterDetailsViewItemList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.e("DANS LE VIEW MODEL", "Une erreur s'est produite " + e.getMessage());
                        getCharacterByIdFromBDD(id);
                    }
                }));
    }


    public void getCharacterByIdFromBDD(int id) {
        compositeDisposable.clear();
        compositeDisposable.add(characterRepository.getACharacter(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<CharacterEntity>>() {

                    @Override
                    public void onNext(List<CharacterEntity> characterEntities) {
                        try {
                            character.setValue(characterEntityDetailsViewItemMapper.map(characterEntities));
                        } catch (Exception e) {
                            this.onError(e);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                        //Yet, do not do nothing in this app

                        System.out.println(e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public void addCharacter(final int charId){
        compositeDisposable.add(characterRepository.addCharacter(charId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {

                    @Override
                    public void onComplete() {
                        System.out.println("ADDED ID " + charId);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println(e.toString() + " / ADD " + charId);
                    }
                }));
    }

    public void deleteCharacter(final int charId){
        compositeDisposable.add(characterRepository.removeCharacter(charId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {

                    @Override
                    public void onComplete() {
                        System.out.println("DELETED ID " + charId);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        System.out.println(e.toString()+ " / DELETE " + charId);
                    }
                }));
    }
}