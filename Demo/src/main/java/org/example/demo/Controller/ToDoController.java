package org.example.demo.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.demo.Entity.Todo;
import org.example.demo.Service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/todos")
public class ToDoController {
    private final TodoService todoService;
    @GetMapping
    public ResponseEntity<List<Todo>> getTodos(){
        List<Todo> todos =  todoService.findAll();
        return new ResponseEntity<>(todos, HttpStatus.OK);

    }
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable Long id){
          Todo todo =  todoService.findById(id);
          return new ResponseEntity<>(todo, HttpStatus.OK);
    }
   @PostMapping
    public ResponseEntity<Todo> addTodo( @Valid @RequestBody Todo todo){
        Todo saveTodo = todoService.addTodo(todo);
        return new ResponseEntity<>(saveTodo, HttpStatus.CREATED);
   }
   @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@Valid @RequestBody Todo todo ,  @PathVariable Long id){
        Todo updateTodo = todoService.update(todo,id);
        return new ResponseEntity<>(updateTodo, HttpStatus.OK);
   }
   @DeleteMapping("/{id}")
    public ResponseEntity<Todo> deleteTodo(@PathVariable Long id){
         Todo deleteTodo = todoService.delete(id);
         return new ResponseEntity<>(deleteTodo, HttpStatus.OK);
   }
}