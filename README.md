# Sensors  

**Types of Sensors**

- *Motion Sensors*  
These are used to measure acceleration forces and rotational forces along with three axes.  

- *Position Sensors*  
These are used to measure the physical position of device.  

- *Environmental Sensors*  
These are used to measure the environmental changes such as temperature, humidity etc.  


Android provides `SensorManager` and Sensor classes to use the sensors in our application. In order to use sensors, first thing you need to do is to instantiate the object of `SensorManager` class. It can be achieved as follows.  
```java
SensorManager sMgr;
sMgr = (SensorManager)this.getSystemService(SENSOR_SERVICE);
```
The next thing you need to do is to instantiate the object of Sensor class by calling the `getDefaultSensor()` method of the SensorManager class.   
```java
Sensor light;
light = sMgr.getDefaultSensor(Sensor.TYPE_LIGHT);
```
Once that sensor is declared , you need to register its listener and override two methods which are `onAccuracyChanged` and `onSensorChanged`.    

```java
sMgr.registerListener(this, light,SensorManager.SENSOR_DELAY_NORMAL);
public void onAccuracyChanged(Sensor sensor, int accuracy) {
}

public void onSensorChanged(SensorEvent event) {
}
```
| Method  | Description |
| ------------- | ------------- |
|onAccuracyChanged(Sensor sensor, int accuracy)| is called when sensor accuracy is changed |
|onSensorChanged(SensorEvent event)|is called when sensor values are changed|


Apart from the these methods, there are other methods provided by the SensorManager class for managing sensors framework.  


| Method  | Description |
| ------------- | ------------- |
|getDefaultSensor(int type)|This method get the default sensor for a given type|
|getInclination(float[] I)|This method computes the geomagnetic inclination angle in radians from the inclination matrix|	
|registerListener(SensorListener listener, int sensors, int rate)|This method registers a listener for the sensor|	
|unregisterListener(SensorEventListener listener, Sensor sensor)|This method unregisters a listener for the sensors with which it is registered|	
|getOrientation(float[] R, float[] values)|This method computes the device's orientation based on the rotation matrix|
|getAltitude(float p0, float p)|This method computes the Altitude in meters from the atmospheric pressure and the pressure at sea level|
