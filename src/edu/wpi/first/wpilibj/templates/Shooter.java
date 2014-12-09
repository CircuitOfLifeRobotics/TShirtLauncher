/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Relay;

public class Shooter {

    private final Relay[] relays;
    
    private int current = 0;
    
    public Shooter(int aPort, int bPort, int cPort) {
        relays = new Relay[3];
        relays[0] = new Relay(aPort);
        relays[1] = new Relay(bPort);
        relays[2] = new Relay(cPort);
    }
    
    public void setBarrel(int barrel) {
        current = barrel % 3;
    }
    
    public int getBarrel() {
        return current;
    }
    
    private boolean lastBtn = false;
    public void update(boolean fireBtn) {
        if (fireBtn != lastBtn) {
            if (fireBtn) {
                relays[current].set(Relay.Value.kForward);
            } else {
                relays[current].set(Relay.Value.kOff);
                current = (current + 1) % 3;
            }
            lastBtn = fireBtn;
        }
    }
    
}
