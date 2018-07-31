package com.example.home.planner.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.home.planner.model.EarningCard;

import java.util.List;

@Dao
public interface EarningCardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAllCards(List<EarningCard> earningCards);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveEarningCard(EarningCard... earningCard);

    @Update
    void update(EarningCard card);

    @Delete
    void delete(EarningCard earningCard);

    @Query("SELECT * FROM earningcard")
    LiveData<List<EarningCard>> getAllEarningCard();

    @Query("DELETE FROM earningcard")
    void deleteAll();
}
