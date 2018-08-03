package com.example.home.planner.ui.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.home.planner.R;
import com.example.home.planner.data.CardViewModel;
import com.example.home.planner.model.EarningCard;
import com.example.home.planner.model.Note;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateCardActivity extends AppCompatActivity {

    @BindView(R.id.create_title)
    EditText title;
    @BindView(R.id.create_amount)
    EditText amount;
    @BindView(R.id.create_message)
    EditText message;
    @BindView(R.id.check_earning)
    RadioButton checkEarning;
    @BindView(R.id.check_note)
    RadioButton checkNote;
    @BindView(R.id.check_group)
    RadioGroup checkGroup;
    private CardViewModel viewModel;

    public static void start(Context context) {
        Intent intent = new Intent(context, CreateCardActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_card);
        ButterKnife.bind(this);
        viewModel = ViewModelProviders.of(this).get(CardViewModel.class);
        checkGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.check_note) {
                amount.setVisibility(View.GONE);
                message.setVisibility(View.VISIBLE);
            } else {
                amount.setVisibility(View.VISIBLE);
                message.setVisibility(View.GONE);
            }
        });
    }


    @OnClick(R.id.create_button)
    void createCard() {
        if (checkEarning.isChecked()) {
            EarningCard card = new EarningCard();
            card.setTitle(title.getText().toString());
            if (amount.getText().length() != 0) {
                card.setAmount(Double.parseDouble(amount.getText().toString()));
            }
            viewModel.saveCard(card);
            MainActivity.start(this);
        } else if (checkNote.isChecked()) {
            Note note = new Note();
            note.setTitle(title.getText().toString());
            note.setMessage(String.valueOf(message.getText()));
            viewModel.saveNote(note);
            MainActivity.start(this);
        }
    }
}
