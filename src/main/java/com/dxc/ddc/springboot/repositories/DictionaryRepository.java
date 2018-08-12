package com.dxc.ddc.springboot.repositories;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dxc.ddc.springboot.data.domain.Dictionary;

/**
 *
 * @author Huanfeng, cai
 * @since JDK 1.8
 */
@Repository
@CacheConfig(cacheNames = "dictionary")
public interface DictionaryRepository extends JpaRepository<Dictionary, Long> {
	
	@Cacheable
	List<Dictionary> findByFieldOrderByCodeAsc(String field);
	
	@Override
	@Query
	@Cacheable
	List<Dictionary> findAll();
	
	@Cacheable
	Dictionary findByCode(String code);
	
	@Cacheable
	@Query("select d from Dictionary d where d.field = 'Attributes' and d.code != :code order by d.code")
	List<Dictionary> findByAttributesNotCodeQuery(@Param("code") String code);
}
