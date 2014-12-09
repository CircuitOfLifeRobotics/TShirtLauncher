/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
 
public class Button {
    private final Joystick stick;
    private final int btn;
    
    private boolean laststate = false;
    
    public Button(Joystick stick, int btn) {
        this.stick = stick;
        this.btn = btn;
    }
    
    public boolean check() {
        boolean state = stick.getRawButton(btn);
        boolean ret = state != laststate && state;
        laststate = state;
        return ret;
    }
            
}
