package com.project.userManagement.data.repository;


import com.project.userManagement.data.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPagingRepository extends PagingAndSortingRepository<User, Integer>{

    Page<User> findAllBy(Pageable pageable);

    User findById(int id);

}
