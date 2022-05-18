package frc.robot.subsystems;

import static frc.robot.Constants.TURRET_SPARK;


import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
;

 

public class TurretSubsystem extends SubsystemBase {
  private static final int deviceID = 1;
  private CANSparkMax m_motor;
  private SparkMaxPIDController m_pidController;
  private RelativeEncoder m_encoder;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM, maxVel, minVel, maxAcc, allowedErr;
  private final AHRS m_navx = new AHRS(SPI.Port.kMXP, (byte) 200);


    
    public TurretSubsystem() {
      m_motor = new CANSparkMax(TURRET_SPARK, MotorType.kBrushless);
    
      m_motor.restoreFactoryDefaults();

      m_pidController = m_motor.getPIDController();
      m_encoder = m_motor.getEncoder();

      kP = 5e-5; 
      kI = 1e-6;
      kD = 0; 
      kIz = 0; 
      kFF = 0.000156; 
      kMaxOutput = 1; 
      kMinOutput = -1;
      maxRPM = 11000;

      maxVel = 9000;
      maxAcc = 3000;


      m_pidController.setP(kP);
      m_pidController.setI(kI);
      m_pidController.setD(kD);
      m_pidController.setIZone(kIz);
      m_pidController.setFF(kFF);
      m_pidController.setOutputRange(kMinOutput, kMaxOutput);

      int smartMotionSlot = 0;
      m_pidController.setSmartMotionMaxVelocity(maxVel, smartMotionSlot);
      m_pidController.setSmartMotionMinOutputVelocity(minVel, smartMotionSlot);
      m_pidController.setSmartMotionMaxAccel(maxAcc, smartMotionSlot);
      m_pidController.setSmartMotionAllowedClosedLoopError(allowedErr, smartMotionSlot);

      
    double setPoint, processVariable;
    
      setPoint = (m_navx.getYaw() * 17.5);
      /**
       * As with other PID modes, Smart Motion is set by calling the
       * setReference method on an existing pid object and setting
       * the control type to kSmartMotion
       */
      m_pidController.setReference(setPoint, CANSparkMax.ControlType.kSmartMotion);
      processVariable = m_encoder.getPosition();
    }
  
    
    } 