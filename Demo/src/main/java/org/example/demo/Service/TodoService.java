package org.example.demo.Service;

import org.example.demo.Entity.Todo;

import java.util.List;

public interface TodoService {
    List<Todo> findAll();
    Todo findById(long id);

    Todo addTodo(Todo todo);
    Todo update(Todo todo, long id);
    Todo delete(long id);
}
