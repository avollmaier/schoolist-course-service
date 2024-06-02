package at.schoolist.courseservice.domain.service;

import at.schoolist.courseservice.domain.exception.ResourceNotFoundException;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class CrudService<T, U> implements ICrudService<T, U> {

  protected final JpaRepository<T, U> repository;

  protected CrudService(JpaRepository<T, U> repository) {
    this.repository = repository;
  }

  @Override
  public List<T> getAll() {
    return repository.findAll();
  }

  @Override
  public Boolean existsById(U id) {
    return repository.existsById(id);
  }

  @Override
  public T getById(U id) {
    return repository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Resource not found with id: " + id));
  }

  @Override
  public T create(T entity) {
    return repository.save(entity);
  }

  @Override
  public T update(U id, T entity) {
    if (!repository.existsById(id)) {
      throw new ResourceNotFoundException("Resource not found with id: " + id);
    }
    return repository.save(entity);
  }

  @Override
  public void delete(U id) {
    repository.deleteById(id);
  }
}