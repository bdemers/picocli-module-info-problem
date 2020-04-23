package org.example.problem;

import picocli.AutoComplete;
import picocli.CommandLine;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Model.CommandSpec;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "example",
        mixinStandardHelpOptions = true, // adds --help, --version
        description = "An Example to reproduce a module-info problem with picocli.",
        subcommands = {
                CommandLine.HelpCommand.class,
                AutoComplete.GenerateCompletion.class})
public class Main implements Callable<Integer> {

    @Spec
    private CommandSpec spec;

    @Option(names = "--verbose", description = "Verbose logging")
    private boolean verbose = false;

    public static void main(String... args) {
        int exitCode = new CommandLine(new Main()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        System.out.println("Hello Pico");
        return 0;
    }
}
