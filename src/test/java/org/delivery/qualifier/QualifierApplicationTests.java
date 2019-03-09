package org.delivery.qualifier;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) //components 
public class QualifierApplicationTests {

  @LocalServerPort
  int localPort;
  
  @Value("${adjectives}")
  List<String> adjectives;

  @Value("${nouns}")
  List<String> nouns;

  @Autowired
  TestRestTemplate restTemplate;

  @Test
  public void testRootIndexContent() {
    String content = restTemplate.getForObject(String.format("http://localhost:%s/", localPort), String.class);
    assertTrue(content.contains("Behold..."));
  }

  @Test
  public void testGreeter() {
    String name = "timoteo";
    String content = restTemplate.getForObject(String.format("http://localhost:%d/greet/%s", localPort, name), String.class);
    assertTrue(content.endsWith(name));
  }

  @Test
  public void testQualifier() {
    String name = "liyina";
    String content = restTemplate.getForObject(String.format("http://localhost:%d/qualify/%s", localPort, name), String.class);
    assertTrue(content.startsWith(name));
  }

  @Test
  public void testQualifierNouns() {
    String name = "david";
    String content = restTemplate.getForObject(String.format("http://localhost:%d/qualify/%s", localPort, name), String.class);
    assertTrue(nouns.stream().anyMatch(content::contains));
  }

  @Test
  public void testQualifierAdjectives() {
    String name = "roberto";
    String content = restTemplate.getForObject(String.format("http://localhost:%d/qualify/%s", localPort, name), String.class);
    assertTrue(adjectives.stream().anyMatch(content::contains));
  }

}

