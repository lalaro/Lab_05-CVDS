import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller public class TodoController {

    private TodoService todoService;
    
    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService; 
    }
    
    @GetMapping("/todo")
    public String getAllTodos(Model model) {
       Todo todo = servicio.getTodo(); 
       model.addAttribute("todo", todo);
    
       return "todo"; // Nombre de la vista 
    }
    
}