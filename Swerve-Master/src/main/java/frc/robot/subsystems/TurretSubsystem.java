package frc.robot.subsystems;

import static frc.robot.Constants.TURRET_SPARK;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
;

 

public class TurretSubsystem extends SubsystemBase {
    public CANSparkMax motor;
    public RelativeEncoder encoder;
    
    public TurretSubsystem() {
      motor = new CANSparkMax(TURRET_SPARK, MotorType.kBrushless);
      encoder = motor.getEncoder();
    }
  
    public void motorPower(Double power) {
      motor.set(power);
    }
  
    public Double encoderValue() {
      return encoder.getPosition();
    }
  
    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }
    public void startTurret(){
      
    }
  } 