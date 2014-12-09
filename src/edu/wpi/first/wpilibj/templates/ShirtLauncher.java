/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class ShirtLauncher extends IterativeRobot {

    RobotDrive drive;
    Joystick xbox;
    Button solenoidBtn;
    Button[] barrelButtons;
    Shooter shooter;
    Compressor compressor;
    Solenoid first, second;

    public void robotInit() {
        xbox = new Joystick(1);
        
        drive = new RobotDrive(1, 2);
        drive.setMaxOutput(.5);
        
        shooter = new Shooter(1, 2, 3);
        
        compressor = new Compressor(1, 4);
        compressor.start();
        
        first = new Solenoid(1);
        second = new Solenoid(2);
        
        solenoidBtn = new Button(xbox, XboxButtonMap.LB);
        
        barrelButtons = new Button[3];
        barrelButtons[0] = new Button(xbox, XboxButtonMap.X);
        barrelButtons[1] = new Button(xbox, XboxButtonMap.Y);
        barrelButtons[2] = new Button(xbox, XboxButtonMap.B);
        
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {

    }

    boolean solenoid = false;
    boolean laststate = false;

    public void teleopPeriodic() {
        // tilt
        if (solenoidBtn.check()) {
            solenoid = !solenoid;
        }
        first.set(solenoid);
        second.set(!solenoid);

        // shooter
        for (int b = 0; b < barrelButtons.length; b++) {
            if (barrelButtons[b].check())
                shooter.setBarrel(b);
        }
        
        shooter.update(xbox.getRawButton(XboxButtonMap.A));
        
        // turbo
        boolean state = xbox.getRawButton(XboxButtonMap.RB);
        if (state != laststate) {
            drive.setMaxOutput(state ? 1 : .5);
            laststate = state;
        }

        // drivetrain
        drive.arcadeDrive(xbox.getRawAxis(2), xbox.getRawAxis(4));
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {

    }

}
