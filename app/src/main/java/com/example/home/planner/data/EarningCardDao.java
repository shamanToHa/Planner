package com.example.home.planner.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.home.planner.model.EarningCard;
import com.example.home.planner.model.Note;

import java.util.List;

@Dao
public interface EarningCardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveEarningCard(EarningCard... earningCard);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveNote(Note... notes);

    @Query("DELETE FROM earningcard WHERE id = :cardID")
    void delete(long cardID);

    @Query("DELETE FROM note WHERE id = :noteID")
    void deleteNote(long noteID);

    @Query("SELECT * FROM earningcard")
    LiveData<List<EarningCard>> getAllEarningCard();

    @Query("SELECT * FROM note")
    LiveData<List<Note>> getAllNotes();

    @Query("DELETE FROM earningcard")
    void deleteAllCards();

    @Query("DELETE FROM note")
    void deleteAllNotes();
}
