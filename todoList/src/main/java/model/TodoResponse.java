package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponse{
    private Long id;
    private String title;
    private Long order;
    private boolean completed;
    private String url;

    public TodoResponse(TodoEntity todoEntity) {
        this.id = todoEntity.getId();
        this.title = todoEntity.getTitle();
        this.order = todoEntity.getOrder();
        this.completed = todoEntity.isCompleted();

        this.url = "http://localhost:8080/"+this.id;
    }
}