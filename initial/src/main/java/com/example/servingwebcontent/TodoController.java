// Controlador para manejar la solicitud y proporcionar los datos a la vista
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping("/todo")
    public String showTodo(Model model) {
        Todo todo = todoService.getTodo();
        model.addAttribute("todo", todo);
        return "todo";
    }
}
