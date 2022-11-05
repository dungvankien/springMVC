package com.cg.service.user;

import com.cg.model.User;
import com.cg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class UserServiceImpl implements IUserService{

    @Autowired
    UserRepository userRepository;
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
