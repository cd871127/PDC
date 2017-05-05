package com.anthony.common;

import com.anthony.user.dto.UserDTO;

import java.util.HashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by CHENDONG239 on 2017-04-27.
 */
public class UserManager {
    private static UserManager instance = new UserManager();
    private ReadWriteLock rwl = new ReentrantReadWriteLock();
    private HashMap<String, UserDTO> userMap = new HashMap<>();

    private UserManager() {
    }


    public static UserManager getInstance() {
        return instance;
    }

    public HashMap<String, UserDTO> getUserMap() {
        return userMap;
    }

    public void addUser(UserDTO userDTO) {
        rwl.writeLock().lock();
        userMap.put(userDTO.getToken(), userDTO);
        rwl.writeLock().unlock();
    }

    public void removeUser(UserDTO userDTO) {
        rwl.writeLock().lock();
        userMap.remove(userDTO.getToken());
        rwl.writeLock().unlock();
    }

    public UserDTO getUserByToken(String token) {
        rwl.readLock().lock();
        UserDTO userDTO = userMap.get(token);
        rwl.readLock().unlock();
        return userDTO;
    }
}
