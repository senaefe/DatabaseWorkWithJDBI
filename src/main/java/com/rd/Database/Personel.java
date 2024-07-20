package com.rd.Database;

public class Personel {
    private int id;
    private String name;
    private String surname;
    private String job;
    private String department;
    private int salary;

    public Personel(int id, String name, String surname, String job, String department, int salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.job = job;
        this.department = department;
        this.salary = salary;
    }

    // Getter ve Setter metodları
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Personel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", job='" + job + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }
}
