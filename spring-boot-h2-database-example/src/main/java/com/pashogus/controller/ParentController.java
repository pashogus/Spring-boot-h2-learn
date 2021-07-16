package com.pashogus.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pashogus.model.Parent;
import com.pashogus.repository.ParentRepository;

//https://vladmihalcea.com/the-best-way-to-map-a-onetoone-relationship-with-jpa-and-hibernate/


@RestController
@RequestMapping("api/v1/oneToOne")
public class ParentController {
	
	@Autowired
	ParentRepository parentRepository;
	
	/* ################################################################################################################### */
	
	/*
	 * 
		{
	"name":"Shan5",
	"address":"sundara",
	"child":
	{
		"name":"gopi4"
	}
		}
		
	 * 
	 * Hibernate: insert into parent (id, created_at, updated_at, address, name) values (null, ?, ?, ?, ?)
2021-03-06 15:42:09.106 TRACE 3676 --- [nio-8080-exec-4] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [TIMESTAMP] - [Sat Mar 06 15:42:09 IST 2021]
2021-03-06 15:42:09.106 TRACE 3676 --- [nio-8080-exec-4] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [TIMESTAMP] - [Sat Mar 06 15:42:09 IST 2021]
2021-03-06 15:42:09.106 TRACE 3676 --- [nio-8080-exec-4] o.h.type.descriptor.sql.BasicBinder      : binding parameter [3] as [VARCHAR] - [sundara]
2021-03-06 15:42:09.106 TRACE 3676 --- [nio-8080-exec-4] o.h.type.descriptor.sql.BasicBinder      : binding parameter [4] as [VARCHAR] - [Shan3]
2021-03-06 15:42:09.110 DEBUG 3676 --- [nio-8080-exec-4] org.hibernate.SQL                        : insert into child (created_at, updated_at, name, parent_id) values (?, ?, ?, ?)
Hibernate: insert into child (created_at, updated_at, name, parent_id) values (?, ?, ?, ?)
2021-03-06 15:42:09.111 TRACE 3676 --- [nio-8080-exec-4] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [TIMESTAMP] - [Sat Mar 06 15:42:09 IST 2021]
2021-03-06 15:42:09.111 TRACE 3676 --- [nio-8080-exec-4] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [TIMESTAMP] - [Sat Mar 06 15:42:09 IST 2021]
2021-03-06 15:42:09.111 TRACE 3676 --- [nio-8080-exec-4] o.h.type.descriptor.sql.BasicBinder      : binding parameter [3] as [VARCHAR] - [gopi1]
2021-03-06 15:42:09.111 TRACE 3676 --- [nio-8080-exec-4] o.h.type.descriptor.sql.BasicBinder      : binding parameter [4] as [BIGINT] - [34]

	 */
	@PostMapping("/create")
	public Parent addParent (@RequestBody Parent parent)
	{
		return parentRepository.save(parent);
	}
	
	/* ################################################################################################################### */
	
