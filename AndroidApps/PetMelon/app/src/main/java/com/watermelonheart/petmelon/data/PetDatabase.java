package com.watermelonheart.petmelon.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Pet.class}, version = 1)
public abstract class PetDatabase extends RoomDatabase {

    public abstract PetDao getPetDao();

}
