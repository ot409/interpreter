package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

public interface ByteCode {
    /**
     * Factory method to create a new instance of a specific bytecode based on its type.
     * @param type the type of bytecode to create.
     * @param args the arguments passed to the bytecode.
     * @return a new instance of the specified bytecode type.
     * @throws IllegalArgumentException if the specified bytecode type is invalid.
     */
    static ByteCode getNewInstance(String type, String[] args){
        return switch(type){
            case "LIT" -> new LitByteCode(args);
            case "HALT" -> new HaltByteCode(args);
            case "POP" -> new PopByteCode(args);
            case "FALSEBRANCH" -> new FalseBranchByteCode(args);
            case "GOTO" -> new GotoByteCode(args);
            case "STORE" -> new StoreByteCode(args);
            case "LOAD" -> new LoadByteCode(args);
            case "ARGS" -> new ArgsByteCode(args);
            case "CALL" -> new CallByteCode(args);
            case "RETURN" -> new ReturnByteCode(args);
            case "BOP" -> new BopByteCode(args);
            case "READ" -> new ReadByteCode(args);
            case "WRITE" -> new WriteByteCode(args);
            case "LABEL" -> new LabelByteCode(args);
            case "DUMP" -> new DumpByteCode(args);
            default -> throw new IllegalArgumentException();
        };
    }

    /**
     * Executes the bytecode operation.
     * @param virtualMachine the virtual machine executing the bytecode.
     */
    void execute(VirtualMachine virtualMachine);

    /*void dump(VirtualMachine vm);*/

}