	/*
	 * 

	[
		{
	"name":"Shan5",
	"address":"sundara",
	"child":
	{
		"name":"gopi4"
	}
		},
		{
			"name":"Shan6",
	"address":"sundara",
	"child":
	{
		"name":"gopi5"
	}
		}
	]
	
	http://localhost:8080/api/v1/oneToOne/createAll
	 * Hibernate: insert into parent (id, created_at, updated_at, address, name) values (null, ?, ?, ?, ?)
2021-03-06 15:58:36.338 TRACE 3676 --- [nio-8080-exec-7] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [TIMESTAMP] - [Sat Mar 06 15:58:36 IST 2021]
2021-03-06 15:58:36.338 TRACE 3676 --- [nio-8080-exec-7] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [TIMESTAMP] - [Sat Mar 06 15:58:36 IST 2021]
2021-03-06 15:58:36.338 TRACE 3676 --- [nio-8080-exec-7] o.h.type.descriptor.sql.BasicBinder      : binding parameter [3] as [VARCHAR] - [sundara]
2021-03-06 15:58:36.338 TRACE 3676 --- [nio-8080-exec-7] o.h.type.descriptor.sql.BasicBinder      : binding parameter [4] as [VARCHAR] - [Shan5]
2021-03-06 15:58:36.341 DEBUG 3676 --- [nio-8080-exec-7] org.hibernate.SQL                        : insert into child (created_at, updated_at, name, parent_id) values (?, ?, ?, ?)
Hibernate: insert into child (created_at, updated_at, name, parent_id) values (?, ?, ?, ?)
2021-03-06 15:58:36.341 TRACE 3676 --- [nio-8080-exec-7] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [TIMESTAMP] - [Sat Mar 06 15:58:36 IST 2021]
2021-03-06 15:58:36.342 TRACE 3676 --- [nio-8080-exec-7] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [TIMESTAMP] - [Sat Mar 06 15:58:36 IST 2021]
2021-03-06 15:58:36.342 TRACE 3676 --- [nio-8080-exec-7] o.h.type.descriptor.sql.BasicBinder      : binding parameter [3] as [VARCHAR] - [gopi4]
2021-03-06 15:58:36.342 TRACE 3676 --- [nio-8080-exec-7] o.h.type.descriptor.sql.BasicBinder      : binding parameter [4] as [BIGINT] - [37]
2021-03-06 15:58:36.344 DEBUG 3676 --- [nio-8080-exec-7] org.hibernate.SQL                        : insert into parent (id, created_at, updated_at, address, name) values (null, ?, ?, ?, ?)
Hibernate: insert into parent (id, created_at, updated_at, address, name) values (null, ?, ?, ?, ?)
2021-03-06 15:58:36.344 TRACE 3676 --- [nio-8080-exec-7] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [TIMESTAMP] - [Sat Mar 06 15:58:36 IST 2021]
2021-03-06 15:58:36.344 TRACE 3676 --- [nio-8080-exec-7] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [TIMESTAMP] - [Sat Mar 06 15:58:36 IST 2021]
2021-03-06 15:58:36.344 TRACE 3676 --- [nio-8080-exec-7] o.h.type.descriptor.sql.BasicBinder      : binding parameter [3] as [VARCHAR] - [sundara]
2021-03-06 15:58:36.345 TRACE 3676 --- [nio-8080-exec-7] o.h.type.descriptor.sql.BasicBinder      : binding parameter [4] as [VARCHAR] - [Shan6]
2021-03-06 15:58:36.347 DEBUG 3676 --- [nio-8080-exec-7] org.hibernate.SQL                        : insert into child (created_at, updated_at, name, parent_id) values (?, ?, ?, ?)
Hibernate: insert into child (created_at, updated_at, name, parent_id) values (?, ?, ?, ?)
2021-03-06 15:58:36.347 TRACE 3676 --- [nio-8080-exec-7] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [TIMESTAMP] - [Sat Mar 06 15:58:36 IST 2021]
2021-03-06 15:58:36.348 TRACE 3676 --- [nio-8080-exec-7] o.h.type.descriptor.sql.BasicBinder      : binding parameter [2] as [TIMESTAMP] - [Sat Mar 06 15:58:36 IST 2021]
2021-03-06 15:58:36.348 TRACE 3676 --- [nio-8080-exec-7] o.h.type.descriptor.sql.BasicBinder      : binding parameter [3] as [VARCHAR] - [gopi5]
2021-03-06 15:58:36.348 TRACE 3676 --- [nio-8080-exec-7] o.h.type.descriptor.sql.BasicBinder      : binding parameter [4] as [BIGINT] - [38]

	 */
	
	@PostMapping("/createAll")
	public List<Parent> addParents (@RequestBody List<Parent> parent)
	{
		return parentRepository.saveAll(parent);
	}
	
	
	/* ################################################################################################################### */	
	
