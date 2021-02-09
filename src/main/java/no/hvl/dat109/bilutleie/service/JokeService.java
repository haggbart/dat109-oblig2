package no.hvl.dat109.bilutleie.service;

import com.afrunt.randomjoke.Joke;
import com.afrunt.randomjoke.Jokes;
import com.afrunt.randomjoke.suppliers.ChuckNorris;
import org.springframework.stereotype.Service;

@Service
public class JokeService {

    private final Jokes jokes = new Jokes().with(ChuckNorris.class);

    public String getRandomJoke() {
        Joke joke = jokes.randomJoke().orElse(null);
        return joke != null ? joke.getText() : "Fant ingen vits";
    }
}
