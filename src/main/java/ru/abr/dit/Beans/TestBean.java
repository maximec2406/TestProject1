package ru.abr.dit.Beans;

public class TestBean {

    private String name;
    private String info;

    public TestBean() {
//        this.name = "BEAN NAME";
    }

    public TestBean(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
