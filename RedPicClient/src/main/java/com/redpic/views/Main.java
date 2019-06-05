package com.redpic.views;

import com.redpic.services.ApiRest;

/**
 *
 * @author alayon
 */
public class Main {
    
    public static void main(String[] args) {
        ApiRest.setServer("localhost");
        new LoginForm().setVisible(true);
    }
}
