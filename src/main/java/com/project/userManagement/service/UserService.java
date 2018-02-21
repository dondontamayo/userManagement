package com.project.userManagement.service;

import com.project.userManagement.data.entity.User;
import com.project.userManagement.data.repository.UserPagingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserService {

    private final UserPagingRepository userPagingRepository;

    public UserService(final UserPagingRepository userPagingRepository) {
        this.userPagingRepository = userPagingRepository;
    }

    public Page<User> getAllUsers(Pageable pageable) {
        return userPagingRepository.findAllBy(pageable);
    }

    public User getUserById(int id) {
        return userPagingRepository.findById(id);
    }

    public User createUser(User user) {
        return userPagingRepository.save(user);
    }

    public User updateUser(Integer id, User user) {
        User targetUser = userPagingRepository.findById(id);
        if (targetUser==null) {
            return null;
        } else {
            targetUser.setEmail(user.getEmail());
            targetUser.setName(user.getName());
            targetUser.setPassword(user.getPassword());
            targetUser.setUserName(user.getUserName());
            return userPagingRepository.save(targetUser);
        }
    }

    public void deleteUser(Integer id) {

        userPagingRepository.delete(id);
    }
}
