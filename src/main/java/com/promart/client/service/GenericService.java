package com.promart.client.service;

import java.io.Serializable;
import java.util.List;

/**
 * Client Service Interface.
 *
 * @param <T>
 * @param <ID>
 * @author Christian Arias [chri.arias@gmail.com]
 */
public interface GenericService<T, ID extends Serializable> {

	T findById(ID id);
	
	T save(T entity);
	
	List<T> listAll();

}
