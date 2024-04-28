package com.crio.jukebox;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import com.crio.jukebox.appconfig.ApplicationConfig;
import com.crio.jukebox.commands.CommandInvoker;


public class App {
    // To run the application  ./gradlew run --args="INPUT_FILE=jukebox-input.txt"
	public static void main(String[] args) {
		List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
        String expectedSequence = "INPUT-FILE";
        String actualSequence = commandLineArgs.stream()
                .map(a -> a.split("=")[0])
                .collect(Collectors.joining("$"));
        if(expectedSequence.equals(actualSequence)){
            run(commandLineArgs);
        }
	}

    public static void run(List<String> commandLineArgs) {
    // Complete the final logic to run the complete program.

    ApplicationConfig appConfig = new ApplicationConfig();
    CommandInvoker commandInvoker = appConfig.getCommandInvoker();

    BufferedReader reader;

    String inputFile = commandLineArgs.get(0).split("=")[1];
    commandLineArgs.remove(0);

    try{
        reader = new BufferedReader(new FileReader(inputFile));
        String line = reader.readLine();

        while(line != null)
        {
            List<String> tokens = Arrays.asList(line.split(" "));
            commandInvoker.executeCommand(tokens.get(0), tokens);
            line = reader.readLine();
        }

        reader.close();;


    }
    catch(Exception e)
    {
        e.printStackTrace();
    }

    }
}
