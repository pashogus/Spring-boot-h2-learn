package com.pashogus.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/*
 * 
 * Hibernate: create table post (id bigint not null, title varchar(255), primary key (id))
2021-03-05 14:34:15.410 DEBUG 13496 --- [         task-1] org.hibernate.SQL                        : create table post_tag (post_id bigint not null, tag_id bigint not null)
Hibernate: create table post_tag (post_id bigint not null, tag_id bigint not null)
2021-03-05 14:34:15.411 DEBUG 13496 --- [         task-1] org.hibernate.SQL                        : create table tag (id bigint not null, name varchar(255), primary key (id))
Hibernate: create table tag (id bigint not null, name varchar(255), primary key (id))
2021-03-05 14:34:15.412 DEBUG 13496 --- [         task-1] org.hibernate.SQL                        : alter table tag drop constraint if exists UK_qp93jyuw586kcgdajsb3tfbjy
Hibernate: alter table tag drop constraint if exists UK_qp93jyuw586kcgdajsb3tfbjy
2021-03-05 14:34:15.413 DEBUG 13496 --- [         task-1] org.hibernate.SQL                        : alter table tag add constraint UK_qp93jyuw586kcgdajsb3tfbjy unique (name)
Hibernate: alter table tag add constraint UK_qp93jyuw586kcgdajsb3tfbjy unique (name)
2021-03-05 14:34:15.415 DEBUG 13496 --- [         task-1] org.hibernate.SQL                        : create sequence hibernate_sequence start with 1 increment by 1
Hibernate: create sequence hibernate_sequence start with 1 increment by 1
2021-03-05 14:34:15.438 DEBUG 13496 --- [         task-1] org.hibernate.SQL                        : alter table post_tag add constraint FKac1wdchd2pnur3fl225obmlg0 foreign key (tag_id) references tag
Hibernate: alter table post_tag add constraint FKac1wdchd2pnur3fl225obmlg0 foreign key (tag_id) references tag
2021-03-05 14:34:15.442 DEBUG 13496 --- [         task-1] org.hibernate.SQL                        : alter table post_tag add constraint FKc2auetuvsec0k566l0eyvr9cs foreign key (post_id) references post
Hibernate: alter table post_tag add constraint FKc2auetuvsec0k566l0eyvr9cs foreign key (post_id) references post
 * 
 * 
 */

@Entity(name = "Post")
@Table(name = "post")
@JsonIdentityInfo(
       generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Post {

	@Id
    @GeneratedValue
    private Long id;
 
    private String title;
 
    public Post() {}
 
    public Post(String title) {
        this.title = title;
    }
 
    @ManyToMany(cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
    })
    @JoinTable(name = "post_tag",
        joinColumns = @JoinColumn(name = "post_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags = new ArrayList<>();
 
    //Getters and setters ommitted for brevity
 
    public void addTag(Tag tag) {
        tags.add(tag);
       tag.getPosts().add(this);
    }
 
 
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.getPosts().remove(this);
    }
 
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        return id != null && id.equals(((Post) o).getId());
    }
 
    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
