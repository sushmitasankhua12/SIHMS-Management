package com.sihm.SIHMSystem.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sihm.SIHMSystem.Model.Userdetails;

@Repository
public interface UserdetailsRepository extends JpaRepository<Userdetails, Long> {

	@Query("from Userdetails where username=:username and statusFlag=0")
	Userdetails findByusername(String username);

	Userdetails findByusernameIgnoreCase(String username);

	@Query("from Userdetails where email=:username and statusFlag=0")
	Userdetails findByEmail(String username);

	@Query("from Userdetails where email=:username and statusFlag=0")
	Userdetails findBymobile(String username);

}
