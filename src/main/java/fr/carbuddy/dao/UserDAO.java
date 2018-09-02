package fr.carbuddy.dao;

import java.util.List;

import fr.carbuddy.bean.User;
import fr.carbuddy.enumeration.order.by.UserOrderBy;
import fr.carbuddy.exception.DAORuntimeException;
import fr.carbuddy.exception.NotValidException;

public interface UserDAO {

    public User create(User user) throws DAORuntimeException, NotValidException;
    
    public User findByEmail(String email) throws DAORuntimeException;
    
    public User findById(Long id) throws DAORuntimeException;
    
    public List<User> listUser(UserOrderBy orderBy, boolean asc);
    
    public boolean updateUser(User userToUpdate, User newUser) throws DAORuntimeException;
    
    public boolean deleteUser(User userToDelete) throws DAORuntimeException;

	public User findByUsername(String username);
    
}
