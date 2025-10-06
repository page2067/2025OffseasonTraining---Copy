// Constants.java
package frc.robot;

public final class Constants {

    public static final class OIConstants {
        public static final int kDriverControllerPort = 0; // Xbox Controller Port
        public static final int kOperatorControllerPort = 1; // Xbox Controller Port
    }

    public static final class InletConstants {
        public static final int kInletCanID = 14;
        public static final double kInletkP = 0.1; // Placeholder, tune accordingly
        public static final double kInletkI = 0.0; // Placeholder, tune accordingly
        public static final double kInletkD = 0.0; // Placeholder, tune accordingly
        public static final double kInletkV = 0.1; // Placeholder, tune accordingly
        // public static final double kInletTargetVelocity = -40.0; // RPM
    }

    public static final class CoralHandConstants {
        public static final int kCoralHandCanID = 17;  // 17
        public static final double kCoralHandkP = 0.1; // Placeholder, tune accordingly
        public static final double kCoralHandkI = 0.0; // Placeholder, tune accordingly
        public static final double kCoralHandkD = 0.0; // Placeholder, tune accordingly
        public static final double kCoralHandkV = 0.1; // Placeholder, tune accordingly
        // public static final double kCoralHandTargetVelocity = -40.0; // RPM
    }

    public static final class CoralShoulderConstants {
        public static final int kCoralShoulderCanID = 16; // 10
        public static final double kGearRatio = (54.0 / 22.0) / 9.0;
        public static final double kCoralShoulderCruiseVelocityRPM = 6000.0;
        public static final double kCoralShoulderAccelerationRPM = 300.0;
        public static final double kCoralShoulderkP =15.0; // Placeholder, tune accordingly
        public static final double kCoralShoulderkI = 0.0; // Placeholder, tune accordingly
        public static final double kCoralShoulderkD = 0.0; // Placeholder, tune accordingly
        
    }

    public static final class ElevatorConstants {
        public static final int kElevatorCanID = 10;  // 16
        public static final double kElevatorCruiseVelocityRPM = 100.0;  // max is 6000.0
        public static final double kElevatorAccelerationRPM = 10.0;    // was 12000.0
        public static final double kElevatorkP = 20.0; // Placeholder, tune accordingly
        public static final double kElevatorkI = 0.0; // Placeholder, tune accordingly
        public static final double kElevatorkD = 0.0; // Placeholder, tune accordingly
        public static final double kElevatorkV = 0.0; // Placeholder, tune accordingly
        public static final double kElevatorDistancePerMotorRevolution = 5.0/1.0;  //3.1415927 * 1.751 / 16.0; // Inches  
       
    }
}