	/*
	 *  http://localhost:8080/api/v1/oneToOne/getAll 
	 * Hibernate: select parent0_.id as id1_3_, parent0_.created_at as created_2_3_, parent0_.updated_at as updated_3_3_, parent0_.address as address4_3_, parent0_.name as name5_3_ from parent parent0_
2021-03-06 15:36:50.907 TRACE 3676 --- [nio-8080-exec-6] o.h.type.descriptor.sql.BasicExtractor   : extracted value ([id1_3_] : [BIGINT]) - [33]
2021-03-06 15:36:50.907 TRACE 3676 --- [nio-8080-exec-6] o.h.type.descriptor.sql.BasicExtractor   : extracted value ([created_2_3_] : [TIMESTAMP]) - [2021-03-06 15:31:40.02]
2021-03-06 15:36:50.907 TRACE 3676 --- [nio-8080-exec-6] o.h.type.descriptor.sql.BasicExtractor   : extracted value ([updated_3_3_] : [TIMESTAMP]) - [2021-03-06 15:31:40.02]
2021-03-06 15:36:50.907 TRACE 3676 --- [nio-8080-exec-6] o.h.type.descriptor.sql.BasicExtractor   : extracted value ([address4_3_] : [VARCHAR]) - [sundara]
2021-03-06 15:36:50.907 TRACE 3676 --- [nio-8080-exec-6] o.h.type.descriptor.sql.BasicExtractor   : extracted value ([name5_3_] : [VARCHAR]) - [Shan2]
2021-03-06 15:36:50.908 DEBUG 3676 --- [nio-8080-exec-6] org.hibernate.SQL                        : select child0_.parent_id as parent_i1_0_0_, child0_.created_at as created_2_0_0_, child0_.updated_at as updated_3_0_0_, child0_.name as name4_0_0_ from child child0_ where child0_.parent_id=?
Hibernate: select child0_.parent_id as parent_i1_0_0_, child0_.created_at as created_2_0_0_, child0_.updated_at as updated_3_0_0_, child0_.name as name4_0_0_ from child child0_ where child0_.parent_id=?
2021-03-06 15:36:50.908 TRACE 3676 --- [nio-8080-exec-6] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [BIGINT] - [33]
2021-03-06 15:36:50.909 TRACE 3676 --- [nio-8080-exec-6] o.h.type.descriptor.sql.BasicExtractor   : extracted value ([created_2_0_0_] : [TIMESTAMP]) - [2021-03-06 15:31:40.193]
2021-03-06 15:36:50.909 TRACE 3676 --- [nio-8080-exec-6] o.h.type.descriptor.sql.BasicExtractor   : extracted value ([updated_3_0_0_] : [TIMESTAMP]) - [2021-03-06 15:31:40.193]
2021-03-06 15:36:50.909 TRACE 3676 --- [nio-8080-exec-6] o.h.type.descriptor.sql.BasicExtractor   : extracted value ([name4_0_0_] : [VARCHAR]) - [gopi]

	 */
	@GetMapping("/getAll")
	public List<Parent> getParents ()
	{
		return parentRepository.findAll();
	}
	
	/* ################################################################################################################### */
	
