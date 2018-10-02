package com.watermelonheart.petmelon.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.content.ContentValues;

@Entity
public class Pet {

    public static final String TABLE_NAME = "pet-db";

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = "_id")
    private long _id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "breed")
    private String breed;

    @ColumnInfo(name = "gender")
    private int gender;

    @ColumnInfo(name = "weight")
    private int weight;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * @param values this methods is used to store all the
     *               data in a pet object, so that it can be
     *               stored in the DB
     * @return returns a Pet object that contains all the
     * the values entered by the user
     */
    public static Pet fromContentValues(ContentValues values) {
        final Pet pet = new Pet();

        if (values.containsKey("name")) {
           pet.setName(values.getAsString("name"));
        }

        if (values.containsKey("breed")) {
            pet.setBreed(values.getAsString("breed"));
        }

        if (values.containsKey("gender")) {
            pet.setBreed(values.getAsString("gender"));
        }

        if (values.containsKey("weight")) {
            pet.setBreed(values.getAsString("weight"));
        }

        return pet;
    }

    @Override
    public String toString() {
        return get_id()+" "+getName()+" "+getBreed()+" "+getGender()+" "+getWeight()+"\n\n";
    }
}
