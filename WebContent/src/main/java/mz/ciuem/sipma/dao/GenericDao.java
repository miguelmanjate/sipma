package mz.ciuem.sipma.dao;

import java.util.List;

import mz.ciuem.sipma.interfaces.Entity;

public interface GenericDao<T extends Entity> {

	public long count();

	public T create(T t);

	public void delete(Object id);

	public T find(Object id);

	public List<T> getAll();

	public T update(T t);

	public T first();

	public T last();

}
