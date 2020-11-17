package application.banking.transaction;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HistoryCommand {

    private List<ICommand> commandlist = new ArrayList();
    private List<ICommand> undolist = new ArrayList();

    public void undo() throws IOException {
        if (commandlist.size()>0){
            ICommand commandObject =commandlist.get(commandlist.size()-1);
          commandlist.remove(commandObject);
            commandObject.unexecute();
            undolist.add(commandObject);
        }
    }

    public void redo() throws IOException {
        if (undolist.size()>0){
            ICommand commandObject = undolist.get(undolist.size()-1);
            commandObject.execute();
            undolist.remove(commandObject);
            commandlist.add(commandObject);
        }
    }

    public void addCommand(ICommand commandObject){
        commandlist.add(commandObject);
    }

}
