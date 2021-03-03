package com.example.rick_morty.presentation.characterDisplay;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import androidx.recyclerview.widget.RecyclerView;

import com.example.rick_morty.R;
import com.example.rick_morty.presentation.characterDisplay.viewItem.CharacterViewItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter of recyclerView to display Character
 */
public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    private List<CharacterViewItem> characterViewItemList;
    private CharacterInterface characterInterface;
    private boolean asList;


    public static class CharacterViewHolder extends RecyclerView.ViewHolder {
        private ImageButton characterImageButton;
        private TextView nameTextView;
        private View view;
        private CharacterViewItem characterViewItem;
        private CharacterInterface characterInterface;
        private boolean isList;

        public CharacterViewHolder(View view, final CharacterInterface characterInterface, boolean isList) {
            super(view);
            this.view = view;
            this.characterInterface = characterInterface;
            this.isList = isList;
            if(isList){
                this.characterImageButton = this.view.findViewById(R.id.character_nameList_image);
                this.nameTextView = this.view.findViewById(R.id.character_textView);
            } else {
                this.characterImageButton = view.findViewById(R.id.character_image_grid);
            }
            setupListener(isList);

        }

        private void setupListener(boolean isList) {
            characterImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    characterInterface.onCharacterClicked(characterViewItem.getCharacterId());
                }
            });
            if (isList) {
                nameTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        characterInterface.onCharacterClicked(characterViewItem.getCharacterId());
                    }
                });
            }
        }

        public void bind(CharacterViewItem viewItem){
            this.characterViewItem = viewItem;
            Glide.with(view)
                    .load(viewItem.getImage())
                    .into(characterImageButton);

            if(isList){
                this.nameTextView.setText(characterViewItem.getName());
            }
        }
        
        public ImageButton getImageButton() {
            return characterImageButton;
        }
    }

    public CharacterAdapter(CharacterInterface characterInterface, boolean asList) {
        characterViewItemList = new ArrayList<>() ;
        this.characterInterface = characterInterface;
        this.asList = asList;
    }

    public void bindViewModels(List<CharacterViewItem> characterViewItemList){
        this.characterViewItemList.clear();
        this.characterViewItemList.addAll(characterViewItemList);
        notifyDataSetChanged();
    }

    @Override
    public CharacterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(asList ? R.layout.character_listview : R.layout.character_image, viewGroup, false);

        return new CharacterViewHolder(view, characterInterface, asList);
    }

    @Override
    public void onBindViewHolder(CharacterViewHolder holder, final int position) {

        holder.bind(characterViewItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return characterViewItemList.size();
    }


}
