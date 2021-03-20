package frc.robot.command;

import org.strykeforce.thirdcoast.util.LogitechAxis;
import org.strykeforce.thirdcoast.util.LogitechF310;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystem.DriveSubsystem;

public final class TeleOpDriveCommand extends CommandBase {

  private static final double DEADBAND = 0.05;
  private static final DriveSubsystem DRIVE = RobotContainer.DRIVE;
  private static final LogitechF310 controls = RobotContainer.CONTROLS;

  public TeleOpDriveCommand() {
    addRequirements(DRIVE);
  }

  @Override
  public void execute() {
    double forward = deadband(controls.getAxis(LogitechAxis.LY));
    double strafe = deadband(-controls.getAxis(LogitechAxis.LX));
    double yaw = deadband(-controls.getAxis(LogitechAxis.RX));

    DRIVE.drive(forward, strafe, yaw);
  }

  @Override
  public void end(boolean interrupted) {
    DRIVE.drive(0.0, 0.0, 0.0);
  }

  private double deadband(double value) {
    if (Math.abs(value) < DEADBAND) return 0.0;
    return value;
  }
}
