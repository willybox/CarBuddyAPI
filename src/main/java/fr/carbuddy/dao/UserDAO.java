package fr.carbuddy.dao;

import java.util.Set;

import fr.carbuddy.bean.User;
import fr.carbuddy.exception.DAORuntimeException;

public interface UserDAO {

    public void create(User user) throws DAORuntimeException;
    
    public Set<User> findByEmail(String email) throws DAORuntimeException;
    
    public Set<User> findByName(String name) throws DAORuntimeException;
    
    public User findById(Long id) throws DAORuntimeException;
    
    public boolean updateUser(User userToUpdate, User newUser) throws DAORuntimeException;
    
    public boolean deleteUser(User userToDelete) throws DAORuntimeException;
    
}
