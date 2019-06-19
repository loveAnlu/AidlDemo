package com.lu.aidlclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.lu.aidlserver.Student;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private com.lu.aidlserver.IMyAidlInterface binder = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //AIDL(android接口定义语言) 是 Android 提供的用于与 Service 进行跨应用、跨进程通信的一种机制，高效、灵活，使用方便。
        //android5.0之前都可以通过配置在manifest里service 的action来启动。android5.0之后都必须使用显示intent。
//        //启动aidl 的服务
//        Intent intent = new Intent();
//        intent.setComponent(new ComponentName("com.lu.aidlserver",
//                "com.lu.aidlserver.MyAidlService"));
//        //第一个参数是包名，第二个是类名

        Intent service = new Intent("com.lu.aidlserver.MyAidlService");
        // 绑定AIDL 隐式
        bindService(service, connection, BIND_AUTO_CREATE );


    }

    // 创建远程调用对象
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 从远程service中获得AIDL实例化对象
            //这里不能强制转换，因为虽然类的内容是一样的，但是却不是同一个。（每个app能访问到的毕竟是自己的类）
            binder = com.lu.aidlserver.IMyAidlInterface.Stub.asInterface(service);
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            binder = null;
        }
    };


    public void onClickTest(View v){
        try {

            Log.e("lu","Name : "+binder == null ? "null" :
                    binder.getName()+"      计算a+b="+binder.countSum(9,1));

            if(binder!=null){
                List<Student> students = new ArrayList<>();
                students.addAll(binder.addStudent(new Student(18,"小卢","男")));
                students.addAll(binder.addStudent(new Student(19,"张三","男")));
                students.addAll(binder.addStudent(new Student(20,"李四","男")));
                Log.e("lu","List<Student> : "+students.toString());
            }

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


}
