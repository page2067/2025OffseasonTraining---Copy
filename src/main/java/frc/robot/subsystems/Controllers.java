/*
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.VelocityCommands.RunCoralHand;
import frc.robot.commands.VelocityCommands.RunCoralHandDutyCycle;
import frc.robot.commands.VelocityCommands.RunInlet;
import frc.robot.commands.IntakeCoralToCoralHand;
import frc.robot.commands.PositionCommands.SetCoralShoulder;
import frc.robot.commands.PositionCommands.SetElevator;

public class Controllers extends SubsystemBase {
private static Controllers instance = null;

// private static final int DRIVER_CONTROLLER_PORT = 0;
// private final CommandXboxController m_driverController;

private static final int OPERATOR_CONTROLLER_PORT = 1;
private final CommandXboxController m_operatorController;

 // Subsystems
 private final Inlet m_inlet = new Inlet();
 private final CoralHand m_coralHand = new CoralHand();
 private final CoralShoulder m_coralShoulder = new CoralShoulder();
 private final Elevator m_elevator = new Elevator();
 private final CoralHandDutyCycle coralHandDutyCycle = new CoralHandDutyCycle();

 // Sensors
    private final DigitalInput m_coralSensor = new DigitalInput(1);

  public static Controllers getInstance() {
    if (instance == null) {
      instance = new Controllers();
    }
    return instance;
  }

  private Controllers() {
  // m_driverController = new CommandXboxController(DRIVER_CONTROLLER_PORT);
  m_operatorController = new CommandXboxController(OPERATOR_CONTROLLER_PORT);

  configureBindings();
  }

 private void configureBindings() {
        
       
        new Trigger(m_coralSensor::get)
                .onTrue(new InstantCommand(() -> {
                    m_inlet.stopMotor();
                    m_coralHand.stopMotor();
                }));

       
       // If DIO 1 is false AND Button A is pressed, run Inlet and CoralHand motors
       m_operatorController.a()
       //.whileTrue(new RunInletCoralHand(m_inlet, m_coralHand, m_coralSensor));         
       .whileTrue(new RunCoralHand(m_coralHand, -60.0).alongWith(new RunInlet(m_inlet,-60.0))); 
       m_operatorController.b()
       .whileTrue(new IntakeCoralToCoralHand(m_inlet, m_coralHand, m_coralSensor));

        // If POV Up is pressed, run CoralShoulder and Elevator in parallel
        m_operatorController.povUp()
        
        .toggleOnTrue(new SetCoralShoulder(m_coralShoulder,-50.0));

        // If Y is pressed, run CoralShoulderand the elevator in parallel
        m_operatorController.y()
        .toggleOnTrue(new SetCoralShoulder(m_coralShoulder,-60.0).alongWith(new SetElevator(m_elevator,-10.0)));
        
        // If B is pressed, run L1 Score: Set coralshoulder, wait then run coralhand.
        m_operatorController.povDown()
        .toggleOnTrue(new SetCoralShoulder(m_coralShoulder,-15.0).alongWith(new WaitCommand(0.3).andThen(new RunCoralHand(m_coralHand,400.0))));
       
        // If x is pressed, run DutyCycle
        m_operatorController.x()
        .whileTrue(new RunCoralHandDutyCycle(coralHandDutyCycle, 0.50));
  
        // If start is pressed, run CoralHandDutyCycle sequence.
        m_operatorController.start()
        .onTrue(new RunCoralHandDutyCycle(coralHandDutyCycle, 0.50).andThen(new RunCoralHandDutyCycle(coralHandDutyCycle, 0.00)));
        }


    
    public Command getAutonomousCommand() {
        return null;
    }
}

//   private void configDriverBindings() {
//     Drivetrain drivetrain = Drivetrain.getInstance();
//     // CoralShoulder coralShoulder = CoralShoulder.getInstance();
//     // Elevator elevator = Elevator.getInstance();
//     // CoralHand coralhand = CoralHand.getInstance();
//     Climber climber = Climber.getInstance();
//     // AlgaeGroundIntake algaeGroundIntake = AlgaeGroundIntake.getInstance();
    
    

//     drivetrain.setDefaultCommand(
//       Commands.run(
//         () -> drivetrain.drive(
//           m_driverController.getLeftX(),
//           m_driverController.getLeftY(),
//           m_driverController.getRightX()
//         ),
//         drivetrain
//       )
//     );



//     m_driverController.rightBumper().onTrue(new InstantCommand(() -> drivetrain.resetFieldOriented(true)));
//     m_driverController.leftBumper().onTrue(new InstantCommand(() -> drivetrain.resetFieldOriented(false)));

//     m_driverController.leftTrigger().onTrue(new DriveToPose(DriveToPose.ScorePosition.LEFT));
//     m_driverController.leftTrigger().onFalse(new InstantCommand(() -> drivetrain.drive(0.0, 0.0, 0.0), drivetrain));
    
//     m_driverController.rightTrigger().onTrue(new DriveToPose(DriveToPose.ScorePosition.RIGHT));
//     m_driverController.rightTrigger().onFalse(new InstantCommand(() -> drivetrain.drive(0.0, 0.0, 0.0), drivetrain));

//     m_driverController.povDown().onTrue(new DriveToPose(DriveToPose.ScorePosition.FEEDER));
//     m_driverController.povDown().onFalse(new InstantCommand(() -> drivetrain.drive(0.0, 0.0, 0.0), drivetrain));

//     m_driverController.a().onTrue(new InstantCommand(() -> drivetrain.driveRobotRelative(new ChassisSpeeds(-1.0, 0.0, 0.0))));
//     m_driverController.a().onFalse(new InstantCommand(() -> drivetrain.driveRobotRelative(new ChassisSpeeds(0.0, 0.0, 0.0))));

//     //scoring
//     // m_operatorController.povDown().onTrue(new LevelOneScore());
//     m_operatorController.povLeft().onTrue(new LevelTwoScore());
//     m_operatorController.povRight().onTrue(new LevelThreeScore());
//     m_operatorController.povUp().onTrue(new LevelFourScore());

//     //algae
//     // m_operatorController.a().onTrue(new AlgaeGroundPickup());
//     // m_operatorController.a().onTrue(new InstantCommand(() -> algaeGroundIntake.setRollerPower(1.0)));
//     // m_operatorController.a().onFalse(new InstantCommand(() -> algaeGroundIntake.setRollerPower(0.0)));
 
//     // algae pump.
//      m_operatorController.b().onTrue(new InstantCommand(() -> AlgaePump.getInstance().setSpeeds(0.75)));
//      m_operatorController.b().onFalse(new InstantCommand(() -> AlgaePump.getInstance().setSpeeds(0.0)));

//     //remove algae from reef (this may be changed before next comp.)
//     m_operatorController.y().onTrue(new SetCoralShoulderPosition(-110.0, false, false).alongWith(new SetCoralHandVelocity(6000.0))); //level 2 algae
//     m_operatorController.y().onFalse(new SetCoralShoulderPosition(0.0, false,false).alongWith(new SetCoralHandVelocity(0.0)));

//     //coral maniipulation
//     m_operatorController.rightTrigger().onTrue(new SetCoralHandVelocity(-2000.0)); // .alongWith(new SetIntakeVelocity(-750.0))); //place coral
//     m_operatorController.rightTrigger().onFalse(new SetCoralHandVelocity(0.0));

//     m_operatorController.a().onTrue(new SetCoralShoulderPosition(-265.00, false));

//     // m_operatorController.a().onTrue(new SetCoralHandVelocity(3000));
//     // m_operatorController.a().onFalse(new SetCoralHandVelocity(0));
//     // L1 scoring
//     m_operatorController.rightBumper().onTrue(new SetCoralShoulderPosition(-30.0, true, true).andThen(new WaitCommand(1)).andThen(new SetCoralHandVelocity(1150.00))); //eject from intake + hand
//     m_operatorController.rightBumper().onFalse(new SetCoralShoulderPosition(0.0, false).alongWith(new SetCoralHandVelocity(0.0))); //eject from intake
    

//     //intake to Coral Hand 
//     m_operatorController.leftTrigger().onTrue(new Handoff());


//     m_operatorController.start().onTrue(new ZeroAll());

//     //Barge
//     m_operatorController.back().onTrue(new SetElevatorPosition(-29.50, false, false));
//     m_operatorController.back().onFalse(new SetElevatorPosition(0.0, true, false));
    
//     //Climb
//     m_operatorController.leftStick().onTrue(new InstantCommand(() -> climber.setTargetPositionDegrees(-220.87, false))); //possibly this button in the future 
//     m_operatorController.leftStick().onFalse(new InstantCommand(() -> climber.setTargetPositionDegrees(-70.00, false)));

//     m_operatorController.rightStick().onTrue(new SetElevatorPosition(0.0, true, false).alongWith(new SetCoralShoulderPosition(-180.0, false, false)));
    
//     }

//   public void setRumble(double rumbleValue) {

//     m_driverController.getHID().setRumble(RumbleType.kBothRumble, rumbleValue);
//     m_operatorController.getHID().setRumble(RumbleType.kBothRumble, rumbleValue);
//   }

//   @Override
//   public void periodic() {} 
// }
*/