package interpreter.virtualmachine;

import interpreter.bytecodes.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program {

    private List<ByteCode> program;

    /**
     * Instantiates a program object using an
     * ArrayList
     */
    public Program() {
        program = new ArrayList<>();
    }

    /**
     * Gets the size of the current program.
     * @return size of program
     */
    public int getSize() {
        return program.size();
    }

    /**
     * Grabs an instance of a bytecode at an index.
     * @param programCounter index of bytecode to get.
     * @return a bytecode.
     */
    public ByteCode getCode(int programCounter) {
        return program.get(programCounter);
    }

    /**
     * Adds a bytecode instance to the Program List.
     * @param c bytecode to be added
     */
    public void addByteCode(ByteCode c) {
        program.add(c);
    }

    /**
     * Makes multiple passes through the program ArrayList resolving
     * address for Goto,Call and FalseBranch bytecodes. These bytecodes
     * can only jump to Label codes that have a matching label value.
     * HINT: make note of what type of data-structure ByteCodes are stored in.
     * **** METHOD SIGNATURE CANNOT BE CHANGED *****
     */
    public void resolveAddress() {

        // Map to store label addresses
        Map<String, Integer> labelAddresses = new HashMap<>();

        // First pass: store label addresses
        for (int i = 0; i < program.size(); i++) {
            ByteCode bytecode = program.get(i);
            if (bytecode instanceof LabelByteCode) {
                // Get the label from LabelByteCode instruction
                LabelByteCode labelCode = (LabelByteCode) bytecode;
                String label = labelCode.getLabel();

                // Store the label address in the map
                labelAddresses.put(label, i);
            }
        }

        // Second pass: resolve addresses for Goto, Call, and FalseBranch bytecodes
        for (int i = 0; i < program.size(); i++) {
            ByteCode bytecode = program.get(i);
            if (bytecode instanceof GotoByteCode) {
                GotoByteCode gotoByteCode = (GotoByteCode) bytecode;
                // Resolve address for GotoByteCode instruction
                String label = gotoByteCode.getLabel();

                // Get the address from the labelAddresses map
                int address = labelAddresses.get(label);

                // Set the resolved address for the GotoCode instruction
                gotoByteCode.setAddress(address);

            } else if (bytecode instanceof CallByteCode) {
                // Resolve address for CallCode instruction
                CallByteCode callByteCode = (CallByteCode) bytecode;
                String label = callByteCode.getLabel();

                // Get the address from the labelAddresses map
                int address = labelAddresses.get(label);

                // Set the resolved address for the CallByteCode instruction
                callByteCode.setAddress(address);

            } else if (bytecode instanceof FalseBranchByteCode) {
                // Resolve address for FalseBranchByteCode instruction
                FalseBranchByteCode falseBranchByteCode = (FalseBranchByteCode) bytecode;
                String label = falseBranchByteCode.getLabel();

                // Get the address from the labelAddresses map
                int address = labelAddresses.get(label);

                // Set the resolved address for the FalseBranchByteCode instruction
                falseBranchByteCode.setAddress(address);
            }
        }
    }
}
