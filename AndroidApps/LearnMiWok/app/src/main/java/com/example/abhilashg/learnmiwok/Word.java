package com.example.abhilashg.learnmiwok;

/**
 * Created by abhilashg on 22/1/17.
 */

public class Word {

    private String mDefaultTranslation ;
    private int mImageResourceId ;
    private String mMikwokTraslation;

    private static final int NO_IMAGE_PROVIDED = -1;
    public Word (String defaultTranslation, String miwokTranslation)
    {
        mDefaultTranslation = defaultTranslation;
        mMikwokTraslation = miwokTranslation;
    }

    public Word (String defaultTranslation, String miwokTranslation, int ImageResourceId)
    {
        mDefaultTranslation = defaultTranslation;
        mMikwokTraslation = miwokTranslation;
        mImageResourceId = ImageResourceId;
    }

    public String getDefaultTranslation()
    {
        return mDefaultTranslation;
    }

    public String getMiwokTranslation()
    {
        return mMikwokTraslation;
    }

    public int getImageResourceId() {return mImageResourceId;}

    public boolean hasImage ()
    {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
}
