package org.example.demo.Service.IMP;

import lombok.RequiredArgsConstructor;
import org.example.demo.Entity.Todo;
import org.example.demo.Repository.TodoRepository;
import org.example.demo.Service.TodoService;
import org.example.demo.exception.CustomResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TodoServiceImp implements TodoService {
     private final TodoRepository todoRepository;

    @Override
    public List<Todo> findAll() {
        return  todoRepository.findAll();
    }

    @Override
    public Todo findById(long id) {
        return todoRepository.findById(id).orElseThrow(() -> new CustomResourceNotFoundException("Todo Not Found for id: "+id));
    }

    @Override
    public Todo addTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Todo update(Todo todo, long id) {
        Todo old = todoRepository.findById(id).orElse(null);
        if(old != null){
            old.setTitle(todo.getTitle());
            old.setCompleted(todo.isCompleted());
            return todoRepository.save(old);
        }
        return null;
    }

    @Override
    public Todo delete(long id) {
        Todo deletetodo =  todoRepository.findById(id).orElse(null);
        if(deletetodo != null){
            todoRepository.deleteById(id);
        }
        return deletetodo;
    }
}
