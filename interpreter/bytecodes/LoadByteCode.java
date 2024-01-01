package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

public class LoadByteCode implements ByteCode, Dumpable {
    private int offsetOfCurrentFrame;
    private String id;

    /**
     * Constructs a LoadByteCode object with the given arguments.
     * @param args the arguments passed to the LoadByteCode bytecode,
     * including the offset in the current frame and an optional identifier.
     * @throws IllegalArgumentException if the number of arguments is invalid,
     * the offset is not a strictly positive value, or the offset value
     * is not an integer.
     */
    public LoadByteCode(String[] args) {
        /* System.out.println("LoadByteCode - args.length : " + args.length);*/
        // validate the number of arguments
        if (args.length > 3) {
            throw new IllegalArgumentException("Invalid number of arguments for LoadByteCode.");
        }

        try {
            // parse and validate the offset value
            int offset = Integer.parseInt(args[1]);
            /*System.out.println("offset : " + offset);*/
            if (offset < 0) {
                throw new IllegalArgumentException("Offset must be a strictly positive value.");
            }
            this.offsetOfCurrentFrame = offset;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid offset value. Must be an integer.");
        }

        // check if an identifier is present
        if (args.length == 3) {
            this.id = args[2];
        }
    }

    /**
     * Executes the LoadByteCode bytecode by moving the value from the specified offset in the current frame
     * to the top of the runtime stack in the virtual machine.
     * @param virtualMachine the virtual machine executing the bytecode.
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.load(this.offsetOfCurrentFrame);
    }

    /**
     * Returns a string representation of the LoadByteCode bytecode.
     * If an identifier is present, it includes the identifier.
     * @return a string representation of the LoadByteCode bytecode.
     */
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("LOAD ").append(offsetOfCurrentFrame);
        if (id != null) {
            builder.append(" ").append(id).append("\tint ").append(id);
        }
        return builder.toString();
    }

    /**
     * Test the LoadByteCode class by creating an instance and printing it.
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        String[] arguments = {"LOAD", "2", "variableName"};
        LoadByteCode loadByteCode = new LoadByteCode(arguments);
        System.out.println(loadByteCode);
    }
}
