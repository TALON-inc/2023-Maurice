// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
// import edu.wpi.first.wpilibj2.command.button.Trigger;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ClimbUp;
import frc.robot.commands.ClimbDown;
import frc.robot.commands.Drive;
import frc.robot.commands.LiftDown;
import frc.robot.commands.LiftUp;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Bucket;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // Controller declaration
  CommandXboxController m_driverController = new CommandXboxController(CONTROLLER_PORT);
  // private final Button climbUpButton, climbDownButton;

  // The robot's subsystems and commands are defined here...
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final Arm m_Arm = new Arm();
  private final Bucket m_Bucket = new Bucket();


  private final ClimbDown m_climbdown = new ClimbDown(m_Arm);
  private final ClimbUp m_climbup = new ClimbUp(m_Arm);
  private final Drive m_drive = new Drive(m_drivetrain, m_driverController);
  private final LiftUp m_liftup = new LiftUp(m_Bucket);
  private final LiftDown m_liftdown = new LiftDown(m_Bucket);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // m_driverController = new XboxController(CONTROLLER_PORT);
    // climbUpButton = new JoystickButton(m_driverController, BUTTON_A);
    // climbDownButton = new JoystickButton(m_driverController, BUTTON_B) ;
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    m_driverController.a().whileTrue(m_climbup);
    m_driverController.b().whileTrue(m_climbdown);
    m_driverController.y().whileTrue(m_liftdown);
    m_driverController.x().whileTrue(m_liftup);
 
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  // public Command getAutonomousCommand() {
  //   // An ExampleCommand will run in autonomous
  //   return m_autoCommand;
  // }

  public Command getDrive() {
    return m_drive;
  }
}
