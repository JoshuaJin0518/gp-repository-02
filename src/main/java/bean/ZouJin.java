package bean;

public class ZouJin {
    private String name;
    private int age;

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
        return "ZouJin{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
