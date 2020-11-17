package application.banking.transaction;

import java.io.IOException;

public interface  ICommand {
    public void execute () throws IOException;
    public void unexecute () throws IOException;


}
