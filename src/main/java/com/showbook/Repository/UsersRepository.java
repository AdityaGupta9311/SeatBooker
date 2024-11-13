package com.showbook.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.showbook.Modals.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {


	public Users findByEmail(String email);

}
