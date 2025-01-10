// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
public class KitChassis extends SubsystemBase {
  /** Creates a new chassis. */
  private SparkMax leftFront = new SparkMax(10, MotorType.kBrushed);
  private SparkMax leftBack = new SparkMax(11, MotorType.kBrushed);
  private SparkMax rightBack = new SparkMax(12, MotorType.kBrushed);
  private SparkMax rightFront = new SparkMax(13, MotorType.kBrushed);
  public KitChassis() {
    SparkMaxConfig rightSideConfig = new SparkMaxConfig();
    SparkMaxConfig glConfig = new SparkMaxConfig();


    glConfig
      .smartCurrentLimit(50)
        .idleMode(IdleMode.kBrake);
    rightSideConfig.apply(glConfig).inverted(true);


    rightBack.configure(rightSideConfig,ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
    rightFront.configure(rightSideConfig,ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
    leftBack.configure(glConfig, ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
    leftFront.configure(glConfig, ResetMode.kResetSafeParameters,PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
