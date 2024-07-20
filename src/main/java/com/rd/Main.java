package com.rd;

import com.rd.Database.DatabaseConnection;
import com.rd.Database.Personel;
import com.rd.Database.PersonelCRUD;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Jdbi jdbi = DatabaseConnection.getJdbi();
        PersonelCRUD crud = new PersonelCRUD(jdbi);

        crud.createTable();

        crud.truncateTable();

        Personel p1 = new Personel(1, "Sena", "Efe", "Software Engineer", "IT", 50000);
        Personel p2 = new Personel(2, "Bora", "Bayraktar", "Manager", "Management", 150000);
        Personel p3 = new Personel(3, "Aylin", "Umut", "IK", "HR", 30000);

        crud.insertPersonel(p1);
        crud.insertPersonel(p2);
        crud.insertPersonel(p3);

        System.out.println("Personnel List");
        List<Personel> personeller = crud.listPersonel();
        personeller.forEach(personel -> System.out.println(personel));


        System.out.println("-----");

        p1.setJob("Senior Software Engineer");
        p1.setSalary(60000);
        crud.updatePersonel(p1);

        crud.deletePersonel(p2.getId());

        System.out.println("Updated state:");
        personeller = crud.listPersonel();
        personeller.forEach(personel -> System.out.println(personel));
    }
}
