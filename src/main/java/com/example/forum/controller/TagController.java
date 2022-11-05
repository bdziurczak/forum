package com.example.forum.controller;

import com.example.forum.model.Tag;
import com.example.forum.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TagController {
    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/tag")
    public String tagList (Model model) {
        Iterable<Tag> tags = tagService.returnTags();
        model.addAttribute("tags", tags);
        return "tag/tag_list";
    }
    @GetMapping("/tag/add")
    public String getAddTag(Model model) {
        model.addAttribute("tag", new Tag());
        return "tag/add_tag";
    }
    @PostMapping("/tag/add")
    public String postAddTag(Model model, @ModelAttribute Tag tag) {
        tagService.save(tag);
        model.addAttribute("tag", tag);
        return "tag/add_tag_result";
    }
}
