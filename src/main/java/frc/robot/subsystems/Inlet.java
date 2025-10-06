// subsystems/Inlet.java
package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.controls.VelocityVoltage;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.InletConstants;

public class Inlet extends SubsystemBase {

    private final TalonFX m_inletMotor = new TalonFX(InletConstants.kInletCanID);
    private final VelocityVoltage m_velocityRequest = new VelocityVoltage(0).withSlot(0);

    private static final CurrentLimitsConfigs CURRENT_LIMITS_CONFIGS = new CurrentLimitsConfigs()
        .withSupplyCurrentLimit(40)
        .withStatorCurrentLimit(60);

    public Inlet() {
        Slot0Configs slot0Configs = new Slot0Configs();
        slot0Configs.kP = InletConstants.kInletkP;
        slot0Configs.kI = InletConstants.kInletkI;
        slot0Configs.kD = InletConstants.kInletkD;
        slot0Configs.kV = InletConstants.kInletkV;
        m_inletMotor.getConfigurator().apply(slot0Configs);
        m_inletMotor.getConfigurator().apply(CURRENT_LIMITS_CONFIGS);
    }

    public void runInlet(double velocityRPM) {
        m_inletMotor.setControl(m_velocityRequest.withVelocity(velocityRPM));
    }

    public void stopMotor() {
        m_inletMotor.stopMotor();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
