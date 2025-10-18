package com.natasha.todolist.repository;
import com.natasha.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TaskRepository extends JpaRepository<Task, Long>{
    // Esta interface herda os métodos de CRUD (Salvar, Buscar, Deletar)
    //não precisa de codigo aqui

}
