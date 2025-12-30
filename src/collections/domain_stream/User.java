package domain_stream;

import java.util.List;

public record User(String name, int age, Sex sex, List<Contact> contacts) {
}