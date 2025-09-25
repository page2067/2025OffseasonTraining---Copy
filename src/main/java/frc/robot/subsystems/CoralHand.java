// subsystems/CoralHand.java
package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.controls.VelocityVoltage;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CoralHandConstants;

public class CoralHand extends SubsystemBase {

    private final TalonFX m_coralHandMotor = new TalonFX(CoralHandConstants.kCoralHandCanID);
    private final VelocityVoltage m_velocityRequest = new VelocityVoltage(0).withSlot(0);

    public CoralHand() {
        Slot0Configs slot0Configs = new Slot0Configs();
        slot0Configs.kP = CoralHandConstants.kCoralHandkP;
        slot0Configs.kI = CoralHandConstants.kCoralHandkI;
        slot0Configs.kD = CoralHandConstants.kCoralHandkD;
        slot0Configs.kV = CoralHandConstants.kCoralHandkV;
        m_coralHandMotor.getConfigurator().apply(slot0Configs);
    }

    public void runCoralHand(double velocityRPM) {
        m_coralHandMotor.setControl(m_velocityRequest.withVelocity(velocityRPM));
    }

    public void stopMotor() {
        m_coralHandMotor.stopMotor();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
