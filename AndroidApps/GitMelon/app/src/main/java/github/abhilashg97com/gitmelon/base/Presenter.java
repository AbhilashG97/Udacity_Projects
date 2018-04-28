package github.abhilashg97com.gitmelon.base;

public interface Presenter<T extends MvpView> {

    void attachView(T mvpView);

    void detachView();

}
