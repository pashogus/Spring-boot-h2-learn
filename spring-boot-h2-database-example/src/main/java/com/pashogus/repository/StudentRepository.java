package com.pashogus.repository;
import org.springframework.data.repository.CrudRepository;

import com.pashogus.model.Student;
public interface StudentRepository extends CrudRepository<Student, Integer>
{
}
