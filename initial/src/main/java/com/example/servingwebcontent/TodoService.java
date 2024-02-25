// Clase para realizar solicitudes HTTP
import org.springframework.web.client.RestTemplate;

public class TodoService {
    private static final String API_URL = "https://jsonplaceholder.typicode.com/todos/1";

    public Todo getTodo() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(API_URL, Todo.class);
    }
}
