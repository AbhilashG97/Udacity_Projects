package com.watermelonheart.shopmelon.base;

public interface Presenter<T extends MvpView> {

    void attachView(T mvpView);

    void detachView();

}
