package com.example.forum.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class Question {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(
            name = "uuid2",
            strategy = "uuid2"
    )
    @Type(type="uuid-char")
    private UUID id;

    @Column(columnDefinition = "TEXT")
    private String title;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String description;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question",  orphanRemoval = true)
    private List<Answer> answers;

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @ManyToMany
    private List<Tag> tags;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public UUID getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
        answer.setQuestion(this);
    }
    public void removeAnswer(Answer answer) {
        answer.setQuestion(null);
        this.answers.remove(answer);
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }

    public void setTag(Tag tag) {
        this.tags.add(tag);
        tag.addQuestion(this);
    }
    public Tag getTag() {
        return this.tags.get(0);
    }

    public List<Tag> getTags() {
        return tags;
    }
}
