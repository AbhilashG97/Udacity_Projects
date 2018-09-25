package com.watermelonheart.petmelon.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;

@Dao
public interface PetDao {

    @Query("SELECT * FROM Pet")
    ArrayList<Pet> getAllPets();

    @Query("SELECT * FROM Pet WHERE _id = (:petId)")
    Pet getPet(int petId);

    @Insert
    void insertPet(Pet pet);

    @Delete
    void removePet(Pet pet);
}
