package com.GF.verbum.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.GF.verbum.DB.Entities.palfraEntity;


@Database(entities = palfraEntity.class,version = 1 ,exportSchema = false)
public  abstract  class palaFraRoomDataBase extends RoomDatabase {
}
