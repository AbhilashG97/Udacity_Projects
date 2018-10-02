package com.watermelonheart.petmelon.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.watermelonheart.petmelon.data.Pet;
import com.watermelonheart.petmelon.data.PetDatabase;

public class PetProvider extends ContentProvider {

    public static final String AUTHORITY = "com.watermelonheart.petmelon";

    public static final Uri uri = Uri.parse("content://"+AUTHORITY+"/"+ Pet.TABLE_NAME);

    private static final UriMatcher MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    // Get a list of pets
    private static final int CODE_PET_DIR = 1;

    // Get a specific pet
    private static final int CODE_PET_ITEM = 2;

    static {
        MATCHER.addURI(AUTHORITY, Pet.TABLE_NAME, 2);
        MATCHER.addURI(AUTHORITY, Pet.TABLE_NAME+"/*", 1);
    }

    @Override
    public boolean onCreate() {
        return true;
    }


    @Nullable
    @Override
    public Cursor query( @NonNull Uri uri, @Nullable String[] strings, @Nullable String s,
                         @Nullable String[] strings1, @Nullable String s1) {

        final int code = MATCHER.match(uri);
        if(code == CODE_PET_DIR || code == CODE_PET_ITEM) {

            final Context context = getContext();
            if(context == null) {
                return null;
            }

            final Cursor cursor;
            PetDatabase database = PetDatabase.getInstance(context);

            if(code == CODE_PET_ITEM) {
                cursor = database.getPetDao().getPet(ContentUris.parseId(uri));

            } else {
                cursor  = database.getPetDao().getAllPetsThroughCursor();
            }
            cursor.setNotificationUri(context.getContentResolver(),uri);
            return cursor;
        } else {
            throw new IllegalArgumentException("Unknown Uri: "+uri);
        }

    }

    @Nullable
    @Override
    public String getType( @NonNull Uri uri) {
        switch (MATCHER.match(uri)) {
            case CODE_PET_DIR:
                return "vnd.android.cursor.dir/" + AUTHORITY + "." + Pet.TABLE_NAME;
            case CODE_PET_ITEM:
                return "vnd.android.cursor.item/" + AUTHORITY + "." + Pet.TABLE_NAME;
            default:
                throw new IllegalArgumentException("Unknown Uri : " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert( @NonNull Uri uri, @Nullable ContentValues contentValues) {

        switch (MATCHER.match(uri)) {
            case CODE_PET_DIR:
                final Context context = getContext();

                if (context == null) {
                    return null;
                }

                PetDatabase database = PetDatabase.getInstance(context);
                long id = database.getPetDao().insertPet(Pet.fromContentValues(contentValues));
                context.getContentResolver().notifyChange(uri, null);
                return ContentUris.withAppendedId(uri, id);
            case CODE_PET_ITEM:
                throw new IllegalArgumentException("Invalid Uri, cannot insert with ID : "+uri);
            default:
                throw new IllegalArgumentException("Unknown Uri : "+uri);
        }
    }

    @Override
    public int delete( @NonNull Uri uri,  @Nullable String s, @Nullable String[] strings) {
        switch (MATCHER.match(uri)) {
            case CODE_PET_ITEM:
                final Context context = getContext();
                if(context == null) {
                    return 0;
                }

                PetDatabase database = PetDatabase.getInstance(context);
                int count = database.getPetDao().deletePetById(ContentUris.parseId(uri));
                context.getContentResolver().notifyChange(uri, null);
                return count;
            case CODE_PET_DIR:
                throw new IllegalArgumentException("Invalid Uri, cannot update delete without ID "+uri);
            default:
                throw new IllegalArgumentException("Unknown Uri : "+uri);
        }
    }

    @Override
    public int update(@NonNull Uri uri,  @Nullable ContentValues contentValues,  @Nullable String s, @Nullable String[] strings) {
        switch (MATCHER.match(uri)) {
            case CODE_PET_ITEM:
                final Context context = getContext();
                if (context == null) {
                    return 0;
                }
                PetDatabase database = PetDatabase.getInstance(context);

                final Pet pet = Pet.fromContentValues(contentValues);
                pet.set_id(ContentUris.parseId(uri));
                int count = database.getPetDao().update(pet);
                context.getContentResolver().notifyChange(uri, null );
                return count;
            case CODE_PET_DIR:
                throw new IllegalArgumentException("Invalid Uri, cannot update without _id "+uri);
            default:
                throw new IllegalArgumentException("Unknown Uri : "+uri);
        }
    }
}
