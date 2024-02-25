import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TodoService {

    private static final String API_URL = "https://jsonplaceholder.typicode.com/todos/1";

    public Todo getTodo() {
        RestTemplate restTemplate = new RestTemplate();
        Todo todo = restTemplate.getForObject(API_URL, Todo.class);
        return todo;
    }
}
