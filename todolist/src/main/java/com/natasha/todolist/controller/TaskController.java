package com.natasha.todolist.controller;
import com.natasha.todolist.model.Task;
import com.natasha.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // 1. Marca como um componente que recebe requisições HTTP (a API)
@RequestMapping("/api/tarefas") // 2. Define o caminho base da API (ex: /api/tarefas)
public class TaskController {
    @Autowired // 3. Injeta o Repositório, permitindo o acesso ao banco de dados
    private TaskRepository repository;

    // POST /api/tarefas (CREATE: Cria uma nova tarefa)
    @PostMapping
    public Task createTask (@RequestBody Task newTask){
        return repository.save(newTask);
    }

    // GET /api/tarefas (READ: Lista todas as tarefas)
    @GetMapping
    public List<Task> listAll(){
        return repository.findAll();
    }

    // GET /api/tarefas/{id} (READ: Busca uma tarefa por ID)
    @GetMapping("/{id}/")
    public Optional<Task> searchById(@PathVariable Long id){
        return  repository.findById(id);
    }

    // PUT /api/tarefas/{id} (UPDATE: Atualiza uma tarefa existente)
    @PutMapping("/{id}/")
    public Task updateTask(@PathVariable Long id, @RequestBody Task taskUpdated){
        return repository.findById(id).map(tarefaExistente -> {
            tarefaExistente.setTitle(taskUpdated.getTitle());
            tarefaExistente.setDescription(taskUpdated.getDescription());
            tarefaExistente.setConcluded(taskUpdated.isConcluded());
            return repository.save(tarefaExistente);
        }).orElseGet(() -> {
            // Se o ID não existir, ele cria uma nova (com o ID fornecido)
            taskUpdated.setId(id);
            return repository.save(taskUpdated);
        });

    }

}



