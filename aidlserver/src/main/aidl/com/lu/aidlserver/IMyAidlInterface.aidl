// IMyAidlInterface.aidl
package com.lu.aidlserver;
// Declare any non-default types here with import statements
import com.lu.aidlserver.Student;

interface IMyAidlInterface {
    /**
         * Demonstrates some basic types that you can use as parameters
         * and return values in AIDL.
         * 从注解可以看得出 basicTypes()方法中的参数都是 aidl定义可以用的参数类型
         */
    //    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
    //            double aDouble, String aString);

           String getName();//获取一个名称
           int countSum(int a ,int b);//计算a+b
           List<Student> addStudent(in Student student);
}
