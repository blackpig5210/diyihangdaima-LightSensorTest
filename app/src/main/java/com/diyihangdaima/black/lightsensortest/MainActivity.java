package com.diyihangdaima.black.lightsensortest;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView lightLevel;
    private SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lightLevel = (TextView) findViewById(R.id.light_level);
        //第一步：获取SensorManager实例
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //第二步：调用getDefaultSensor方法获取任意的传感器内容
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        //第四步：调用SensorManager的registListener()方法来注册SensorEventListener
        sensorManager.registerListener(listener, sensor,
                SensorManager.SENSOR_DELAY_NORMAL);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sensorManager != null) {
            sensorManager.unregisterListener(listener);
        }
    }

    //第三步：借助SensorEventListener监听传感器输出的信号
    private SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            //values数组中的第一个下标的值就是当前的光照强度
            float value = sensorEvent.values[0];
            lightLevel.setText("Current light level is " + value + " lx");
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
} 
