package org;

import org.dao.ContactDAO;
import org.model.Contact;

import java.util.List;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ContactDAO dao = new ContactDAO();

    public static void main(String[] args){
        int option;
        do {
            System.out.println("\n--- Contact Manager ---");
            System.out.println("1. Ver contactos");
            System.out.println("2. Agregar contacto");
            System.out.println("3. Editar contacto");
            System.out.println("4. Eliminar contacto");
            System.out.println("0. Salir");
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> showContacts();
                case 2 -> addContact();
                case 3 -> updateContact();
                case 4 -> deleteContact();
            }
        } while (option != 0);
    }

    private static void showContacts() {
        List<Contact> contacts = dao.findAll();
        System.out.println("Lista de contactos: ");
        for (Contact c : contacts) {
            System.out.printf("%d - %s (%s, %s)%n",
            c.getId(), c.getName(), c.getEmail(), c.getPhone());
        }
    }

    private static void addContact() {
        System.out.println("Nombre: ");
        String name = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Teléfono: ");
        String phone = scanner.nextLine();

        dao.save(new Contact(name, email, phone));
        System.out.println("Contacto agregado.");
    }

    private static void deleteContact() {
        showContacts();
        System.out.println("ID del contacto a eliminar: ");
        int id = scanner.nextInt();
        dao.deleteById(id);
        System.out.println("Contacto eliminado.");
    }
}
