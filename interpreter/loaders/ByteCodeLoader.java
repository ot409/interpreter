package interpreter.loaders;

import interpreter.bytecodes.ByteCode;
import interpreter.virtualmachine.Program;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public final class ByteCodeLoader {
    private String codSourceFileName;

    /**
     * Constructs ByteCodeLoader object given a COD source code
     * file name
     * @param fileName name of .cod File to load.
     */
    public ByteCodeLoader(String fileName){
        this.codSourceFileName = fileName;
    }

    /**
     * Loads a program from a .cod file.
     * @return a constructed Program Object.
     * @throws InvalidProgramException thrown when
     * loadCodes fails.
     */
    public Program loadCodes() throws InvalidProgramException {
        Program program;

        try (BufferedReader reader = new BufferedReader(new FileReader(this.codSourceFileName))){
            String line;
            String[] items;
            String byteCodeName;
            ByteCode bc;
            program = new Program();

            // Read each line of the file
            while(reader.ready()){
                line = reader.readLine();

                items = line.split("\\s+");
                byteCodeName = items[0];

                // create a new instance of the corresponding ByteCode subclass
                bc = ByteCode.getNewInstance(byteCodeName, items);

                // add the created ByteCode object to the Program
                program.addByteCode(bc);

            }
        } catch (FileNotFoundException e) {
            // throw a RuntimeException if the file is not found
            throw new RuntimeException(e);
        } catch (IOException e) {
            // throw a RuntimeException if an IO error occurs during file reading
            throw new RuntimeException(e);
        }

        // return the constructed Program object
        return program;
    }
}
