package com.lu.aidlserver;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Author: 安仔夏天勤奋
 * Date: 2019/6/18
 * Desc:  自定义数据类型，用于进程间通信的话，必须实现Parcelable接口，
 * Parcelable是类似于Java中的Serializable，Android中定义了Parcelable，
 * 用于进程间数据传递，对传输数据进行分解，编组的工作，相对于Serializable，
 * 它对于进程间通信更加高效。
 *
 */
public class Student implements Parcelable {

    private int age;
    private String name;
    private String sex;

    public Student(){}
    public Student(int age, String name, String sex) {
        this.age = age;
        this.name = name;
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "\n名字="+this.name+"\t\t性别="+this.sex+"\t\t年龄="+this.age;
    }

    /**
     * Parcelable对数据进行分解/编组的时候必须使用相同的顺序，
     * 字段以什么顺序分解的，编组时就以什么顺序读取数据，不然会有问题
     * @param in Parcel
     */
    protected Student(Parcel in) {
        this.age = in.readInt();
        this.name = in.readString();
        this.sex = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(age);
        dest.writeString(name);
        dest.writeString(sex);
    }
}
