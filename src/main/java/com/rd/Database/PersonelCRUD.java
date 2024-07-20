package com.rd.Database;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Update;

import java.util.List;

public class PersonelCRUD {
    private final Jdbi jdbi;

    public PersonelCRUD(Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    public void createTable() {
        jdbi.useHandle(handle -> handle.execute("CREATE TABLE IF NOT EXISTS sena_efe (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100), " +
                "surname VARCHAR(100), " +
                "job VARCHAR(100), " +
                "department VARCHAR(100), " +
                "salary INT)"));
    }

    public void truncateTable() {
        jdbi.useHandle(handle -> handle.execute("TRUNCATE TABLE sena_efe"));
    }

    public void insertPersonel(Personel personel) {
        jdbi.useHandle(handle -> {
            Update update = handle.createUpdate("INSERT INTO sena_efe (name, surname, job, department, salary) VALUES (?, ?, ?, ?, ?)");
            update.bind(0, personel.getName())
                    .bind(1, personel.getSurname())
                    .bind(2, personel.getJob())
                    .bind(3, personel.getDepartment())
                    .bind(4, personel.getSalary())
                    .execute();
        });
    }

    public List<Personel> listPersonel() {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM sena_efe")
                .map((rs, ctx) -> new Personel(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("job"),
                        rs.getString("department"),
                        rs.getInt("salary")
                ))
                .list());
    }

    public void updatePersonel(Personel personel) {
        jdbi.useHandle(handle -> {
            Update update = handle.createUpdate("UPDATE sena_efe SET name = ?, surname = ?, job = ?, department = ?, salary = ? WHERE id = ?");
            update.bind(0, personel.getName())
                    .bind(1, personel.getSurname())
                    .bind(2, personel.getJob())
                    .bind(3, personel.getDepartment())
                    .bind(4, personel.getSalary())
                    .bind(5, personel.getId())
                    .execute();
            System.out.println(personel.getName() + " has been updated. Updated details: " + personel);
        });
    }

    public void deletePersonel(int id) {
        Personel personel = getPersonelById(id);
        if (personel != null) {
            jdbi.useHandle(handle -> handle.execute("DELETE FROM sena_efe WHERE id = ?", id));
            System.out.println(personel.getName() + " (" + personel + ") has been removed from the project.");
        } else {
            System.out.println("Personel with id " + id + " not found.");
        }
    }

    private Personel getPersonelById(int id) {
        return jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM sena_efe WHERE id = :id")
                .bind("id", id)
                .map((rs, ctx) -> new Personel(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("job"),
                        rs.getString("department"),
                        rs.getInt("salary")
                ))
                .findOne()
                .orElse(null));
    }
}
