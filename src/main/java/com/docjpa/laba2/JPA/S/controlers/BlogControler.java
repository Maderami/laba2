package com.docjpa.laba2.JPA.S.controlers;

import com.docjpa.laba2.JPA.S.models.ListDocs;
import com.docjpa.laba2.JPA.S.repo.ListDocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogControler {

    @Autowired
    private ListDocRepository listdocrepository;
    @GetMapping("/doclist")
    public String documentListBView(Model model){
        Iterable<ListDocs> listDocs = listdocrepository.findAll();
        model.addAttribute("listDocs",listDocs);
        return "doclist";
    }
    @GetMapping("/doclist/addDocument")
    public String addDocument(Model model){

        return "addDocument";
    }

    @PostMapping("/doclist/addDocument")
    public String docAddPost(@RequestParam String title_doc, @RequestParam String author,@RequestParam String creation_doc,@RequestParam String registration_doc
            ,@RequestParam String status,@RequestParam String description_doc, Model model){

       ListDocs listDocs = new ListDocs(  title_doc,  description_doc,  LocalDate.parse( creation_doc, DateTimeFormatter.ofPattern("dd-mm-yyyy")),  LocalDate.parse(registration_doc,DateTimeFormatter.ofPattern("dd-mm-yyyy")),  status,  author );
       listdocrepository.save(listDocs);
        return "redirect:/doclist";
    }

    @GetMapping("/doclist/{id_doc}")
    public String DocumentDetails(@PathVariable(value="id_doc") long id_doc,Model model) {
        if(!listdocrepository.existsById(id_doc)) {
           return "redirect:/";
        }
        Optional<ListDocs> listD = listdocrepository.findById(id_doc);
        ArrayList<ListDocs> res = new ArrayList<>();
        listD.ifPresent(res::add);
        model.addAttribute("listD", res);
        return "doclistdetails";
    }
    @GetMapping("/doclist/{id_doc}/edit")
    public String DocumentEdit(@PathVariable(value="id_doc") long id_doc,Model model) {
        if(!listdocrepository.existsById(id_doc)) {
            return "redirect:/";
        }
        Optional<ListDocs> listD = listdocrepository.findById(id_doc);
        ArrayList<ListDocs> res = new ArrayList<>();
        listD.ifPresent(res::add);
        model.addAttribute("listD", res);
        return "doclistedit";
    }

    @PostMapping("/doclist/{id_doc}/edit")
    public String docUpdatePost(@PathVariable(value="id_doc") long id_doc,@RequestParam String title_doc, @RequestParam String author,@RequestParam String creation_doc,@RequestParam String registration_doc
            ,@RequestParam String status,@RequestParam String description_doc, Model model){
        ListDocs listds = listdocrepository.findById(id_doc).orElseThrow();
        listds.setTitle_doc(title_doc);
        listds.setAuthor(author);
        listds.setCreation_doc(LocalDate.parse( creation_doc, DateTimeFormatter.ofPattern("dd-mm-yyyy")));
        listds.setRegistration_doc( LocalDate.parse(registration_doc,DateTimeFormatter.ofPattern("dd-mm-yyyy")));
        listds.setStatus(status);
        listds.setDescription_doc(description_doc);
        listdocrepository.save(listds);
        return "redirect:/doclist";
    }
    @PostMapping("/doclist/{id_doc}/delete")
    public String docDeletePost(@PathVariable(value="id_doc") long id_doc, Model model){
        ListDocs listds = listdocrepository.findById(id_doc).orElseThrow();
        listdocrepository.delete(listds);
        return "redirect:/doclist";
    }
//    @GetMapping("/actionDocument")
//    public String actionDocument(Model model) {
//        model.addAttribute("title", "Action documents");
//        return "actionDocument";
//    }
    @GetMapping("/documentCount")
    public String documentCount( Model model) {

        Iterable<ListDocs> listDocs = listdocrepository.findAll();
        long countApprove = listdocrepository.count();
        model.addAttribute("lists", listDocs);
        model.addAttribute("countApprove",countApprove);
        return "documentCount";
    }


}