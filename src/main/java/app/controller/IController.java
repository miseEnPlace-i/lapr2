package app.controller;

public interface IController<T> {
  public void save();

  public String stringifyData();
}
