package com.watermelonheart.petmelon.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import java.util.List;

@Dao
public interface PetDao {

    @Query("SELECT * FROM Pet")
    List<Pet> getAllPets();

    @Query("SELECT * FROM Pet")
    Cursor getAllPetsThroughCursor();

    @Query("SELECT * FROM Pet WHERE _id = (:petId)")
    Cursor getPet(long petId);

    @Query("SELECT COUNT(_id) FROM Pet")
    int getTotalPets();

    @Query("DELETE FROM Pet WHERE _id = (:petId)")
    int deletePetById(long petId);

    @Query("DELETE FROM Pet")
    void removeAllPets();

    @Insert
    long insertPet(Pet pet);

    @Delete
    int removePet(Pet pet);

    @Update
    int update(Pet pet);
}
