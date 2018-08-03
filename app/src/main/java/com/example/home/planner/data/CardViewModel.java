package com.example.home.planner.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.home.planner.model.EarningCard;
import com.example.home.planner.model.Note;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CardViewModel extends AndroidViewModel {
    private EarningCardDao cardDao;
    private ExecutorService executorService;

    public CardViewModel(@NonNull Application application) {
        super(application);
        cardDao = LibDataBase.getInstance(getApplication()).getEarningCardDao();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<EarningCard>> getAllEarningCards() {
        return cardDao.getAllEarningCard();
    }

    public LiveData<List<Note>> getAllNotes() {
        return cardDao.getAllNotes();
    }

    public void saveCard(EarningCard card) {
        executorService.execute(() -> cardDao.saveEarningCard(card));
    }

    public void saveNote(Note note) {
        executorService.execute(() -> cardDao.saveNote(note));
    }

    public void deleteCard(long id) {
        executorService.execute(() -> cardDao.delete(id));
    }

    public void deleteNote(long id) {
        executorService.execute(() -> cardDao.deleteNote(id));
    }

    public void deleteAllCards() {
        executorService.execute(() -> cardDao.deleteAllCards());
    }
    public void deleteAllNotes() {
        executorService.execute(() -> cardDao.deleteAllNotes());
    }
}