	/*http://localhost:8080/api/v1/oneToOne/get/33
	 * Hibernate: select parent0_.id as id1_3_0_, parent0_.created_at as created_2_3_0_, parent0_.updated_at as updated_3_3_0_, parent0_.address as address4_3_0_, parent0_.name as name5_3_0_ from parent parent0_ where parent0_.id=?
2021-03-06 15:39:01.447 TRACE 3676 --- [nio-8080-exec-2] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [BIGINT] - [33]
2021-03-06 15:39:01.448 TRACE 3676 --- [nio-8080-exec-2] o.h.type.descriptor.sql.BasicExtractor   : extracted value ([created_2_3_0_] : [TIMESTAMP]) - [2021-03-06 15:31:40.02]
2021-03-06 15:39:01.448 TRACE 3676 --- [nio-8080-exec-2] o.h.type.descriptor.sql.BasicExtractor   : extracted value ([updated_3_3_0_] : [TIMESTAMP]) - [2021-03-06 15:31:40.02]
2021-03-06 15:39:01.448 TRACE 3676 --- [nio-8080-exec-2] o.h.type.descriptor.sql.BasicExtractor   : extracted value ([address4_3_0_] : [VARCHAR]) - [sundara]
2021-03-06 15:39:01.448 TRACE 3676 --- [nio-8080-exec-2] o.h.type.descriptor.sql.BasicExtractor   : extracted value ([name5_3_0_] : [VARCHAR]) - [Shan2]
2021-03-06 15:39:01.448 DEBUG 3676 --- [nio-8080-exec-2] org.hibernate.SQL                        : select child0_.parent_id as parent_i1_0_0_, child0_.created_at as created_2_0_0_, child0_.updated_at as updated_3_0_0_, child0_.name as name4_0_0_ from child child0_ where child0_.parent_id=?
Hibernate: select child0_.parent_id as parent_i1_0_0_, child0_.created_at as created_2_0_0_, child0_.updated_at as updated_3_0_0_, child0_.name as name4_0_0_ from child child0_ where child0_.parent_id=?
2021-03-06 15:39:01.448 TRACE 3676 --- [nio-8080-exec-2] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [BIGINT] - [33]
2021-03-06 15:39:01.449 TRACE 3676 --- [nio-8080-exec-2] o.h.type.descriptor.sql.BasicExtractor   : extracted value ([created_2_0_0_] : [TIMESTAMP]) - [2021-03-06 15:31:40.193]
2021-03-06 15:39:01.449 TRACE 3676 --- [nio-8080-exec-2] o.h.type.descriptor.sql.BasicExtractor   : extracted value ([updated_3_0_0_] : [TIMESTAMP]) - [2021-03-06 15:31:40.193]
2021-03-06 15:39:01.449 TRACE 3676 --- [nio-8080-exec-2] o.h.type.descriptor.sql.BasicExtractor   : extracted value ([name4_0_0_] : [VARCHAR]) - [gopi]

	 * http://localhost:8080/api/v1/oneToOne/get/1
	 *	 *Simply returns null as we are using Optional , no message no error nothing
	 *                   ----
	 * 
	 */
	@GetMapping("/get/{id}")
	public Optional<Parent> getParent (@PathVariable(value="id") Long id)
	{
		return parentRepository.findById(id);
	}
	
/* ################################################################################################################### */
	
	/*
	 * http://localhost:8080/api/v1/oneToOne/delete/1
	 * Hibernate: delete from child where parent_id=?
2021-03-06 15:36:41.575 TRACE 3676 --- [nio-8080-exec-3] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [BIGINT] - [1]
2021-03-06 15:36:41.578 DEBUG 3676 --- [nio-8080-exec-3] org.hibernate.SQL                        : delete from parent where id=?
Hibernate: delete from parent where id=?
2021-03-06 15:36:41.578 TRACE 3676 --- [nio-8080-exec-3] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [BIGINT] - [1]
2021-03-06 15:36:50.906 DEBUG 3676 --- [nio-8080-exec-6] org.hibernate.SQL                        : select parent0_.id as id1_3_, parent0_.created_at as created_2_3_, parent0_.updated_at as updated_3_3_, parent0_.address as address4_3_, parent0_.name as name5_3_ from parent parent0_

	 * 
	 * 
	 */
	//how to identify if key is deleted
	@DeleteMapping("/delete/{id}")
	public void deleteParent (@PathVariable(value="id") Long id)
	{
		 parentRepository.deleteById(id);
		
	}
	
	
	/* ################################################################################################################### */
	
	@DeleteMapping("/delete/v1/{id}")
	public Optional<Parent> deleteParent1 (@PathVariable(value="id") Long id)
	{
		 parentRepository.deleteById(id);
		 return parentRepository.findById(id);
	}

}
