package org.delivery.qualifier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;

@RestController
public class ServiceController {

//static final List<String> adjectives = Arrays.asList("spicy", "sweet", "salty", "oily", "crunchy", "bitter", "rotten");
//static final List<String> nouns = Arrays.asList("pepper", "banana", "apple", "orange", "peach", "egg");
  @Value("${adjectives}")
  List<String> adjectives;

  @Value("${nouns}")
  List<String> nouns;

  @GetMapping("/")
  public String index() {
    return "Behold... The Qualifier!!!";
  }

  @GetMapping("/greet/{name}")
  public String greet(@PathVariable("name") String name) {
    return String.format("Hello there!... %s", name);
  }

  @GetMapping("/qualify/{name}")
  public String qualifyName(@PathVariable("name") String name) {
    return String.format("%s is a %s %s", name, adjective(), noun());
  }

  private String noun() {
    return randomValue(nouns);
  }

  private String adjective() {
    return randomValue(adjectives);
  }

  private String randomValue(List<String> list) {
    return list.get(new Random().nextInt(list.size()));
  }

}
