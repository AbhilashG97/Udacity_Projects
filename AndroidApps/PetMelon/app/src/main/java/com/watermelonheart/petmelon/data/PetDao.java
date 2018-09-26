package com.watermelonheart.petmelon.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface PetDao {

    @Query("SELECT * FROM Pet")
    List<Pet> getAllPets();

    @Query("SELECT * FROM Pet WHERE _id = (:petId)")
    Pet getPet(int petId);

    @Query("SELECT COUNT(_id) FROM Pet")
    int getTotalPets();

    @Insert
    void insertPet(Pet pet);

    @Delete
    void removePet(Pet pet);
}
