package ru.stepic.web.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.stepic.web.dao.PersonDAO;
import ru.stepic.web.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private PersonDAO personDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("people", personDAO.index()); //в файле index.html из массива people получаем person
        return "people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("person", personDAO.show(id)); //алишев спринг 21 урок 30 минута
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute ("person") Person person){
        return "people/new";
    }

//    @GetMapping("/new")
//    public String newPerson(Model model)
//    {  аннотация @ModelAttribute из метода выше создает, заполняет, передает в представление обьект аналог этого метода
//        model.addAttribute("person", new Person());
//        return "people/new";
//    }
//@Valid Person person, BindingResult bindingResult){   урок 24 Алишев Spring
//        if (bindingResult.hasErrors())    урок 24 Алишев Spring Валидация
//            return "people/new";    эта часть кода валидирует поля внедряемые @ModelAttribute из формы в браузере
//            и если они не соответсвуют условиям валидации заданным на полях в Person, то получаем BindingResult
    //        if (bindingResult.hasErrors()) проверка естьли в нем ошибки, и если да возвращаем снова на ту же форму,
    //        но в форме отображаются уже не успешные данные, @ModelAttribute их ввел.
//             урок 24 Алишев Spring
    @PostMapping
    public String create(@ModelAttribute("person") @Valid Person person,
                         BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return "people/new";

        personDAO.save(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute(personDAO.show(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                         @PathVariable("id") int id){
        if (bindingResult.hasErrors())
            return "people/edit";

        personDAO.update(id, person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }
}
