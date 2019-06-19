package com.lu.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: 安仔夏天勤奋
 * Date: 2019/6/18
 * Desc: 服务
 */
public class MyAidlService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();//返回给客户端的Binder
    }

    //获取aidl的Binder
    private class MyBinder extends IMyAidlInterface.Stub{
        @Override
        public String getName() throws RemoteException {
            return "我是AIDL";
        }

        @Override
        public int countSum(int a, int b) throws RemoteException {
            return a+b;
        }

        @Override
        public List<Student> addStudent(Student student) throws RemoteException {
            List<Student> students=new ArrayList<>();
            students.add(student);
            return students;
        }
    }
}
