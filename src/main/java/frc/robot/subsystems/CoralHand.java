
// subsystems/CoralHand.java
package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.controls.VelocityVoltage;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CoralHandConstants;

public class CoralHand extends SubsystemBase {

    private final TalonFX m_coralHandMotor = new TalonFX(CoralHandConstants.kCoralHandCanID);
    private final VelocityVoltage m_velocityRequest = new VelocityVoltage(0).withSlot(0);

    private static final CurrentLimitsConfigs CURRENT_LIMITS_CONFIGS = new CurrentLimitsConfigs()
        .withSupplyCurrentLimit(40)
        .withStatorCurrentLimit(60);

    public CoralHand() {
        Slot0Configs slot0Configs = new Slot0Configs();
        slot0Configs.kP = CoralHandConstants.kCoralHandkP;
        slot0Configs.kI = CoralHandConstants.kCoralHandkI;
        slot0Configs.kD = CoralHandConstants.kCoralHandkD;
        slot0Configs.kV = CoralHandConstants.kCoralHandkV;
        m_coralHandMotor.getConfigurator().apply(slot0Configs);
        m_coralHandMotor.getConfigurator().apply(CURRENT_LIMITS_CONFIGS);
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

    public static CoralHand getInstance() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getInstance'");
    }
}
