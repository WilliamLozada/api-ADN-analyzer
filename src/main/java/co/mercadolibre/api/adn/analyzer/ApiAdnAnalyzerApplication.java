package co.mercadolibre.api.adn.analyzer;

import java.io.PrintStream;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ApiAdnAnalyzerApplication {

  public static void main(String[] args) {
    SpringApplication app = new SpringApplication(ApiAdnAnalyzerApplication.class);
    app.setBanner(
        (environment, sourceClass, out) -> out.print("\n\n\t\n"
            + "  __  __ ______ _      _____                                                                   \n"
            + " |  \\/  |  ____| |    |_   _|                                                                  \n"
            + " | \\  / | |__  | |      | |                                                                    \n"
            + " | |\\/| |  __| | |      | |                                                                    \n"
            + " | |  | | |____| |____ _| |_                                                                   \n"
            + " |_|__|_|______|______|_____|      ___ _ _ _                   _                       _       \n"
            + " |_   _|               \\ \\        / (_) | (_)                 | |                     | |      \n"
            + "   | |  _ __   __ _     \\ \\  /\\  / / _| | |_  __ _ _ __ ___   | |     ___ ______ _  __| | __ _ \n"
            + "   | | | '_ \\ / _` |     \\ \\/  \\/ / | | | | |/ _` | '_ ` _ \\  | |    / _ \\_  / _` |/ _` |/ _` |\n"
            + "  _| |_| | | | (_| |_     \\  /\\  /  | | | | | (_| | | | | | | | |___| (_) / / (_| | (_| | (_| |\n"
            + " |_____|_| |_|\\__, (_)     \\/  \\/   |_|_|_|_|\\__,_|_| |_| |_| |______\\___/___\\__,_|\\__,_|\\__,_|\n"
            + "               __/ |                                                                           \n"
            + "              |___/                                                                            \n\n\n".toUpperCase()));
    app.run(args);
  }
}
