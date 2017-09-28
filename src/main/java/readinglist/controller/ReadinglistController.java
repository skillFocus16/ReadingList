package readinglist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import readinglist.config.AmazonProperties;
import readinglist.domain.Book;
import readinglist.repository.ReadingListRepository;

import java.util.List;

/**
 * Created by naaminicharles on 9/28/17.
 */
@Controller
@RequestMapping("/")
/*@ConfigurationProperties: This specifies that this bean should have its properties injected (via setter
methods) with values from configuration properties. More specifically, the prefix
attribute specifies that the ReadingListController bean will be injected with proper-
ties with an “amazon” prefix.*/
//@ConfigurationProperties(prefix = "amazon")
public class ReadinglistController {

    private AmazonProperties amazonProperties;
    private ReadingListRepository readingListRepository;

    @Autowired
    public ReadinglistController(AmazonProperties amazonProperties, ReadingListRepository readingListRepository) {
        this.amazonProperties = amazonProperties;
        this.readingListRepository = readingListRepository;
    }

    @RequestMapping(method=RequestMethod.GET)
    public String readersBooks(String reader, Model model) {
        List<Book> readingList = readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
            model.addAttribute("reader", reader);
            model.addAttribute("amazonID", amazonProperties.getAssociateId());
        }
        return "readingList";

    }

    @RequestMapping(method=RequestMethod.POST)
    public String addToReadingList(String reader, Book book) {
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/";
    }


/*
    @RequestMapping(value="/{reader}", method= RequestMethod.GET)
    public String readersBooks(@PathVariable("reader") String reader, Model model) {
        List<Book> readingList = readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
        }
        return "readingList";
    }

    @RequestMapping(value="/{reader}", method= RequestMethod.POST)
    public String addToReadingList(@PathVariable("reader") String reader, Book book) {
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/{reader}";
    }*/

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
