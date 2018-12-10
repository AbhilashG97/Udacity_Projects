# AgeraMelon

This is simple application built with [Agera](https://github.com/google/agera).

:warning: This app is not complete due to [this](https://github.com/google/agera/issues/171) issue.

## Theory 

### Basics 

The foundation of Agera is a set of very simple interfaces including: 

1. ```Observable``` 
Something that can be observed for changes and stores a list of updatable objects. When a change occurs, the updatables are poked.

```java
public interface Observable {

  void addUpdatable(Updatable updatable);

  void removeUpdatable(Updatable updatable);
}
```

2. ```Updatable```
Called when an event has occurred. Its update() method will be called when the class it observes changes, but can also be called manually to force an update.

```java
public interface Updatable {

  void update();
}
```

3. ```Supplier```
Something that supplies data when the get() method is called. In this case, "Data" can be anything.

```java
public interface Supplier<T> {

  T get();
}
```

4. ```Receiver```
Something that can receive (and normally store) a value send to it via accept().

```java
public interface Receiver<T> {

  void accept(T value);
}
```

