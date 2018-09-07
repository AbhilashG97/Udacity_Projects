package com.watermelonheart.shopmelon.base;

import com.watermelonheart.shopmelon.exceptions.MvpViewNotAttachedException;

public class BasePresenter<T extends  MvpView> implements Presenter<T> {

    private T mMvpView;

    @Override
    public void attachView(MvpView mvpView) {
        mMvpView = mMvpView;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }

    public T getMvpView() {
        return mMvpView;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public void checkViewAttached() {
        if(!isViewAttached()) throw new MvpViewNotAttachedException();
    }

}
