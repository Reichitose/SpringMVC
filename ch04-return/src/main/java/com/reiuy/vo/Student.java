package com.reiuy.vo;

//vo这个包名一般就是用来储存保存请求参数值的对象的

public class Student {
    //要求属性名和请求中参数名一样
    private String name;
    private Integer age;

    public Student() {
        System.out.println("这里是Student的无参构造方法");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
