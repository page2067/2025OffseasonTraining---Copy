// subsystems/Elevator.java
package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.controls.MotionMagicVoltage;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ElevatorConstants;

public class Elevator extends SubsystemBase {

    private final TalonFX m_elevatorMotor = new TalonFX(ElevatorConstants.kElevatorCanID);
    private final MotionMagicVoltage m_motionMagicRequest = new MotionMagicVoltage(0).withSlot(0);

    public Elevator() {
        // Configure Motion Magic settings
        MotionMagicConfigs motionMagicConfigs = new MotionMagicConfigs();
        motionMagicConfigs.MotionMagicCruiseVelocity = ElevatorConstants.kElevatorCruiseVelocityRPM;
        motionMagicConfigs.MotionMagicAcceleration = ElevatorConstants.kElevatorAccelerationRPM;
        m_elevatorMotor.getConfigurator().apply(motionMagicConfigs);

        // Configure PID gains for Motion Magic
        Slot0Configs slot0Configs = new Slot0Configs();
        slot0Configs.kP = ElevatorConstants.kElevatorkP;
        slot0Configs.kI = ElevatorConstants.kElevatorkI;
        slot0Configs.kD = ElevatorConstants.kElevatorkD;
        slot0Configs.kV = ElevatorConstants.kElevatorkV;
        m_elevatorMotor.getConfigurator().apply(slot0Configs);
    }

    public void setElevatorPosition(double distanceInches) {
        double motorRevolutions = distanceInches / ElevatorConstants.kElevatorDistancePerMotorRevolution;
        m_elevatorMotor.setControl(m_motionMagicRequest.withPosition(motorRevolutions));
    }
    //@Log(name = "position (in)")
    public double getPositionInches() {
        double rotations = m_elevatorMotor.getPosition().getValueAsDouble();
        //return Conversions.rotationsToArcLength(rotations, OUTPUT_SPROCKET_PITCH_RADIUS_INCHES);
                return rotations;
    }
    public void stopMotor() {
        m_elevatorMotor.stopMotor();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
      
        SmartDashboard.putNumber("Elevator position (in)", getPositionInches());
       
    }
}
