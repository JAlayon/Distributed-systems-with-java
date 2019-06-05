/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redpic.services;

import RedPic.*;
import com.redpic.utils.User;
import com.redpic.utils.User;
import javax.swing.JOptionPane;

/**
 *
 * @author alayon
 */
public class CorbaClient extends RedPicClientPOA{

    private User user;
    
    public CorbaClient(User user){
        this.user = user;
    } 
    
    @Override
    public void update(String img, String username) {
        JOptionPane.showMessageDialog(null, username + " ha subido una nueva imagen: " + img, "Mensaje", JOptionPane.INFORMATION_MESSAGE);
        System.out.println();
    }

    @Override
    public String getEmail() {
        return user.getEmail();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }
}
