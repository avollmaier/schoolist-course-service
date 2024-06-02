package at.schoolist.courseservice.domain.service;

import java.util.List;

public interface ICrudService<T, U> {
  List<T> getAll();

  T getById(U id);

  Boolean existsById(U id);

  T create(T entity);

  T update(U id, T entity);

  void delete(U id);
}