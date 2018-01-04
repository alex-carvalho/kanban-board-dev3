package br.unisinos.kanban.model;

/**
 * @author Alex Carvalho
 */
public enum CardState {

    TO_DO("TO DO"),
    DOING("DOING"),
    DONE("DONE"),
    ARCHIVED("ARCHIVED");

    private String name;

    CardState(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
