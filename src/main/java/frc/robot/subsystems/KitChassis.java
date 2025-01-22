// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;

import java.util.function.DoubleSupplier;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
public class KitChassis extends SubsystemBase {
  /** Creates a new chassis. */
  private DifferentialDrive drive;
  private SparkMax leftFront = new SparkMax(10, MotorType.kBrushless);
  private SparkMax leftBack = new SparkMax(11, MotorType.kBrushless);
  private SparkMax rightBack = new SparkMax(12, MotorType.kBrushless);
  private SparkMax rightFront = new SparkMax(13, MotorType.kBrushless);
  public KitChassis() {
    SparkMaxConfig rightSideConfig = new SparkMaxConfig();
    SparkMaxConfig globalConfig = new SparkMaxConfig();
    SparkMaxConfig leftBackConfig = new SparkMaxConfig();
    SparkMaxConfig rightBackConfig = new SparkMaxConfig();
    leftBack.resumeFollowerMode();
    drive = new DifferentialDrive(leftFront::set, rightFront::set);
    drive.setSafetyEnabled(true);

    globalConfig
      .smartCurrentLimit(50)
        .idleMode(IdleMode.kBrake);
    rightSideConfig.apply(globalConfig).inverted(true);
    leftBackConfig.apply(globalConfig)
        .follow(leftFront);
    rightBackConfig.apply(rightSideConfig)
        .follow(rightFront);

    rightBack.configure(rightBackConfig,ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
    rightFront.configure(rightSideConfig,ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
    leftBack.configure(leftBackConfig, ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
    leftFront.configure(globalConfig, ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
  }
  public void drive(DoubleSupplier forward, DoubleSupplier rotation) {
    leftFront.set(forward.getAsDouble()+rotation.getAsDouble());
    rightFront.set(forward.getAsDouble()-rotation.getAsDouble());
    SmartDashboard.putNumber("forward", forward.getAsDouble());
    SmartDashboard.putNumber("rotation", rotation.getAsDouble());
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
