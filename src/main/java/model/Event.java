package model;

public class Event {

    private String name;
    private String description;
    private String date;
    private String category;

    // Construtor completo
    public Event(String name, String description, String date, String category) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.category = category;
    }

    // Construtor simples usado no CreateEventController e FileEventDao
    public Event(String name, String date) {
        this.name = name;
        this.date = date;
        this.description = "";
        this.category = "";
    }

    public String getName() { return name; }

    public String getDescription() { return description; }

    public String getDate() { return date; }

    public String getCategory() { return category; }

    // MÉTODOS QUE FALTAVAM
    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
