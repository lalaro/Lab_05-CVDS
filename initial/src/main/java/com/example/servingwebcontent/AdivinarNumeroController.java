package com.example.servingwebcontent;


@Controller
public class GuessNumberController {

    private int targetNumber;
    private int prizeAmount;

    @GetMapping("/guess")
    public String showGuessForm(Model model) {
        if (prizeAmount == 0) {
            prizeAmount = 100000;
        }
        targetNumber = generateRandomNumber();
        model.addAttribute("prizeAmount", prizeAmount);
        return "guess-form";
    }

    @PostMapping("/guess") // Agrega esta anotación para permitir solicitudes POST
    public String handleGuess(@RequestParam("guess") int guess, Model model) {
        if (guess == targetNumber) {
            model.addAttribute("message", "¡Felicidades! ¡Has adivinado el número y ganaste $" + prizeAmount + "!");
        } else {
            prizeAmount -= 10000;
            model.addAttribute("message", "Intenta de nuevo. Te quedan $" + prizeAmount + " de premio.");
        }
        return "guess-result";
    }

    @GetMapping("/reset")
    public String resetGame() {
        return "redirect:/guess";
    }

    private int generateRandomNumber() {
        return ThreadLocalRandom.current().nextInt(1, 11);
    }
}