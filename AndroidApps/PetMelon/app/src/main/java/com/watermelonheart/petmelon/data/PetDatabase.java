package com.watermelonheart.petmelon.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Pet.class}, version = 1, exportSchema = false)
public abstract class PetDatabase extends RoomDatabase {

    private static PetDatabase INSTANCE;

    public abstract PetDao getPetDao();

    public static synchronized PetDatabase getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = PetDatabase.buildDatabase(context);
        }
        return INSTANCE;
    }

    private static PetDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context.getApplicationContext(), PetDatabase.class,
                Pet.TABLE_NAME).build();
    }
}
