package com.dxc.ddc.springboot.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dxc.ddc.springboot.data.domain.User;

/**
 *
 * @author Huanfeng, cai
 * @since JDK 1.8
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

	@Query("select u from User u where lower(u.loginId) like concat('%', lower(:keyword), '%') or lower(u.displayName) like concat('%', lower(:keyword), '%') or lower(u.description) like concat('%', lower(:keyword), '%') order by u.updateDate desc, u.loginId asc, u.displayName asc")
    Page<User> findByKeywordPage(@Param("keyword") String keyword, Pageable pageable);

}
