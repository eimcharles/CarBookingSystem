package user.dao;

import user.User;

import java.util.UUID;

/**
 *      UserDAO defines the contract for all data
 *      access operations related to User entities
 * */

public interface UserDAO {

    User[] getUsers();

    User getUserById(UUID id);
}
