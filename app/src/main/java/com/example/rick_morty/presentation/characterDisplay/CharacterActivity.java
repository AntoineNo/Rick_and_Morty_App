package com.example.rick_morty.presentation.characterDisplay;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.rick_morty.data.di.DependencyInjection;
import com.example.rick_morty.presentation.characterDisplay.viewItem.CharacterDetailsViewItem;
import com.example.rick_morty.presentation.viewModel.CharacterViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rick_morty.R;

import java.util.List;

/**
 * Activity to display character details
 */
public class CharacterActivity extends AppCompatActivity {

    private int Id;
    private ImageView imageView;
    private TextView name;
    private TextView species;
    private TextView status;
    private TextView gender;
    private CharacterViewModel characterViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.character_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#42FF00'>Detail</font>"));

        Intent i = getIntent();
        this.Id = i.getIntExtra("CharacterId", 1);

        registerViewModel();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void registerViewModel() {
        characterViewModel = new ViewModelProvider(this, DependencyInjection.getViewModelFactory()).get(CharacterViewModel.class);
        characterViewModel.getCharacter().observe(this, new Observer<List<CharacterDetailsViewItem>>() {
            @Override
            public void onChanged(List<CharacterDetailsViewItem> characterDetailsViewItemList) {
                setLayout(characterDetailsViewItemList.get(0));
            }
        });
        characterViewModel.getCharacterById(Id);

    }

    private void setLayout(CharacterDetailsViewItem character) {

        imageView = findViewById(R.id.character_image);
        name = findViewById(R.id.character_name);
        species = findViewById(R.id.character_species);
        status = findViewById(R.id.character_status);
        gender = findViewById(R.id.character_gender);
        String unknown = " Unknown";

        if (character.getName() == null) name.append(unknown);
        else name.append(character.getName());

        if (character.getSpecies() == null) species.append(unknown);
        else species.append(character.getSpecies());

        if (character.getStatus() == null) status.append(unknown);
        else status.append(character.getStatus());

        if (character.getGender() == null) gender.append(unknown);
        else gender.append(character.getGender());

        Glide.with(this)
                .load(character.getImage())
                .into(imageView);
    }
}