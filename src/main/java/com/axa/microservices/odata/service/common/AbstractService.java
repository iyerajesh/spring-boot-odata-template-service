package com.axa.microservices.odata.service.common;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.axa.microservices.odata.persistence.IOperations;
import com.google.common.collect.Lists;

/*
 * @author Rajesh Iyer
 */

@Transactional
public abstract class AbstractService<T extends Serializable> implements IOperations<T> {

	// read - one

	@Override
	@Transactional(readOnly = true)
	public T findOne(final int id) {
		return getDao().findOne(id);
	}

	// read - all

	@Override
	@Transactional(readOnly = true)
	public List<T> findAll() {
		return Lists.newArrayList(getDao().findAll());
	}

	@Override
	public Page<T> findPaginated(final int page, final int size) {
		return getDao().findAll(new PageRequest(page, size));
	}

	// write

	@Override
	public T create(final T entity) {
		return getDao().save(entity);
	}

	@Override
	public T update(final T entity) {
		return getDao().save(entity);
	}

	@Override
	public void delete(final T entity) {
		getDao().delete(entity);
	}

	@Override
	public void deleteById(final int entityId) {
		getDao().delete(entityId);
	}

	protected abstract PagingAndSortingRepository<T, Integer> getDao();

}
