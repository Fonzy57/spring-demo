package com.site.cda_demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class HelloController {

  @GetMapping("/hello")
  public String sayHello() {
    List<String> myArray = new ArrayList<>();

    myArray.add(
        "https://media4.giphy.com/media/v1.Y2lkPTc5MGI3NjExNWpsMjM1ZXh5Nm54NXl1M2k1bXdjdGhheXV4MzVqMXFkZzBucGVjaiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/8cG6zdMFPB7ag/giphy.gif"
    );
    myArray.add(
        "https://media3.giphy.com/media/v1.Y2lkPTc5MGI3NjExejh4dGQzYWZoMGUyZmRkc3Q0ZWE0NDJycHdzd2dkdjRkNjluaXd3cCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/l0HlPtbGpcnqa0fja/giphy.gif"
    );

    myArray.add(
        "https://media1.giphy.com/media/v1.Y2lkPTc5MGI3NjExZThkM2FsYWYxajhtdzZ0dzd0ZXc5YWVreXpiZDQ3aDR4ZWJuM29idSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/l41m4O2HIBkdedREA/giphy.gif"
    );
    myArray.add(
        "https://media0.giphy.com/media/v1.Y2lkPTc5MGI3NjExM3F4MWJ3MGV0YzMyejdsODV2bDczZDZmM3FjczFtZ2h2b2dhMjg2ZiZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/H8TIQqPXKHNT2/giphy.gif"
    );
    myArray.add(
        "https://media2.giphy.com/media/v1.Y2lkPTc5MGI3NjExNmd4MHM3c2J3ZTB4czdqeWo4M3N3bjIzNTlpeXR4cDdyZTVyNWM1aSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/3o6ZsYxFRLqno054GI/giphy.gif"
    );
    myArray.add(
        "https://media3.giphy.com/media/v1.Y2lkPTc5MGI3NjExNHV4MzJmcjVxNGQ4eGhjbHRodTZjaDd6bjVsa2RwMm5rcHN1eWlmMSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/l2SpTqt1GogboNnBm/giphy.gif"
    );


    // Sélection aléatoire d'une image
    Random rand = new Random();
    String randomImage = myArray.get(rand.nextInt(myArray.size()));

    // Construction de la réponse HTML
    return "<html>" +
        "<head>" +
        "<style>" +
        "body { background-color: #23272a; color: #32c0d9; display: flex; align-items: center; justify-content: center; " +
        "height: 100vh; margin: 0; }" +
        ".container { display: flex; align-items: center; flex-direction: column; text-align: center; }" +
        "</style>" +
        "</head>" +
        "<body>" +
        "<div class='container'>" +
        "<img src=\"" + randomImage + "\" />" +
        "<br />" +
        "<h1>Le serveur Marsh mais il n'y a rien à voir ici !</h1>" +
        "</div>" +
        "</body>" +
        "</html>";
  }
}
