package com.nbh.mvctest3;

public class Data {

    private String name;
    private String age;
    private String date;

    public Data(String name, String age, String date) {
        this.name = name;
        this.age = age;
        this.date = date;
    }

    /**
     * @return name
     **/
    public String getName() {
        return name;
    }

    /**
     * @return age
     **/
    public String getAge() {
        return age;
    }

    /**
     * @return date
     **/
    public String getDate() {
        return date;
    }
}
