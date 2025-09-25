// subsystems/CoralShoulder.java
package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CoralShoulderConstants;

public class CoralShoulder extends SubsystemBase {

    private final TalonFX m_coralShoulderMotor = new TalonFX(CoralShoulderConstants.kCoralShoulderCanID);
    private final MotionMagicVoltage m_motionMagicRequest = new MotionMagicVoltage(0).withSlot(0);

    public CoralShoulder() {
        // Configure Motion Magic settings
        MotionMagicConfigs motionMagicConfigs = new MotionMagicConfigs();
        motionMagicConfigs.MotionMagicCruiseVelocity = CoralShoulderConstants.kCoralShoulderCruiseVelocityRPM;
        motionMagicConfigs.MotionMagicAcceleration = CoralShoulderConstants.kCoralShoulderAccelerationRPM;
        m_coralShoulderMotor.getConfigurator().apply(motionMagicConfigs);

        // Configure PID gains for Motion Magic
        Slot0Configs slot0Configs = new Slot0Configs();
        slot0Configs.kP = CoralShoulderConstants.kCoralShoulderkP;
        slot0Configs.kI = CoralShoulderConstants.kCoralShoulderkI;
        slot0Configs.kD = CoralShoulderConstants.kCoralShoulderkD;
        m_coralShoulderMotor.getConfigurator().apply(slot0Configs);
    }

    public void setCoralShoulder(double angleDegrees) {
        double motorRotations = (angleDegrees / 360.0) * CoralShoulderConstants.kGearRatio;
        m_coralShoulderMotor.setControl(m_motionMagicRequest.withPosition(motorRotations));
    }

    public void stopMotor() {
        m_coralShoulderMotor.stopMotor();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}

