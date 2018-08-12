/**
 *
 * @author Huanfeng, cai
 * @since JDK 1.8
 */
package com.dxc.ddc.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.dxc.ddc.springboot.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@ActiveProfiles("dev")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase()
@Slf4j
public class UserRepositoryTest {
	   @Autowired
	   private TestEntityManager entityManager;

	   @Autowired
	   private UserRepository userRepository;
	   
	   @Test
	   public void whenFindAll() {
		   
		   log.debug("##### entityManager={}", entityManager);
		   
//	       //given
//	       Arrival firstArrival = new Arrival();
//	       firstArrival.setCity("Yerevan");
//	       entityManager.persist(firstArrival);
//	       entityManager.flush();
//
//	       Arrival secondArrival = new Arrival();
//	       secondArrival.setCity("Israel");
//	       entityManager.persist(secondArrival);
//	       entityManager.flush();
//
//	       //when
//	       List<Arrival> arrivals = arrivalRepository.findAll();
//
//	       //then
//	       assertThat(arrivals.size()).isEqualTo(9);
//	       assertThat(arrivals.get(7)).isEqualTo(firstArrival);
//	       assertThat(arrivals.get(8)).isEqualTo(secondArrival);
	   }
}
