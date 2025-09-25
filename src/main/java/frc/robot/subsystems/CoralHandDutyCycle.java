// subsystems/CoralHand.java
package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

// import com.ctre.phoenix6.controls.DutyCycleOut;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CoralHandConstants;

public class CoralHandDutyCycle extends SubsystemBase {

    private final TalonFX m_coralHandMotor = new TalonFX(CoralHandConstants.kCoralHandCanID);
    // private final DigitalInput m_coralSensor = new DigitalInput(1);
   
    public CoralHandDutyCycle() {}

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

// sets speed as percent of max voltage from -1 to 1  ( open loop alternate to velo control)
    public void setSpeed(double kSpeed) {
        m_coralHandMotor.set(kSpeed);
    }
 
    // public boolean getSensorState() {
       //  return m_coralSensor.get();
    // }

    public void stopMotor() {
        m_coralHandMotor.stopMotor();
    }

  }
