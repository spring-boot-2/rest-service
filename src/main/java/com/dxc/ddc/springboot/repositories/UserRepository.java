package com.dxc.ddc.springboot.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dxc.ddc.springboot.data.domain.User;

/**
 *
 * @author Huanfeng, cai
 * @since JDK 1.8
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Page<User> findByLoginIdIgnoreCaseContainingOrDisplayNameIgnoreCaseContaining(String loginId, String displayName, Pageable pageable);

}
