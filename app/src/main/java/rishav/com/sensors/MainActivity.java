package rishav.com.sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Math.abs;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    SensorManager sm;
    Sensor sensor1,sensor2;
    AudioManager am;
    TextView tv,tv2;
    int flag=0;
    String set;
    int max,curr,val,direction=0;
    static double div,result,r;
    SeekBar sb;
    int count=0,d=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.textView);
        tv2=(TextView)findViewById(R.id.textView2);
        sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        sb=(SeekBar)findViewById(R.id.seekBar);
        sensor1=sm.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        sensor2=sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        am=(AudioManager)getSystemService(AUDIO_SERVICE);
        max=am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        sb.setMax(max);
        int current=am.getStreamVolume(AudioManager.STREAM_MUSIC);
        sb.setProgress(current);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                am.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public void sensorList(View v)
    {

    }

    @Override
    protected void onPause() {
        sm.unregisterListener(this);
        super.onPause();

    }

    @Override
    protected void onResume() {
        super.onResume();
        sm.registerListener(this,sensor1,SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM);
        sm.registerListener(this,sensor2,SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor s=event.sensor;
        if(s.getType()==Sensor.TYPE_ACCELEROMETER) {
            float[] values = event.values;
            int x = (int) values[0];
            int y = (int) values[1];
            int z = (int) values[2];
            tv.setText("X=" + x + "\nY=" + y + "\nZ=" + z);


            if(direction==0)
            {
                curr = am.getStreamVolume(AudioManager.STREAM_MUSIC);
            }
            if(d==0) {
                if (y >= 5) {
                    direction = 1;
                    switch (y) {
                        case 5:
                            div = (y - 5) * ((15 - curr) / 4);
                            result = curr + div;
                            r=result;
                            am.setStreamVolume(AudioManager.STREAM_MUSIC, (int) result, 0);
                            break;
                        case 6:
                            div = (y - 5) * ((15 - curr) / 4);
                            result = curr + div;
                            if(result>=r) {
                                am.setStreamVolume(AudioManager.STREAM_MUSIC, (int) result, 0);
                                r=result;
                            }
                            else {
                                d = 1;
                            }
                            break;
                        case 7:
                            div = (y - 5) * ((15 - curr) / 4);
                            result = curr + div;
                            if(result>=r) {
                                am.setStreamVolume(AudioManager.STREAM_MUSIC, (int) result, 0);
                                r=result;
                            }
                            else
                                d=1;
                            break;
                        case 8:
                            div = (y - 5) * ((15 - curr) / 4);
                            result = curr + div;
                            if(result>=r) {
                                am.setStreamVolume(AudioManager.STREAM_MUSIC, (int) result, 0);
                                r=result;
                            }

                            else
                                d=1;
                            break;
                        case 9:
                            div = (y - 5) * ((15 - curr) / 4);
                            result = curr + div;
                            if(result>=r) {
                                am.setStreamVolume(AudioManager.STREAM_MUSIC, 15, 0);
                                r=result;
                            }
                            else
                                d=1;
                            break;
                        default:
                            result=15;
                            am.setStreamVolume(AudioManager.STREAM_MUSIC, 15, 0);
                            r=result;
                    }
                }
                else if(y<=-2)
                {
                    direction = 1;
                    switch (y) {
                        case -2:
                            div = abs(y + 2) * ((curr) / 4);
                            result = curr - div;
                            r=result;
                            am.setStreamVolume(AudioManager.STREAM_MUSIC, (int) result, 0);
                            break;
                        case -3:
                            div = abs(y + 2) * ((curr) / 4);
                            result = curr - div;
                            if(result<=r) {
                                am.setStreamVolume(AudioManager.STREAM_MUSIC, (int) result, 0);
                                r=result;
                            }
                            else {
                                d = 1;
                            }
                            break;
                        case -4:
                            div = abs(y + 2) * ((curr) / 4);
                            result = curr - div;
                            if(result<=r) {
                                am.setStreamVolume(AudioManager.STREAM_MUSIC, (int) result, 0);
                                r=result;
                            }
                            else
                                d=1;
                            break;
                        case -5:
                            div = abs(y + 2) * ((curr) / 4);
                            result = curr - div;
                            if(result<=r) {
                                am.setStreamVolume(AudioManager.STREAM_MUSIC, (int) result, 0);
                                r=result;
                            }

                            else
                                d=1;
                            break;
                        case -6:
                            div = abs(y + 2) * ((curr) / 4);
                            result = curr - div;
                            if(result<=r) {
                                am.setStreamVolume(AudioManager.STREAM_MUSIC, (int) result, 0);
                                r=result;
                            }
                            else
                                d=1;
                            break;
                        default:
                            result=0;
                            am.setStreamVolume(AudioManager.STREAM_MUSIC, (int) result, 0);
                            r=result;
                    }
                }
                }
             if (y <= 4 && y >= -1) {
                direction = 0;
                d=0;
            }
            if (flag == 0) {
                if (x <= -5) {
                    flag = 1;
                    set = "Next";
                    Toast.makeText(this, "" + set, Toast.LENGTH_SHORT).show();
                } else if (x >= 5) {
                    flag = 1;
                    set = "previous";
                    Toast.makeText(this, "" + set, Toast.LENGTH_SHORT).show();
                }
            } else if (flag == 1) {
                if (x >= -1 && x <= 1) {
                    flag = 0;
                    set = "normal";
                    Toast.makeText(this, "" + set, Toast.LENGTH_SHORT).show();
                }
            }
        }
        else if(s.getType()==Sensor.TYPE_PROXIMITY)
        {
            float [] values=event.values;
            tv2.setText(String.valueOf(values[0]));
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
